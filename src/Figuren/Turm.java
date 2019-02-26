package Figuren;

import java.util.ArrayList;

import Schach.Steuerung;

public class Turm extends Figur {
	
	public Turm(String spielerFarbe, int y, int x, Steuerung steuerung) {
		super(spielerFarbe, x, y, steuerung);
		if(spielerFarbe == "weiss") {
			this.image = "Assets/Figuren/turm_weiss.png";
		}else {
			this.image = "Assets/Figuren/turm_schwarz.png";
		}
	}

	@Override
	public ArrayList<Koordinate> getAllPossibleMoves() {
		// ArrayList mit allen moeglichen Zuegen
		ArrayList<Koordinate> possibleMoves = new ArrayList<Koordinate>();
		
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
