package Schach;

import java.util.ArrayList;

import Figuren.Bauer;
import Figuren.Dame;
import Figuren.Figur;
import Figuren.Koenig;
import Figuren.Laeufer;
import Figuren.Springer;
import Figuren.Turm;

public class Schachbrett {
	
	private Figur schachbrett[][];
	
	private ArrayList<Figur> figurenSpielerWeiss;
	private ArrayList<Figur> figurenSpielerSchwarz;
	
	private Koenig koenigSpielerWeiss;
	private Koenig koenigSpielerSchwarz;

	public Schachbrett() {
		schachbrett = new Figur[8][];
		for(int i = 0; i < 8; i++) {
            schachbrett[i] = new Figur[8];
        }
		
		// Figuren von Spieler Weiss
		schachbrett[0][0] = new Turm("schwarz", 0, 0, this);
		schachbrett[1][0] = new Springer("schwarz", 1, 0, this);
		schachbrett[2][0] = new Laeufer("schwarz", 2, 0, this);
		schachbrett[3][0] = new Dame("schwarz", 3, 0, this);
		schachbrett[4][0] = new Koenig("schwarz", 4, 0, this);
		koenigSpielerSchwarz = (Koenig) schachbrett[4][0];
		schachbrett[5][0] = new Laeufer("schwarz", 5, 0, this);
		schachbrett[6][0] = new Springer("schwarz", 6, 0, this);
		schachbrett[7][0] = new Turm("schwarz", 7, 0, this);
		for(int i = 0; i < 8; i++) {
			schachbrett[i][1] = new Bauer("schwarz", i, 1, this);
		}
		
		// Figuren von Spieler Schwarz
		schachbrett[0][7] = new Turm("weiss", 0, 7, this);
		schachbrett[1][7] = new Springer("weiss", 1, 7, this);
		schachbrett[2][7] = new Laeufer("weiss", 2, 7, this);
		schachbrett[3][7] = new Dame("weiss", 3, 7, this);
		schachbrett[4][7] = new Koenig("weiss", 4, 7, this);
		koenigSpielerWeiss = (Koenig) schachbrett[4][7];
		schachbrett[5][7] = new Laeufer("weiss", 5, 7, this);
		schachbrett[6][7] = new Springer("weiss", 6, 7, this);
		schachbrett[7][7] = new Turm("weiss", 7, 7, this);
		for(int i = 0; i < 8; i++) {
			schachbrett[i][6] = new Bauer("weiss", i, 6, this);
		}
	}
	
	public Koenig getKing(String currentPlayer) {
		return currentPlayer == "weiss" ? koenigSpielerWeiss: koenigSpielerSchwarz;
	}
	
	public Koenig getOpponentKing(String currentPlayer) {
		return currentPlayer == "weiss" ? koenigSpielerSchwarz: koenigSpielerWeiss;
	}
	
	public Figur getFigurAt(int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7) return null;
		else return schachbrett[x][y];
	}
	
	public Figur[][] getBoard() {
		return this.schachbrett;
	}

}
