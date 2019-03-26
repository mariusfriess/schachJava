package Schach;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class SpielerGrafik extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea playerNameField = new JTextArea();
	private JTextArea playerTimeField = new JTextArea();
	
	private JButton undoButton = new JButton("Undo");
	
	private JPanel infoPanel = new JPanel(new GridBagLayout());
	private JPanel buttonPanel = new JPanel(new FlowLayout());

	public SpielerGrafik(String playerName, Steuerung steuerung) {
		playerNameField.setText(playerName);
		
		setPreferredSize(new Dimension(200, 800));
		setBackground(Color.white);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		
		playerNameField.setEditable(false);
		playerTimeField.setEditable(false);
		playerNameField.setFocusable(false);
		playerTimeField.setFocusable(false);
		playerNameField.setFont(new Font("Sans serif", Font.PLAIN, 22));
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
		
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				steuerung.undoLastMove();
			}
		});
		
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.add(undoButton);
		
		add(infoPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/***
	 * Wandelt die in Sekunden uebergebene Zeit in ein Format mit Minuten
	 * und Sekunden um und zeigt es auf der Grafik an
	 * @param time
	 */
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
	
	/***
	 * Setzt die Farbe hinter dem Spielernamen, damit erkennbar wird, welcher Spieler
	 * an der Reihe ist
	 * @param activate
	 */
	public void setActive(boolean activate) {
		if(activate) {
			infoPanel.setBackground(new Color(125, 135, 150));
		}else {
			infoPanel.setBackground(Color.white);
		}
	}

}
