package Schach;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Figuren.*;

public class Steuerung {

	private Schachbrett brett = new Schachbrett();
	
	private GUI gui = new GUI(this);
	
	private String currentPlayer = "weiss";
	
	private Timer timer = new Timer();
	private int timeSpielerWeiss = 300;
	private int timeSpielerSchwarz = 300;
	
	private Figur selectedFigur;
	private ArrayList<Koordinate> possibleMoves;
	
	private boolean running = true;
	private boolean kiPlaying = true;
	
	public static final int FPS = 60;
	public static final long maxLoopTime = 1000 / FPS;
	
	private int clickedX = -1;
	private int clickedY = -1;
	
	private KI ki = new KI(this);
	
	public Steuerung(boolean ki) {
		this.kiPlaying = ki;
	}
	
	private void repaintWindow() {
		gui.getSchachbrettGrafik().paintImmediately(0,0,800,800);
		gui.repaintMenus();
	}
	
	private void isKingInCheck() {
		Koenig k = brett.getKing(currentPlayer);
		Koenig k2 = brett.getOpponentKing(currentPlayer);
		if(k.istImSchach()){
			gui.getSchachbrettGrafik().setRedTile(new Koordinate(k.getX(), k.getY()));
		}else if(k2.istImSchach()){
			gui.getSchachbrettGrafik().setRedTile(new Koordinate(k2.getX(), k2.getY()));
		}else {
			gui.getSchachbrettGrafik().setRedTile(null);
		}
	}
	
	/*
	private void isKingInCheckMate() {
		Koenig k = brett.getKing(currentPlayer);
		if(k.istSchachmatt()){
			gui.repaint();
			changePlayer();
			gameOver();
		}
	}*/
	
	public void spielerKlick() {
		Figur f = brett.getFigurAt(clickedX, clickedY);
		if(f != null && f.getSpielerFarbe() == currentPlayer) {
			selectedFigur = f;
			possibleMoves = f.getAllPossibleMoves();
			gui.getSchachbrettGrafik().setPossibleMoves(possibleMoves);
			repaintWindow();
		}else {
			gui.getSchachbrettGrafik().setPossibleMoves(null);
			// TODO Bewege Figur wenn moeglich
			if(possibleMoves == null || selectedFigur == null) return;
			for(Koordinate possibleMove: possibleMoves) {
				if(possibleMove.getX() == clickedX && possibleMove.getY() == clickedY) {
					// TODO Bewege Figur
					if(brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] instanceof Bauer) {
						// TODO Spieler kann Figur tauschen, wenn Bauer am gegenerischen Ende
						if(currentPlayer == "weiss" && clickedY == 0) {
							Dame d = new Dame("weiss", clickedX, 0, brett);
							brett.getBoard()[clickedX][0] = d;
							brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] = null;
							selectedFigur = null;
							changePlayer();
							continue;
						}
						else if(currentPlayer == "schwarz" && clickedY == 7) {
							Dame d = new Dame("schwarz", clickedX, 7, brett);
							brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] = null;
							brett.getBoard()[clickedX][7] = d;
							selectedFigur = null;
							changePlayer();
							continue;
						}
					}
					move(clickedX, clickedY, selectedFigur);
					moveDone(new Move(new Koordinate(clickedX, clickedY), new Koordinate(-1, -1), selectedFigur, null));
					selectedFigur = null;
					repaintWindow();
					if(kiPlaying == true) {
						getZug();
					}
				}else {
					possibleMoves = null;
				}
			}
		}
	}
	
	public ArrayList<Move> getAllPossibleMovesForCurrentPlayer(){
		ArrayList<Move> allMoves = new ArrayList<Move>();
		Figur schachFeld[][] = brett.getBoard();
		for(int i = 0; i < schachFeld.length; i++) {
			for(int j = 0; j < schachFeld[i].length; j++) {
				if(schachFeld[i][j] != null && schachFeld[i][j].getSpielerFarbe() == currentPlayer) {
					ArrayList<Koordinate> moves = schachFeld[i][j].getAllPossibleMoves();
					for(Koordinate move: moves) {
						if(move.getX() >= 0 && move.getY() >= 0 && move.getX() < 8 && move.getY() < 8) {
							allMoves.add(new Move(move, new Koordinate(i, j), schachFeld[i][j], schachFeld[move.getX()][move.getY()]));
						}
					}
				}
			}
		}
		return allMoves;
	}
	
	private boolean checkForKing(boolean isCurrentPlayer) {
		String player = currentPlayer;
		if(!isCurrentPlayer) player = currentPlayer == "weiss"? "schwarz": "weiss";
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(brett.getFigurAt(i, j) instanceof Koenig && brett.getFigurAt(i, j).getSpielerFarbe() == player) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void moveDone(Move m) {
		if(!checkForKing(true)) {
			changePlayer(); 
			gameOver();
		}
		else if(!checkForKing(false)) {
			gameOver();
		}
		isKingInCheck();
	}
	
	public void move(int newX, int newY, Figur f) {
		brett.getBoard()[f.getX()][f.getY()] = null;
		brett.getBoard()[newX][newY] = f;
		f.setPosition(newX, newY);
		changePlayer();
	}
	
	public void move(Move move) {
		brett.getBoard()[move.getOldTile().getX()][move.getOldTile().getY()] = null;
		brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()] = move.getFigur();
		brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()].setPosition(move.getNewTile().getX(), move.getNewTile().getY());
		changePlayer();
	}
	
	public void undoMove(Move move) {
		brett.getBoard()[move.getOldTile().getX()][move.getOldTile().getY()] = move.getFigur();
		brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()] = null;
		brett.getBoard()[move.getOldTile().getX()][move.getOldTile().getY()].setPosition(move.getOldTile().getX(), move.getOldTile().getY());
		if(move.getTargetFigur() != null) {
			brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()] = move.getTargetFigur();
			move.getTargetFigur().setPosition(move.getNewTile().getX(), move.getNewTile().getY());
		}
		changePlayer();
		isKingInCheck();
	}
	
	private void gameOver() {
		running = false;
		//t = null;
		timer.cancel();
		System.out.println("Spieler " + currentPlayer + " hat gewonnen");
		
		Object[] options = {"Neues Spiel", "Schlie�en"};

		int dialogResult = JOptionPane.showOptionDialog(
				null,
				"Spieler " + currentPlayer + " hat gewonnen!", 
				"Game Over",
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[0]);
		
		System.out.println("RESULT: "+ dialogResult);
		if(dialogResult == 0) {
			timeSpielerWeiss = 300;
			timeSpielerSchwarz = 300;
			brett = new Schachbrett();
			currentPlayer = "weiss";
			gui.getSpielerWeissGrafik().setTime(timeSpielerWeiss);
		    gui.getSpielerSchwarzGrafik().setTime(timeSpielerSchwarz);
		    gui.getSpielerWeissGrafik().setActive(true);
		    clickedX = -1;
		    clickedY = -1;
		    possibleMoves = null;
		    selectedFigur = null;
			running = true;
			//t = new Thread(this);
			//t.run();
		}
	}
	
	private void getZug() {
		Thread t = new Thread() {
			@Override
			public void run() {
				Move m = ki.calcMove();
				if(m != null) {
					System.out.println("FROM: " + m.getOldTile().getX() + "/" + m.getOldTile().getY());
					System.out.println("TO: " + m.getNewTile().getX() + "/" + m.getNewTile().getY());
				}
				move(m);
				moveDone(m);
				repaintWindow();
			}
		};
		t.start();
	}
	
	private void changePlayer() {
		currentPlayer = currentPlayer == "weiss" ? "schwarz": "weiss";
		/*
		if(currentPlayer == "weiss"){
			gui.getSpielerWeissGrafik().setActive(true);
			gui.getSpielerSchwarzGrafik().setActive(false);
		}else {
			gui.getSpielerWeissGrafik().setActive(false);
			gui.getSpielerSchwarzGrafik().setActive(true);
		}*/
		/*
		timer.cancel();
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(currentPlayer == "weiss"){
					timeSpielerWeiss--;
					gui.getSpielerWeissGrafik().setTime(timeSpielerWeiss);
				}else {
					timeSpielerSchwarz--;
					gui.getSpielerSchwarzGrafik().setTime(timeSpielerSchwarz);
				}
			}
		}, 1000, 1000);*/
	}
	
	public Schachbrett getSchachbrett() {
		return brett;
	}
	
	public boolean istPattOderSchachmatt() {
		return false;
	}
	public void setClick(int x, int y) {
		this.clickedX = x;
		this.clickedY = y;
	}
}
