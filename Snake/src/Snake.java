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

	public boolean hitBorder(int w, int h) {
		if (this.x + this.xdir < 0 || this.x + this.xdir > w - 1 || this.y + this.ydir < 0
				|| this.y + this.ydir > h - 1) {
			return true;

		} else {
			return false;
		}

	}

	public boolean ateFruit(Fruit fruit) {

		return this.x + this.xdir == fruit.x && this.y + this.ydir == fruit.y;
	}

}
