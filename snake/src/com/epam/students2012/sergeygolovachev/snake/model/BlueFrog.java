package com.epam.students2012.sergeygolovachev.snake.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Blue frog.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class BlueFrog extends Frog {
	/**
	 * Constructor with no arguments.
	 */
	public BlueFrog() {
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
	public BlueFrog(List<Location> startLocation, long movementInterval,
			SnakeGameModel snakeGameModel) throws IllegalArgumentException,
			SecurityException, InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		super(startLocation, movementInterval, snakeGameModel);
	}

	/**
	 * Handles snake eating blue frog.
	 */
	public void beEaten() {
		getSnakeGameModel().stop();

	}
}
