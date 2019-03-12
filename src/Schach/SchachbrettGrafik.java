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
	private static final long serialVersionUID = 1137300914749937758L;
	
	private int size = 100;
	private int clickedX = -1;
	private int clickedY = -1;

	private Steuerung steuerung;
	private ArrayList<Koordinate> possibleMoves = null;

	public SchachbrettGrafik(Steuerung steuerung) {
		this.steuerung = steuerung;
		
		repaint();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				possibleMoves = null;
				clickedX = e.getX() / 100;
				clickedY = e.getY() / 100;
				steuerung.spielerKlick(clickedX, clickedY);
				repaint();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		drawBoard(g);
		if(possibleMoves != null) {
			drawPossibleMoves(g);
		}
        drawFiguren(g);
	}
	
	public void setPossibleMoves(ArrayList<Koordinate> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	
	private void drawPossibleMoves(Graphics g) {
		Color color = new Color(252, 236, 93);
		for(Koordinate possibleMove: possibleMoves) {
			int x = possibleMove.getX() * size;
			int y = possibleMove.getY() * size;
			drawCircle(g, x, y, color);
 		}
	}
	
	// Rechteck mit gegebener Farbe und Groesse zeichnen
	private void drawTile(Graphics g, int x, int y, Color color){
        g.setColor(color);
        g.fillRect(x, y, this.size, this.size);
	}
	
	private void drawCircle(Graphics g, int x, int y, Color color){
        g.setColor(color);
        int z = (int) (this.size * 0.8);
        System.out.println(z);
        g.fillRoundRect(x + ((this.size - z) / 2), y + ((this.size - z) / 2), z, z, 20, 20);
	}

	private void drawFiguren(Graphics g) {
		Figur schachFeld[][] = steuerung.getBoard();
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
	}
	
}
