package Schach;

import java.util.ArrayList;

import Figuren.Bauer;
import Figuren.Dame;
import Figuren.Figur;
import Figuren.Koenig;
import Figuren.Laeufer;
import Figuren.Springer;
import Figuren.Turm;

public class KI {
	
	private Steuerung steuerung;
	private int depth = 5;

	public KI(Steuerung steuerung) {
		this.steuerung = steuerung;
	}
	
	public Move calcMove() {
		//max(currentDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
		Move bestMove = null;
		int bestMoveVal = -9999;
		ArrayList<Move> allMoves = steuerung.getAllPossibleMovesForCurrentPlayer();
		for(Move move: allMoves) {
			steuerung.move(move);
			int value = minimax(depth - 1, -10000, 10000, false);
			steuerung.undoMove(move);
			if(value >= bestMoveVal) {
				bestMoveVal = value;
				bestMove = move;
				System.out.println(value);
			}
		}
		return bestMove;
	}
	
	private int minimax(int depth, int alpha, int beta, boolean isMaximising) {
		if(depth == 0) {
			return -evaluateBoard();
		}
		
		if(isMaximising) {
			int nBestMoveVal = -9999;
			ArrayList<Move> allMoves = steuerung.getAllPossibleMovesForCurrentPlayer();
			for(Move move: allMoves) {
				steuerung.move(move);
				nBestMoveVal = Math.max(nBestMoveVal, minimax(depth - 1, alpha, beta, !isMaximising));
				steuerung.undoMove(move);
				alpha = Math.max(alpha, nBestMoveVal);
				if(beta <= alpha) {
					return nBestMoveVal;
				}
			}
			return nBestMoveVal;
		}else {
			int nBestMoveVal = 9999;
			ArrayList<Move> allMoves = steuerung.getAllPossibleMovesForCurrentPlayer();
			for(Move move: allMoves) {
				steuerung.move(move);
				nBestMoveVal = Math.min(nBestMoveVal, minimax(depth - 1, alpha, beta, !isMaximising));
				steuerung.undoMove(move);
				beta = Math.min(beta, nBestMoveVal);
				if(beta <= alpha) {
					return nBestMoveVal;
				}
			}
			return nBestMoveVal;
		}
	}
	
	private int evaluateBoard() {
		int total = 0;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				total = total + pieceValue(steuerung.getSchachbrett().getFigurAt(i,j));
			}
		}
		return total;
	}
	
	private int pieceValue(Figur f) {
		if(f == null) return 0;
		int val = 0;
		if(f instanceof Bauer) val = 10;
		else if(f instanceof Springer) val = 30;
		else if(f instanceof Laeufer) val = 30;
		else if(f instanceof Turm) val = 50;
		else if(f instanceof Dame) val = 90;
		else if(f instanceof Koenig) val = 900;
		
		return f.getSpielerFarbe() == "weiss" ? val: -val;
	}
}
