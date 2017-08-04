
/*
 *	 This example should display a message dialog containing 
 *	 the text from a text field when the user presses 
 *   Enter in that text field
 */

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class TextFieldFrame extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private final JTextField textField1; // text field with set size
	private final JTextField textField2; // text field with text
	private final JTextField textField3; // text field with text and size
	private final JPasswordField passwordField; // password field with text
	
	// TextFieldFrame constructor adds JTextFields to JFrame
	public TextFieldFrame()
	{
		super("Testing JTextField and JPasswordField");
		setLayout(new FlowLayout());
		
		// construct text field with 10 columns
		textField1 = new JTextField(10); //columns = width
		/*
		 
		 A text column’s width in pixels is determined by the 
		 average width of a character in the text field’s current 
		 font. When text is displayed in a text field and the 
		 text is wider than the field itself, a portion of the 
		 text at the right side is not visible
		 
		 */
		
		add(textField1); // add textField1 to JFrame
		
		// construct text field with default text
		
		/*The width of the field is determined by the width 
		of the default text specified in the constructor*/
		
		textField2 = new JTextField("Enter text here");
		add(textField2); // add textField2 to JFrame
		
		// construct text field with default text and 21 columns
		
		/*the default text "Uneditable text field" to display 
		and the text field’s width in columns ( 21 )*/
		
		textField3 = new JTextField("Uneditable text field", 21);
		
		//the user cannot modify the text in the field
		textField3.setEditable(false); // disable editing
		add(textField3); // add textField3 to JFrame
		
		// construct password field with default text
		passwordField = new JPasswordField("Hidden text");
		add(passwordField); // add passwordField to JFrame
		
		// register event handlers
		/*
		Before an application can respond to an event for
		a particular GUI component, you must:
		1. Create a class that represents the event handler 
		and implements an appropriate interface—known as 
		an event-listener interface.
		2. Indicate that an object of the class from Step 1 
		should be notified when the event occurs—known as 
		registering the event handler.
		*/
		
		/*
		event-registration statements that specify handler as the event 
		handler for the three JTextField s and the JPasswordField .
		 */
		
		TextFieldHandler handler = new TextFieldHandler();
		
		/* method addActionListener to register the event handler 
		  for each component
		 
		 This method receives as its argument an ActionListener object, 
		 which can be an object of any class that implements
		 ActionListener	
		 
		In order to method actionPerformed to be called,
		the program must register this object as the event
        handler for each text field (addActionListener)
		 
		*/
		textField1.addActionListener(handler);
		textField2.addActionListener(handler);
		textField3.addActionListener(handler);
		passwordField.addActionListener(handler);
		
		//now the object handler listens for events
		
	}

	// private inner class for event handling
	/*
	Normally, a component’s supported events are described
	in the Java API documentation for that component’s class 
	and its superclasses. When the user presses Enter in
	a JTextField or JPasswordField , an ActionEvent occurs.
	
	Such an event is processed by an object that implements 
	the interface ActionListener
	
	*/
	private class TextFieldHandler implements ActionListener
	{
		// process text field events
		
		/*actionPerformed method specifies the tasks to perform
		when an	ActionEvent occurs
		
		actionPerformed method will be called automatically when the 
		user presses Enter in any of the GUI’s text fields.
		*/
		
		@Override
		public void actionPerformed(ActionEvent event)
		{
			String string = "";
			
			/* determine which text field generated the event each
			time actionPerformed is called */
			
			/* the system creates a unique ActionEvent object that contains
			information about the event that just occurred, 
			such as the event source and the text in the text field. 
			The system passes this ActionEvent object to the event 
			listener’s actionPerformed method */
			
			// user pressed Enter in JTextField textField1
			
			/*method getSource returns a reference
			to the event source*/
			
			if ( event.getSource() == textField1 ){ //Is the event source 
													//textField1 ?
				/*the == operator to determine if they refer to the same
				object (textField1)*/
				
				string = String.format("textField1: %s",
						event.getActionCommand() );
				
				/*getActionCommand to obtain the text the user typed in
				the text field that generated the event.*/
			}
			// user pressed Enter in JTextField textField2
			else if ( event.getSource() == textField2 )
				string = String.format("textField2: %s",
				event.getActionCommand() );
			
			// user pressed Enter in JTextField textField3
			else if ( event.getSource() == textField3 )
				string = String.format("textField3: %s",
				event.getActionCommand() );
			
				// user pressed Enter in JTextField passwordField
			else if ( event.getSource() == passwordField )
				string = String.format("passwordField: %s",
				event.getActionCommand() );
			
			// display JTextField content
			JOptionPane.showMessageDialog(null, string);
	} //end private inner class TextFieldHandler
} // end private inner class TextFieldHandler
	
	

} //end class TextFieldFrame
