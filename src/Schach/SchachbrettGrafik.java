package Schach;

import javax.imageio.ImageIO;
import javax.swing.*;

import Figuren.Figur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class SchachbrettGrafik extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1137300914749937758L;
	
	private int size = 100;

	private Steuerung steuerung;

	public SchachbrettGrafik(Steuerung steuerung) {
		this.steuerung = steuerung;
		
		repaint();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println((e.getX() / 100) + 1 + " | " + (1 + (e.getY() / 100)));
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		drawBoard(g);
	}
	
	// Rechteck mit gegebener Farbe und Gr��e zeichnen
	private void drawTile(Graphics g, int x, int y, Color color){
        g.setColor(color);
        g.fillRect(x, y, this.size, this.size);
	}
	
	private void drawFiguren(Graphics g) {
		Figur schachFeld[][] = steuerung.getBoard();
		for(int i = 0; i < schachFeld.length; i++) {
			for(int j = 0; j < schachFeld[i].length; j++) {
				if(schachFeld[i][j] != null) {
					try {
			            BufferedImage image = ImageIO.read(new File(schachFeld[i][j].getImage()));  // read image for this piece
			            g.drawImage(image, i * this.size, j * this.size, this.size, this.size, null);             // draw the image
			        } catch (Exception e) { // this should never happen
			            System.out.println("ERROR: Cannot load image file\n"); // this exception should never happen
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
        drawFiguren(g);
	}
	
}
