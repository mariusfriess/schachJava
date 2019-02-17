package Schach;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import Figuren.Figur;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	public GUI(Steuerung steuerung) {
		SchachbrettGrafik chessboard_view = new SchachbrettGrafik(steuerung);
        this.add(chessboard_view, BorderLayout.CENTER);
        this.getContentPane().setPreferredSize(new Dimension(800, 800));
		this.setTitle("Schach");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
