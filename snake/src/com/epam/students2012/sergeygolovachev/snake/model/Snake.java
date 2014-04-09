package com.epam.students2012.sergeygolovachev.snake.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Snake, which is controlled by user.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class Snake extends Animal {

	/**
	 * Snake's turn direction enumeration.
	 * 
	 * @author Sergey_Golovachev
	 * @version 1.00
	 * 
	 */
	public static enum TurnDirection {
		NO, LEFT, RIGHT
	};

	// the index of snake's tail
	private final static int TAIL_INDEX = 0;
	// the direction of snake's movement
	private MovementDirection movementDirection;
	// the direction of next turn
	private TurnDirection nextTurn;

	/**
	 * Constructor with no arguments.
	 */
	public Snake() {
		this.movementDirection = Animal.MovementDirection.RIGHT;
		this.nextTurn = Snake.TurnDirection.NO;
	}

	/**
	 * Constructor with specifies snake's start location.
	 * 
	 * @param startLocation the snake's starting location
	 */
	public Snake(List<Location> startLocation, long movementInterval,
			SnakeGameModel snakeGameModel,
			Animal.MovementDirection movementDirection)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		super(startLocation, movementInterval, snakeGameModel);
		this.movementDirection = movementDirection;
		this.nextTurn = Snake.TurnDirection.NO;

	}

	/**
	 * Gets location of snake's head on next move.
	 * 
	 * @return the location of snake's head on next move
	 */
	private Location nextHeadCoordinates() {
		int x = 0;
		int y = 0;
		MovementDirection md =
				changeMovementDirection(this.movementDirection, this.nextTurn);
		switch (md) {
		case UP:
			x = getBody().get(getBody().size() - 1).getX();
			y = getBody().get(getBody().size() - 1).getY() - 1;
			break;
		case DOWN:
			x = getBody().get(getBody().size() - 1).getX();
			y = getBody().get(getBody().size() - 1).getY() + 1;
			break;
		case RIGHT:
			x = getBody().get(getBody().size() - 1).getX() + 1;
			y = getBody().get(getBody().size() - 1).getY();
			break;
		case LEFT:
			x = getBody().get(getBody().size() - 1).getX() - 1;
			y = getBody().get(getBody().size() - 1).getY();
			break;
		default:
			break;
		}
		Location l =
				Location.getLocation(getSnakeGameModel().getGameField(), x, y);
		return l;
	}

	/**
	 * Computes new movement direction based on old movement direction and turn
	 * direction.
	 * 
	 * @param md the old movement direction
	 * @param td the turn direction
	 * @return the new movement direction
	 */
	private MovementDirection changeMovementDirection(MovementDirection md,
			TurnDirection td) {
		MovementDirection resultMovementDirection = md;
		switch (td) {
		case RIGHT:
			switch (md) {
			case UP:
				resultMovementDirection = Animal.MovementDirection.RIGHT;
				break;
			case RIGHT:
				resultMovementDirection = Animal.MovementDirection.DOWN;
				break;
			case DOWN:
				resultMovementDirection = Animal.MovementDirection.LEFT;
				break;
			case LEFT:
				resultMovementDirection = Animal.MovementDirection.UP;
				break;
			default:
				break;
			}
			break;
		case LEFT:
			switch (md) {
			case UP:
				resultMovementDirection = Animal.MovementDirection.LEFT;
				break;
			case LEFT:
				resultMovementDirection = Animal.MovementDirection.DOWN;
				break;
			case DOWN:
				resultMovementDirection = Animal.MovementDirection.RIGHT;
				break;
			case RIGHT:
				resultMovementDirection = Animal.MovementDirection.UP;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return resultMovementDirection;
	}

	/**
	 * Gets snake's body.
	 */
	public List<Location> getBody() {
		return (List<Location>) super.getBody();
	}

	/**
	 * Moves snake.
	 */
	public void move() {
		Location newHead = new Location();
		if (nextHeadCoordinates() != null) {
			newHead = new Location(nextHeadCoordinates());
			this.movementDirection =
					changeMovementDirection(this.movementDirection,
							this.nextTurn);
		} else {
			getSnakeGameModel().stop();
		}
		Animal nextCellAnimal =
				getSnakeGameModel().getGameField().getAnimals().get(newHead);
		if (newHead.equals(getTail())) {
			removeTail();
			addHead(newHead);
		} else if (nextCellAnimal instanceof Snake) {
			getSnakeGameModel().stop();
		} else if (nextCellAnimal instanceof Frog) {
			((Frog) nextCellAnimal).beEaten();
		} else {
			addHead(newHead);
			if (!getBody().get(TAIL_INDEX).equals(
					getBody().get(getBody().size() - 1))) {
				removeTail();
			}
		}
		this.nextTurn = Snake.TurnDirection.NO;
	}

	/**
	 * Tells snake to turn left on next move.
	 */
	public void turnLeft() {
		this.nextTurn = Snake.TurnDirection.LEFT;
	}

	/**
	 * Tells snake to turn right on next move.
	 */
	public void turnRight() {
		this.nextTurn = Snake.TurnDirection.RIGHT;
	}

	/**
	 * Removes snake's tail.
	 */
	public void removeTail() {
		getSnakeGameModel().getGameField().removeAnimalPart(
				getBody().get(TAIL_INDEX));
		getBody().remove(TAIL_INDEX);
	}

	/**
	 * Adds snake's head.
	 */
	public void addHead(Location l) {
		getBody().add(l);
		getSnakeGameModel().getGameField().addAnimalPart(l, this);
	}

	/**
	 * Gets snake's head location.
	 * 
	 * @return the snake's head location
	 */
	public Location getHead() {
		return getBody().get(getBody().size() - 1);
	}

	/**
	 * Gets snake's tail location.
	 * 
	 * @return the snake's tail location
	 */
	public Location getTail() {
		return getBody().get(TAIL_INDEX);
	}
}