
public interface Figure {
	
	//interfaces cannot have constructors
	//Abstract methods do not specify a body 
	
	//interface methods are always public and abstract
	double getArea(); //methods could have an argument
	double getVolume();
	String getName(); 
	
	public default void default_method() {
		System.out.println("default method");
	}
	
	static void helper_method() {
		System.out.println("static method");
	}

}
