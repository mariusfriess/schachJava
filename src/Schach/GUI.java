package Schach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import Figuren.Figur;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SchachbrettGrafik schachbrettGrafik;
	private SpielerGrafik spielerWeissGrafik = new SpielerGrafik("Spieler 1");
	private SpielerGrafik spielerSchwarzGrafik = new SpielerGrafik("Spieler 2");
		
	public GUI(Steuerung steuerung) {
		schachbrettGrafik = new SchachbrettGrafik(steuerung);
		setLayout(new BorderLayout());

        this.add(spielerWeissGrafik, BorderLayout.LINE_START);
        this.add(schachbrettGrafik, BorderLayout.CENTER);
        this.add(spielerSchwarzGrafik, BorderLayout.LINE_END);
        
        this.getContentPane().setPreferredSize(new Dimension(1200, 800));
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
	
	public SpielerGrafik getSpielerWeissGrafik() {
		return this.spielerWeissGrafik;
	}
	
	public SpielerGrafik getSpielerSchwarzGrafik() {
		return this.spielerSchwarzGrafik;
	}
	
	public void repaintMenus() {
		this.spielerWeissGrafik.paintImmediately(0,0,200,200);
		this.spielerSchwarzGrafik.paintImmediately(0,0,200,200);
	}
}
