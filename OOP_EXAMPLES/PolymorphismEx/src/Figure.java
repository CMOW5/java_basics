
public abstract class Figure extends Object {

	public Figure() {
		System.out.println("Figure Class constructor without arguments " + this);
	}
	
	public double getArea(){
		return 0.0;
	}

	public double getVolume(){
		return 0.0;
	}
	
	public abstract String getName(); //this method has to be overridden by all subclasses 
	
}
