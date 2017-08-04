// Fig. 12.15: ButtonFrame.java
// Command buttons and action events.
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ButtonFrame extends JFrame {
	
	private final JButton plainJButton; // button with just text
	private final JButton fancyJButton; // button with icons
	
	// ButtonFrame adds JButtons to JFrame
	public ButtonFrame()
	{
		super("Testing Buttons");
		setLayout(new FlowLayout());
		
		plainJButton = new JButton("Plain Button"); // button with text
		add(plainJButton); // add plainJButton to JFrame
		
		/*this next two tatements assume that the image files are stored 
		 * in the same directory as the application
		 */

		Icon bug1 = new ImageIcon(getClass().getResource("bug1.gif"));
		Icon bug2 = new ImageIcon(getClass().getResource("bug2.gif"));
		
		fancyJButton = new JButton("Fancy Button", bug1); // set image
		
		/*By default, the text is displayed to the right of the icon*/
		
		/* rollover Icon is an Icon that’s displayed when the user 
		 * positions the mouse over the JButton .
		 */
		fancyJButton.setRolloverIcon(bug2); // set rollover image
		
		add(fancyJButton); // add fancyJButton to JFrame
		
		// create new ButtonHandler for button event handling
		//addActionListener to register it as the event handler 
		//for each JButton
		ButtonHandler handler = new ButtonHandler();
		fancyJButton.addActionListener(handler);
		plainJButton.addActionListener(handler);
	}

	// inner class for button event handling
	private class ButtonHandler implements ActionListener
	{
		// handle button event
		@Override
		public void actionPerformed(ActionEvent event)
		{
			JOptionPane.showMessageDialog( ButtonFrame.this , String.format(
			"You pressed: %s", event.getActionCommand() ));
		/*For a JButton event, ActionEvent method getActionCommand 
		 * returns the label on the JButton.
		 */
			
			/*ButtonFrame.this rather than null as the first argument
			 * causes the message dialog to appear centered over the
			 *  application’s window.
			 */
			
			/*When this argument is not null , it represents the so-called
			  parent GUI component of the message dialog 
			  (in this case the application window is the parent component)
			  and enables the dialog to be centered over that component
			  when the dialog is displayed. ButtonFrame.this 
			  represents the this reference of the object of top-level 
			  class ButtonFrame
			 */
			
			/* keyword this refers to the current inner-class object being
				manipulated. An inner-class method can use its outer-class
				 object’s this by preceding this with the outer-class name 
				 and a dot ( . ) separator, as in ButtonFrame.this . */
		
		}
	}	
} // end class ButtonFrame
	



