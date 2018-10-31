package Breakout;

import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends BreakoutObject{

	private Color[] colors = {Color.WHITE, Color.CYAN, Color.LAWNGREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.BLACK};
	
	private int level;
	
	public Tile(int x, int y, int width, int height, int level){
		this.direction = 0;
		this.location = new Point(0, 0);
		this.shape = new Rectangle(x, y, width, height);
		this.level = level;
		this.shape.setFill(this.colors[this.level]);
	}
	
	public Color getColor(){
		return colors[level];
	}
	
	@Override
	public String toString(){
		return this.location.getX() + ", " + this.location.getY() + "; " + this.getColor().toString();
	}
	
	@Override
	public void tick(){
		this.shape.setFill(this.colors[this.level]);
		super.tick();
	}
	
	@Override
	public void reactToCollision(BreakoutObject go){
		if(go instanceof Ball) this.level --;
	}
	
}
