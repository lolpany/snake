package com.epam.students2012.sergeygolovachev.snake.view;

/**
 * Snake's program parameters.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class SnakeProgramParameters {
	// the game field width
	private int gameFieldWidth;
	// the game field height
	private int gameFieldHeight;
	// the snake's length
	private int snakeLength;
	// the number of frogs
	private int numberOfFrogs;
	// the snake's movement interval
	private long snakeMovementInterval;

	/**
	 * Constructor without parameters.
	 */
	public SnakeProgramParameters() {
		this.gameFieldWidth = 0;
		this.gameFieldHeight = 0;
		this.snakeLength = 0;
		this.numberOfFrogs = 0;
		this.snakeMovementInterval = 0;
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param gameFieldWidth the game field width
	 * @param gameFieldHeight the game field height
	 * @param snakeLength the snake's length
	 * @param numberOfFrogs the number of frogs
	 * @param snakeMovementInterval the snake's movement interval
	 */
	public SnakeProgramParameters(int gameFieldWidth, int gameFieldHeight,
			int snakeLength, int numberOfFrogs, long snakeMovementInterval) {
		this.gameFieldWidth = gameFieldWidth;
		this.gameFieldHeight = gameFieldHeight;
		this.snakeLength = snakeLength;
		this.numberOfFrogs = numberOfFrogs;
		this.snakeMovementInterval = snakeMovementInterval;
	}

	/**
	 * Gets game field width.
	 * 
	 * @return the game field width
	 */
	public int getGameFieldWidth() {
		return this.gameFieldWidth;
	}

	/**
	 * Gets game field height.
	 * 
	 * @return the game field height
	 */
	public int getGameFieldHeight() {
		return this.gameFieldHeight;
	}

	/**
	 * Gets snake's length.
	 * 
	 * @return the snake's length
	 */
	public int getSnakeLength() {
		return this.snakeLength;
	}

	/**
	 * Gets number of frogs.
	 * 
	 * @return the number of frogs
	 */
	public int getNumberOfFrogs() {
		return this.numberOfFrogs;
	}

	/**
	 * Gets snake's movement interval.
	 * 
	 * @return
	 */
	public long getSnakeMovementInterval() {
		return this.snakeMovementInterval;
	}
}
