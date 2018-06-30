import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelFrame extends JFrame {

	private final JPanel buttonJPanel; // panel to hold buttons
	private final JButton[] buttons;
	
	public PanelFrame() {
		super("Panel Demo");
		buttons = new JButton[5];
		buttonJPanel = new JPanel();
		buttonJPanel.setLayout(new GridLayout(1, buttons.length));
		
		// create and add buttons
		for (int count = 0; count < buttons.length; count++)
		{
			buttons[count] = new JButton("Button " + (count + 1));
			buttonJPanel.add(buttons[count]); // add button to panel
		}
		//class JPanel does not have a
		//content pane, unlike a JFrame
		add(buttonJPanel, BorderLayout.SOUTH); // add panel to JFrame
	}

}
