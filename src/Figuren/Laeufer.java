package Figuren;

public class Laeufer extends Figur {
	
	public Laeufer(String spielerFarbe) {
		if(spielerFarbe == "wei�") {
			this.image = "Assets/Figuren/laeufer_wei�.png";
		}else {
			this.image = "Assets/Figuren/laeufer_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
