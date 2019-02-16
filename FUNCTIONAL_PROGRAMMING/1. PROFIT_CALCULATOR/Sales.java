package demo;

public class Sales implements QuantityOfInterest {
	private final FunctionOverTime valueFunction;
	
	public Sales(FunctionOverTime valueFuntion) {
		this.valueFunction = valueFuntion;
	}
	
	public String getName() {
		return "Sales";
	}
	
	public double valueAt(int time) {
		return valueFunction.valueAt(time);
	}
}
