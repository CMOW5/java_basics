package demo;

@FunctionalInterface
public interface FunctionOverTime {
	
	double valueAt(int time);
	
	static FunctionOverTime monthByMonth(final double[] values) {
		return (time) -> values[time -1];
	}
	
	public static FunctionOverTime constant(final double value) {
		return polynomial(new double[] {value});
	}
	
	public static FunctionOverTime line(final double intercept, final double slope) {
		return polynomial(new double[] {intercept, slope});
	}
	
	static FunctionOverTime polynomial(final double[] coefficients) {
		return (time) -> {
			Double sum = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				sum += Math.pow(time, i) * coefficients[i];
			}
			return sum;
		};
	}
	
	@FunctionalInterface
	static interface FunctionOf3 {
		double apply (double a, double b, double c);
	}
	
	static FunctionOverTime combinationOf3(FunctionOverTime a, FunctionOverTime b,
			FunctionOverTime c, FunctionOf3 f) {
		
		return (time) -> f.apply(a.valueAt(time), b.valueAt(time), c.valueAt(time));
	} 
}
