package Figuren;

public class Dame extends Figur {
	
	public Dame(String spielerFarbe) {
		if(spielerFarbe == "wei�") {
			this.image = "Assets/Figuren/dame_wei�.png";
		}else {
			this.image = "Assets/Figuren/dame_schwarz.png";
		}
	}

	@Override
	public void getAllPossibleMoves() {
		// TODO Auto-generated method stub

	}

}
