package com.epam.students2012.sergeygolovachev.snake.controller;

import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameModel;

/**
 * Snake game controller.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class SnakeGameController {
	// the model reference
	private SnakeGameModel snakeGameModel;
	// the mouse click listener
	private MouseClickListener mouseClickListener;
	// the start action listener
	private StartActionListener startActionListener;
	// the stop action listener
	private StopActionListener stopActionListener;
	// the pause action listener
	private PauseActionListener pauseActionListener;

	/**
	 * Constructor without parameters.
	 */
	public SnakeGameController() {
		this.snakeGameModel = null;
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param snakeGameModel the model reference
	 */
	public SnakeGameController(SnakeGameModel snakeGameModel) {
		this.snakeGameModel = snakeGameModel;
		this.mouseClickListener = new MouseClickListener(this);
		this.startActionListener = new StartActionListener(this);
		this.stopActionListener = new StopActionListener(this);
		this.pauseActionListener = new PauseActionListener(this);
	}

	/**
	 * Starts game.
	 */
	public void startGame() {
		snakeGameModel.start();
	}

	/**
	 * Stops game.
	 */
	public void stopGame() {
		snakeGameModel.stop();
	}

	/**
	 * Pauses game.
	 */
	public void pauseGame() {
		snakeGameModel.pause();
	}

	/**
	 * Turns snake left.
	 */
	public void turnSnakeLeft() {
		snakeGameModel.turnSnakeLeft();
	}

	/**
	 * Turns snake right.
	 */
	public void turnSnakeRight() {
		snakeGameModel.turnSnakeRight();
	}

	/**
	 * Gets mouse click listener.
	 * 
	 * @return the mouse click listener
	 */
	public MouseClickListener getMouseClickListener() {
		return this.mouseClickListener;
	}

	/**
	 * Gets start button action listener
	 * 
	 * @return the start button action listener
	 */
	public StartActionListener getStartActionListener() {
		return this.startActionListener;
	}

	/**
	 * Gets stop button action listener.
	 * 
	 * @return the stop button action listener.
	 */
	public StopActionListener getStopActionListener() {
		return this.stopActionListener;
	}

	/**
	 * Gets pause button action listener.
	 * 
	 * @return the pause button action listener
	 */
	public PauseActionListener getPauseActionListener() {
		return this.pauseActionListener;
	}
}
