package Figuren;

public class Koenig extends Figur {
	
	public Koenig(String spielerFarbe) {
		if(spielerFarbe == "wei�") {
			this.image = "Assets/Figuren/koenig_wei�.png";
		}else {
			this.image = "Assets/Figuren/koenig_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
