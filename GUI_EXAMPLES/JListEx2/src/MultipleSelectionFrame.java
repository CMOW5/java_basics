import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
public class MultipleSelectionFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JList<String> colorJList; // list to hold color names
	private final JList<String> copyJList; // list to hold copied names
	private JButton copyJButton; // button to copy selected names
	
	private static final String[] colorNames = {"Black", "Blue", "Cyan",
	"Dark Gray", "Gray", "Green", "Light Gray", "Magenta", "Orange",
	"Pink", "Red", "White", "Yellow"};
	
	// MultipleSelectionFrame constructor
	public MultipleSelectionFrame()
	{
		super("Multiple Selection Lists");
		setLayout(new FlowLayout());
		
		colorJList = new JList<String>(colorNames); // list of color names
		colorJList.setVisibleRowCount(5); // show five rows
		colorJList.setSelectionMode(
				ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		add(new JScrollPane(colorJList)); // add list with scrollpane
		
		copyJButton = new JButton("Copy >>>");
		copyJButton.addActionListener(
			new ActionListener() // anonymous inner class
			{
				// handle button event
				@Override
				public void actionPerformed(ActionEvent event)
				{
					// place selected values in copyJList
					copyJList.setListData(
							colorJList.getSelectedValuesList().toArray(new String[0]));
			
				/*  getSelectedValuesList returns a List<String> (because the JList was
				 *  created as a JList<String> ) representing the selected items in colorJList.
				 *  We call the List<String> ’s toArray method to convert this into an 
				 *  array of String s that can be passed as the argument to copyJList’s
				 *  setListData method. List method toArray receives as its argument 
				 *  an array representing the type of array that the method will return
				 */
				}
			}
		);
		
		add(copyJButton); // add copy button to JFrame
		
		copyJList = new JList<String>(); // list to hold copied color names
		copyJList.setVisibleRowCount(5); // show 5 rows
		copyJList.setFixedCellWidth(100); // set width
		copyJList.setFixedCellHeight(15); // set height
		copyJList.setSelectionMode(
		ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		add(new JScrollPane(copyJList)); // add list with scrollpane
		
	}
} // end class MultipleSelectionFrame