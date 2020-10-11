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
	static Cell grid[][] = new Cell[width / cellSize][height / cellSize];
	static Cell[] tail = new Cell[4];

	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake");
		Game app = new Game();
		frame.add(app);
		frame.setResizable(false);
		frame.setSize(438, 412);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch (keyCode) {
				case KeyEvent.VK_UP:
					System.out.println("UP");
					tail[0].y--;
					app.repaint();
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("DOWN");
					tail[0].y++;
					app.repaint();
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("LEFT");
					tail[0].x--;
					app.repaint();
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("RIGHT");
					tail[0].x++;
					app.repaint();
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

		tail[0] = new Cell(4, 7);
		tail[1] = new Cell(3, 7);
		tail[2] = new Cell(2, 7);
		tail[3] = new Cell(1, 7);

		Snake player = new Snake(4, 7, 1, 0, tail);

		app.repaint();
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
