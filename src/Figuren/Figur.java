package Figuren;

import java.util.ArrayList;

import Schach.Steuerung;

public abstract class Figur {
	
	protected String image;
	protected int x;
	protected int y;
	protected String spielerFarbe;
	protected Steuerung game;

	public Figur(String spielerFarbe, int x, int y, Steuerung game){
		this.spielerFarbe = spielerFarbe;
		this.x = x;
		this.y = y;
		this.game = game;
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
		if(this instanceof Bauer) {
			((Bauer) this).setFirstMove(false);
		}
		this.x = x;
		this.y = y;
	}
	
	public boolean unzulaessigerZug(int x, int y) {
		return false;
	}
	
	public abstract ArrayList<Koordinate> getAllPossibleMoves();
}
