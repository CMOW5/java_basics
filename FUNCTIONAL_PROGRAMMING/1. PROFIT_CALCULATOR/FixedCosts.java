package demo;

public class FixedCosts implements QuantityOfInterest {
	private final FunctionOverTime valueFunction;
	
	public FixedCosts(FunctionOverTime valueFuntion) {
		this.valueFunction = valueFuntion;
	}
	
	public String getName() {
		return "FixedCosts";
	}
	
	public double valueAt(int time) {
		return valueFunction.valueAt(time);
	}
}
