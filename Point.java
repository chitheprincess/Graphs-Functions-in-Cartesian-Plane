package cartesian;

public class Point {
	
	double x;
	double y; 
	
	public Point(){
		
	}
	
	public Point(double xVal, double yVal){
		x = xVal;
		y = yVal;
	}
	
	public void setX(double xVal){
		x = xVal;
	}
	
	public void setY(double yVal){
		y = yVal;
	}
	
	public void printPoint(){
		System.out.println("(" + x + " , " + y + ")");
	}
}
