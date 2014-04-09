package com.epam.students2012.sergeygolovachev.snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import com.epam.students2012.sergeygolovachev.snake.model.BlueFrog;
import com.epam.students2012.sergeygolovachev.snake.model.BodyPart;
import com.epam.students2012.sergeygolovachev.snake.model.GreenFrog;
import com.epam.students2012.sergeygolovachev.snake.model.RedFrog;
import com.epam.students2012.sergeygolovachev.snake.model.Snake;

/**
 * Panel on which game field is drawn.
 * 
 * @author Sergey_Golovachev
 * @version 1.00
 * 
 */
public class GameFieldPanel extends JPanel {
	// snake's head diameter;
	public static final int SNAKE_HEAD_SIZE = SnakeGameView.CELL_SIZE;
	// snake's body diameter;
	public static final int SNAKE_BODY_SIZE =
			(int) Math.round(SnakeGameView.CELL_SIZE / 1.5);
	// snake's tail diameter;
	public static final int SNAKE_TAIL_SIZE = SnakeGameView.CELL_SIZE / 2;
	// frog's body diameter
	public static final int FROG_SIZE =
			(int) Math.round(SnakeGameView.CELL_SIZE / 1.5);
	// the serial version UID
	private static final long serialVersionUID = 1L;
	// the off screen image for double buffering
	private Image offScreenlmage;
	// the view reference
	private SnakeGameView snakeGameView;

	/**
	 * Constructor.
	 * 
	 * @param snakeGameView the view reference
	 */
	public GameFieldPanel(SnakeGameView snakeGameView) {
		this.snakeGameView = snakeGameView;
		this.offScreenlmage =
				new BufferedImage(SnakeGameView.CELL_SIZE
						* snakeGameView.getGameField().getWidth(),
						SnakeGameView.CELL_SIZE
								* snakeGameView.getGameField().getHeight(),
						BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * Paints component.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.snakeGameView.getCurrentGameState() != null) {
			Graphics g1 = offScreenlmage.getGraphics();
			synchronized (g1) {
				g.drawImage(offScreenlmage, 0, 0, null);
			}
		}

	}

	/**
	 * Paints game field.
	 * 
	 * @param g the graphics
	 * @param locationStrings the location strings of drawn animal's body parts
	 * @param drawBackground true to redraw background
	 */
	public void paintGameField(List<BodyPart> bodyParts, boolean drawBackground) {
		Graphics g = offScreenlmage.getGraphics();

		synchronized (g) {
			if (g != null) {
				g.setColor(Color.BLACK);
				if (drawBackground) {
					for (int i = 0; i < this.snakeGameView
							.getCurrentGameState().getGameFieldHeight(); i++) {
						for (int j = 0; j < this.snakeGameView
								.getCurrentGameState().getGameFieldWidth(); j++) {
							g.fillRect(j * SnakeGameView.CELL_SIZE, i
									* SnakeGameView.CELL_SIZE,
									SnakeGameView.CELL_SIZE,
									SnakeGameView.CELL_SIZE);
						}
					}
				}
				for (BodyPart bodyPart : bodyParts) {
					int x = bodyPart.getX();
					int y = bodyPart.getY();
					g.setColor(Color.BLACK);
					g.fillRect(x * SnakeGameView.CELL_SIZE, y
							* SnakeGameView.CELL_SIZE, SnakeGameView.CELL_SIZE,
							SnakeGameView.CELL_SIZE);
					int bodyPartSize = 0;


					if (bodyPart.getAnimalClass() != null) {
						if (bodyPart.getAnimalClass().equals(Snake.class)) {
							if (bodyPart.isHead()) {
								bodyPartSize = SNAKE_HEAD_SIZE;
							} else if (bodyPart.isTail()) {
								bodyPartSize = SNAKE_TAIL_SIZE;
							} else {
								bodyPartSize = SNAKE_BODY_SIZE;
							}
							g.setColor(Color.YELLOW);

						} else if (bodyPart.getAnimalClass().equals(
								GreenFrog.class)) {
							bodyPartSize = FROG_SIZE;
							g.setColor(Color.GREEN);
						} else if (bodyPart.getAnimalClass().equals(
								RedFrog.class)) {
							bodyPartSize = FROG_SIZE;
							g.setColor(Color.RED);
						} else if (bodyPart.getAnimalClass().equals(
								BlueFrog.class)) {
							bodyPartSize = FROG_SIZE;
							g.setColor(Color.BLUE);
						}
						g
								.fillOval(
										x
												* SnakeGameView.CELL_SIZE
												+ (SnakeGameView.CELL_SIZE - bodyPartSize)
												/ 2,
										y
												* SnakeGameView.CELL_SIZE
												+ (SnakeGameView.CELL_SIZE - bodyPartSize)
												/ 2, bodyPartSize, bodyPartSize);
					}
				}
			}
		}

	}
}