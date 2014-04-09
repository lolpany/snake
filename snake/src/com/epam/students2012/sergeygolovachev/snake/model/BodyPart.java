package com.epam.students2012.sergeygolovachev.snake.model;

public class BodyPart {
	// shift value of x coordinate for hashCode method
	private final static int X_VALUE_HASH_SHIFT = 2 << 10;
	private int x;
	private int y;
	private Class<?> animalClass;
	private boolean tail;
	private boolean head;

	public BodyPart() {
		this.x = 0;
		this.y = 0;
		this.animalClass = null;
		this.tail = false;
		this.head = false;
	}

	public BodyPart(int x, int y, Class<?> animalClass, boolean tail,
			boolean head) {
		this.x = x;
		this.y = y;
		this.animalClass = animalClass;
		this.tail = tail;
		this.head = head;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Class<?> getAnimalClass() {
		return this.animalClass;
	}

	public boolean isTail() {
		return tail;
	}

	public boolean isHead() {
		return head;
	}

	public int hashCode() {
		return (this.x << X_VALUE_HASH_SHIFT + this.y);
	}

	public boolean equals(Object o) {
		if (!(o instanceof BodyPart)) {
			return false;
		} else {
			return ((this.x == ((BodyPart) o).x)
					&& (this.y == ((BodyPart) o).y)
					&& ((this.animalClass == ((BodyPart) o).animalClass))
					&& (this.tail == ((BodyPart) o).tail) && (this.head == ((BodyPart) o).head));
		}
	}
}
