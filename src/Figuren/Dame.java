package Figuren;

import java.util.ArrayList;

import Schach.Steuerung;

public class Dame extends Figur {
	
	public Dame(String spielerFarbe, Steuerung steuerung) {
		super(spielerFarbe, steuerung);
		if(spielerFarbe == "weiss") {
			this.image = "Assets/Figuren/dame_weiss.png";
		}else {
			this.image = "Assets/Figuren/dame_schwarz.png";
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
		
		// nach oben
		for(int i = this.y + 1; i < 8; i++) {
			if(this.game.getFigurAt(this.x, i) != null)
				break;
			possibleMoves.add(new Koordinate(this.x, i));
		}
		
		// nach unten
		for(int i = this.y - 1; i >= 0; i--) {
			if(this.game.getFigurAt(this.x, i) != null)
				break;
			possibleMoves.add(new Koordinate(this.x, i));

		}
		
		// nach links
		for(int i = this.x - 1; i >= 0; i--) {
			if(this.game.getFigurAt(i, this.y) != null)
				break;
			possibleMoves.add(new Koordinate(i, this.y));
		}
		
		// nach rechts
		for(int i = this.x + 1; i < 8; i++) {
			if(this.game.getFigurAt(i, this.y) != null)
				break;
			possibleMoves.add(new Koordinate(i, this.y));
		}
		
		return possibleMoves;
	}

}
