package demo;

public class IncrementalCosts implements QuantityOfInterest {
	private final FunctionOverTime valueFunction;
	
	public IncrementalCosts(FunctionOverTime valueFuntion) {
		this.valueFunction = valueFuntion;
	}
	
	public String getName() {
		return "IncrementalCosts";
	}
	
	public double valueAt(int time) {
		return valueFunction.valueAt(time);
	}
}
