package Schach;

import Figuren.*;

public class Steuerung {

	private Figur schachFeld[][];
	private GUI gui = new GUI(this);
	
	public Steuerung() {		
		schachFeld = new Figur[8][];
		for(int i = 0; i < 8; i++) {
            schachFeld[i] = new Figur[8];
        }
		
		// Figuren von Spieler Wei�
		schachFeld[0][0] = new Turm("wei�");
		schachFeld[1][0] = new Springer("wei�");
		schachFeld[2][0] = new Laeufer("wei�");
		schachFeld[3][0] = new Dame("wei�");
		schachFeld[4][0] = new Koenig("wei�");
		schachFeld[5][0] = new Laeufer("wei�");
		schachFeld[6][0] = new Springer("wei�");
		schachFeld[7][0] = new Turm("wei�");
		schachFeld[0][1] = new Bauer("wei�");
		schachFeld[1][1] = new Bauer("wei�");
		schachFeld[2][1] = new Bauer("wei�");
		schachFeld[3][1] = new Bauer("wei�");
		schachFeld[4][1] = new Bauer("wei�");
		schachFeld[5][1] = new Bauer("wei�");
		schachFeld[6][1] = new Bauer("wei�");
		schachFeld[7][1] = new Bauer("wei�");
		
		// Figuren von Spieler Schwarz
		schachFeld[0][7] = new Turm("schwarz");
		schachFeld[1][7] = new Springer("schwarz");
		schachFeld[2][7] = new Laeufer("schwarz");
		schachFeld[3][7] = new Dame("schwarz");
		schachFeld[4][7] = new Koenig("schwarz");
		schachFeld[5][7] = new Laeufer("schwarz");
		schachFeld[6][7] = new Springer("schwarz");
		schachFeld[7][7] = new Turm("schwarz");
		schachFeld[0][6] = new Bauer("schwarz");
		schachFeld[1][6] = new Bauer("schwarz");
		schachFeld[2][6] = new Bauer("schwarz");
		schachFeld[3][6] = new Bauer("schwarz");
		schachFeld[4][6] = new Bauer("schwarz");
		schachFeld[5][6] = new Bauer("schwarz");
		schachFeld[6][6] = new Bauer("schwarz");
		schachFeld[7][6] = new Bauer("schwarz");
	}
	
	public Figur[][] getBoard() {
		return this.schachFeld;
	}
}
