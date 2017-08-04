import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

/*
	The first type parameter indicates the type returned by 
	the doInBackground method; the second indicates the type 
	that’s passed between the publish and process methods
	to handle intermediate results
*/

public class BackgroundCalculator extends SwingWorker<Long, Object> {
	
	private final int n; // Fibonacci number to calculate
	private final JLabel resultJLabel; // JLabel to display the result
	
	// constructor
	public BackgroundCalculator(int n, JLabel resultJLabel)
	{
		this.n = n;
		this.resultJLabel = resultJLabel;
	}
	
	@Override
	protected Long doInBackground() throws Exception {
		
		return fibonacci(n);
	}
	
	// code to run on the event dispatch thread 
	// when doInBackground returns
	protected void done()
	{
		try
		{
			// get the result of doInBackground and display it
			resultJLabel.setText(get().toString());
		}
		catch (InterruptedException ex)
		{
			resultJLabel.setText("Interrupted while waiting for results.");
		}
		catch (ExecutionException ex)
		{
			resultJLabel.setText(
					"Error encountered while performing calculation.");
		}
	}
	
	// recursive method fibonacci; calculates nth Fibonacci number
	public long fibonacci(long number)
	{
		if (number == 0 || number == 1)
			return number;
		else
			return fibonacci(number - 1) + fibonacci(number - 2);
	}
	
}// end class BackgroundCalculator
