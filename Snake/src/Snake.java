import java.util.ArrayList;

public class Snake {
	int x, y, xdir, ydir;

//	public Snake(int x2, int y2, int xdir2, int ydir2, Cell[] tail2) {
//		this.x = x2;
//		this.y = y2;
//		this.xdir = xdir2;
//		this.ydir = ydir2;
//		this.tail = tail2;
//	}
	public Snake(int x2, int y2, int xdir2, int ydir2) {
		//init for the snakeHead
		this.x = x2;
		this.y = y2;
		this.xdir = xdir2;
		this.ydir = ydir2;
	}

	public void move() {
		//moves the snakeHead
		this.x += this.xdir;
		this.y += this.ydir;
	}

	public boolean hitBorder(int w, int h) {
		//checks if the snakeHead will outside the grid in the next frame
		if (this.x + this.xdir < 0 || this.x + this.xdir > w - 1 || this.y + this.ydir < 0
				|| this.y + this.ydir > h - 1) {
			return true;

		}
		return false;

	}

	public boolean ateFruit(Fruit fruit) {
		//returns if next frame the snakeHead will eat the fruit
		return this.x + this.xdir == fruit.x && this.y + this.ydir == fruit.y;
	}



	//This has not been debugged, check for errors later
	public int hitTail(ArrayList<Cell> tail) {
		//determines what the snakeHead's position will be in the next frame
		int newX = this.x + this.xdir;
		int newY = this.y + this ydir;

		//checks if in the next frame it is coinciding with the tail at any point
		for (int i = 0; i < tail.size(); i++) {
			if (newX == tail.get(i).x && newY == tail.get(i).y) {
				//returns which point it hit
				return i;
			}
		}
		
		//if it didn't hit the tail at any point
		return -1;
	}


}
