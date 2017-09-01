package abstractClassEx1;

public class PayrollSystemTest {

	public static void main(String[] args) {
		
		SalariedEmployee salariedEmployee =
			new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
		HourlyEmployee hourlyEmployee =
			new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
		
		System.out.println("Employees processed individually:");
		
		System.out.printf("%n%s%n%s: $%,.2f%n%n",
				salariedEmployee, "earned", salariedEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n",
				hourlyEmployee, "earned", hourlyEmployee.earnings());
		
		// create four-element Employee array
		Employee[] employees = new Employee[2];
		// initialize array with Employees
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;
		
		System.out.printf("Employees processed polymorphically:%n%n");
		
		for(Employee currentEmployee : employees) 
		{	
			
			if ( currentEmployee instanceof HourlyEmployee ) 
			{
				// (Downcasting)
				HourlyEmployee employee =
						(HourlyEmployee) currentEmployee ;
				//employee.setHours(30);
			}
			//dynamic binding
			System.out.printf(
					"earned $%,.2f%n%n", currentEmployee.earnings() );
			/*
			Using a superclass Employee variable, we can
			invoke only methods found in class Employee — earnings , 
			toString and Employee ’s get and set methods.
			*/
		}
		
		// get type name of each object in employees array
		for (int j = 0; j < employees.length; j++)
			
			System.out.printf("Employee %d is a %s%n", j,
					employees[j].getClass().getName());
		
	}

}
