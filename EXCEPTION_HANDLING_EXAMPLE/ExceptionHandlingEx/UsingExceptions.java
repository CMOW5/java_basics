
public class UsingExceptions {

	public static void main(String[] args) {
		
		try
		{
			throwException(); // call method throwException
		} // end try
		catch (Exception exception)
		{
			//System.err.println( "Exception handled in main" );
			System.out.println( "Exception handled in main" );
			exception.printStackTrace();
		} // end catch

		doesNotThrowException();
		
	} // end main
	
	public static void throwException() throws Exception
	{
		try // throw an exception and immediately catch it
		{
			System.out.println("Method throwException");
			throw new Exception(); // generate exception
		} // end try
		catch (Exception exception) // catch exception thrown in try
		{
			//System.err.println("Exception handled in method throwException" );
			System.out.println("Exception handled in method throwException" );
			throw exception; // rethrow for further processing, it is cathed at main
							 
			// code here would not be reached; would cause compilation errors
		}// end catch
		finally // executes regardless of what occurs in try...catch
		{
			//System.err.println( "Finally executed in throwException" );
			System.out.println( "Finally executed in throwException" );
		} // end finally
		
		// code here would not be reached; would cause compilation errors
	} // end method throwException
	
	// demonstrate finally when no exception occurs
	public static void doesNotThrowException()
	{
		try // try block does not throw an exception
		{
			System.out.println( "Method doesNotThrowException" );
		} // end try
		catch ( Exception exception ) // does not execute
		{
			//System.err.println( exception );
			System.out.println( exception );
		} // end catch
		finally // executes regardless of what occurs in try...catch
		{
			//System.err.println("Finally executed in doesNotThrowException" );
			System.out.println("Finally executed in doesNotThrowException" );
		} // end finally
		
		System.out.println( "End of method doesNotThrowException" );
		
	}	// end method doesNotThrowException

} // end class UsingExceptions
