package Schach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SchachbrettGrafik schachbrettGrafik;
	private SpielerGrafik spielerWeissGrafik;
	private SpielerGrafik spielerSchwarzGrafik;
		
	public GUI(Steuerung steuerung) {
		schachbrettGrafik = new SchachbrettGrafik(steuerung);
		spielerWeissGrafik = new SpielerGrafik("Spieler Weiss", steuerung);
		spielerSchwarzGrafik = new SpielerGrafik("Spieler Schwarz", steuerung);
		setLayout(new BorderLayout());

        add(spielerWeissGrafik, BorderLayout.LINE_START);
        add(schachbrettGrafik, BorderLayout.CENTER);
        add(spielerSchwarzGrafik, BorderLayout.LINE_END);
        
        getContentPane().setPreferredSize(new Dimension(1200, 800));
		setTitle("Schach");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public SchachbrettGrafik getSchachbrettGrafik() {
		return schachbrettGrafik;
	}
	
	public SpielerGrafik getSpielerWeissGrafik() {
		return spielerWeissGrafik;
	}
	
	public SpielerGrafik getSpielerSchwarzGrafik() {
		return spielerSchwarzGrafik;
	}
}
