package Schach;

import java.util.ArrayList;

import Figuren.*;

public class Steuerung implements Runnable {

	private Figur schachFeld[][];
	
	private GUI gui = new GUI(this);
	private Schachbrett brett = new Schachbrett();
	
	private String currentPlayer = "weiss";
	
	private Figur selectedFigur;
	private ArrayList<Koordinate> possibleMoves;
	
	private Koenig koenigSpielerWeiss;
	private Koenig koenigSpielerSchwarz;
	
	private boolean running = true;
	
	public static final int FPS = 60;
	public static final long maxLoopTime = 1000 / FPS;
	
	private int clickedX = -1;
	private int clickedY = -1;
	
	public static void main(String[] args) {
		new Steuerung();
	}
	
	public Steuerung() {		
		
		
		new Thread(this).run();
	}
	
	@Override
	public void run() {
		System.out.print("Thread started");
		long timestamp;
	    long oldTimestamp;
	    
	    while(running) {
	      oldTimestamp = System.currentTimeMillis();
	      update();
	      timestamp = System.currentTimeMillis();
	      if(timestamp-oldTimestamp > maxLoopTime) {
	        System.out.println("Wir zu spät!");
	        continue;
	      }
	      //gui.repaint();
	      gui.getSchachbrettGrafik().paintImmediately(0, 0, 800, 800);
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
			// TODO Show possible Moves
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
					brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] = null;
					brett.getBoard()[clickedX][clickedY] = selectedFigur;
					selectedFigur.setPosition(clickedX, clickedY);
					selectedFigur = null;
					currentPlayer = currentPlayer == "weiss" ? "schwarz": "weiss";
				}else {
					possibleMoves = null;
				}
			}
		}
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
