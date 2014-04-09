package com.epam.students2012.sergeygolovachev.snake.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameModel.RunState;

/**
 * Abstract class - root of hierarchy of ingame moving characters. It implements
 * Runnable interface, so every animal's movement cycle runs as different
 * thread.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public abstract class Animal implements Runnable {
	/**
	 * Snake's movement direction enumeration.
	 * 
	 * @author Sergey_Golovachev
	 * @version 1.00
	 * 
	 */
	public static enum MovementDirection {
		UP, RIGHT, DOWN, LEFT
	};

	/**
	 * Default interval between two moves of animal.
	 */
	public final static long DEFAULT_MOVEMENT_INTERVAL = 500;
	// the set representing animal's body
	private Collection<Location> body;
	// the time interval between two moves
	private long movementInterval;
	// the boolean value which is true if animal is dead
	private boolean dead;
	// the model reference
	private SnakeGameModel snakeGameModel;

	/**
	 * Constructor with no arguments.
	 */
	public Animal() {
		this.body = null;
		this.dead = false;
		this.movementInterval = Animal.DEFAULT_MOVEMENT_INTERVAL;
		this.snakeGameModel = null;
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
	public Animal(Collection<Location> startLocation, long movementInterval,
			SnakeGameModel snakeGameModel) throws IllegalArgumentException,
			SecurityException, InvocationTargetException, NoSuchMethodException {
		try {
			setBody(startLocation);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.body.addAll(startLocation);
		this.movementInterval = movementInterval;
		this.dead = false;
		this.snakeGameModel = snakeGameModel;
		for (Location bodyPart : this.body) {
			getSnakeGameModel().getGameField().addAnimalPart(bodyPart, this);
		}
	}

	@SuppressWarnings("unchecked")
	private void setBody(Collection<Location> body)
			throws InstantiationException, IllegalAccessException {
		Class<? extends Collection> x = body.getClass();
		this.body = x.newInstance();
	}

	/**
	 * Gets animal's body.
	 * 
	 * @return animal's body collection
	 */
	public Collection<Location> getBody() {
		return body;
	}

	// static Object o = new Object();
	// static int i = 0;

	/**
	 * Main animal movement cycle.
	 */
	public void run() {
		// synchronized (o) {
		// i++;
		// System.out.println(i);
		// }

		while (!this.dead) {
			try {
				synchronized (this.snakeGameModel.getGameField()) {
					if ((this.snakeGameModel.getRunState() != RunState.PAUSED
							&& this.snakeGameModel.getRunState() != RunState.STOP && this.snakeGameModel
							.getRunState() != RunState.STOPPED)
							&& this.dead == false) {
						move();
						this.getSnakeGameModel().updated();
					}
				}
				Thread.sleep(this.movementInterval);
			} catch (InterruptedException e) {
				die();
			}
		}
		// System.out.println("i'm dead!");
		// synchronized (o) {
		// i--;
		// System.out.println(i);
		// }
	}

	/**
	 * Abstract method, whose implementations must move corresponding animal.
	 */
	public abstract void move();

	/**
	 * Method that marks animal as dead.
	 */
	public void die() {
		this.dead = true;
	}

	/**
	 * Gets snake's game model.
	 * 
	 * @return the snake's game model
	 */
	public SnakeGameModel getSnakeGameModel() {
		return this.snakeGameModel;
	}

	/**
	 * Checks if animal is dead.
	 * 
	 * @return true if animal is dead, false otherwise
	 */
	public boolean isDead() {
		return this.dead;
	}
}