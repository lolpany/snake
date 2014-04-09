package com.epam.students2012.sergeygolovachev.snake.view;

/**
 * Utility class for snake program parameters check.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public final class SnakeProgramParametersChecker {
	// the minimal field dimension size
	private final static int MIN_GAME_FIELD_DIMENSION = 3;
	// the minimal number of frogs
	private final static int MIN_NUMBER_OF_FROGS = 1;
	// the default snake's length
	private final static int DEFAULT_SNAKE_LENGTH = 5;
	// the minimal snake's movement interval
	private final static long MIN_SNAKE_MOVEMENT_INTERVAL = 200;
	// the maximal snake's movement interval
	private final static long MAX_SNAKE_MOVEMENT_INTERVAL = 10000;
	// new line string
	private final static String NEW_LINE = ".\n";
	// will be set to message string
	private final static String WILL_BE_SET_TO_MESSAGE = ". Will be set to ";
	// will be corrected to message string
	private final static String WILL_BE_CORRECTED_TO_MESSAGE =
			". Will be corrected to ";
	// not integer game field height parameter message string
	private final static String NOT_INTEGER_GAME_FIELD_HEIGHT_MESSAGE =
			"Game field height must be integer";
	// no game field height parameter message string
	private final static String NO_GAME_FIELD_HEIGHT_MESSAGE =
			"No game field height parameter";
	// out of bounds game field height parameter message string
	private final static String OUT_OF_BOUNDS_GAME_FIELD_HEIGHT_MESSAGE =
			"Game field height must be more or equal to ";
	// not integer game field width message string
	private final static String NOT_INTEGER_GAME_FIELD_WIDTH_MESSAGE =
			"Game field width must be integer";
	// no game field width parameter message string
	private final static String NO_GAME_FIELD_WIDTH_MESSAGE =
			"No game field width parameter";
	// out of bounds game field width parameter message string
	private final static String OUT_OF_BOUNDS_GAME_FIELD_WIDTH_MESSAGE =
			"Game field width must be more or equal to ";
	// not integer snake's length message string
	private final static String NOT_INTEGER_SNAKE_LENGTH_MESSAGE =
			"Snake's length must be integer";
	// no snake's length parameter message string
	private final static String NO_SNAKE_LENGTH_MESSAGE =
			"No snake's length parameter";
	// out of bounds snake's length parameter message string
	private final static String OUT_OF_BOUNDS_SNAKE_LENGTH_MESSAGE =
			"Snake's length must be less or equal to game field height ";
	// not integer number of frogs message string
	private final static String NOT_INTEGER_NUMBER_OF_FROGS_MESSAGE =
			"Number of frogs must be integer";
	// no number of frogs parameter message string
	private final static String NO_NUMBER_OF_FROGS_MESSAGE =
			"No number of frogs parameter";
	// less than minimum number of frogs parameter message string
	private final static String LESS_THAN_MIN_NUMBER_OF_FROGS_MESSAGE =
			"Number of frogs can't be less then ";
	// more than maximum number of frogs parameter message string
	private final static String MORE_THAN_MAX_NUMBER_OF_FROGS_MESSAGE =
			"Number of frogs can't be more then ";
	// not integer snake's movement interval message string
	private final static String NOT_INTEGER_SNAKE_MOVEMENT_INTERVAL_MESSAGE =
			"Snake's movement interval must be integer";
	// no snake's movement interval message string
	private final static String NO_SNAKE_MOVEMENT_INTERVAL_MESSAGE =
			"No snake's movement interval parameter";
	// less than minimum snake's movement interval message string
	private final static String LESS_THAN_MIN_SNAKE_MOVEMENT_INTERVAL_MESSAGE =
			"Snake's movement interval must be more or equal to ";
	// more than maximum snake's movement interval message string
	private final static String MORE_THAN_MAX_SNAKE_MOVEMENT_INTERVAL_MESSAGE =
			"Snake's movement interval must be less or equal to ";

	// private constructor
	private SnakeProgramParametersChecker() {
	}

	/**
	 * Checks snake program parameters.
	 * 
	 * @param arg the command line parameters array
	 * @return the correct snake game parameters
	 */
	public static SnakeProgramParameters CheckParameters(String[] arg) {
		String parameterExceptionReason = "";
		int gameFieldHeight = 0;
		int gameFieldWidth = 0;
		int snakeLength = 0;
		int numberOfFrogs = 0;
		long snakeMovementInterval = 0;
		try {
			gameFieldHeight = Integer.parseInt(arg[0]);
		} catch (NumberFormatException e) {
			parameterExceptionReason +=
					NOT_INTEGER_GAME_FIELD_HEIGHT_MESSAGE
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION + NEW_LINE;
			gameFieldHeight = MIN_GAME_FIELD_DIMENSION;
		} catch (ArrayIndexOutOfBoundsException e) {
			parameterExceptionReason +=
					NO_GAME_FIELD_HEIGHT_MESSAGE + WILL_BE_SET_TO_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION + NEW_LINE;
			gameFieldHeight = MIN_GAME_FIELD_DIMENSION;
		}
		if (gameFieldHeight < MIN_GAME_FIELD_DIMENSION) {
			gameFieldHeight = MIN_GAME_FIELD_DIMENSION;
			parameterExceptionReason +=
					OUT_OF_BOUNDS_GAME_FIELD_HEIGHT_MESSAGE
							+ +MIN_GAME_FIELD_DIMENSION
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION + NEW_LINE;
		}
		try {
			gameFieldWidth = Integer.parseInt(arg[1]);
		} catch (NumberFormatException e) {
			parameterExceptionReason +=
					NOT_INTEGER_GAME_FIELD_WIDTH_MESSAGE
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION + NEW_LINE;
			gameFieldWidth = MIN_GAME_FIELD_DIMENSION;
		} catch (ArrayIndexOutOfBoundsException e) {
			parameterExceptionReason +=
					NO_GAME_FIELD_WIDTH_MESSAGE + WILL_BE_SET_TO_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION + NEW_LINE;
			gameFieldWidth = MIN_GAME_FIELD_DIMENSION;
		}
		if (gameFieldWidth < MIN_GAME_FIELD_DIMENSION) {
			gameFieldWidth = MIN_GAME_FIELD_DIMENSION;
			parameterExceptionReason +=
					OUT_OF_BOUNDS_GAME_FIELD_WIDTH_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_GAME_FIELD_DIMENSION + NEW_LINE;
		}
		try {
			snakeLength = Integer.parseInt(arg[2]);
		} catch (NumberFormatException e) {
			parameterExceptionReason +=
					NOT_INTEGER_SNAKE_LENGTH_MESSAGE
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ DEFAULT_SNAKE_LENGTH + NEW_LINE;
			snakeLength = DEFAULT_SNAKE_LENGTH;
		} catch (ArrayIndexOutOfBoundsException e) {
			parameterExceptionReason +=
					NO_SNAKE_LENGTH_MESSAGE + WILL_BE_SET_TO_MESSAGE
							+ DEFAULT_SNAKE_LENGTH + NEW_LINE;
			snakeLength = DEFAULT_SNAKE_LENGTH;
		}
		if (snakeLength > gameFieldHeight) {
			snakeLength = gameFieldHeight;
			parameterExceptionReason +=
					OUT_OF_BOUNDS_SNAKE_LENGTH_MESSAGE + gameFieldHeight
							+ WILL_BE_CORRECTED_TO_MESSAGE + gameFieldHeight
							+ NEW_LINE;
		}

		try {
			numberOfFrogs = Integer.parseInt(arg[3]);
		} catch (NumberFormatException e) {
			parameterExceptionReason +=
					NOT_INTEGER_NUMBER_OF_FROGS_MESSAGE
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_NUMBER_OF_FROGS + NEW_LINE;
			numberOfFrogs = MIN_NUMBER_OF_FROGS;
		} catch (ArrayIndexOutOfBoundsException e) {
			parameterExceptionReason +=
					NO_NUMBER_OF_FROGS_MESSAGE + WILL_BE_SET_TO_MESSAGE
							+ MIN_NUMBER_OF_FROGS + NEW_LINE;
			numberOfFrogs = MIN_NUMBER_OF_FROGS;
		}
		int maxNumberOfFrogs = gameFieldHeight * gameFieldWidth - snakeLength;
		if (numberOfFrogs < MIN_NUMBER_OF_FROGS) {
			numberOfFrogs = MIN_NUMBER_OF_FROGS;
			parameterExceptionReason +=
					LESS_THAN_MIN_NUMBER_OF_FROGS_MESSAGE + MIN_NUMBER_OF_FROGS
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_NUMBER_OF_FROGS + NEW_LINE;
		}
		if (numberOfFrogs > maxNumberOfFrogs) {
			numberOfFrogs = maxNumberOfFrogs;
			parameterExceptionReason +=
					MORE_THAN_MAX_NUMBER_OF_FROGS_MESSAGE + maxNumberOfFrogs
							+ WILL_BE_CORRECTED_TO_MESSAGE + maxNumberOfFrogs
							+ NEW_LINE;
		}
		try {
			snakeMovementInterval = Integer.parseInt(arg[4]);
		} catch (NumberFormatException e) {
			parameterExceptionReason +=
					NOT_INTEGER_SNAKE_MOVEMENT_INTERVAL_MESSAGE
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_SNAKE_MOVEMENT_INTERVAL + NEW_LINE;
			snakeMovementInterval = MIN_SNAKE_MOVEMENT_INTERVAL;
		} catch (ArrayIndexOutOfBoundsException e) {
			parameterExceptionReason +=
					NO_SNAKE_MOVEMENT_INTERVAL_MESSAGE + WILL_BE_SET_TO_MESSAGE
							+ MIN_SNAKE_MOVEMENT_INTERVAL + NEW_LINE;
			snakeMovementInterval = MIN_SNAKE_MOVEMENT_INTERVAL;
		}
		if (snakeMovementInterval < MIN_SNAKE_MOVEMENT_INTERVAL) {
			snakeMovementInterval = MIN_SNAKE_MOVEMENT_INTERVAL;
			parameterExceptionReason +=
					LESS_THAN_MIN_SNAKE_MOVEMENT_INTERVAL_MESSAGE
							+ MIN_SNAKE_MOVEMENT_INTERVAL
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MIN_SNAKE_MOVEMENT_INTERVAL + NEW_LINE;
		}
		if (snakeMovementInterval > MAX_SNAKE_MOVEMENT_INTERVAL) {
			snakeMovementInterval = MAX_SNAKE_MOVEMENT_INTERVAL;
			parameterExceptionReason +=
					MORE_THAN_MAX_SNAKE_MOVEMENT_INTERVAL_MESSAGE
							+ MAX_SNAKE_MOVEMENT_INTERVAL
							+ WILL_BE_CORRECTED_TO_MESSAGE
							+ MAX_SNAKE_MOVEMENT_INTERVAL + NEW_LINE;
		}

		System.out.println(parameterExceptionReason);
		return new SnakeProgramParameters(gameFieldHeight, gameFieldWidth,
				snakeLength, numberOfFrogs, snakeMovementInterval);
	}
}
