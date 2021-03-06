package edu.sjsu.cs.ghost151;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.Random;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import javax.swing.ImageIcon;

/**
 * <b>ConsoleGameDriver</b> is responsible for instantiating the Game instance
 * and requesting the number of ghosts the user would like in the simulation.
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
	private int numberOfGhosts = 4;

	Game game = Game.INSTANCE;

	AudioPlayer mgpGlobal = AudioPlayer.player;
	AudioStream startGame;
	AudioData mdGlobal;
	ContinuousAudioDataStream loopGlobal = null;

	public WindowGameDriver() {

		try {
			startGame = new AudioStream(new FileInputStream("start.wav"));
			mdGlobal = startGame.getData();
			loopGlobal = new ContinuousAudioDataStream(mdGlobal);
		} catch (IOException error) {
		}

		mgpGlobal.start(loopGlobal);

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
				mgpGlobal.stop(loopGlobal);
				StartGame();
			}
		});
		contentPanel.add(startButton, constraints);

		launcherFrame.pack();
		launcherFrame.setLocation(100, 0);
		launcherFrame.setVisible(true);
	}

	public void StartGame() {
		try {
			numberOfGhosts = Integer.valueOf(numberOfGhostsTextArea.getText());
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(launcherFrame, "Invalid # of ghosts!");
			return;
		}

		Board board = game.getBoard();

		int maximumGhosts = board.ROWS * board.COLUMNS - 2 * board.ROWS - 2
				* (board.COLUMNS - 2) - 1;

		if (numberOfGhosts > maximumGhosts) {
			JOptionPane.showMessageDialog(launcherFrame,
					"The maximum # of ghosts is: " + maximumGhosts);
			return;
		}

		if (numberOfGhosts < 1) {
			JOptionPane.showMessageDialog(launcherFrame,
					"Need at least 1 ghost on the board");
			return;
		}

		Runnable r1 = new Runnable() {
			public void run() {
				game.Run(numberOfGhosts);
			}
		};

		Runnable r2 = new Runnable() {
			public void run() {

				Board board = game.getBoard();

				outputFrame = new JFrame("Ghost151 Simulation");
				outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Container contentPanel = outputFrame.getContentPane();
				contentPanel.setLayout(new GridBagLayout());

				GridBagConstraints constraints = new GridBagConstraints();

				constraints.gridx = 0;
				constraints.gridy = 0;
				outputGame = new JTextArea(board.ROWS + 3, board.COLUMNS);

				try {
					outputGame
							.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
				} catch (Exception e) {
					System.out
							.println("WARNING: Monospace font not supported: "
									+ e);
				}
				outputGame.setText(game.rawData);
				outputGame.setForeground(Color.getHSBColor(225, 225, 225));
				outputGame.setBackground(Color.getHSBColor(328, 80, 0));
				contentPanel.add(outputGame, constraints);

				constraints.gridx = 0;
				constraints.gridy = 1;
				constraints.gridwidth = 2;
				constraints.fill = GridBagConstraints.HORIZONTAL;

				outputFrame.pack();
				outputFrame.setLocation(100, 0);
				outputFrame.setVisible(true);

				while (game.isRunning) {
					outputGame.setText(game.rawData);
					System.out.println(game.rawData);
					try {
						Thread.sleep(500);
					} catch (InterruptedException interruptedException) {
					}
				}
				outputGame.setText(game.rawData);
				outputGame.append(game.GetStatistics());
			}
		};

		Runnable r3 = new Runnable() {
			public void run() {
				AudioPlayer mgp = AudioPlayer.player;
				AudioStream bgm;
				AudioStream endGame;
				AudioData md;
				AudioData md2;
				ContinuousAudioDataStream loop = null;
				AudioDataStream deathSoundAudioDataStream = null;

				try {
					bgm = new AudioStream(new FileInputStream("siren.wav"));
					md = bgm.getData();
					loop = new ContinuousAudioDataStream(md);
				} catch (IOException error) {
				}

				try {
					endGame = new AudioStream(new FileInputStream("death.wav"));
					md2 = endGame.getData();
					deathSoundAudioDataStream = new AudioDataStream(md2);
				} catch (IOException error) {
				}

				while (game.isRunning) {
					mgp.start(loop);
				}
				mgp.stop(loop);
				mgp.start(deathSoundAudioDataStream);
			}
		};

		Thread thr1 = new Thread(r1);
		Thread thr2 = new Thread(r2);
		Thread thr3 = new Thread(r3);
		thr1.start();
		thr2.start();
		thr3.start();
	}
}
