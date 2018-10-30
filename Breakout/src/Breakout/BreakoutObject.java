package Breakout;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class BreakoutObject {

	public Shape shape;
	private int direction;
	private int velocity;
	private Point location;
	
	public void setVelocity(int velocity){
		this.velocity = velocity;
	}
	
	public void tick(){
		this.location.translate((int) (velocity * Math.cos(this.toRadian(direction))), (int) (velocity * Math.sin(this.toRadian(direction))));
	}
	
	private double toRadian(int degrees){
		return (((double) degrees) / 180) * Math.PI;
	}
	
	public boolean isColliding(BreakoutObject go){
		
		return this.shape.getBoundsInLocal().intersects(go.shape.getBoundsInLocal());
		
	}
	
}
