package Schach;

import java.util.ArrayList;

import Figuren.*;

public class Steuerung {

	private Figur schachFeld[][];
	
	private GUI gui = new GUI(this);
	
	private String currentPlayer = "weiss";
	
	private Figur selectedFigur;
	private ArrayList<Koordinate> possibleMoves;
	
	public static void main(String[] args) {
		new Steuerung();
	}
	
	public Steuerung() {		
		schachFeld = new Figur[8][];
		for(int i = 0; i < 8; i++) {
            schachFeld[i] = new Figur[8];
        }
		
		// Figuren von Spieler Weiss
		schachFeld[0][0] = new Turm("schwarz", 0, 0, this);
		schachFeld[1][0] = new Springer("schwarz", 1, 0, this);
		schachFeld[2][0] = new Laeufer("schwarz", 2, 0, this);
		schachFeld[3][0] = new Dame("schwarz", 3, 0, this);
		schachFeld[4][0] = new Koenig("schwarz", 4, 0, this);
		schachFeld[5][0] = new Laeufer("schwarz", 5, 0, this);
		schachFeld[6][0] = new Springer("schwarz", 6, 0, this);
		schachFeld[7][0] = new Turm("schwarz", 7, 0, this);
		for(int i = 0; i < 8; i++) {
			schachFeld[i][1] = new Bauer("schwarz", i, 1, this);
		}
		
		// Figuren von Spieler Schwarz
		schachFeld[0][7] = new Turm("weiss", 0, 7, this);
		schachFeld[1][7] = new Springer("weiss", 1, 7, this);
		schachFeld[2][7] = new Laeufer("weiss", 2, 7, this);
		schachFeld[3][7] = new Dame("weiss", 3, 7, this);
		schachFeld[4][7] = new Koenig("weiss", 4, 7, this);
		schachFeld[5][7] = new Laeufer("weiss", 5, 7, this);
		schachFeld[6][7] = new Springer("weiss", 6, 7, this);
		schachFeld[7][7] = new Turm("weiss", 7, 7, this);
		for(int i = 0; i < 8; i++) {
			schachFeld[i][6] = new Bauer("weiss", i, 6, this);
		}
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
	public void spielerKlick(int x, int y) {
		Figur f = getFigurAt(x, y);
		if(f != null && f.getSpielerFarbe() == currentPlayer) {
			// TODO Show possible Moves
			selectedFigur = f;
			possibleMoves = f.getAllPossibleMoves();
			System.out.println(f.getSpielerFarbe() + " | " + f.getX() + " | " + f.getY());
			gui.getSchachbrettGrafik().setPossibleMoves(possibleMoves);
		}else {
			// TODO Bewege Figur wenn moeglich
			if(possibleMoves == null) return;
			for(Koordinate possibleMove: possibleMoves) {
				if(possibleMove.getX() == x && possibleMove.getY() == y) {
					// TODO Bewege Figur
					schachFeld[selectedFigur.getX()][selectedFigur.getY()] = null;
					schachFeld[x][y] = selectedFigur;
					selectedFigur.setPosition(x, y);
					selectedFigur = null;
					currentPlayer = currentPlayer == "weiss" ? "schwarz": "weiss";
					//gui.getMenuGrafik().spielerWechseln(currentPlayer);
				}else {
					possibleMoves = null;
				}
			}
		}
	}
	
	public boolean istPattOderSchachmatt() {
		return false;
	}
	
	public Figur getFigurAt(int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7) return null;
		else return schachFeld[x][y];
	}
	
	public Figur[][] getBoard() {
		return this.schachFeld;
	}
}
