package Figuren;

public abstract class Figur {
	
	protected String image;
	protected int x;
	protected int y;
	protected String spielerFarbe;

	public Figur(String spielerFarbe){
		this.spielerFarbe = spielerFarbe;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public abstract void getAllPossibleMoves(Figur spielFeld[][]);
}
