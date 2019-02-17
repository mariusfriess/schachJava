package Figuren;

public class Springer extends Figur {
	
	public Springer(String spielerFarbe) {
		if(spielerFarbe == "weiﬂ") {
			this.image = "Assets/Figuren/springer_weiﬂ.png";
		}else {
			this.image = "Assets/Figuren/springer_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
