package Figuren;

public class Laeufer extends Figur {
	
	public Laeufer(String spielerFarbe) {
		if(spielerFarbe == "weiﬂ") {
			this.image = "Assets/Figuren/laeufer_weiﬂ.png";
		}else {
			this.image = "Assets/Figuren/laeufer_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
