package com.epam.students2012.sergeygolovachev.snake.model;

/**
 * Element location on game field.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class Location {
	/**
	 * Separator of coordinates for toString method.
	 */
	private final static String SEPARATOR = ";";
	// shift value of x coordinate for hashCode method
	private final static int X_VALUE_HASH_SHIFT = 2 << 10;
	// the x coordinate
	private int x;
	// the y coordinate
	private int y;

	private int gameFieldWidth;
	private int gameFieldHeight;

	/**
	 * Constructor with no arguments.
	 */
	public Location() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Constructor which creates location from game field.
	 * 
	 * @param gameField the game field
	 */
	public Location(int gameFieldWidth, int gameFieldHeight) {
		this.gameFieldHeight = gameFieldHeight;
		this.gameFieldWidth = gameFieldWidth;
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Constructor with arguments.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	private Location(int gameFieldWidth, int gameFieldHeight, int x, int y) {
		this.gameFieldHeight = gameFieldHeight;
		this.gameFieldWidth = gameFieldWidth;
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor that creates copy of specified location.
	 * 
	 * @param l the location to copy
	 */
	public Location(Location l) {
		this.gameFieldHeight = l.getGameFieldHeight();
		this.gameFieldWidth = l.getGameFieldWidth();
		this.x = l.getX();
		this.y = l.getY();
	}

	/**
	 * Factory method, that returns location or null if location can't be
	 * created.
	 * 
	 * @param gameField the game fieldD
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the created location
	 */
	public static Location getLocation(GameField gameField, int x, int y) {
		Location l =
				new Location(gameField.getHeight(), gameField.getWidth(),
						(gameField.getWidth() + x) % gameField.getWidth(),
						(gameField.getHeight() + y) % gameField.getHeight());
		return l;
	}

	/**
	 * Gets x coordinate.
	 * 
	 * @return the x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets the x coordinate.
	 * 
	 * @param x the x coordinate
	 */
	public void setX(int x) {
		this.x = (this.gameFieldWidth + x) % this.gameFieldWidth;
	}

	/**
	 * Gets the y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the y coordinate.
	 * 
	 * @param y the y coordinate
	 */
	public void setY(int y) {
		this.y = (this.gameFieldHeight + y) % this.gameFieldHeight;
	}

	/**
	 * Converts location to string.
	 * 
	 * @return the concatenation of x and y coordinate, with separator in
	 *         between them.
	 */
	public String toString() {
		return Integer.toString(this.x) + Location.SEPARATOR
				+ Integer.toString(this.y);
	}

	public int hashCode() {
		/**
		 * {@inheritDoc}
		 */
		final int SHIFT = X_VALUE_HASH_SHIFT;
		return (this.x << SHIFT + this.y);
	}

	public boolean equals(Object other) {
		/**
		 * {@inheritDoc}
		 */
		if (!(other instanceof Location))
			return false;
		return ((this.x == ((Location) other).x) && (this.y == ((Location) other).y));
	}

	public int getGameFieldHeight() {
		return this.gameFieldHeight;
	}

	public int getGameFieldWidth() {
		return this.gameFieldWidth;
	}

	/**
	 * Computes distance to other location.
	 * 
	 * @param l the other location
	 * @return the distance
	 */
	public Double distance(Location l) {
		double xDiff = this.getX() - l.getX();
		double yDiff = this.getY() - l.getY();
		return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
}