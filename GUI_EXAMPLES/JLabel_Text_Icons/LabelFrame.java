
// JLabels with text and icons.
import java.awt.FlowLayout; // specifies how components are arranged
import javax.swing.JFrame; // provides basic window features
import javax.swing.JLabel; // displays text and images
import javax.swing.SwingConstants; // common constants used with Swing
import javax.swing.Icon; // interface used to manipulate images
import javax.swing.ImageIcon; // loads images

public class LabelFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private final JLabel label1; // JLabel with just text
	private final JLabel label2; // JLabel constructed with text and icon
	private final JLabel label3; // JLabel with added text and icon

	public LabelFrame()
	{
	
	//1. specify the window’s layout	
		// super-class JFrame ’s constructor uses this String 
		// as the text in the window’s title bar.
		super("Testing JLabel");
		
		/*
		 With the FlowLayout layout manager, components are placed 
		 in a container from left to right in the order in which they’re 
		 added. When no more components can fit on the current line, they 
		 continue to display left to right on the next line. 
		 If the container is resized, a FlowLayout reflows the components, 
		 possibly with fewer or more rows based on the new container width.
		 Every container has a default layout, 
		 which we’re changing for LabelFrame to a FlowLayout
		 */
		
		//	The argument to the method setLayout must be an object
		//	of a class that implements the LayoutManager interface 
		//	(e.g., FlowLayout )
		
		setLayout(new FlowLayout()); // set frame layout 
		
		
	//2. create and attach GUI components to the window		
		// JLabel constructor with a string argument
		label1 = new JLabel("Label with text");
		
		/*the tool tip that’s displayed when the user positions 
		  the mouse cursor over the JLabel in the GUI */
		label1.setToolTipText("This is label1");
		
		//attaches label1 to the LabelFrame
		add(label1); // add label1 to JFrame
		
		// JLabel constructor with string, Icon and alignment arguments
		Icon bug = new ImageIcon(getClass().getResource("bug1.png"));
		
		/*
		the expression getClass().getResource("bug1.png") invokes 
		method getClass (inherited indirectly from class Object ) 
		to retrieve a reference to the Class object that represents 
		the LabelFrame class declaration. That reference is then used 
		to invoke Class method getResource , which returns the location
		of the image as a URL. The ImageIcon constructor uses the URL 
		to locate the image, then loads it into memory
		*/
		
		label2 = new JLabel("Label with text and icon", bug,
		SwingConstants.LEFT);
		
		/*
		The last constructor argument SwingConstants.LEFT
		indicates that the label’s contents
		are left justified, or left aligned (i.e., the icon and
		text are at the left side of the label’s area on the screen)
		*/
		
		label2.setToolTipText("This is label2");
		add(label2); // add label2 to JFrame
		
		label3 = new JLabel(); // JLabel constructor no arguments
		label3.setText("Label with icon and text at bottom");
		label3.setIcon(bug); // add icon to JLabel
		
		/*
		By default, the text appears to the right of the image when
		a label contains both text and an image. The horizontal and 
		vertical alignments of a JLabel can be set with	methods 
		setHorizontalAlignment and setVerticalAlignment, respectively
		*/
		
		label3.setHorizontalTextPosition(SwingConstants.CENTER);
		label3.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		
		
		label3.setToolTipText("This is label3");
		add(label3); // add label3 to JFrame
		
	}
}
