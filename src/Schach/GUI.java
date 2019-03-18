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
	private SpielerWeissGrafik spielerWeissGrafik = new SpielerWeissGrafik();
	private SpielerSchwarzGrafik spielerSchwarzGrafik = new SpielerSchwarzGrafik();
		
	public GUI(Steuerung steuerung) {
		schachbrettGrafik = new SchachbrettGrafik(steuerung);
		this.setLayout(new BorderLayout());
		
        this.add(schachbrettGrafik, BorderLayout.CENTER);
        this.add(spielerWeissGrafik, BorderLayout.WEST);
        this.add(spielerSchwarzGrafik, BorderLayout.EAST);
        
        this.getContentPane().setPreferredSize(new Dimension(1200, 800));
		this.setTitle("Schach");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.createBufferStrategy(2);
	}
	
	public SchachbrettGrafik getSchachbrettGrafik() {
		return this.schachbrettGrafik;
	}
	
	public SpielerWeissGrafik getSpielerWeissGrafik() {
		return this.spielerWeissGrafik;
	}
	
	public SpielerSchwarzGrafik getSpielerSchwarzGrafik() {
		return this.spielerSchwarzGrafik;
	}
	
	public void repaintAll() {
		spielerSchwarzGrafik.paintImmediately(0, 0, 200, 800);
		spielerWeissGrafik.paintImmediately(0, 0, 200, 800);
		schachbrettGrafik.paintImmediately(0, 0, 800, 800);
	}
}
