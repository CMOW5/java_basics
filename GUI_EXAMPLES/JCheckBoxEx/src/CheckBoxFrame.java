import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CheckBoxFrame extends JFrame {
	
	private final JTextField textField; // displays text in changing fonts
	private final JCheckBox boldJCheckBox; // to select/deselect bold
	private final JCheckBox italicJCheckBox; // to select/deselect italic
	
	public CheckBoxFrame()
	{
		super("JCheckBox Test");
		setLayout(new FlowLayout());
		
		// set up JTextField and set its font
		textField = new JTextField("Watch the font style change", 20);
		textField.setFont(new Font("Serif", Font.PLAIN, 14));
		/*
		"Serif" (a generic font name that represents a font such as Times and is sup-
				ported on all Java platforms)
				
		Font.PLAIN style and 14 -point size		
		*/
		
		
		add(textField); // add textField to JFrame
		
		boldJCheckBox = new JCheckBox("Bold");
		
		/*
		 * The String passed to the JCheckBox constructor is the checkbox
         * label that appears to the right of the JCheckBox by default.
		 */
		
		italicJCheckBox = new JCheckBox("Italic");
		add(boldJCheckBox); // add bold checkbox to JFrame
		add(italicJCheckBox); // add italic checkbox to JFrame
		
		// register listeners for JCheckBoxes
		CheckBoxHandler handler = new CheckBoxHandler();
		boldJCheckBox.addItemListener(handler);
		italicJCheckBox.addItemListener(handler);
	}
	
	// private inner class for ItemListener event handling
	private class CheckBoxHandler implements ItemListener
	{
		
		/* When the user clicks a JCheckBox , an ItemEvent occurs. 
		 * This event can be handled by an ItemListener object, 
		 * which must implement method itemStateChanged .
		*/
		@Override
		public void itemStateChanged(ItemEvent event)
		{
			Font font = null; // stores the new Font
			// determine which CheckBoxes are checked and create Font
			if ( boldJCheckBox.isSelected() && italicJCheckBox.isSelected() )
				font = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
			else if ( boldJCheckBox.isSelected() )
				font = new Font("Serif", Font.BOLD, 14);
			else if ( italicJCheckBox.isSelected() )
				font = new Font("Serif", Font.ITALIC, 14);
			else
				font = new Font("Serif", Font.PLAIN, 14);
				textField.setFont(font);
			}
	}

}
