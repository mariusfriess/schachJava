package Schach;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

import Figuren.*;

public class Steuerung {

	private Schachbrett brett = new Schachbrett();
	private GUI gui = new GUI(this);
	private KI ki = new KI(this);
	
	private String currentPlayer = "weiss";
	
	private Timer timer1 = new Timer();
	private int timeSpielerWeiss = 300;
	private int timeSpielerSchwarz = 300;
	
	private Figur selectedFigur;
	private ArrayList<Koordinate> possibleMoves;
	private ArrayList<Move> moveHistory = new ArrayList<Move>();

	private boolean kiPlaying = false;
	private boolean timerActive = false;
	
	public Steuerung(boolean ki, int timerCounter) {
		this.kiPlaying = ki;
		if(!ki && timerCounter > 0) {
			timerActive = true;
			timeSpielerWeiss = timerCounter * 60;
			timeSpielerSchwarz = timerCounter * 60;
			gui.getSpielerSchwarzGrafik().setTime(timeSpielerSchwarz);
			gui.getSpielerWeissGrafik().setTime(timeSpielerWeiss);
		}
		gui.getSpielerWeissGrafik().setActive(true);
	}
	
	/***
	 * Zeichnet die verschiedenen Panels direkt neu
	 */
	private void repaintWindow() {
		gui.getSpielerWeissGrafik().paintImmediately(0,0,200,200);
		gui.getSpielerSchwarzGrafik().paintImmediately(0,0,200,200);
		gui.getSchachbrettGrafik().paintImmediately(0,0,800,800);
	}
	
	/***
	 * Ueberprueft ob einer der beiden Koenige im Schach steht und zeigt dies auf der Spieloberflaeche an
	 */
	private void isKingInCheck() {
		Koenig k = brett.getKing(currentPlayer);
		Koenig k2 = brett.getOpponentKing(currentPlayer);
		if(k.istImSchach()){
			gui.getSchachbrettGrafik().setRedTile(new Koordinate(k.getX(), k.getY()));
		}else if(k2.istImSchach()){
			gui.getSchachbrettGrafik().setRedTile(new Koordinate(k2.getX(), k2.getY()));
		}else {
			gui.getSchachbrettGrafik().setRedTile(null);
		}
	}
	
	/***
	 * Wird beim klick in der SchachbrettGrafik von dieser aufgerufen und zeigt entweder die moeglichen
	 * Zuege des Spielers an oder leitet einen Zug des Spielers ein
	 * @param clickedX
	 * @param clickedY
	 */
	public void spielerKlick(int clickedX, int clickedY) {
		Figur f = brett.getFigurAt(clickedX, clickedY);
		if(f != null && f.getSpielerFarbe() == currentPlayer) {
			// Setze die Ausgewählte Figur und zeige alle moeglichen Zuege fuer diese Figur an
			selectedFigur = f;
			possibleMoves = f.getAllPossibleMoves();
			gui.getSchachbrettGrafik().setPossibleMoves(possibleMoves);
			repaintWindow();
		}else {
			gui.getSchachbrettGrafik().setPossibleMoves(null);
			repaintWindow();
			// Bewege Figur wenn moeglich
			if(possibleMoves == null || selectedFigur == null) return;
			for(Koordinate possibleMove: possibleMoves) {
				// Wenn auf einen der moeglichen Zuege geklickt wurde, kann die Figur bewegt werden
				if(possibleMove.getX() == clickedX && possibleMove.getY() == clickedY) {
					// Falls die Figur ein Bauer ist und am anderen Ende des Feldes angekommen ist, wird er automatisch zur Dame
					if(brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] instanceof Bauer && currentPlayer == "weiss" && clickedY == 0) {
						Dame d = new Dame("weiss", clickedX, 0, brett);
						brett.getBoard()[clickedX][0] = d;
						brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] = null;
						selectedFigur = null;
						changePlayer();
						moveDone();
					} else if(brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] instanceof Bauer && currentPlayer == "schwarz" && clickedY == 7) {
						Dame d = new Dame("schwarz", clickedX, 7, brett);
						brett.getBoard()[selectedFigur.getX()][selectedFigur.getY()] = null;
						brett.getBoard()[clickedX][7] = d;
						selectedFigur = null;
						changePlayer();
						moveDone();
					} else {
						move(new Move(new Koordinate(clickedX, clickedY), new Koordinate(selectedFigur.getX(), selectedFigur.getY()), selectedFigur, brett.getFigurAt(clickedX, clickedY)));
						moveDone();
					}
					selectedFigur = null;
					// Falls der Spieler gegen den Computer spielt, wird dieser nun seinen Zug machen
					if(kiPlaying == true) {
						getZug();
					}
				}else {
					possibleMoves = null;
				}
			}
		}
	}
	
	/***
	 * Konvertiere mehrere ArrayList mit Koordinaten in eine ArrayList mit allen Moves
	 * @return allMoves - ArrayList mit allen Zuegen fuer ein Spieler
	 */
	public ArrayList<Move> getAllPossibleMovesForCurrentPlayer(){
		ArrayList<Move> allMoves = new ArrayList<Move>();
		Figur schachFeld[][] = brett.getBoard();
		for(int i = 0; i < schachFeld.length; i++) {
			for(int j = 0; j < schachFeld[i].length; j++) {
				if(schachFeld[i][j] != null && schachFeld[i][j].getSpielerFarbe() == currentPlayer) {
					ArrayList<Koordinate> moves = schachFeld[i][j].getAllPossibleMoves();
					for(Koordinate move: moves) {
						if(move.getX() >= 0 && move.getY() >= 0 && move.getX() < 8 && move.getY() < 8) {
							allMoves.add(new Move(move, new Koordinate(i, j), schachFeld[i][j], schachFeld[move.getX()][move.getY()]));
						}
					}
				}
			}
		}
		return allMoves;
	}
	
	/***
	 * Ueberprueft ob der Koenig eines Spielers noch auf dem Feld ist
	 * @param isCurrentPlayer
	 * @return isKingOnField
	 */
	private boolean checkForKing(boolean isCurrentPlayer) {
		String player = currentPlayer;
		if(!isCurrentPlayer) player = currentPlayer == "weiss"? "schwarz": "weiss";
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(brett.getFigurAt(i, j) instanceof Koenig && brett.getFigurAt(i, j).getSpielerFarbe() == player) {
					return true;
				}
			}
		}
		return false;
	}
	
	/***
	 * Ueberprueft nach dem endgueltigen Beenden eines Zuges ob das Spiel vorbei ist
	 * und aendert die Anzeige des aktuellen Spielers und initialisiert den Timer
	 * um die Zeit der Spieler zu messen
	 */
	public void moveDone() {
		if(!checkForKing(true)) {
			changePlayer(); 
			gameOver();
		}
		else if(!checkForKing(false)) {
			gameOver();
		}
		isKingInCheck();
		if(currentPlayer == "weiss"){
			gui.getSpielerWeissGrafik().setActive(true);
			gui.getSpielerSchwarzGrafik().setActive(false);
		}else {
			gui.getSpielerWeissGrafik().setActive(false);
			gui.getSpielerSchwarzGrafik().setActive(true);
		}
		if(!kiPlaying && timerActive) {
			timer1.cancel();
			timer1 = new Timer();
			// Im Sekundentakt wird die Zeit des jeweiligen Spielers heruntergezaehlt
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					if(currentPlayer == "weiss"){
						timeSpielerWeiss--;
						gui.getSpielerWeissGrafik().setTime(timeSpielerWeiss);
					}else {
						timeSpielerSchwarz--;
						gui.getSpielerSchwarzGrafik().setTime(timeSpielerSchwarz);
					}
				}
			}, 1000, 1000);
		}
		repaintWindow();
	}
	
	/***
	 * Bewegt eine Figur nach dem entsprechenden Move
	 * @param move
	 */
	public void move(Move move) {
		// Alte Position wird entfernt
		brett.getBoard()[move.getOldTile().getX()][move.getOldTile().getY()] = null;
		// Figur wird auf neue Position gesetzt
		brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()] = move.getFigur();
		// Position wird im Objekt der Figur aktualisiert
		brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()].setPosition(move.getNewTile().getX(), move.getNewTile().getY());
		// Spieler wird gewechselt
		changePlayer();
		// Zug wird gespeichert
		moveHistory.add(move);
	}
	
	/***
	 * Macht den entsprechenden Move rueckgaenig
	 * @param move
	 */
	public void undoMove(Move move) {
		brett.getBoard()[move.getOldTile().getX()][move.getOldTile().getY()] = move.getFigur();
		brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()] = null;
		brett.getBoard()[move.getOldTile().getX()][move.getOldTile().getY()].setPosition(move.getOldTile().getX(), move.getOldTile().getY());
		// Falls beim eigentlichen Zug eine Figur geschlagen wurde, wird diese nun auch zurueckgesetzt
		if(move.getTargetFigur() != null) {
			brett.getBoard()[move.getNewTile().getX()][move.getNewTile().getY()] = move.getTargetFigur();
			move.getTargetFigur().setPosition(move.getNewTile().getX(), move.getNewTile().getY());
		}
		changePlayer();
		moveHistory.remove(moveHistory.size() - 1);
	}
	
	/***
	 * Macht den letzten gespeicherten Move rueckgaenig
	 */
	public void undoLastMove() {
		if(moveHistory.size() > 0)
			undoMove(moveHistory.get(moveHistory.size() - 1));
		gui.getSchachbrettGrafik().setPossibleMoves(null);
		moveDone();
	}
	
	/***
	 * Zeigt am Ende des Spieles einen GameOver Dialog an
	 */
	private void gameOver() {
		timer1.cancel();
		System.out.println("Spieler " + currentPlayer + " hat gewonnen");
		
		Object[] options = {"Neues Spiel", "Schliessen"};

		int dialogResult = JOptionPane.showOptionDialog(
				null,
				"Spieler " + currentPlayer + " hat gewonnen!", 
				"Game Over",
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[0]);
		
		System.out.println("RESULT: "+ dialogResult);
		if(dialogResult == 0) {
			timeSpielerWeiss = 300;
			timeSpielerSchwarz = 300;
			brett = new Schachbrett();
			currentPlayer = "weiss";
			gui.getSpielerWeissGrafik().setTime(timeSpielerWeiss);
		    gui.getSpielerSchwarzGrafik().setTime(timeSpielerSchwarz);
		    gui.getSpielerWeissGrafik().setActive(true);
		    possibleMoves = null;
		    selectedFigur = null;
		} else {
			gui.dispose();
			System.exit(0);
		}
	}
	
	/***
	 * Fordert die KI auf ihren Zug zu berechnen
	 */
	private void getZug() {
		Move m = ki.calcMove();
		if(m != null) {
			System.out.println("FROM: " + m.getOldTile().getX() + "/" + m.getOldTile().getY());
			System.out.println("TO: " + m.getNewTile().getX() + "/" + m.getNewTile().getY());
		}
		move(m);
		moveDone();
		repaintWindow();
	}
	
	/***
	 * Aendert den aktuellen Spieler in den jeweils anderen
	 */
	private void changePlayer() {
		currentPlayer = currentPlayer == "weiss" ? "schwarz": "weiss";
	}
	
	/***
	 * @return schachbrett
	 */
	public Schachbrett getSchachbrett() {
		return brett;
	}
}
