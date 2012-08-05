package edu.sjsu.cs.ghost151;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

/**
 * <b>ConsoleGameDriver</b> is responsible for instantiating the Game instance and
 * requesting the number of ghosts the user would like in the simulation.
 */
public class WindowGameDriver {
	public static void main(String[] args) {
		new WindowGameDriver();
	}
	
	private JFrame launcherFrame;
	private JTextArea numberOfGhostsTextArea;
        public JFrame outputFrame;
        public JTextArea outputGame;
        public Board gBoard;
        public String outputData;
        
	public WindowGameDriver() {
		launcherFrame = new JFrame("Ghost151");
		launcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPanel = launcherFrame.getContentPane();
		contentPanel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		// add a label to prompt the user
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		contentPanel.add(new JLabel("Number of ghosts: "), constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		numberOfGhostsTextArea = new JTextArea("4", 1, 4);
		contentPanel.add(numberOfGhostsTextArea, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartGame();
			}
		});
		contentPanel.add(startButton, constraints);
		
		launcherFrame.pack();
		launcherFrame.setLocation(100, 0);
		launcherFrame.setVisible(true);
	}
	
	public void StartGame() {
		Game game = Game.INSTANCE;
		int numberOfGhosts = 4;

		try {
			numberOfGhosts = Integer.valueOf(numberOfGhostsTextArea.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(launcherFrame, "Invalid # of ghosts!");
			return;
		}

		Board board = game.getBoard();

		int maximumGhosts = board.ROWS * board.COLUMNS - 2 * board.ROWS - 2
				* (board.COLUMNS - 2) - 1;

		if (numberOfGhosts > maximumGhosts) {
			JOptionPane.showMessageDialog(launcherFrame, "The maximum # of ghosts is: " + maximumGhosts);
			return;
		}
		
		if (numberOfGhosts < 1) {
			JOptionPane.showMessageDialog(launcherFrame, "Need at least 1 ghost on the board");
			return;
		}
                
                game.Run(numberOfGhosts);
                
	}
}
