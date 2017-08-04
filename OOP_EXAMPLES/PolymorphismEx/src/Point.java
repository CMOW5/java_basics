
public class Point extends Figure {

	//avoid using protected unless you want to provide a service to your own sub classes
	private int x; 
	private int y;
	
	public Point() {
		/* implicit call to Object constructor (all class inherits from object)
		 no argument constructor is required by the subclasses (compilation error if they are 
		 not provided) 
		*/ 
		System.out.println("Point Class constructor without arguments " + this);
		//this makes a implicit call to toString method
	}
	
	public Point (int xValue, int yValue) {
		x = xValue;
		y = yValue;
		System.out.println("Point Class constructor with arguments " + this);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	//we MUST override the public abstract method inherited by the Figure Abstract super Class
	public String getName(){
		return "Point";
	}
	
	@Override
	//Override the toStrig method of the Object superclass
	public String toString() {
		return "[" + getX() + "," + getY() + "]";
		//return super.toString(); -> calls the toString non Override (original) method from Object 
	}

} // end Point Class
