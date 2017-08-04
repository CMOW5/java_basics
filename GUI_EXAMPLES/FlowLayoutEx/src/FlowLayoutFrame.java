import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

public class FlowLayoutFrame extends JFrame {
	
	private final JButton leftJButton; // button to set alignment left
	private final JButton centerJButton; // button to set alignment center
	private final JButton rightJButton; // button to set alignment right
	private final FlowLayout layout; // layout object
	private final Container container; // container to set layout

	public FlowLayoutFrame() {
		
		super("FlowLayout Demo");
		layout = new FlowLayout();
		container = getContentPane(); // get container to layout
		/*A container’s layout is set with method setLayout of class 
		 * Container. next line sets the layout manager to the 
		 * FlowLayout declared before. Normally, the layout is set 
		 * before any GUI components are added to a container.
		*/
		setLayout(layout);
		
		// set up leftJButton and register listener
		leftJButton = new JButton("Left");
		add(leftJButton); // add Left button to frame
		leftJButton.addActionListener(
			new ActionListener() // anonymous inner class
			{
				// process leftJButton event
				@Override
				public void actionPerformed(ActionEvent event)
				{
					layout.setAlignment(FlowLayout.LEFT);
					// realign attached components
					//specify that the JFrame
					//should be rearranged based on the adjusted layout
					layout.layoutContainer(container);
				}
			}
		);
	
		// set up centerJButton and register listener
		centerJButton = new JButton("Center");
		add(centerJButton); // add Center button to frame
		centerJButton.addActionListener(
			new ActionListener() // anonymous inner class
			{
				// process centerJButton event
				@Override
				public void actionPerformed(ActionEvent event)
				{
					layout.setAlignment(FlowLayout.CENTER);
					// realign attached components
					layout.layoutContainer(container);
				}
			}
		);
		
		// set up rightJButton and register listener
		rightJButton = new JButton("Right");
		add(rightJButton); // add Right button to frame
		rightJButton.addActionListener(
			new ActionListener() // anonymous inner class
			{
				// process rightJButton event
				@Override
				public void actionPerformed(ActionEvent event)
				{
					layout.setAlignment(FlowLayout.RIGHT);
					// realign attached components
					layout.layoutContainer(container);
				}
			}
		);
		
	}

}
