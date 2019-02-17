package Figuren;

import java.util.ArrayList;

import Schach.Steuerung;

public abstract class Figur {
	
	protected String image;
	protected int x;
	protected int y;
	protected String spielerFarbe;
	protected Steuerung game;

	public Figur(String spielerFarbe, Steuerung game){
		this.spielerFarbe = spielerFarbe;
		this.game = game;
	}
	
	public String getImage() {
		return this.image;
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
