// Testing TextFieldFrame.
import javax.swing.JFrame;

/*
 contains the main method that executes this application
 and displays an object of class TextFieldFrame
 */

public class TextFieldTest {

	public static void main(String[] args) {
		
		TextFieldFrame textFieldFrame = new TextFieldFrame();
		textFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textFieldFrame.setSize(350, 100);
		textFieldFrame.setVisible(true);

	}

}
