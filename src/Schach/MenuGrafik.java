package Schach;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuGrafik extends JPanel {
	
	private JTextArea textFeld = new JTextArea("Spieler Weiss ist an der Reihe.");
	private FlowLayout flowLayout = new FlowLayout();

	public MenuGrafik() {
		setLayout(flowLayout);
		this.setSize(800, 40);
		textFeld.setEditable(false);
		this.add(textFeld);
	}
	
	public void spielerWechseln(String neuerSpieler) {
		textFeld.setText("Spieler " + neuerSpieler.substring(0, 1).toUpperCase() + neuerSpieler.substring(1) + " ist an der Reihe.");
	}

}
