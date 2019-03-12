package Schach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SpielerSchwarzGrafik extends JPanel {

	private String spielerName = "Spieler 2";
	private JTextArea textfeld = new JTextArea(spielerName);

	public SpielerSchwarzGrafik() {
		this.setPreferredSize(new Dimension(200, 800));
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		textfeld.setEditable(false);
		textfeld.setFont(new Font("Sans serif", Font.PLAIN, 24));
		setLayout(new BorderLayout());
		add(textfeld, BorderLayout.EAST);
	}

}
