import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	static int cellSize = 25;
	static int height = 375;
	static int width = 425;
	static int startTail = 10;
	static Cell grid[][] = new Cell[width / cellSize][height / cellSize];
	static Cell[] tail = new Cell[startTail];

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Snake");
		Game app = new Game();
		frame.add(app);
		frame.setResizable(false);
		frame.setSize(438, 412);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		for (int i = 0; i < startTail; i++) {
			tail[i] = new Cell(startTail - i, 7);
		}

		Snake snakeHead = new Snake(startTail, 7, 1, 0, tail);

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

		while (true) {

			for (int i = tail.length - 1; i > 0; i--) {
				tail[i].x = tail[i - 1].x;
				tail[i].y = tail[i - 1].y;
			}
			tail[0].x += snakeHead.xdir;
			tail[0].y += snakeHead.ydir;

			snakeHead.move();
			tail[0].x = snakeHead.x;
			tail[0].y = snakeHead.y;
			app.repaint();
			Thread.sleep(500);
		}

	}

	@Override
	public void paint(Graphics g) {
		for (int y = 0; y < height / cellSize; y++) {
			for (int x = 0; x < width / cellSize; x++) {
				Color color = ((x + y) % 2 == 0) ? new Color(142, 204, 57) : new Color(167, 217, 72);
				g.setColor(color);
				g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
			}
		}

		g.setColor(new Color(0, 0, 0));
		g.fillRect(tail[0].x * cellSize, tail[0].y * cellSize, cellSize, cellSize);

		g.setColor(new Color(255, 0, 0));
		for (int i = 1; i < tail.length; i++) {
			g.fillRect(tail[i].x * cellSize, tail[i].y * cellSize, cellSize, cellSize);

		}
	}
}
