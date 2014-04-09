package com.epam.students2012.sergeygolovachev.snake.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.epam.students2012.sergeygolovachev.snake.controller.SnakeGameController;
import com.epam.students2012.sergeygolovachev.snake.model.BodyPart;
import com.epam.students2012.sergeygolovachev.snake.model.GameField;
import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameModel;
import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameState;
import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameModel.RunState;

/**
 * Snake game view.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class SnakeGameView extends JPanel implements Observer {
	// the painted cell size
	public static final int CELL_SIZE = 20;
	// the serial version UID the score field size
	private static final long serialVersionUID = 1L;
	// the score field size
	private static final Dimension SCORE_FIELD_SIZE = new Dimension(100, 50);
	// the score field font size
	private static final int SCORE_FIELD_FONT_SIZE = 40;
	// the game field
	private GameField gameField;
	// the previous game state
	private SnakeGameState previousGameState;
	// the current game state
	private SnakeGameState currentGameState;
	// the controller reference
	private SnakeGameController snakeGameController;
	// the start button
	private JButton startButton;
	// the stop button
	private JButton stopButton;
	// the score field
	private JTextField scoreField;
	// the pause button
	private JButton pauseButton;
	// the game field panel
	private GameFieldPanel gameFieldPanel;

	/**
	 * Constructor without parameters.
	 */
	public SnakeGameView() {
		this.snakeGameController = null;
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param snakeGameModel the model reference
	 */
	public SnakeGameView(SnakeGameModel snakeGameModel) {
		this.snakeGameController = new SnakeGameController(snakeGameModel);
		this.gameField = snakeGameModel.getGameField();
		this.setLayout(new BorderLayout());
		this.gameFieldPanel = new GameFieldPanel(this);
		this.gameFieldPanel.setSize(CELL_SIZE * this.gameField.getWidth(),
				CELL_SIZE * this.gameField.getHeight());
		this.gameFieldPanel.setPreferredSize(new Dimension(CELL_SIZE
				* this.gameField.getWidth(), CELL_SIZE
				* this.gameField.getHeight()));
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		this.startButton = new JButton();
		this.startButton.setText("start");
		this.startButton.setEnabled(true);
		this.startButton.addActionListener(this.snakeGameController
				.getStartActionListener());
		controlPanel.add(this.startButton);
		this.stopButton = new JButton();
		this.stopButton.setText("stop");
		this.stopButton.setEnabled(false);
		this.stopButton.addActionListener(this.snakeGameController
				.getStopActionListener());
		controlPanel.add(this.stopButton);
		this.scoreField = new JTextField();
		this.scoreField.setPreferredSize(SCORE_FIELD_SIZE);
		this.scoreField.setFont(new Font("Default", Font.BOLD,
				SCORE_FIELD_FONT_SIZE));
		this.scoreField.setEditable(false);
		controlPanel.add(this.scoreField);
		this.pauseButton = new JButton();
		this.pauseButton.addActionListener(snakeGameController
				.getPauseActionListener());
		this.pauseButton.setText("pause");
		this.pauseButton.setEnabled(false);
		controlPanel.add(this.pauseButton);
		this.add(controlPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(this.gameFieldPanel);
		this.add(scrollPane, BorderLayout.CENTER);
		this.gameFieldPanel.addMouseListener(this.snakeGameController
				.getMouseClickListener());
	}

	/**
	 * Called on model update.
	 */
	public void update(Observable o, Object arg) {
		this.previousGameState = this.currentGameState;
		this.currentGameState = (SnakeGameState) arg;
		switch (this.currentGameState.getRunState()) {
		case START:
		case STARTED:
			this.startButton.setEnabled(false);
			this.stopButton.setEnabled(true);
			this.pauseButton.setEnabled(true);
			break;
		case STOP:
		case STOPPED:
			this.startButton.setEnabled(true);
			this.stopButton.setEnabled(false);
			this.pauseButton.setEnabled(false);
			break;
		case PAUSED:
			this.startButton.setEnabled(true);
			this.stopButton.setEnabled(true);
			this.pauseButton.setEnabled(false);
			break;
		default:
			break;
		}
//		 this.scoreField.setText(String
//		 .valueOf(this.currentGameState.getScore()));
		 Set<BodyPart> previousBodyParts = new HashSet<BodyPart>();
		 if (this.previousGameState != null) {
			 previousBodyParts = this.previousGameState.getAnimalBodyParts();
		 }
		Set<BodyPart> currentBodyParts =
				this.currentGameState.getAnimalBodyParts();
		List<BodyPart> statesDifference = new LinkedList<BodyPart>();
		if (this.previousGameState != null) {
			if (this.currentGameState.getRunState() == RunState.STARTED) {
				Set<BodyPart> addedBodyParts =
						new HashSet<BodyPart>(currentBodyParts);
				addedBodyParts.removeAll(previousBodyParts);
				Set<BodyPart> removedBodyParts =
						new HashSet<BodyPart>(previousBodyParts);
				removedBodyParts.removeAll(currentBodyParts);
				Set<BodyPart> modifiedRemovedBodyParts =
						new HashSet<BodyPart>();
				for (BodyPart removedBodyPart : removedBodyParts) {
					modifiedRemovedBodyParts
							.add(new BodyPart(removedBodyPart.getX(),
									removedBodyPart.getY(), null, false, false));
				}
				statesDifference.addAll(modifiedRemovedBodyParts);
				statesDifference.addAll(addedBodyParts);
				this.gameFieldPanel.paintGameField(statesDifference, false);
			} else if (currentGameState.getRunState() == RunState.STOP) {
				this.gameFieldPanel.paintGameField(new LinkedList<BodyPart>(
						currentBodyParts), true);
			} else if (currentGameState.getRunState() == RunState.START) {
				this.gameFieldPanel.paintGameField(new LinkedList<BodyPart>(
						currentBodyParts), true);
			}
		} else {
			this.gameFieldPanel.paintGameField(new LinkedList<BodyPart>(
					currentBodyParts), true);
		}
		repaint(1000);
	}

	/**
	 * Gets current game state.
	 * 
	 * @return the current game state
	 */
	public SnakeGameState getCurrentGameState() {
		return this.currentGameState;
	}

	/**
	 * Gets controller.
	 * 
	 * @return the snake game controller
	 */
	public SnakeGameController getSnakeGameController() {
		return this.snakeGameController;
	}

	/**
	 * Gets game field panel.
	 * 
	 * @return the game field panel
	 */
	public GameFieldPanel getGameFieldPanel() {
		return this.gameFieldPanel;
	}

	/**
	 * Gets game field.
	 * 
	 * @return the game field
	 */
	public GameField getGameField() {
		return this.gameField;
	}
}