package com.epam.students2012.sergeygolovachev.snake.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents game playing field - m*n matrix
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 */
public class GameField {
	// playing field height
	private int height;
	// playing field width
	private int width;
	// the animal's body parts map
	private Map<Location, Animal> animals;
	// the free locations list
	private List<Location> freeLocations;
	private Snake snake;

	/**
	 * Constructor without parameters.
	 */
	public GameField() {
		this.height = 0;
		this.width = 0;
		this.animals = null;
		this.freeLocations = new LinkedList<Location>();
	}

	public GameField(GameField gameField) {
		this.height = gameField.height;
		this.width = gameField.width;
		this.animals = new HashMap<Location, Animal>(gameField.getAnimals());
		this.freeLocations =
				new LinkedList<Location>(gameField.getFreeLocations());
	}

	/**
	 * Constructor with parameters which specify field size.
	 * 
	 * @param height the field height. Must be more then 0.
	 * @param width the field width. Must be more then 0.
	 */
	public GameField(int height, int width) {
		this.height = height;
		this.width = width;
		this.animals = new HashMap<Location, Animal>();
		this.freeLocations = new LinkedList<Location>();
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				Location l = new Location(this.width, this.height);
				l.setX(i);
				l.setY(j);
				this.freeLocations.add(l);
			}
		}
	}

	/**
	 * Gets field height.
	 * 
	 * @return the field height
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Sets field height.
	 * 
	 * @param height the field height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets field width.
	 * 
	 * @return the field width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Sets field width.
	 * 
	 * @param width the field width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	public Map<Location, Animal> getAnimals() {
		return this.animals;
	}

	public synchronized Map<Location, Animal> cloneAnimals() {
		return (new HashMap<Location, Animal>(this.animals));
	}

	public synchronized void addAnimalPart(Location l, Animal a) {
		this.animals.put(l, a);
		this.freeLocations.remove(l);
	}

	public synchronized void removeAnimalPart(Location l) {
		this.animals.remove(l);
		this.freeLocations.add(l);
	}

	public int getFreeLocationsNumber() {
		return this.freeLocations.size();
	}

	public synchronized List<Location> getFreeLocations() {
		return this.freeLocations;
	}
}