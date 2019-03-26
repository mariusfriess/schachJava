package Figuren;

import java.util.ArrayList;
import Schach.Schachbrett;

public class Laeufer extends Figur {
	
	public Laeufer(String spielerFarbe, int x, int y, Schachbrett brett) {
		super(spielerFarbe, x, y, brett);
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
			if(this.game.getFigurAt(i, j) == null)
				possibleMoves.add(new Koordinate(i, j));
			else if(game.getFigurAt(i, j).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(i, j));
				break;
			}
			else break;
		}
		
		// nach oben rechts
		for(int i = this.x + 1, j = this.y + 1; i < 8 && j < 8; i++, j++) {
			if(this.game.getFigurAt(i, j) == null)
				possibleMoves.add(new Koordinate(i, j));
			else if(game.getFigurAt(i, j).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(i, j));
				break;
			}
			else break;
		}
		
		// nach unten rechts
		for(int i = this.x + 1, j = this.y - 1; i < 8 && j >= 0; i++, j--) {
			if(this.game.getFigurAt(i, j) == null)
				possibleMoves.add(new Koordinate(i, j));
			else if(game.getFigurAt(i, j).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(i, j));
				break;
			}
			else break;
		}
		
		// nach unten links
		for(int i = this.x - 1, j = this.y - 1; i >= 0 && j >= 0; i--, j--) {
			if(this.game.getFigurAt(i, j) == null)
				possibleMoves.add(new Koordinate(i, j));
			else if(game.getFigurAt(i, j).getSpielerFarbe() != spielerFarbe) {
				possibleMoves.add(new Koordinate(i, j));
				break;
			}
			else break;
		}
		
		return possibleMoves;
	}

}
