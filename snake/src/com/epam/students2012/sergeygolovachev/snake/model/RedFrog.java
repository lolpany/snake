package com.epam.students2012.sergeygolovachev.snake.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Red frog.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class RedFrog extends Frog {
	private final static int RED_FROG_SCORE_VALUE = 2;

	/**
	 * Constructor without arguments.
	 */
	public RedFrog() {
	}

	/**
	 * Constructor with specified parameters.
	 * 
	 * @param startLocation the start location of animal body
	 * @param movementInterval the animal's movement interval
	 * @param gameField the game field reference
	 * @param snakeGameModel the model reference
	 * @param animals the animal's body parts map reference
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public RedFrog(List<Location> startLocation, long movementInterval,
			SnakeGameModel snakeGameModel) throws IllegalArgumentException,
			SecurityException, InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		super(startLocation, movementInterval, snakeGameModel);
	}

	/**
	 * Handles snake eating red frog.
	 */
	public void beEaten() {
		getSnakeGameModel().setScore(
				getSnakeGameModel().getScore() + RED_FROG_SCORE_VALUE);
		die();
		getSnakeGameModel().getSnake().addHead(getBody().iterator().next());
		getSnakeGameModel().getSnake().removeTail();
		getSnakeGameModel().getSnake().removeTail();
		getSnakeGameModel().generateFrog(true);
	}
}
