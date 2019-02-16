package demo;

public class Main {
	
	final static double[] EXPECTED_SALES_JAN_DEC =
		{42.0, 45.6, 43.6, 50.2, 55.6, 54.7, 58.0, 57.3, 62.0,
				60.3, 71.2, 88.8};
	
	public static void main(String[] args) { 
		
		final Sales sales = new Sales(
				FunctionOverTime.monthByMonth(EXPECTED_SALES_JAN_DEC));
				
		final FixedCosts fixedCosts =  new FixedCosts(
				FunctionOverTime.constant(0.15));
		
		final IncrementalCosts incrementalCosts = new IncrementalCosts(
				FunctionOverTime.line(5.1, 0.15));
		
		final Profit profit = new Profit(
				sales, incrementalCosts, fixedCosts);
		
		/*
		final FunctionOverTime sales = 
				FunctionOverTime.monthByMonth(EXPECTED_SALES_JAN_DEC);
				
		final FunctionOverTime fixedCosts = 
				FunctionOverTime.constant(0.15);
		
		final FunctionOverTime incrementalCosts = 
				FunctionOverTime.line(5.1, 0.15);
		
		final FunctionOverTime profit =
				FunctionOverTime.combinationOf3(
						sales, incrementalCosts, fixedCosts,
						(s, ic, fc) -> s - ic - fc
				);
		*/
	}
}
