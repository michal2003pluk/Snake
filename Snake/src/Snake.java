import java.util.ArrayList;

public class Snake {
	int x, y, xdir, ydir, len;

//	public Snake(int x2, int y2, int xdir2, int ydir2, Cell[] tail2) {
//		this.x = x2;
//		this.y = y2;
//		this.xdir = xdir2;
//		this.ydir = ydir2;
//		this.tail = tail2;
//	}
	public Snake(int x2, int y2, int xdir2, int ydir2, ArrayList<Cell> tail) {
		this.x = x2;
		this.y = y2;
		this.xdir = xdir2;
		this.ydir = ydir2;
	}

	public void move() {
		this.x += this.xdir;
		this.y += this.ydir;
	}

	public void checkCollision() {

	}

}
