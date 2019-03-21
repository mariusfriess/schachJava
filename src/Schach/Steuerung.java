package Schach;

import java.awt.Dialog;
import java.awt.Dimension;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Figuren.*;

public class Steuerung implements Runnable {

	private Schachbrett brett = new Schachbrett();
	
	private GUI gui = new GUI(this);
	
	private String currentPlayer = "weiss";
	
	private Timer timer = new Timer();
	private int timeSpielerWeiss = 300;
	private int timeSpielerSchwarz = 300;
	
	private Figur selectedFigur;
	private ArrayList<Koordinate> possibleMoves;
	
	//private Koenig koenigSpielerWeiss;
	//private Koenig koenigSpielerSchwarz;
	
	private boolean running = true;
	
	public static final int FPS = 30;
	public static final long maxLoopTime = 1000 / FPS;
	
	private int clickedX = -1;
	private int clickedY = -1;
	
	Thread t = new Thread(this);
	
	public static void main(String[] args) {
		new Steuerung();
	}
	
	public Steuerung() {
		t.run();
	}
	
	private void repaintWindow() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					gui.getSchachbrettGrafik().paintImmediately(0,0,800,800);
					gui.repaintMenus();
				}
				
			});
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.print("Thread started");
		long timestamp;
	    long oldTimestamp;
	    
	    gui.getSpielerWeissGrafik().setTime(timeSpielerWeiss);
	    gui.getSpielerSchwarzGrafik().setTime(timeSpielerSchwarz);
	    gui.getSpielerWeissGrafik().setActive(true);
	    
	    while(running) {
	      oldTimestamp = System.currentTimeMillis();
	      update();
	      timestamp = System.currentTimeMillis();
	      if(timestamp-oldTimestamp > maxLoopTime) {
	        System.out.println("Wir zu spaeht!");
	        continue;
	      }
	      repaintWindow();
	      timestamp = System.currentTimeMillis();
	      //System.out.println(maxLoopTime + " : " + (timestamp-oldTimestamp));
	      if(timestamp-oldTimestamp <= maxLoopTime) {
	        try {
	          Thread.sleep(maxLoopTime - (timestamp-oldTimestamp) );
	        } catch (InterruptedException e) {
	          e.printStackTrace();
	        }
	      }
	    }
		
	}
	
	private void update() {
		spielerKlick();
	}
	
	/*
	private boolean spielerKannSichBewegen() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(schachFeld[i][j] != null) {
					ArrayList<Koordinate> possibleMoves = schachFeld[i][j].getAllPossibleMoves();
					for(Koordinate possibleMove: possibleMoves) {
						// TODO
					}
				}
			}
		}
		return true;
	}*/
	
	/**
	 * @param x
	 * @param y
	 */
	public void spielerKlick() {
		Figur f = brett.getFigurAt(clickedX, clickedY);
		if(f != null && f.getSpielerFarbe() == currentPlayer) {
			selectedFigur = f;
			possibleMoves = f.getAllPossibleMoves();
			gui.getSchachbrettGrafik().setPossibleMoves(possibleMoves);
		}else {
			gui.getSchachbrettGrafik().setPossibleMoves(null);
			// TODO Bewege Figur wenn moeglich
			if(possibleMoves == null || selectedFigur == null) return;
			for(Koordinate possibleMove: possibleMoves) {
				if(possibleMove.getX() == clickedX && possibleMove.getY() == clickedY) {
					// TODO Bewege Figur
					if(brett.getBoard()[clickedX][clickedY] instanceof Koenig) {
						gameOver();
					}
					if(brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] instanceof Bauer) {
						// TODO Spieler kann Figur tauschen, wenn Bauer am gegenerischen Ende
						if(currentPlayer == "weiss" && clickedY == 0) {
							System.out.println("TEST 1");
						}
						else if(currentPlayer == "schwarz" && clickedY == 7) {
							System.out.println("TEST 2");
						}
					}
					brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] = null;
					brett.getBoard()[clickedX][clickedY] = selectedFigur;
					selectedFigur.setPosition(clickedX, clickedY);
					selectedFigur = null;
					changePlayer();
				}else {
					possibleMoves = null;
				}
			}
		}
	}
	
	private void gameOver() {
		running = false;
		t = null;
		timer.cancel();
		System.out.println("Spieler " + currentPlayer + " hat gewonnen");
		
		Object[] options = {"Neues Spiel", "Schlieﬂen"};

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
			t = new Thread(this);
			t.run();
		}
	}
	
	private void changePlayer() {
		currentPlayer = currentPlayer == "weiss" ? "schwarz": "weiss";
		if(currentPlayer == "weiss"){
			gui.getSpielerWeissGrafik().setActive(true);
			gui.getSpielerSchwarzGrafik().setActive(false);
		}else {
			gui.getSpielerWeissGrafik().setActive(false);
			gui.getSpielerSchwarzGrafik().setActive(true);
		}
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
		}, 1000, 1000);
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
