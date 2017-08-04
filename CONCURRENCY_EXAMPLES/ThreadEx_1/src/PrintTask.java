import java.security.SecureRandom;

public class PrintTask implements Runnable {
	
	private static final SecureRandom generator = new SecureRandom();
	private final int sleepTime; // random sleep time for thread
	private final String taskName;
	
	// constructor
	public PrintTask(String taskName)
	{
		this.taskName = taskName;
		// pick random sleep time between 0 and 5 seconds
		sleepTime = generator.nextInt(5000); // milliseconds
	}
	
	@Override
	public void run() {
		try
		{
			System.out.printf("%s going to sleep for %d milliseconds.%n",
					taskName, sleepTime);
			Thread.sleep(sleepTime); // put thread to sleep
		}
		catch (InterruptedException exception)
		{
			exception.printStackTrace();
			/*
			 * interrupt method to deliver the InterruptedException to the current
			 * thread
			 */
			//interrupt main 
			Thread.currentThread().interrupt(); // re-interrupt the thread
		}
		
		// print task name
		System.out.printf("%s done sleeping%n", taskName);
	}

}
