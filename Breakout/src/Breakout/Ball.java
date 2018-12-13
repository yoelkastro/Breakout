package Breakout;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends BreakoutObject{

	private final int BALL_VELOCITY = 7;	//Maximum velocity of the ball
	
	private int radius;	//Radius of the ball
	
	public Ball(int x, int y, int radius){
		this.radius = radius;
		
		this.xVelocity = this.BALL_VELOCITY * Math.cos(Math.PI / 4);
		this.yVelocity = this.xVelocity;	//Set the ball at a 45 degree by making both axis equal
		
		this.location = new Point(x, y);
		this.shape = new Circle(0, 0, radius);
		this.shape.setFill(Color.GRAY);
		
		this.tick();
	}
	
	public void resetLocation(int x, int y){	//Bring the ball back to its starting position
		this.location = new Point(x, y);
	}
	
	public void setDefaultVelocity(){	//Reinitialize the balls velocity factors
		this.xVelocity = this.BALL_VELOCITY * Math.cos(Math.PI / 4);
		this.yVelocity = this.xVelocity;
	}
	
	public int getRadius(){	//Returns the radius of the ball
		return this.radius;
	}
	
	@Override
	public void tick(){	//Relocate the ball
		super.tick();
	}
	
	@Override
	public void reactToCollision(BreakoutObject go){
		if(go.shape.getFill() != Color.WHITE){	//Check if it should interact with the object 
			if(Math.abs(this.getWidth() / 2 + go.getWidth() / 2) >= 
					Math.abs((this.location.getX() + (this.getWidth() / 2)) - (go.location.getX() + (go.getWidth() / 2)))){	//Checks if ball hit from the top or bottom
				
				this.yVelocity *= -1;	//Reverse y velocity
				
				if(go instanceof Paddle){
					
					this.xVelocity += ((this.location.getX() + (this.getWidth() / 2)) - (go.location.getX() + (go.getWidth() / 2))) / this.BALL_VELOCITY;	//If the ball hits the paddle, the x velocity gets altered depending on their relative location
				}
			}
			else {
				
				this.xVelocity *= -1;	//If the ball hit from the side, reverse x velocity
			}
			
			if((this.xVelocity * this.xVelocity) + (this.yVelocity * this.yVelocity) != this.BALL_VELOCITY * this.BALL_VELOCITY){	//Sets the total ball velocity back to 
				this.xVelocity /= Math.sqrt(((this.xVelocity * this.xVelocity) + (this.yVelocity * this.yVelocity)) / (this.BALL_VELOCITY * this.BALL_VELOCITY));
				this.yVelocity /= Math.sqrt(((this.xVelocity * this.xVelocity) + (this.yVelocity * this.yVelocity)) / (this.BALL_VELOCITY * this.BALL_VELOCITY));
			}
		}
	}
	
}
