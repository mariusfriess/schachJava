package Schach;

import Figuren.Figur;
import Figuren.Koordinate;

public class Move {
	private Koordinate newTile;
	private Koordinate oldTile;
	private Figur figur;
	private Figur targetFigur;
	
	public Move(Koordinate newTile, Koordinate oldTile, Figur figur, Figur targetFigur) {
		this.setNewTile(newTile);
		this.setOldTile(oldTile);
		this.setFigur(figur);
		this.setTargetFigur(targetFigur);
	}

	public Koordinate getNewTile() {
		return newTile;
	}

	public void setNewTile(Koordinate newTile) {
		this.newTile = newTile;
	}

	public Koordinate getOldTile() {
		return oldTile;
	}

	public void setOldTile(Koordinate oldTile) {
		this.oldTile = oldTile;
	}

	public Figur getFigur() {
		return figur;
	}

	public void setFigur(Figur figur) {
		this.figur = figur;
	}

	public Figur getTargetFigur() {
		return targetFigur;
	}

	public void setTargetFigur(Figur targetFigur) {
		this.targetFigur = targetFigur;
	}
	
	
}
