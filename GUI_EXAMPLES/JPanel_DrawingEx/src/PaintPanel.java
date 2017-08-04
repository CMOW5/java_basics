import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PaintPanel extends JPanel {

	// list of Point references
	private final ArrayList<Point> points = new ArrayList<>();
	/* we use an ArrayList of Points to store the location at which 
	 * each mouse drag event occurs.
	 * each point stores the coordinates of each mouse drag event
	 */
	
	// set up GUI and register mouse event handler
	public PaintPanel()
	{
		// handle frame mouse motion event
		addMouseMotionListener(
				new MouseMotionAdapter() // anonymous inner class
				{
					// store drag coordinates and repaint
					@Override
					public void mouseDragged(MouseEvent event)
					{
						points.add( event.getPoint() );
						repaint(); // repaint JFrame
						/*
						 * to indicate that the PaintPanel should be 
						 * refreshed on the screen as soon as possible
						 * with a call to the PaintPanel’s paintComponent
						 *  method.
						 */
					}
				}
		);
	}
	
	// draw ovals in a 4-by-4 bounding box at specified locations on window
	
	/*
	 * Method paintComponent, which receives a Graphics parameter, is
     * called automatically any time the PaintPanel needs to be displayed
     *  on the screen—such as when the GUI is first displayed—or 
     *  refreshed on the screen—such as when method repaint is called 
     *  or when the GUI component has been hidden by another window 
     *  on the screen and subsequently becomes visible again.
	 */
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); // clears drawing area
		// draw all points
		for (Point point : points)
		g.fillOval( point.x, point.y , 16, 16);
	}

}
