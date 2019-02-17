package Figuren;

import java.util.ArrayList;

import Schach.Steuerung;

public class Laeufer extends Figur {
	
	public Laeufer(String spielerFarbe, Steuerung steuerung) {
		super(spielerFarbe, steuerung);
		if(spielerFarbe == "weiss") {
			this.image = "Assets/Figuren/laeufer_weiss.png";
		}else {
			this.image = "Assets/Figuren/laeufer_schwarz.png";
		}
	}

	@Override
	public ArrayList<Koordinate> getAllPossibleMoves() {
		// ArrayList mit allen moeglichen Zuegen
		ArrayList<Koordinate> possibleMoves = new ArrayList<Koordinate>();
		
		// nach oben links
		for(int i = this.x - 1, j = this.y + 1; i >= 0 && j < 8; i--, j++) {
			if(this.game.getFigurAt(i, j) != null)
				break;
			possibleMoves.add(new Koordinate(i, j));
		}
		
		// nach oben rechts
		for(int i = this.x + 1, j = this.y + 1; i < 8 && j < 8; i++, j++) {
			if(this.game.getFigurAt(i, j) != null)
				break;
			possibleMoves.add(new Koordinate(i, j));
		}
		
		// nach unten rechts
		for(int i = this.x + 1, j = this.y - 1; i < 8 && j >= 0; i++, j--) {
			if(this.game.getFigurAt(i, j) != null)
				break;
			possibleMoves.add(new Koordinate(i, j));
		}
		
		// nach unten links
		for(int i = this.x - 1, j = this.y - 1; i >= 0 && j >= 0; i--, j--) {
			if(this.game.getFigurAt(i, j) != null)
				break;
			possibleMoves.add(new Koordinate(i, j));
		}
		
		return possibleMoves;
	}

}
