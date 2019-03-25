package Figuren;

import java.util.ArrayList;

import Schach.Schachbrett;
import Schach.Steuerung;

public class Koenig extends Figur {
	
	private boolean firstMove = true;
	
	public Koenig(String spielerFarbe, int x, int y, Schachbrett brett) {
		super(spielerFarbe, x, y, brett);
		if(spielerFarbe == "weiss") {
			this.image = "Assets/Figuren/koenig_weiss.png";
		}else {
			this.image = "Assets/Figuren/koenig_schwarz.png";
		}
	}
	
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	
	public boolean istImSchach() {
		Figur schachFeld[][] = game.getBoard();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(schachFeld[i][j] != null && schachFeld[i][j].spielerFarbe != this.spielerFarbe) {
					ArrayList<Koordinate> possibleMoves = schachFeld[i][j].getAllPossibleMoves();
					for(Koordinate possibleMove: possibleMoves) {
						if(this.x == possibleMove.getX() && this.y == possibleMove.getY()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean istSchachmatt() {
		// TODO RICHTIG IMPLEMENTIEREN
		if(!(istImSchach())) return false;
		Figur schachFeld[][] = game.getBoard();
		ArrayList<Koordinate> myPossibleMoves = getAllPossibleMoves();
		for(Koordinate myPossibleMove: myPossibleMoves) {
			boolean z = false;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(schachFeld[i][j] != null && schachFeld[i][j].spielerFarbe != this.spielerFarbe) {
						ArrayList<Koordinate> possibleMoves = schachFeld[i][j].getAllPossibleMoves();
						for(Koordinate possibleMove: possibleMoves) {
							if(myPossibleMove.getX() == possibleMove.getX() && myPossibleMove.getY() == possibleMove.getY()) {
								z = true;
							}
						}
					}
				}
			}
			if(z == false) return false;
		}
		return true;
	}

	@Override
	public ArrayList<Koordinate> getAllPossibleMoves() {
		ArrayList<Koordinate> possibleMoves = new ArrayList<Koordinate>();
		
		// nach oben
		if((this.y - 1 >= 0 && game.getFigurAt(this.x, this.y - 1) == null ) 
			|| ( game.getFigurAt(this.x, this.y - 1) != null && game.getFigurAt(this.x, this.y - 1).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(this.x, this.y - 1));
		
		// nach unten
		if((this.y + 1 < 8 && game.getFigurAt(this.x, this.y + 1) == null )
			|| ( game.getFigurAt(this.x, this.y + 1) != null && game.getFigurAt(this.x, this.y + 1).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(this.x, this.y + 1));
		
		// nach links
		if((this.x - 1 >= 0 && game.getFigurAt(this.x - 1, this.y) == null )
			|| ( game.getFigurAt(this.x - 1, this.y) != null && game.getFigurAt(this.x - 1, this.y).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(this.x - 1, this.y));
		
		// nach unten
		if((this.x + 1 < 8 && game.getFigurAt(this.x + 1, this.y) == null )
			|| ( game.getFigurAt(this.x + 1, this.y) != null && game.getFigurAt(this.x + 1, this.y).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(this.x + 1, this.y));
		
		// nach oben links
		if((this.y - 1 >= 0 && this.x - 1 >= 0 && game.getFigurAt(x - 1, y - 1) == null )
			|| (game.getFigurAt(x - 1, y - 1) != null && game.getFigurAt(x - 1, y - 1).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(x - 1, y - 1));
			
		// nach oben rechts
		if((this.y - 1 >= 0 && this.x + 1 < 8 && game.getFigurAt(x + 1, y - 1) == null )
			|| (game.getFigurAt(x + 1, y - 1) != null && game.getFigurAt(x + 1, y - 1).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(x + 1, y - 1));
		
		// nach unten rechts
		if((this.y + 1 < 8 && this.x + 1 < 8 && game.getFigurAt(x + 1, y + 1) == null )
			|| (game.getFigurAt(x + 1, y + 1) != null && game.getFigurAt(x + 1, y + 1).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(x + 1, y + 1));
		
		// nach unten limks
		if((this.y + 1 < 8 && this.x - 1 >= 0 && game.getFigurAt(x + 1, y - 1) == null )
			|| (game.getFigurAt(x + 1, y - 1) != null && game.getFigurAt(x + 1, y - 1).spielerFarbe != this.spielerFarbe))
			possibleMoves.add(new Koordinate(x + 1, y - 1));
			
		return possibleMoves;
	}

}
