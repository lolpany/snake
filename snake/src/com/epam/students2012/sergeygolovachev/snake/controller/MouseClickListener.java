package com.epam.students2012.sergeygolovachev.snake.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse click listener for snake game field.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class MouseClickListener implements MouseListener {
	// controller reference
	private SnakeGameController snakeGameController;

	/**
	 * Constructor.
	 * 
	 * @param snakeGameController the controller reference
	 */
	public MouseClickListener(SnakeGameController snakeGameController) {
		this.snakeGameController = snakeGameController;
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Mouse pressed handler. Turns snake.
	 */
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			snakeGameController.turnSnakeLeft();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			snakeGameController.turnSnakeRight();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void mouseReleased(MouseEvent e) {
	}
}
