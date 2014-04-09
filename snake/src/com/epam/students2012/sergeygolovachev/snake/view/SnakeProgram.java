package com.epam.students2012.sergeygolovachev.snake.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameModel;

/**
 * Snake program.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class SnakeProgram {
	// the frame width
	private static final int FRAME_WIDTH = 450;
	// the frame height
	private static final int FRAME_HEIGHT = 500;

	/**
	 * Program entry point.
	 * 
	 * @param args the command line parameters: args[0] - the game field height,
	 *            args[1] - the game field width, args[2] - the snake's length,
	 *            args[3] - the number of frogs, args[4] - the snake's movement
	 *            interval
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws IllegalArgumentException,
			SecurityException, IllegalAccessException {
		SnakeProgramParameters parameters = new SnakeProgramParameters();
		parameters = SnakeProgramParametersChecker.CheckParameters(args);
		JFrame snakeFrame = new JFrame();
		SnakeGameModel snakeGameModel = new SnakeGameModel();
		snakeGameModel =
				new SnakeGameModel(parameters.getGameFieldWidth(), parameters
						.getGameFieldHeight(), parameters.getSnakeLength(),
						parameters.getNumberOfFrogs(), parameters
								.getSnakeMovementInterval());
		SnakeGameView snakeGameView = new SnakeGameView(snakeGameModel);
		snakeGameModel.addObserver(snakeGameView);
		snakeFrame.setLayout(new BorderLayout());
		snakeFrame.add(snakeGameView, BorderLayout.CENTER);
		snakeFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		snakeFrame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		snakeFrame.setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		snakeFrame.getRootPane().setDoubleBuffered(true);
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeFrame.setVisible(true);
	}
}