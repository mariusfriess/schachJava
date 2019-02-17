package Schach;

import java.util.ArrayList;

import Figuren.*;

public class Steuerung {

	private Figur schachFeld[][];
	private GUI gui = new GUI(this);
	private int currentPlayer = 1;
	
	public Steuerung() {		
		schachFeld = new Figur[8][];
		for(int i = 0; i < 8; i++) {
            schachFeld[i] = new Figur[8];
        }
		
		// Figuren von Spieler Weiss
		schachFeld[0][0] = new Turm("schwarz", this);
		schachFeld[0][0].setPosition(0, 0);
		schachFeld[1][0] = new Springer("schwarz", this);
		schachFeld[1][0].setPosition(1, 0);
		schachFeld[2][0] = new Laeufer("schwarz", this);
		schachFeld[2][0].setPosition(2, 0);
		schachFeld[3][0] = new Dame("schwarz", this);
		schachFeld[3][0].setPosition(3, 0);
		schachFeld[4][0] = new Koenig("schwarz", this);
		schachFeld[4][0].setPosition(4, 0);
		schachFeld[5][0] = new Laeufer("schwarz", this);
		schachFeld[5][0].setPosition(5, 0);
		schachFeld[6][0] = new Springer("schwarz", this);
		schachFeld[6][0].setPosition(6, 0);
		schachFeld[7][0] = new Turm("schwarz", this);
		schachFeld[7][0].setPosition(7, 0);
		schachFeld[0][1] = new Bauer("schwarz", this);
		schachFeld[0][1].setPosition(0, 1);
		schachFeld[1][1] = new Bauer("schwarz", this);
		schachFeld[1][1].setPosition(1, 1);
		schachFeld[2][1] = new Bauer("schwarz", this);
		schachFeld[2][1].setPosition(2, 1);
		schachFeld[3][1] = new Bauer("schwarz", this);
		schachFeld[3][1].setPosition(3, 1);
		schachFeld[4][1] = new Bauer("schwarz", this);
		schachFeld[4][1].setPosition(4, 1);
		schachFeld[5][1] = new Bauer("schwarz", this);
		schachFeld[5][1].setPosition(5, 1);
		schachFeld[6][1] = new Bauer("schwarz", this);
		schachFeld[6][1].setPosition(6, 1);
		schachFeld[7][1] = new Bauer("schwarz", this);
		schachFeld[7][1].setPosition(7, 1);
		
		// Figuren von Spieler Schwarz
		schachFeld[0][7] = new Turm("weiss", this);
		schachFeld[0][7].setPosition(0, 7);
		schachFeld[1][7] = new Springer("weiss", this);
		schachFeld[1][7].setPosition(1, 7);
		schachFeld[2][7] = new Laeufer("weiss", this);
		schachFeld[2][7].setPosition(2, 7);
		schachFeld[3][7] = new Dame("weiss", this);
		schachFeld[3][7].setPosition(3, 7);
		schachFeld[4][7] = new Koenig("weiss", this);
		schachFeld[4][7].setPosition(4, 7);
		schachFeld[5][7] = new Laeufer("weiss", this);
		schachFeld[5][7].setPosition(5, 7);
		schachFeld[6][7] = new Springer("weiss", this);
		schachFeld[6][7].setPosition(6, 7);
		schachFeld[7][7] = new Turm("weiss", this);
		schachFeld[7][7].setPosition(7, 7);
		schachFeld[0][6] = new Bauer("weiss", this);
		schachFeld[0][6].setPosition(0, 6);
		schachFeld[1][6] = new Bauer("weiss", this);
		schachFeld[1][6].setPosition(1, 6);
		schachFeld[2][6] = new Bauer("weiss", this);
		schachFeld[2][6].setPosition(2, 6);
		schachFeld[3][6] = new Bauer("weiss", this);
		schachFeld[3][6].setPosition(3, 6);
		schachFeld[4][6] = new Bauer("weiss", this);
		schachFeld[4][6].setPosition(4, 6);
		schachFeld[5][6] = new Bauer("weiss", this);
		schachFeld[5][6].setPosition(5, 6);
		schachFeld[6][6] = new Bauer("weiss", this);
		schachFeld[6][6].setPosition(6, 6);
		schachFeld[7][6] = new Bauer("weiss", this);
		schachFeld[7][6].setPosition(7, 6);
	}
	
	private boolean spielerKannSichBewegen() {
		String aktuellerSpielerFarbe = this.currentPlayer == 1 ? "weiss": "schwarz";
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(schachFeld[i][j] != null) {
					ArrayList<Koordinate> possibleMoves = schachFeld[i][j].getAllPossibleMoves();
					for(Koordinate possibleMove: possibleMoves) {
						
					}
				}
			}
		}
		return true;
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
