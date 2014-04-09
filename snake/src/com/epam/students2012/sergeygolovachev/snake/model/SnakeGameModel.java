package com.epam.students2012.sergeygolovachev.snake.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

/**
 * Model of game "Snake".
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class SnakeGameModel extends Observable {
	/**
	 * Game run state enumeration.
	 * 
	 * @author Sergey_Golovachev
	 * @version 1.00
	 * 
	 */
	public static enum RunState {
		STOP, STOPPED, START, STARTED, PAUSED
	};

	/**
	 * Separator for keys of game states map.
	 */
	// public static String LOCATION_CLASS_SEPARATOR = "-";
	// the start score
	private final static int START_SCORE = 0;
	// the snake's start x coordinate
	private final static int SNAKE_START_X_COORDINATE = 0;
	// the snake's start y coordinate
	private final static int SNAKE_START_Y_COORDINATE = 0;
	// the probability of green frog generation
	private final static int greenFrogProbability = 100;
	// the probability of red frog generation
	private final static int redFrogProbability = 1;
	// the probability of blue frog generation
	private final static int blueFrogProbability = 1;
	// the default frog's movement interval
	private final static long DEFAULT_FROG_MOVEMENT_INTERVAL = 1000;
	// the snake's movement interval multiplier
	private final static int SNAKE_MOVEMENT_INTERVAL_MULTIPLIER = 3;
	// frog movement interval
	private final long frogMovementInterval;
	// the game score
	private int score;
	// the game field
	private GameField gameField;
	// the snake
	private Snake snake;
	// the game's run state
	private RunState runState;
	// the snake's length
	private int snakesLength;
	// the snake's movement interval
	private long snakesMovementInterval;
	// the number of frogs
	private int numberOfFrogs;

	/**
	 * Constructor without arguments.
	 */
	public SnakeGameModel() {
		this.frogMovementInterval = DEFAULT_FROG_MOVEMENT_INTERVAL;
		this.score = 0;
		this.gameField = null;
		this.runState = RunState.STOPPED;
	}

	/**
	 * Constructor with parameters.
	 * 
	 * @param gameFieldHeight the game field height
	 * @param gameFieldWidth the game field width
	 * @param snakesLength the snake's length
	 * @param numberOfFrogs the number of frogs
	 * @param snakeMovementInterval the snake's movement interval
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public SnakeGameModel(int gameFieldHeight, int gameFieldWidth,
			int snakesLength, int numberOfFrogs, long snakeMovementInterval)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException {
		this.runState = RunState.STOPPED;
		this.snakesLength = snakesLength;
		this.snakesMovementInterval = snakeMovementInterval;
		this.numberOfFrogs = numberOfFrogs;
		this.frogMovementInterval =
				snakeMovementInterval * SNAKE_MOVEMENT_INTERVAL_MULTIPLIER;
		this.gameField = new GameField(gameFieldHeight, gameFieldWidth);
	}

	/**
	 * Gets game field.
	 * 
	 * @return the game field
	 */
	public GameField getGameField() {
		return this.gameField;
	}

	/**
	 * Generates frog in random cell on game field.
	 * 
	 * @param started true to start generated frog
	 * @return the generated frog
	 */
	public Frog generateFrog(boolean started) {
		Frog generatedFrog = null;
		if (getGameField().getFreeLocationsNumber() != 0) {
			Random frogLocationRandom = new Random();
			Location frogLocation =
					new Location(getGameField().getFreeLocations().get(
							frogLocationRandom.nextInt(getGameField()
									.getFreeLocationsNumber())));

			Random frogType = new Random();
			List<Location> frogStartLocation = new LinkedList<Location>();
			frogStartLocation.add(frogLocation);
			int roll =
					frogType.nextInt(greenFrogProbability + redFrogProbability
							+ blueFrogProbability);
			try {
				if (roll < greenFrogProbability) {
					generatedFrog =
							new GreenFrog(frogStartLocation,
									this.frogMovementInterval, this);
				} else if ((roll >= greenFrogProbability)
						&& (roll < greenFrogProbability + redFrogProbability)) {
					generatedFrog =
							new RedFrog(frogStartLocation,
									this.frogMovementInterval, this);
				} else {
					generatedFrog =
							new BlueFrog(frogStartLocation,
									this.frogMovementInterval, this);
				}
			} catch (Exception e) {

			}
			if (started) {
				new Thread(generatedFrog).start();
			}
		}
		return generatedFrog;
	}

	/**
	 * Gets score.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets score.
	 * 
	 * @param score the score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Starts game.
	 */
	public void start() {
		switch (this.runState) {
		case PAUSED:
			this.runState = RunState.STARTED;
			break;
		case START:
			break;
		case STARTED:
			break;
		default:
			synchronized (this.gameField) {
				this.score = START_SCORE;
				this.gameField =
						new GameField(this.gameField.getHeight(),
								this.gameField.getWidth());
				try {
					List<Location> snakeStartLocation =
							new ArrayList<Location>();
					for (int i = 0; i < snakesLength; i++) {
						snakeStartLocation.add(Location.getLocation(
								this.gameField, SNAKE_START_X_COORDINATE + i,
								SNAKE_START_Y_COORDINATE));
					}
					this.snake =
							new Snake(snakeStartLocation,
									this.snakesMovementInterval, this,
									Animal.MovementDirection.RIGHT);
					ArrayList<Frog> frogs = new ArrayList<Frog>();
					for (int i = 0; i < numberOfFrogs; i++) {
						frogs.add(generateFrog(false));
					}
				} catch (Exception e) {
				}
				this.runState = RunState.START;
				updated();
				Set<Map.Entry<Location, Animal>> animals =
						new HashSet<Map.Entry<Location, Animal>>(getGameField()
								.getAnimals().entrySet());
				Thread snakeThread = new Thread(this.snake);
				snakeThread.start();
				for (Map.Entry<Location, Animal> animalsEntry : animals) {
					if (!(animalsEntry.getValue() instanceof Snake)) {
						Thread animalThread =
								new Thread(animalsEntry.getValue());
						animalThread.start();
					}
				}
			}
			this.runState = RunState.STARTED;
			updated();
			break;
		}
	}

	/**
	 * Stops game.
	 */
	public void stop() {
		switch (this.runState) {
		case STOP:
			break;
		case STOPPED:
			break;
		default:
			this.runState = RunState.STOP;
			updated();
			synchronized (this.gameField) {
				Collection<Animal> animals =
						getGameField().cloneAnimals().values();
				for (Animal animal : animals) {
					if (!animal.isDead()) {
						animal.die();
					}
				}
			}
			this.runState = RunState.STOPPED;
			updated();
		}
	}

	/**
	 * Method, which notifies observers of model.
	 */
	public void updated() {
		SnakeGameState snakeGameState = null;
		synchronized (this.gameField) {
			snakeGameState =
					new SnakeGameState(this.gameField, this.snake,
							this.runState, this.score);
		}
		setChanged();
		notifyObservers(snakeGameState);
	}

	/**
	 * Turns snake right.
	 */
	public void turnSnakeRight() {
		if (snake != null) {
			this.snake.turnRight();
		}
	}

	/**
	 * Turns snake left.
	 */
	public void turnSnakeLeft() {
		if (snake != null) {
			this.snake.turnLeft();
		}
	}

	/**
	 * Gets run state.
	 * 
	 * @return the run state
	 */
	public RunState getRunState() {
		return runState;
	}

	/**
	 * Gets snake.
	 * 
	 * @return the snake.
	 */
	public Snake getSnake() {
		return this.snake;
	}

	/**
	 * Pauses game.
	 */
	public void pause() {
		switch (this.runState) {
		case STOP:
			break;
		case STOPPED:
			break;
		default:
			this.runState = RunState.PAUSED;
			updated();
		}
	}
}