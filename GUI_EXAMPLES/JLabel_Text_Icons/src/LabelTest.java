import javax.swing.JFrame;

public class LabelTest {

	public static void main(String[] args) {
		
		LabelFrame labelFrame = new LabelFrame();
		
		/*
		Specify the default close operation for the window.
		By default, closing a window simply hides the window. 
		However, when the user closes the LabelFrame window, 
		we would like the application to terminate
		
		JFrame.EXIT_ON_CLOSE as the argument to indicate that the 
		program should terminate when the window is closed by the user
		Without it the application will not terminate when the user 
		closes the window
		
		*/
		
		labelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//method to specify the width and height of the window in pixels
		labelFrame.setSize(460, 380);
		
		/*setVisible method	with the argument true to display 
		the window on the screen */
		labelFrame.setVisible(true);

	}

}
