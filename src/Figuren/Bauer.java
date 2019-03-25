package Figuren;

import java.util.ArrayList;

import Schach.Schachbrett;
import Schach.Steuerung;

public class Bauer extends Figur {
	
	public Bauer(String spielerFarbe, int x, int y, Schachbrett brett) {
		super(spielerFarbe, x, y, brett);
		if(spielerFarbe == "weiss") {
			this.image = "Assets/Figuren/bauer_weiss.png";
		}else {
			this.image = "Assets/Figuren/bauer_schwarz.png";
		}
	}

	@Override
	public ArrayList<Koordinate> getAllPossibleMoves() {
		int richtung, x = this.x, y = this.y;
		//System.out.println(x + " " + y);
		if(spielerFarbe == "weiss") richtung = -1;
		else richtung = 1;
		
		// ArrayList mit allen moeglichen Zuegen
		ArrayList<Koordinate> possibleMoves = new ArrayList<Koordinate>();
		
		// MOEGLICHE ZUEGE
		// Beim ersten Zug 2 nach vorne (Darf aber nicht schlagen)
		if(spielerFarbe == "weiss" && y == 6 && game.getFigurAt(x, y + (1 * richtung)) == null && game.getFigurAt(x, y + (2 * richtung)) == null)
			possibleMoves.add(new Koordinate(x, y + (2 * richtung)));
		if(spielerFarbe == "schwarz" && y == 1 && game.getFigurAt(x, y + (1 * richtung)) == null && game.getFigurAt(x, y + (2 * richtung)) == null)
			possibleMoves.add(new Koordinate(x, y + (2 * richtung)));
		// Einen nach vorne (Darf aber nicht schlagen)
		if(game.getFigurAt(x, y + (1 * richtung)) == null)
			possibleMoves.add(new Koordinate(x, y + (1 * richtung)));
		// Einen vor und einen links (Nur um zu schlagen)
		if(game.getFigurAt(x - 1, y + (1 * richtung)) != null && game.getFigurAt(x - 1, y + (1 * richtung)).spielerFarbe != this.spielerFarbe)
			possibleMoves.add(new Koordinate(x - 1, y + (1 * richtung)));
		// Einen vor und einen rechts (Nur um zu schlagen)
		if(game.getFigurAt(x + 1, y + (1 * richtung)) != null && game.getFigurAt(x + 1, y + (1 * richtung)).spielerFarbe != this.spielerFarbe)
		possibleMoves.add(new Koordinate(x + 1, y + (1 * richtung)));
		
		return possibleMoves;
	}

}