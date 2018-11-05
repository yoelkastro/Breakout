package Breakout;

import java.awt.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Tile extends BreakoutObject{

	private Color[] colors = {Color.WHITE, Color.CYAN, Color.LAWNGREEN, Color.YELLOW, Color.ORANGE, Color.RED, Color.BLACK};
	
	private int level;
	
	public Tile(int x, int y, int width, int height, int level){
		this.direction = 0;
		this.location = new Point(x, y);
		
		this.shape = new Polygon();
		((Polygon) this.shape).getPoints().addAll(new Double[]{0., 0., 0. + width, 0., 0. + width, 0. + height, 0., 0. + height});
	
		this.level = level;
		this.shape.setFill(this.colors[this.level]);

		this.tick();
	}
	
	public Color getColor(){
		return colors[level];
	}
	
	public Line[] getSides(){
		return new Line[]{new Line(((Polygon) this.shape).getPoints().get(0), ((Polygon) this.shape).getPoints().get(1), ((Polygon) this.shape).getPoints().get(2), ((Polygon) this.shape).getPoints().get(3)), 
				new Line(((Polygon) this.shape).getPoints().get(2), ((Polygon) this.shape).getPoints().get(3), ((Polygon) this.shape).getPoints().get(4), ((Polygon) this.shape).getPoints().get(5)),
				new Line(((Polygon) this.shape).getPoints().get(4), ((Polygon) this.shape).getPoints().get(5), ((Polygon) this.shape).getPoints().get(6), ((Polygon) this.shape).getPoints().get(7)),
				new Line(((Polygon) this.shape).getPoints().get(6), ((Polygon) this.shape).getPoints().get(7), ((Polygon) this.shape).getPoints().get(0), ((Polygon) this.shape).getPoints().get(1))};
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
		if(go instanceof Ball && this.level > 0 && this.level != colors.length - 1) this.level --;
	}
	
}
