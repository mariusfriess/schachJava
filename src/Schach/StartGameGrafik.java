package Schach;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StartGameGrafik extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton startGame = new JButton("Spiel 1 gg. 1 starten");
	private JButton startGameKi = new JButton("Spiele gg. KI starten");
	private JLabel timeLimitLabel = new JLabel("Zeitlimit festlegen (in Min., nur 1gg1)");
	private JTextField timeLimit = new JTextField(6);
	
	private void closeFrame() {
		this.dispose();
	}

	public StartGameGrafik() {		
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(timeLimit.getText().length() == 0) {
					new Steuerung(false, 0);
				}else {
					new Steuerung(false, Integer.parseInt(timeLimit.getText()));
				}
				closeFrame();
			}
		});
		
		startGameKi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Steuerung(true, 0);
				closeFrame();
			}
		});
		
		JPanel startBtnPnl = new JPanel();
		startBtnPnl.setLayout(new FlowLayout());
		startBtnPnl.add(startGame);
		startBtnPnl.add(startGameKi);
		startBtnPnl.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		JPanel timeLimitPnl = new JPanel();
		timeLimitPnl.setLayout(new FlowLayout());
		timeLimitPnl.add(timeLimitLabel);
		timeLimitPnl.add(timeLimit);
		timeLimitPnl.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(startBtnPnl, BorderLayout.NORTH);
		add(timeLimitPnl, BorderLayout.SOUTH);
		pack();
		setTitle("Schach Spiel Starten");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new StartGameGrafik();
	}

}
