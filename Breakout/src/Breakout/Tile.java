package Breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{

	private Color[] colors = {Color.WHITE, Color.CYAN, Color.LAWNGREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.BLACK};
	
	private int level;
	private int direction;
	private int velocity;
	
	public Tile(double x, double y, int width, int height, int level){
		super((int) x, (int) y, width, height);
		this.level = level;
		this.setFill(this.colors[this.level]);
	}
	
	public Color getColor(){
		return colors[level];
	}
	
	public String toString(){
		return this.getX() + ", " + this.getY() + "; " + this.getColor().toString();
	}
	
	public void tick(){
		this.setFill(this.colors[this.level]);
	}
	
}
