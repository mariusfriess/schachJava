package Schach;

import javax.imageio.ImageIO;
import javax.swing.*;
import Figuren.Figur;
import Figuren.Koordinate;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class SchachbrettGrafik extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int size = 100;

	private Steuerung steuerung;
	private ArrayList<Koordinate> possibleMoves = null;
	private Koordinate redTile = null;

	public SchachbrettGrafik(Steuerung steuerung) {
		this.steuerung = steuerung;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				// Ein Klick auf das Schachfeld wird an die Steuerung uebermittelt
				steuerung.spielerKlick(e.getX() / size, e.getY() / size);
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawPossibleMoves(g);
        drawFiguren(g);
	}
	
	/***
	 * Zeichnet die moeglichen Zuege eines Spielers auf dem Schachfeld
	 * @param g
	 */
	private void drawPossibleMoves(Graphics g) {
		if(possibleMoves != null) {
			Color color = new Color(252, 236, 93);
			Color color2 = new Color(255, 65, 54);
			for(Koordinate possibleMove: possibleMoves) {
				int x = possibleMove.getX() * size;
				int y = possibleMove.getY() * size;
				// Wenn an der Position des moeglichen Zuges einen andere Figur steht, wird das Feld rot markiert
				if(steuerung.getSchachbrett().getFigurAt(possibleMove.getX(), possibleMove.getY()) != null) {
					drawCircle(g, x, y, color2);
				}else {
					drawCircle(g, x, y, color);
				}
	 		}
		}
	}
	
	/***
	 * Zeichnet ein Rechteck an der entsprechenden Position
	 * @param g
	 * @param x
	 * @param y
	 * @param color
	 */
	private void drawTile(Graphics g, int x, int y, Color color){
        g.setColor(color);
        g.fillRect(x, y, this.size, this.size);
	}
	
	/***
	 * Zeichnet ein abgerundetes Rechteck ab der jeweiligen Position
	 * Wird benutzt um die moeglichen Zuege anzuzeigen
	 * @param g
	 * @param x
	 * @param y
	 * @param color
	 */
	private void drawCircle(Graphics g, int x, int y, Color color){
        g.setColor(color);
        int z = (int) (this.size * 0.8);
        g.fillRoundRect(x + ((this.size - z) / 2), y + ((this.size - z) / 2), z, z, 20, 20);
	}

	/***
	 * Zeichnet die Bilder der Figuren an deren entsprechenden Positionen
	 * @param g
	 */
	private void drawFiguren(Graphics g) {
		Figur schachFeld[][] = steuerung.getSchachbrett().getBoard();
		for(int i = 0; i < schachFeld.length; i++) {
			for(int j = 0; j < schachFeld[i].length; j++) {
				if(schachFeld[i][j] != null) {
					try {
			            BufferedImage image = ImageIO.read(new File(schachFeld[i][j].getImage()));
			            g.drawImage(image, i * this.size, j * this.size, this.size, this.size, null);
			        } catch (Exception e) {
			            System.out.println("ERROR: Bild kann nicht geladen werden");
			            System.exit(0);
			        }
				}
			}
		}
	}
	
	/***
	 * Zeichnet die einzelnen Schachfelder abwechselt in den unterschiedlichen Farben
	 * @param g
	 */
	private void drawBoard(Graphics g) {
		Color color;
		int count = 0; 
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                int x = j * this.size;
                int y = i * this.size;

                if(count % 2 == 0){
                    color = new Color(232, 235, 239);
                }
                else{
                    color = new Color(125, 135, 150);
                }
                drawTile(g, x, y, color);
                count++;
            }
            count++;
        }
        // Gegebenenfalls wird noch angezeigt, ob der Koenig im Schach steht
        if(redTile != null) {
        	drawTile(g, redTile.getX() * size, redTile.getY() * size, new Color(255, 65, 54));
        }
	}
	
	public void setRedTile(Koordinate k) {
		redTile = k;
	}
	
	public void setPossibleMoves(ArrayList<Koordinate> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	
}
