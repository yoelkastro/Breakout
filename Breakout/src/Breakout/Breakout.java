package Breakout;

import java.awt.Point;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Breakout extends Application{

	final int PANEL_WIDTH = 1024;	//Width of the panel
	final int PANEL_HEIGHT = 768;	//Height of the panel
	final int TILE_ROWS = 10;	//Number of Tiles per column
	final int TILE_COLUMNS = 10;	//Number of Tiles per row
	final int TILE_WIDTH = PANEL_WIDTH / TILE_COLUMNS;	//The width of each Tile
	final int TILE_HEIGHT = (int) (PANEL_HEIGHT / 2.6) / TILE_ROWS;	//The hieght of each Tile
	final int TILE_MARGIN_RATIO = 200;	//Used to calculate the distance between each Tile
	final Point TILE_FIRST_LOCATION = new Point(0, (int) (PANEL_HEIGHT / 2.3) - (PANEL_HEIGHT / TILE_MARGIN_RATIO));	//The upper-left corner of the first Tile
	final int PADDLE_Y = (int) (PANEL_HEIGHT / 1.2);	//The y position of the Paddle
	
	private int ticks = 0;	//Increases by one for each game step
	
	private int lives = 3;	//Number of starting lives
	private Scene scene;
	private Canvas canvas;
	private int mouseX;	//X position of the mouse
	private boolean isPaused;	//Game is paused or not
	private Ball ball;	//The Ball
	private Tile[] tiles;	//Tiles to break, first element is the Paddle
	private Tile[] collision = {new Tile(0, 0, 1, this.PANEL_HEIGHT, 6), new Tile(this.PANEL_WIDTH - 1, 0, 1, this.PANEL_HEIGHT, 6), new Tile(0, 0, this.PANEL_WIDTH, 1, 6)};	//Indestructible Tiles to make borders the ball can't pass
	private Pane root = new Pane();
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		this.canvas = new Canvas(this.PANEL_WIDTH, this.PANEL_HEIGHT);
		this.root.getChildren().add(this.canvas);
		this.root.setStyle("-fx-background-color: #FFFFFF");
		
		stage.setTitle("Breakout");
		
		this.scene = new Scene(this.root, this.canvas.getWidth(), this.canvas.getHeight());
		
		stage.setScene(scene);
		
		stage.show();
		
		this.ball = new Ball(this.PANEL_WIDTH / 2, (this.PANEL_HEIGHT / 2), 15);	//Create new Ball with starting position at the middle of the screen and a radius of 15
		this.root.getChildren().add(this.ball.shape);	//Add the ball to the pane
		
		this.tiles = new Tile[(this.TILE_COLUMNS * this.TILE_ROWS) + 1];	//Initialize the Tiles
		
		this.tiles[0] = new Paddle(this.PANEL_WIDTH / 2, this.PADDLE_Y, (int) (this.TILE_WIDTH * 1.5), (int) (this.TILE_HEIGHT / 1.5));	//Make the first tile the Paddle, 1.5 times wider and thinner than other Tiles
		this.root.getChildren().add(this.tiles[0].shape);	//Add the paddle to the pane
		
		for(int y = 0; y < this.TILE_ROWS; y ++){	//Cycle through each column of Tile
			for(int x = 0; x < this.TILE_COLUMNS; x ++){	//Cycle through each row of Tile
				this.tiles[((y * this.TILE_ROWS) + x) + 1] = new Tile((int) (this.TILE_FIRST_LOCATION.getX() + (this.TILE_WIDTH * x) + ((this.PANEL_WIDTH / this.TILE_MARGIN_RATIO))), 	//Set the x position of the Tile
										(int) (this.TILE_FIRST_LOCATION.getY() - (this.TILE_HEIGHT * y) - ((this.PANEL_HEIGHT / this.TILE_MARGIN_RATIO))), 	//Set the y position of the Tile
										this.TILE_WIDTH - (this.PANEL_WIDTH / this.TILE_MARGIN_RATIO), this.TILE_HEIGHT - (this.PANEL_HEIGHT / this.TILE_MARGIN_RATIO), (y / 2) + 1);	//Set the width and height of the tile
				
				this.root.getChildren().add(this.tiles[((y * this.TILE_ROWS) + x) + 1].shape);	//Add the Tile to the pane
			}
		}
		
		for(int i = 0; i < this.collision.length; i ++){
			this.root.getChildren().add(this.collision[i].shape);	//Add the borders to the pane
		}
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				mouseX = (int) e.getSceneX();	//Update x position of mouse each time mouse moves
			}		
		});
		scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				isPaused = !isPaused;	//Pause or unpause the game on click
			}		
		});
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()){
				case SPACE: isPaused = !isPaused; break;	//Pause or unpause game when space is pressed
				default: break;
				}
			}	
		});
		
		
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(Duration.millis(1000 / 60), ae -> {	//Runs 60 times per second
			if(lives > 0)
			tick();	//Move one game step if the player has lives left
		});
		
		t.getKeyFrames().add(kf);
		t.play();
		
	}
	
	public void tick(){	//Runs every game step
		
		if(!isPaused){
			this.tiles[0].setHorizontalVelocity((int) (((Math.abs((this.mouseX - (this.tiles[0].getWidth() / 2)) - this.tiles[0].location.getX()) <= Paddle.MAX_VELOCITY)? Math.abs((this.mouseX - (this.tiles[0].getWidth() / 2)) - this.tiles[0].location.getX()):Paddle.MAX_VELOCITY) * Math.signum(((this.mouseX - (this.tiles[0].getWidth() / 2)) - this.tiles[0].location.getX()))));	//Move the paddle closer to the mouse by its maximum speed. If it is too close, set the x position to the mouse
			this.tiles[0].setVerticalVelocity(0);	//The paddle doesn't move on the y axis
			for(int i = 0; i < this.tiles.length; i ++){	//Checks every tile
				this.tiles[i].tick();	//The tile moves one game step
				this.tiles[i].isColliding(this.ball);	//Checks if the Ball has hit this Tile
				if(this.tiles[i].getColor() == Color.WHITE && this.root.getChildren().contains(this.tiles[i].shape)){
					this.root.getChildren().remove(this.tiles[i].shape);	//Remove the tile from the pane if it is destroyed
				}
			}
			for(int i = 0; i < this.collision.length; i ++){
				this.collision[i].isColliding(this.ball);	//Checks if the ball has hit the borders
			}
			
			if(this.ticks > 60){
				this.ball.tick();	//Makes the ball wait at the start of the game
			}
			if(this.ball.location.getY() > this.PANEL_HEIGHT){	//If the player has let the ball fall:
				this.ticks = 0;	//The game starts recounting
				lives --;	//The player loses a life
				this.ball.resetLocation(this.PANEL_WIDTH / 2, (this.PANEL_HEIGHT / 2));
				this.ball.setDefaultVelocity();	//The ball is reset to its starting position
				this.ball.tick();	//The ball move one game step to update its location
			}
			
			ticks ++;	//Increase the number of game steps by one
		}
		
	}
	
}
