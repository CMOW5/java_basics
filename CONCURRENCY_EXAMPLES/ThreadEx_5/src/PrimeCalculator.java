import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/*
	Class PrimeCalculator with the first type parameter indicating
	the return type of method doInBackground and the second 
	indicating the type of intermediate results passed between 
	methods publish and process
*/

public class PrimeCalculator extends SwingWorker<Integer, Integer> {

	private static final SecureRandom generator = new SecureRandom();
	private final JTextArea intermediateJTextArea; // displays found primes
	private final JButton getPrimesJButton;
	private final JButton cancelJButton;
	private final JLabel statusJLabel; // displays status of calculation
	private final boolean[] primes; // boolean array for finding primes
	
	// constructor
	public PrimeCalculator(int max, JTextArea intermediateJTextArea,
	JLabel statusJLabel, JButton getPrimesJButton,
	JButton cancelJButton)
	{
		this.intermediateJTextArea = intermediateJTextArea;
		this.statusJLabel = statusJLabel;
		this.getPrimesJButton = getPrimesJButton;
		this.cancelJButton = cancelJButton;
		primes = new boolean[max];
		Arrays.fill(primes, true); // initialize all primes elements to true
	}
	
	// finds all primes up to max using the Sieve of Eratosthenes
	public Integer doInBackground()
	{
		int count = 0; // the number of primes found
		
		// starting at the third value, cycle through the array and put
		// false as the value of any greater number that is a multiple
		for (int i = 2; i < primes.length; i++)
		{
			if (isCancelled()) // if calculation has been canceled
				return count;
			else
			{
				//setProgress to update the percentage of
				//the array that’s been traversed so far
				setProgress(100 * (i + 1) / primes.length);
				try
				{
					/*
					Because the computation in method doInBackground 
					progresses quickly, publishing values often, updates
					to the JTextArea can pile up on the event dispatch 
					thread, causing the GUI to become sluggish.
					In fact, when searching for a large number of primes, 
					the event dispatch thread may receive so many requests
					in quick succession to update the JTextArea that it
					runs out of memory in its event queue. 
					This is why we put the worker thread to sleep for a
                    few milliseconds between calls to publish. 
                    The calculation is slowed just enough to allow 
                    the event dispatch thread to keep up with requests 
                    to update the JTextArea with new primes, enabling the
					GUI to update smoothly and remain responsive.
					*/
					
					Thread.sleep(generator.nextInt(5));
				}
				catch (InterruptedException ex)
				{
					statusJLabel.setText("Worker thread interrupted");
					return count;
				}
				if (primes[i]) // i is prime
				{
					publish(i); // make i available for display in prime list
					++count;
					for (int j = i + i; j < primes.length; j += i)
						primes[j] = false; // i is not prime
				}
			}
		}
		return count;
	}
	
	// displays published values in primes list
	// this method executes in the event dispatch thread
	// and receives its argument publishedVals from method publish
	protected void process(List<Integer> publishedVals)
	{	
		/*
		The passing of values between publish in the worker 
		thread and process in the event dispatch thread is 
		asynchronous; process might not be invoked for every 
		call to publish . All Integers published since the 
		last call to process are received as a List by method process .
		*/
		
		for (int i = 0; i < publishedVals.size(); i++)
			intermediateJTextArea.append(publishedVals.get(i) + "\n");
	}
	
	// code to execute when doInBackground completes
	protected void done()
	{
		getPrimesJButton.setEnabled(true); // enable Get Primes button
		cancelJButton.setEnabled(false); // disable Cancel button
		
		try
		{
			// retrieve and display doInBackground return value
			statusJLabel.setText("Found " + get() + " primes.");
		}
		catch (InterruptedException | ExecutionException |
									  CancellationException ex)
		{
			statusJLabel.setText(ex.getMessage());
		}
	}
} // end class PrimeCalculator

