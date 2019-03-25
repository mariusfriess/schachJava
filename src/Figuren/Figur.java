package Figuren;

import java.util.ArrayList;

import Schach.Schachbrett;
import Schach.Steuerung;

public abstract class Figur {
	
	protected String image;
	protected int x;
	protected int y;
	protected String spielerFarbe;
	protected Schachbrett game;

	public Figur(String spielerFarbe, int x, int y, Schachbrett brett){
		this.spielerFarbe = spielerFarbe;
		this.x = x;
		this.y = y;
		this.game = brett;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public String getSpielerFarbe() {
		return this.spielerFarbe;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean unzulaessigerZug(int x, int y) {
		return false;
	}
	
	public abstract ArrayList<Koordinate> getAllPossibleMoves();
}
