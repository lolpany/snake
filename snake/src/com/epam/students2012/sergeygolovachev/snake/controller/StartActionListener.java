package com.epam.students2012.sergeygolovachev.snake.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Start button listener.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class StartActionListener implements ActionListener {
	// the controller reference
	private SnakeGameController snakeGameController;

	/**
	 * Constructor.
	 * 
	 * @param snakeGameController the controller reference
	 */
	public StartActionListener(SnakeGameController snakeGameController) {
		this.snakeGameController = snakeGameController;
	}

	/**
	 * Action handler. Starts game.
	 * 
	 * @param arg0 the action event
	 */
	public void actionPerformed(ActionEvent arg0) {
		this.snakeGameController.startGame();
	}
}
