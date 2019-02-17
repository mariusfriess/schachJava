package Figuren;

import java.util.ArrayList;

public class Bauer extends Figur {
	
	private boolean firstMove = true;
	
	public Bauer(String spielerFarbe) {
		super(spielerFarbe);
		if(spielerFarbe == "weiß") {
			this.image = "Assets/Figuren/bauer_weiß.png";
		}else {
			this.image = "Assets/Figuren/bauer_schwarz.png";
		}
	}
	
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

	@Override
	public void getAllPossibleMoves(Figur spielFeld[][]) {
		int richtung, x = this.x, y = this.y;
		if(spielerFarbe == "weiß") richtung = -1;
		else richtung = 1;
		
		// ArrayList mit allen möglichen Zügen
		ArrayList<Koordinate> möglicheZüge = new ArrayList<Koordinate>();
		
		// Beim ersten Zug 2 nach vorne sonst nur einen nach vorne
		if(firstMove) {
			if(spielFeld[x][y + (1 * richtung)] == null && spielFeld[x][y + (1 * richtung)] == null) {
				möglicheZüge.add(new Koordinate(x, y + (2 * richtung)));
			}
		}
	}

}
