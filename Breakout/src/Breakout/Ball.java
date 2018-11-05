package Breakout;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Ball extends BreakoutObject{

	private final int BALL_VELOCITY = 7;
	
	private int quadrantNo = 1;
	private int relativeAngle = 45;
	
	private int radius;
	
	public Ball(int x, int y, int radius){
		this.radius = radius;
		
		this.direction = 45;
		this.velocity = this.BALL_VELOCITY;
		
		this.location = new Point(x, y);
		this.shape = new Circle(0, 0, radius);
		this.shape.setFill(Color.GRAY);
		
		this.tick();
	}
	
	public void setDefaultVelocity(){
		this.velocity = this.BALL_VELOCITY;
	}
	
	public int getRadius(){
		return this.radius;
	}
	
	@Override
	public void tick(){
		this.location.x = (int) ((this.location.getX() + 1024)% 1024);
		super.tick();
	}
	
	@Override
	public void reactToCollision(BreakoutObject go){
		if(go.shape.getFill() != Color.WHITE){
//			if(Math.abs(this.location.getY() - go.location.getY()) < go.getHeight() / 2 + this.getHeight() / 2) this.direction = this.direction + 90;
//			else this.direction -= 180;
//			this.direction += 90 * ((((((this.direction % 360) / 180) + (((this.direction + 90) % 360) / 180)) % 2) * 2) - 1);
			
			Line[] sides = ((Tile) go).getSides();
			int collisionSide = -1;
			for(int i = 0; i < sides.length; i ++){
				//if(this.getBounds().intersects(sides[i].getBoundsInParent())){
				
			}
			
		
			if(collisionSide != -1)
			System.out.println(collisionSide);
			
			switch(collisionSide){
			case 0:	this.direction = 270; break;
			case 1: this.direction = 90; break;
			case 2:	this.direction = 270; break;
			case 3:	this.direction = 90; break;
			}
			
			this.direction = this.direction % 360;
//			System.out.println(this.direction);
//			System.out.println(Math.abs(this.location.getY() - go.location.getY()) < go.getHeight() / 2 + this.getHeight() / 2);
		}
	}
	
}
