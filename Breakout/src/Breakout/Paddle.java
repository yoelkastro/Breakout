package Breakout;

public class Paddle extends Tile{

	public static final int MAX_VELOCITY = 10;	//Maximum velocity of the Paddle
	
	public Paddle(int x, int y, int width, int heigth){
		super(x, y, width, heigth, 6);	//Initializes an indestructible Tile
	}
	
}
