package Figuren;

public class Koenig extends Figur {
	
	public Koenig(String spielerFarbe) {
		if(spielerFarbe == "weiﬂ") {
			this.image = "Assets/Figuren/koenig_weiﬂ.png";
		}else {
			this.image = "Assets/Figuren/koenig_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
