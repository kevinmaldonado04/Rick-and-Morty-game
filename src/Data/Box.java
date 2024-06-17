package Data;

public class Box {
	
	private int x1, x2, y1, y2;
	
	//Item and Screen Boxes Constructor 
	public Box(int x1,int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	//Player Box Constructor
	public Box(int x1, int y1) {
		this.x1 = x1;
		this.x2 = x1;
		this.y1 = y1;   
		this.y2 = y1;
	}
	
	
	public int getX1() {
		return this.x1;
	}
	public int getX2() {
		return this.x2;
	}
	public int getY1() {
		return this.y1;
	}
	public int getY2() {
		return this.y2;
	}
	
	//Box Collision Checker
	public static boolean isCollision(Box b1, Box b2) {
		if((b1.getX1() > b2.getX2()) || (b1.getX2() < b2.getX1()) || (b1.getY1() > b2.getY2()) || (b1.getY2() < b2.getY1())) {
			return false;
		}
		return true;
		
		
		
	} 
}
