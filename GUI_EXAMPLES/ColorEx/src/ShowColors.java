import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class ShowColors extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton changeBtnColor;
	private Color color = Color.LIGHT_GRAY;
	private Container container;
	
	public ShowColors() {
		super("JColorChooser Example");
		
		container = getContentPane();
		container.setLayout(new FlowLayout());
		
		
		
		changeBtnColor = new JButton("Change Color");
		changeBtnColor.addActionListener( new ActionListener() { //inner anonymous class
			
			//event handler when changeBtnColor is clicked
			@Override
			public void actionPerformed(ActionEvent e) {
				
				color = JColorChooser.showDialog(ShowColors.this, "Select a color", color);
				if (color == null)
					color = Color.LIGHT_GRAY;
					
				container.setBackground(color);
			}
			
		} //end inner class
		); //end addActionListener call
		
		container.add(changeBtnColor);
		setSize(400, 130);
		setVisible(true);
		
	} //end class constructor

	public static void main(String[] args) {
		ShowColors application = new ShowColors();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
