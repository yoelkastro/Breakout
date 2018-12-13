package Breakout;

import java.awt.Point;
import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public class BreakoutObject {

	public Point location;	//Position of BreakoutObject relative to Panel
	public Shape shape;	//Visuals of the BreakoutObject
	protected double xVelocity;	//Change in x position per tick
	protected double yVelocity;	//Change in y position per tick
	
	public void setHorizontalVelocity(int velocity){	//Set the horizontal velocity
		this.xVelocity = velocity;
	}
	
	public void setVerticalVelocity(int velocity){	//Set the vertical velocity
		this.yVelocity = velocity;
	}
	
	public void tick(){
		
		this.location.translate((int) xVelocity, (int) yVelocity);	//Update location of the BreakoutObject
		this.shape.setTranslateX(this.location.getX());	//Update the location of the shape according to the BreakoutObject's location
		this.shape.setTranslateY(this.location.getY());
		
	}
	
	protected double toRadian(double degrees){	//Switches degrees to radians
		return (((double) degrees) / 180) * Math.PI;
	}
	
	protected double toAngle(double radians){	//Switches radians to degrees
		return (((double) radians) / Math.PI) * 180;
	}
	
	public double getWidth(){	//Returns the width of this objects shape
		return this.shape.getBoundsInLocal().getMaxX() - this.shape.getBoundsInLocal().getMinX();
	}
	
	public double getHeight(){	//Returns the height of this objects shape
		return this.getBounds().getMaxY() - this.getBounds().getMinY();
	}
	
	public Bounds getBounds(){	//Returns the Bounds of this objects shape
		return this.shape.getBoundsInParent();
	}
	
	public boolean isColliding(BreakoutObject go){	//Checks if this is colliding with another BreakoutObject
		
		boolean ret = this.getBounds().intersects(go.getBounds());
		
		if(ret){	//If the objects are colliding, react accordingly
			this.reactToCollision(go);
			go.reactToCollision(this);
		}
		
		return ret;
		
	}
	
	public void reactToCollision(BreakoutObject go){}	//Reaction to colliding with another object
	
}
