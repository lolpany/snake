package com.epam.students2012.sergeygolovachev.snake.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Stop button listener.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class StopActionListener implements ActionListener {
	// the controller reference
	private SnakeGameController snakeGameController;

	/**
	 * Constructor.
	 * 
	 * @param snakeGameController the controller reference
	 */
	public StopActionListener(SnakeGameController snakeGameController) {
		this.snakeGameController = snakeGameController;
	}

	/**
	 * Action handler. Stops game.
	 * 
	 * @param arg0 the action event
	 */
	public void actionPerformed(ActionEvent arg0) {
		this.snakeGameController.stopGame();
	}
}
