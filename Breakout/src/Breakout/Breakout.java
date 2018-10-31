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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Breakout extends Application{

	final int PANEL_WIDTH = 1024;
	final int PANEL_HEIGHT = 768;
	final int TILE_ROWS = 10;
	final int TILE_COLUMNS = 10;
	final int TILE_WIDTH = PANEL_WIDTH / TILE_COLUMNS;
	final int TILE_HEIGHT = (int) (PANEL_HEIGHT / 2.6) / TILE_ROWS;
	final int TILE_MARGIN_RATIO = 200;
	final Point TILE_FIRST_LOCATION = new Point(0, (int) (PANEL_HEIGHT / 2.3) - (PANEL_HEIGHT / TILE_MARGIN_RATIO));
	final int PADDLE_Y = (int) (PANEL_HEIGHT / 1.2);
	
	private Scene scene;
	private Canvas canvas;
	private int mouseX;
	private boolean isPaused;
	private Ball ball;
	private Tile[] tiles;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		this.canvas = new Canvas(this.PANEL_WIDTH, this.PANEL_HEIGHT);
		root.getChildren().add(this.canvas);
		root.setStyle("-fx-background-color: #FFFFFF");
		
		stage.setTitle("Breakout");
		
		this.scene = new Scene(root, this.canvas.getWidth(), this.canvas.getHeight());
		
		stage.setScene(scene);
		
		stage.show();
		
		this.tiles = new Tile[(this.TILE_COLUMNS * this.TILE_ROWS) + 1];
		this.tiles[0] = new Paddle(this.PADDLE_Y, (int) (this.TILE_WIDTH * 1.5), (int) (this.TILE_HEIGHT / 1.5));
		root.getChildren().add(this.tiles[0].shape);
		for(int y = 0; y < this.TILE_ROWS; y ++){
			for(int x = 0; x < this.TILE_COLUMNS; x ++){
				this.tiles[((y * this.TILE_ROWS) + x) + 1] = new Tile((int) (this.TILE_FIRST_LOCATION.getX() + (this.TILE_WIDTH * x) + ((this.PANEL_WIDTH / this.TILE_MARGIN_RATIO))), 
										(int) (this.TILE_FIRST_LOCATION.getY() - (this.TILE_HEIGHT * y) - ((this.PANEL_HEIGHT / this.TILE_MARGIN_RATIO))), 
										this.TILE_WIDTH - (this.PANEL_WIDTH / this.TILE_MARGIN_RATIO), this.TILE_HEIGHT - (this.PANEL_HEIGHT / this.TILE_MARGIN_RATIO), (y / 2) + 1);
				
				root.getChildren().add(this.tiles[((y * this.TILE_ROWS) + x) + 1].shape);
			}
		}
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				mouseX = (int) e.getSceneX();
			}		
		});
		scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				isPaused = !isPaused;
			}		
		});
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e) {
				switch(e.getCode()){
				case SPACE: isPaused = !isPaused; break;
				default: break;
				}
			}	
		});
		
		
		Timeline t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(Duration.millis(25), ae -> {
			tick();
		});
		
		t.getKeyFrames().add(kf);
		t.play();
		
	}
	
	public void tick(){
		
		if(!isPaused){
			this.tiles[0].setVelocity((int) (((Math.abs((this.mouseX - (this.tiles[0].getWidth() / 2)) - this.tiles[0].location.getX()) <= Paddle.MAX_VELOCITY)? Math.abs((this.mouseX - (this.tiles[0].getWidth() / 2)) - this.tiles[0].location.getX()):Paddle.MAX_VELOCITY) * Math.signum(((this.mouseX - (this.tiles[0].getWidth() / 2)) - this.tiles[0].location.getX()))));
			for(int i = 0; i < this.tiles.length; i ++){
				this.tiles[i].tick();
			}
		}
		
	}
	
}
