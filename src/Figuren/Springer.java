package Figuren;

public class Springer extends Figur {
	
	public Springer(String spielerFarbe) {
		if(spielerFarbe == "wei�") {
			this.image = "Assets/Figuren/springer_wei�.png";
		}else {
			this.image = "Assets/Figuren/springer_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
