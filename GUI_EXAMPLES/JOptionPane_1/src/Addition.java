import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.*;

public class Addition {

	public static void main(String args[]){
		
		//Enabling the Nimbus Look and Feel
		//"Metal", "Nimbus", "CDE/Motif"
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		//set the program
		
		// obtain user input from JOptionPane input dialogs
		String firstNumber =
				JOptionPane.showInputDialog("Enter first integer");
		
		String secondNumber =
				JOptionPane.showInputDialog("Enter second integer");
		
		// convert String inputs to int values for use in a calculation
		int number1 = Integer.parseInt(firstNumber);
		int number2 = Integer.parseInt(secondNumber);
		int sum = number1 + number2;
		// display result in a JOptionPane message dialog
		JOptionPane.showMessageDialog(null, "The sum is " + sum,
		"Sum of Two Integers",JOptionPane.PLAIN_MESSAGE);
		
	}
}
