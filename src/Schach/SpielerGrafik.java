package Schach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SpielerGrafik extends JPanel {
	
	private String playerName = "Spieler 1";
	
	private JTextArea playerNameField = new JTextArea(playerName);
	private JTextArea playerTimeField = new JTextArea();
	
	JPanel infoPanel = new JPanel(new GridBagLayout());

	public SpielerGrafik(String playerName) {
		this.playerName = playerName;
		
		setPreferredSize(new Dimension(200, 800));
		setBackground(Color.white);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		
		playerNameField.setEditable(false);
		playerTimeField.setEditable(false);
		playerNameField.setFocusable(false);
		playerTimeField.setFocusable(false);
		playerNameField.setFont(new Font("Sans serif", Font.PLAIN, 24));
		playerTimeField.setFont(new Font("Sans serif", Font.PLAIN, 18));
		playerNameField.setBackground(new Color(0,0,0,0));
		playerTimeField.setBackground(new Color(0,0,0,0));
		
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridy = 1;
		infoPanel.add(playerNameField, gbc);
		gbc.gridy = 2;
		infoPanel.add(playerTimeField, gbc);
		
		add(infoPanel, BorderLayout.NORTH);
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
		playerTimeField.setText(minStr + ":" + secStr);
		repaint();
	}
	
	public void setActive(boolean activate) {
		if(activate) {
			infoPanel.setBackground(new Color(125, 135, 150));
		}else {
			infoPanel.setBackground(Color.white);
		}
	}

}
