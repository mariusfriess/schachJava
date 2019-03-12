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
	
	private SchachbrettGrafik schachbrettGrafik;
	private MenuGrafik menuGrafik;
		
	public GUI(Steuerung steuerung) {
		schachbrettGrafik = new SchachbrettGrafik(steuerung);
		menuGrafik = new MenuGrafik();
		this.setLayout(new BorderLayout());
        this.add(schachbrettGrafik, BorderLayout.CENTER);
        this.add(menuGrafik, BorderLayout.SOUTH);
        this.getContentPane().setPreferredSize(new Dimension(800, 840));
		this.setTitle("Schach");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public SchachbrettGrafik getSchachbrettGrafik() {
		return this.schachbrettGrafik;
	}
	
	public MenuGrafik getMenuGrafik() {
		return this.menuGrafik;
	}
}
