package com.epam.students2012.sergeygolovachev.snake.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Frog. Abstract. Parent of all frogs.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public abstract class Frog extends Animal {

	/**
	 * Probability of smart move (away from snake's head).
	 */
	public final static double SMART_MOVE_PROBABILITY = 0.8;

	/**
	 * Constructor without arguments.
	 */
	public Frog() {
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
	public Frog(List<Location> startLocation, long movementInterval,
			SnakeGameModel snakeGameModel) throws IllegalArgumentException,
			SecurityException, InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		super(startLocation, movementInterval, snakeGameModel);
	}

	/**
	 * Moves frog.
	 */
	public void move() {
		final int MAX_INDEX_DEFAULT = -1;
		ArrayList<Animal.MovementDirection> possibleMovementDirections =
				new ArrayList<MovementDirection>();
		ArrayList<Location> possibleMovementLocations =
				new ArrayList<Location>();
		Location frogLocation = getBody().iterator().next();
		Location upLocation = new Location(frogLocation);
		upLocation.setY(upLocation.getY() - 1);
		if (getSnakeGameModel().getGameField().getAnimals().get(upLocation) == null) {
			possibleMovementDirections.add(Animal.MovementDirection.UP);
			possibleMovementLocations.add(upLocation);
		}
		Location leftLocation = new Location(frogLocation);
		leftLocation.setX(leftLocation.getX() - 1);
		if (getSnakeGameModel().getGameField().getAnimals().get(leftLocation) == null) {
			possibleMovementDirections.add(Animal.MovementDirection.LEFT);
			possibleMovementLocations.add(leftLocation);
		}
		Location downLocation = new Location(frogLocation);
		downLocation.setY(downLocation.getY() + 1);
		if (getSnakeGameModel().getGameField().getAnimals().get(downLocation) == null) {
			possibleMovementDirections.add(Animal.MovementDirection.DOWN);
			possibleMovementLocations.add(downLocation);
		}
		Location rightLocation = new Location(frogLocation);
		rightLocation.setX(rightLocation.getX() + 1);
		if (getSnakeGameModel().getGameField().getAnimals().get(rightLocation) == null) {
			possibleMovementDirections.add(Animal.MovementDirection.RIGHT);
			possibleMovementLocations.add(rightLocation);
		}
		if (possibleMovementDirections.size() != 0) {
			double maxDistance = 0;
			int maxIndex = MAX_INDEX_DEFAULT;
			for (int i = 0; i < possibleMovementLocations.size(); i++) {
				Location l = possibleMovementLocations.get(i);
				if (getSnakeGameModel().getSnake().getHead().distance(l) > maxDistance) {
					maxDistance =
							getSnakeGameModel().getSnake().getHead()
									.distance(l);
					maxIndex = i;
				}
			}
			if (maxIndex != MAX_INDEX_DEFAULT) {
				Location smartMove = possibleMovementLocations.get(maxIndex);
				possibleMovementLocations.remove(maxIndex);
				getSnakeGameModel().getGameField().removeAnimalPart(
						frogLocation);
				if (possibleMovementLocations.size() == 0) {
					frogLocation = new Location(smartMove);
				} else {
					Random smartMoveRandom = new Random();
					if (smartMoveRandom.nextDouble() < SMART_MOVE_PROBABILITY) {
						frogLocation = new Location(smartMove);
					} else {
						Random r = new Random();
						Location nextMove =
								possibleMovementLocations.get(r
										.nextInt(possibleMovementLocations
												.size()));
						frogLocation = new Location(nextMove);
					}
				}
				getSnakeGameModel().getGameField().addAnimalPart(frogLocation,
						this);
				getBody().remove(getBody().iterator().next());
				getBody().add(frogLocation);
			}
		}
	}

	/**
	 * Abstract method, whose implementation handles snake eating frog.
	 */
	public abstract void beEaten();
}
