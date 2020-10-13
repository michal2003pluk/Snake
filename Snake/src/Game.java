import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	static int cellSize = 25;
	static int height = 375;
	static int width = 425;
	static int startTail = 4;
	static ArrayList<Cell> tail = new ArrayList<Cell>();
	static boolean gamePlay = true;
	static Snake snakeHead;
	static Fruit fruit;

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Snake");
		Game app = new Game();
		frame.add(app);
		frame.setResizable(false);
		frame.setSize(438, 412);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// creates a new snake tail
		for (int i = 0; i < startTail; i++) {
			tail.add(i, new Cell(startTail - i, 7));
		}

		// creates a new snake Head
		snakeHead = new Snake(startTail, 7, 1, 0, tail);

		// creates a new starting fruit in random x,y
		generateFruit();

		// adding key Detection
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
					snakeHead.xdir = 0;
					snakeHead.ydir = -1;
					break;
				case KeyEvent.VK_DOWN:
					snakeHead.xdir = 0;
					snakeHead.ydir = 1;
					break;
				case KeyEvent.VK_LEFT:
					snakeHead.xdir = -1;
					snakeHead.ydir = 0;
					break;
				case KeyEvent.VK_RIGHT:
					snakeHead.xdir = 1;
					snakeHead.ydir = 0;
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// whilst game is active
		while (gamePlay) {
			
			app.repaint();
			if (snakeHead.hitBorder(width / cellSize, height / cellSize)) {
				gamePlay = false;
				System.out.println("Hit border");
			} else {

				if (snakeHead.ateFruit(fruit)) {
					System.out.printf("Ate fruit at %d, %d%n", fruit.x, fruit.y);
					tail.add(new Cell(tail.get(tail.size() - 1).x, tail.get(tail.size() - 1).y));
					generateFruit();
					app.repaint();
				}

				snakeHead.move();

				for (int i = tail.size() - 1; i > 0; i--) {
					tail.get(i).x = tail.get(i - 1).x;
					tail.get(i).y = tail.get(i - 1).y;
				}

				tail.get(0).x = snakeHead.x;
				tail.get(0).y = snakeHead.y;

				Thread.sleep(250);

			}
		}

	}

	@Override
	public void paint(Graphics g) {
		// Generating Green background
		for (int y = 0; y < height / cellSize; y++) {
			for (int x = 0; x < width / cellSize; x++) {
				Color color = ((x + y) % 2 == 0) ? new Color(142, 204, 57) : new Color(167, 217, 72);
				g.setColor(color);
				g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
			}
		}

		// Displaying snake Head
		g.setColor(new Color(0, 0, 0));
		g.fillRect(snakeHead.x * cellSize, snakeHead.y * cellSize, cellSize, cellSize);

		// Displaying snake tail
		g.setColor(new Color(255, 0, 0));
		for (int i = 1; i < tail.size(); i++) {
			g.fillRect(tail.get(i).x * cellSize, tail.get(i).y * cellSize, cellSize, cellSize);

		}

		// Displaying available fruit
		g.setColor(new Color(255, 255, 0));
		g.fillOval(fruit.x * cellSize + (int) Math.round(0.05 * cellSize),
				fruit.y * cellSize + (int) Math.round(0.05 * cellSize), (int) Math.round(cellSize * 0.9),
				(int) Math.round(cellSize * 0.9));

	}

	public static void generateFruit() {
		Random rnd = new Random();
		int rndX = rnd.nextInt(width / cellSize);
		int rndY = rnd.nextInt(height / cellSize);

		if (snakeHead.x == rndX && snakeHead.y == rndY) {
			generateFruit();
		}
		for (int i = 0; i < tail.size(); i++) {
			if (tail.get(i).x == rndX && tail.get(i).y == rndY) {
				generateFruit();
			}
		}
		fruit = new Fruit(rndX, rndY);
	}

}
