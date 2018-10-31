package Breakout;

public class Paddle extends Tile{

	public static final int MAX_VELOCITY = 15;
	
	public Paddle(int y, int width, int heigth){
		super(0, y, width, heigth, 6);
	}
	
}
