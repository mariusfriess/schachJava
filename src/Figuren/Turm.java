package Figuren;

public class Turm extends Figur {
	
	public Turm(String spielerFarbe) {
		if(spielerFarbe == "wei�") {
			this.image = "Assets/Figuren/turm_wei�.png";
		}else {
			this.image = "Assets/Figuren/turm_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
