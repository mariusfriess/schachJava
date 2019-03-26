package Figuren;

import java.util.ArrayList;
import Schach.Schachbrett;

public class Turm extends Figur {
	
	public Turm(String spielerFarbe, int x, int y, Schachbrett brett) {
		super(spielerFarbe, x, y, brett);
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
			if(this.game.getFigurAt(x, i) == null)
				possibleMoves.add(new Koordinate(x, i));
			else if(game.getFigurAt(x, i).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(x, i));
				break;
			}
			else break;
		}
		
		// nach unten
		for(int i = this.y - 1; i >= 0; i--) {
			if(this.game.getFigurAt(x, i) == null)
				possibleMoves.add(new Koordinate(x, i));
			else if(game.getFigurAt(x, i).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(x, i));
				break;
			}
			else break;
		}
		
		// nach links
		for(int i = this.x - 1; i >= 0; i--) {
			if(this.game.getFigurAt(i, y) == null)
				possibleMoves.add(new Koordinate(i, y));
			else if(game.getFigurAt(i, y).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(i, y));
				break;
			}
			else break;
		}
		
		// nach rechts
		for(int i = this.x + 1; i < 8; i++) {
			if(this.game.getFigurAt(i, y) == null)
				possibleMoves.add(new Koordinate(i, y));
			else if(game.getFigurAt(i, y).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(i, y));
				break;
			}
			else break;
		}
		
		return possibleMoves;
	}

}
