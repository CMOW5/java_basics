
public class Circle extends Point {
	
	private double radius;

	public Circle() {
		// implicit call to Point constructor
		System.out.println("Circle Class constructor without arguments " + this);
	}
	
	public Circle(int xValue, int yValue, double radiusValue) {
		// explicit call to Point constructor
		super(xValue, yValue);
		setRadius(radiusValue);	
		System.out.println("Circle Class constructor with arguments " + this);
	}

	public void setRadius(double radius) {
		this.radius = ( radius < 0.0 ? 0.0 : radius );
	}
	
	public double getRadius() {
		return radius;
	}
	
	public double getDiameter(){
		return 2*getRadius();
	}
	
	public double getCircumference(){
		return Math.PI*getDiameter();
	}
	
	public double getArea(){
		return Math.PI*getRadius()*getRadius();
	}

	@Override
	//Override the toStrig method of the Point superclass
	public String toString() {
		return "Centro = " + super.toString() + "; Radio = " + getRadius();
		//return super.toString();
	}
	
	

}
