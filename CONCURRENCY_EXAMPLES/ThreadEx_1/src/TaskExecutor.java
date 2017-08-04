import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TaskExecutor {

	public static void main(String[] args) {
		
		// create and name each runnable
		PrintTask task1 = new PrintTask("task1");
		PrintTask task2 = new PrintTask("task2");
		PrintTask task3 = new PrintTask("task3");
		
		System.out.println("Starting Executor");
		
		// create ExecutorService to manage threads
		/* to obtain an ExecutorService that’s capable of creating 
		 * new threads as they’re needed by the application. 
		 * These threads are used by ExecutorService to execute
		 *  the Runnables.
		 */ 
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		// start the three PrintTasks
		//executes its Runnable argument some time in the future
		executorService.execute(task1); // start task1
		executorService.execute(task2); // start task2
		executorService.execute(task3); // start task3
		
		/*
		 *method shutdown , which notifies the ExecutorService 
		 *to stop accepting new tasks, but continues executing 
		 *tasks that have already been submitted
		 */
		
		// shut down ExecutorService--it decides when to shut down threads
		executorService.shutdown();
		
		System.out.printf("Tasks started, main ends.%n%n");
		
	}

}
