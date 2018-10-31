package Breakout;

import java.awt.Point;
import javafx.scene.shape.Circle;

public class Ball extends BreakoutObject{

	private final int BALL_VELOCITY = 5;
	
	private int radius;
	
	public Ball(int x, int y, int radius){
		this.radius = radius;
		
		this.location = new Point(x, y);
		this.shape = new Circle(x, y, radius);
		
	}
	
	public int getRadius(){
		return this.radius;
	}
	
	@Override
	public void reactToCollision(BreakoutObject go){
		this.direction = 
	}
	
}
