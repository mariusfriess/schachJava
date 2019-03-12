package Figuren;

import java.util.ArrayList;

import Schach.Steuerung;

public class Dame extends Figur {
	
	public Dame(String spielerFarbe, int x, int y, Steuerung steuerung) {
		super(spielerFarbe, x, y, steuerung);
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
