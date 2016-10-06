package cartesian;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Cartesian extends Application {
	
	double canvW = 1280;
	double canvH = 720;
	
	public static void main(String[] args){
		Application.launch(args);
	}//main
	
	public void start(Stage primaryStage){//1152, 648 -- Make Total Size, 20-24
		GridPane grid = new GridPane();
		grid.setHgap(0);
		grid.setVgap(0);
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.setGridLinesVisible(true);
		Scene primaryScene = new Scene(grid, 1280, 720);
		
		//Canvas
		Canvas canvas = new Canvas(canvW, canvH);
	    GraphicsContext gc = canvas.getGraphicsContext2D();
	        
	    doStuff(gc);

	    grid.add(canvas, 0, 0);
	    primaryStage.setScene(primaryScene);
        primaryStage.show();
	}//start
	
	double scaleX = 20; //(pixels per unit)
	double scaleY = 20;
	double pointsPerXUnit = 20;//maybe make this change over time based on the function
	
	private void doStuff(GraphicsContext gc){ //make it draw amt of gridlines based on screenwidth and scale... 
		drawAxes(gc);
		drawGridLines(gc);
	    drawFunc(gc);
	}//doStuff
	
	private double f(double x){
		return Math.pow(x, 3);
	}//f(x)
	
	private void cartLine(GraphicsContext gc, double x1, double y1, double x2, double y2){
		gc.strokeLine(scaleX*x1 + canvW/2, canvH-(y1*scaleY) - canvH/2, x2*scaleX + canvW/2, canvH-(y2*scaleY) - canvH/2);
	}//setUpCart
	
	private void cartPoint(GraphicsContext gc, Point p){
		gc.fillOval(scaleX*p.x + canvW/2, canvH-(p.y*scaleY) - canvH/2, 1, 2);
	}//cartPoint
	
	private void drawGridLines(GraphicsContext gc){
		gc.setStroke(Color.AQUAMARINE);
		for(double i = -canvW/2*scaleX; i < canvW/2*scaleX; i ++){
			if(i % 5 == 0){
				cartLine(gc, i, canvH/2*scaleY, i, -canvH/2*scaleY);
			}else{
				gc.setLineWidth(.5);
				cartLine(gc, i, canvH/2*scaleY, i, -canvH/2*scaleY);
				gc.setLineWidth(1);
			}
		}
		for(double i = -canvH/2*scaleY; i < canvH/2*scaleY; i ++){
			if(i % 5 == 0){
				cartLine(gc, -canvW/2*scaleX, i, canvW/2*scaleX, i);
			}else{
				gc.setLineWidth(.5);
				cartLine(gc, -canvW/2*scaleX, i, canvW/2*scaleX, i);
				gc.setLineWidth(1);
			}
		}
		gc.setStroke(Color.BLACK);
	}//drawGridLines
	
	private void drawAxes(GraphicsContext gc) {
		gc.setLineWidth(2);
		cartLine(gc, 0, canvH/2, 0, -canvH/2);
		cartLine(gc, canvW/2, 0, -canvW/2, 0);
		gc.setLineWidth(1);
	}//setUpCart
	
	private void drawFunc(GraphicsContext gc){
		double xLow = -canvW/2*scaleX;
		double xHigh = canvW/2*scaleX;
		
		ArrayList<Point> points = new ArrayList<Point>();
		for(double i = xLow; i < xHigh; i = i + (1/pointsPerXUnit)){
			points.add(new Point(i, f(i)));
		}
		for(int i = 0; i < points.size(); i ++){
			cartPoint(gc, points.get(i));
		}
	}//cartFunc
	
}//class


/*
DoubleProperty x = new SimpleDoubleProperty();
DoubleProperty y = new SimpleDoubleProperty();
AnimationTimer timer = new AnimationTimer() {
    public void handle(long now) { 	
    }//handle
};//AnimationTimer
*/