package Breakout;

import java.awt.Point;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public class BreakoutObject {

	public Point location;
	public Shape shape;
	protected int direction;
	protected int velocity;
	
	public void setVelocity(int velocity){
		this.velocity = velocity;
	}
	
	public void tick(){
		this.location.translate((int) (velocity * Math.cos(this.toRadian(direction))), (int) (velocity * Math.sin(this.toRadian(direction))));
		this.shape.setTranslateX(this.location.getX());
		this.shape.setTranslateY(this.location.getY());
	}
	
	protected double toRadian(int degrees){
		return (((double) degrees) / 180) * Math.PI;
	}
	
	public double getWidth(){
		return this.shape.getBoundsInLocal().getMaxX() - this.shape.getBoundsInLocal().getMinX();
	}
	
	public double getHeight(){
		return this.getBounds().getMaxY() - this.getBounds().getMinY();
	}
	
	public Bounds getBounds(){
		return this.shape.getBoundsInLocal();
	}
	
	public boolean isColliding(BreakoutObject go){
		
		boolean ret = this.shape.getBoundsInLocal().intersects(go.shape.getBoundsInLocal());
		
		if(ret){
			this.reactToCollision(go);
			go.reactToCollision(this);
		}
		
		return ret;
		
	}
	
	public void reactToCollision(BreakoutObject go){}
	
}
