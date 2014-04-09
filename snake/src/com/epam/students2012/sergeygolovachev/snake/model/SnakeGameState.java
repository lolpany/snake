package com.epam.students2012.sergeygolovachev.snake.model;

import java.util.HashSet;
import java.util.Set;

import com.epam.students2012.sergeygolovachev.snake.model.SnakeGameModel.RunState;

/**
 * Snake's game state.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class SnakeGameState {
	// the game field
	private Set<BodyPart> animalBodyParts;
	// the game's run state
	private RunState runState;
	// the score
	private int score;
	private int gameFieldHeight;
	private int gameFieldWidth;

	/**
	 * Constructor without parameters.
	 */
	public SnakeGameState() {
		this.animalBodyParts = new HashSet<BodyPart>();
		this.runState = RunState.STOPPED;
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param gameField the game field
	 * @param animals the animal's body parts map
	 * @param runState the game's run state
	 * @param score the score
	 */
	public SnakeGameState(GameField gameField, Snake snake, RunState runState,
			int score) {
		this.animalBodyParts = new HashSet<BodyPart>();
		for (Location bodyPartLocation : gameField.getAnimals().keySet()) {
			Animal currentAnimal = gameField.getAnimals().get(bodyPartLocation);
			BodyPart bodyPart;
			boolean tail = false;
			boolean head = false;
			if (currentAnimal instanceof Snake) {
				if (bodyPartLocation.equals(snake.getTail())) {
					tail = true;
				} else if (bodyPartLocation.equals(snake.getHead())) {
					head = true;
				}
			}
			bodyPart =
					new BodyPart(bodyPartLocation.getX(), bodyPartLocation
							.getY(), currentAnimal.getClass(), tail, head);
			this.animalBodyParts.add(bodyPart);
		}
		this.runState = runState;
		this.score = score;
		this.gameFieldHeight = gameField.getHeight();
		this.gameFieldWidth = gameField.getWidth();
	}

	/**
	 * Gets game field.
	 * 
	 * @return the game field
	 */
	public Set<BodyPart> getAnimalBodyParts() {
		return this.animalBodyParts;
	}

	/**
	 * Gets run state.
	 * 
	 * @return the game's run state
	 */
	public RunState getRunState() {
		return this.runState;
	}

	/**
	 * Gets score.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return this.score;
	}

	public int getGameFieldHeight() {
		return this.gameFieldHeight;
	}

	public int getGameFieldWidth() {
		return this.gameFieldWidth;
	}
}