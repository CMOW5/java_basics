import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListFrame extends JFrame
{
	private final JList<String> colorJList; // list to display colors
	
	private static final String[] colorNames = {"Black", "Blue", "Cyan",
	"Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
	"Orange", "Pink", "Red", "White", "Yellow"};
	
	private static final Color[] colors = {Color.BLACK, Color.BLUE,
	Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN,
	Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
	Color.RED, Color.WHITE, Color.YELLOW};
	
	// ListFrame constructor add JScrollPane containing JList to JFrame
	public ListFrame()
	{
		super("List Test");
		setLayout(new FlowLayout());
		colorJList = new JList<String>(colorNames); // list of colorNames
		colorJList.setVisibleRowCount(5); // display five rows at once
		
		// do not allow multiple selections
		colorJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// add a JScrollPane containing JList to frame
		/*
		 * Unlike a JComboBox , a JList does not provide a scrollbar if 
		 * there are more items in the list than the number of visible rows.
		 * In this case, a JScrollPane object is used to provide
		 * the scrolling capability
		 */
		add(new JScrollPane(colorJList));
		/* The JScrollPane constructor receives as its argument the JComponent
		 * that needs scrolling functionality (in this case, colorJList ). 
		 * Notice in the screen captures that a scrollbar created by the JScrollPane
		 * appears at the right side of the JList . By default, the scrollbar
		 * appears only when the number of items in the JList exceeds the number
		 * of visible items.
		 */
		
		colorJList.addListSelectionListener(
			new ListSelectionListener() // anonymous inner class
			{
				// handle list selection events
				@Override
				public void valueChanged(ListSelectionEvent event)
				{
					getContentPane().setBackground(
					colors[ colorJList.getSelectedIndex() ]);
					/* Each JFrame actually consists of three layers—the
					 * background, the content pane and the glass pane. 
					 * The content pane appears in front of the background
					 * and is where the GUI components in the JFrame are displayed.
					 * The glass pane is used to display tool tips and other items
					 * that should appear in front of the GUI components on 
					 * the screen. The content pane completely hides the background 
					 * of the JFrame; thus, to change the background color behind 
					 * the GUI components, you must change the content pane’s 
					 * background color
					 */
				}
			}
		);
	}
} // end class ListFrame