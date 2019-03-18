package Schach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SpielerSchwarzGrafik extends JPanel {

	private String spielerName = "Spieler 2";
	private JTextArea textfeld = new JTextArea(spielerName);
	private JTextArea timeFeld = new JTextArea();
	JPanel infoPanel = new JPanel(new GridBagLayout());

	public SpielerSchwarzGrafik() {
		this.setPreferredSize(new Dimension(200, 800));
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		textfeld.setEditable(false);
		textfeld.setFont(new Font("Sans serif", Font.PLAIN, 24));
		textfeld.setBackground(new Color(0,0,0,0));
		timeFeld.setEditable(false);
		timeFeld.setBackground(new Color(0,0,0,0));
		this.setLayout(new BorderLayout());
		
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridy = 1;
		infoPanel.add(textfeld, gbc);
		gbc.gridy = 2;
		infoPanel.add(timeFeld, gbc);
		
		add(infoPanel, BorderLayout.NORTH);
	}
	
	public void setActive(boolean activate) {
		if(activate) {
			infoPanel.setBackground(new Color(125, 135, 150));
		}else {
			infoPanel.setBackground(Color.white);
		}
	}
	
	public void setTime(int time) {
		int min = (int) Math.floor(time / 60);
		String minStr = Integer.toString(min);
		if(min < 10) {
			minStr = "0" + minStr;
		}
		int sec = time % 60;
		String secStr = Integer.toString(sec);
		if(sec < 10) {
			secStr = "0" + secStr;
		}
		timeFeld.setText(minStr + ":" + secStr);
	}

}
