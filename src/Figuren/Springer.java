package Figuren;

import java.util.ArrayList;

import Schach.Schachbrett;
import Schach.Steuerung;

public class Springer extends Figur {
	
	public Springer(String spielerFarbe, int x, int y, Schachbrett brett) {
		super(spielerFarbe, x, y, brett);
		if(spielerFarbe == "weiss") {
			this.image = "Assets/Figuren/springer_weiss.png";
		}else {
			this.image = "Assets/Figuren/springer_schwarz.png";
		}
	}

	@Override
	public ArrayList<Koordinate> getAllPossibleMoves() {
		// ArrayList mit allen moeglichen Zuegen
		ArrayList<Koordinate> possibleMoves = new ArrayList<Koordinate>();
		
		if(this.x - 2 >= 0 && this.y + 1 < 8) {
			if(this.game.getFigurAt(this.x - 2, this.y + 1) != null && this.game.getFigurAt(this.x - 2, this.y + 1).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x - 2, this.y + 1));
		}
		
		if(this.x - 1 >= 0 && this.y + 2 < 8) {
			if(this.game.getFigurAt(this.x - 1, this.y + 2) != null && this.game.getFigurAt(this.x - 1, this.y + 2).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x - 1, this.y + 2));
		}
		
		if(this.x + 1 < 8 && this.y + 2 < 8) {
			if(this.game.getFigurAt(this.x + 1, this.y + 2) != null && this.game.getFigurAt(this.x + 1, this.y + 2).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x + 1, this.y + 2));
		}
		
		if(this.x + 2 < 8 && this.y + 1 < 8) {
			if(this.game.getFigurAt(this.x + 2, this.y + 1) != null && this.game.getFigurAt(this.x + 2, this.y + 1).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x + 2, this.y + 1));
		}
		
		if(this.x - 2 >= 0 && this.y - 1 >= 0) {
			if(this.game.getFigurAt(this.x - 2, this.y - 1) != null && this.game.getFigurAt(this.x - 2, this.y - 1).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x - 2, this.y - 1));
		}
		
		if(this.x - 1 >= 0 && this.y - 2 >= 0) {
			if(this.game.getFigurAt(this.x - 1, this.y - 2) != null && this.game.getFigurAt(this.x - 1, this.y - 2).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x - 1, this.y - 2));
		}
		
		if(this.x + 1 < 8 && this.y - 2 >= 0) {
			if(this.game.getFigurAt(this.x + 1, this.y - 2) != null && this.game.getFigurAt(this.x + 1, this.y - 2).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x + 1, this.y - 2));
		}
		
		if(this.x + 2 < 8 && this.y - 1 >= 0) {
			if(this.game.getFigurAt(this.x + 2, this.y - 1) != null && this.game.getFigurAt(this.x + 2, this.y - 1).spielerFarbe == this.spielerFarbe);
			else possibleMoves.add(new Koordinate(this.x + 2, this.y - 1));
		}
		
		return possibleMoves;
	}

}
