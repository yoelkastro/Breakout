package Breakout;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends BreakoutObject{

	private Color[] colors = {Color.WHITE, Color.CYAN, Color.LAWNGREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.BLACK};	//Colors for each stage, white if destroyed, black is indestructable
	
	private int level;	//The current level of the Tile
	
	public Tile(int x, int y, int width, int height, int level){
		this.location = new Point(x, y);
		
		this.shape = new Rectangle(0, 0, width, height);
		
		this.level = level;
		this.shape.setFill(this.colors[this.level]);

		this.tick();
	}
	
	public Color getColor(){	//Returns current color of the Tile
		return colors[level];
	}
	
	@Override
	public String toString(){	//Returns position and color as a string
		return this.location.getX() + ", " + this.location.getY() + "; " + this.getColor().toString();
	}
	
	@Override
	public void tick(){	//Updates color
			
		this.shape.setFill(this.colors[this.level]);
		super.tick();
		
	}
	
	@Override
	public void reactToCollision(BreakoutObject go){	//Decreases level if hit by a Ball
		if(go instanceof Ball && this.level > 0 && this.level != colors.length - 1) this.level --;
	}
	
}
