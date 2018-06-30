/*
Cylinder class inherits from Circle class, 
the public services of the  Cylinder class include the methods:
public void setRadius(double radius)
public double getRadius()
public double getDiameter()
public double getCircumference()
public double getArea()
public String toString()
  
the methods below are inherited indirectly from Point Class  
public void setX(int x) 
public void setY(int y) 
public int getX() 
public int getY()
*/


public class Cylinder extends Circle {

	private double height;
	
	public Cylinder() {
		//implicit call to Circle constructor
		System.out.println("Cylinder Class constructor without arguments " + this);
	}
	
	public Cylinder(int xValue, int yValue, double radiusValue, double heightValue) {
		//implicit call to Circle constructor
		super(xValue, yValue, radiusValue);
		setHeight(heightValue);
		System.out.println("Cylinder Class constructor with arguments " + this);
	}

	
	public void setHeight(double height) {
		this.height = (height < 0.0 ? 0.0 : height);
	}
	
	public double getHeight() {
		return height;
	}

	public double getVolume(){
		return super.getArea()*getHeight();
	}
	
	@Override
	//Override Circle GetArea Method
	public double getArea() {
		return 2*super.getArea()+getCircumference()*getHeight();
	}

	@Override
	//Override Circle toString Method
	public String toString() {
		return super.toString() + "; Altura = " + getHeight();
	}
	

} //end of cylinder Class
