package Breakout;

import java.awt.Point;
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
	
	public boolean isColliding(BreakoutObject go){
		
		return this.shape.getBoundsInLocal().intersects(go.shape.getBoundsInLocal());
		
	}
	
	public double getWidth(){
		return this.shape.getBoundsInLocal().getMaxX() - this.shape.getBoundsInLocal().getMinX();
	}
	
	public double getHeight(){
		return this.shape.getBoundsInLocal().getMaxY() - this.shape.getBoundsInLocal().getMinY();
	}
}
