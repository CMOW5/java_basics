import gnu.io.*;
import java.awt.EventQueue;
//import javax.comm.CommPortIdentifier;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.awt.Font;


public class uba {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("main");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("new uba window");
					uba window = new uba();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public uba() {
		System.out.println("initialize uba");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Bill Acceptor Driver (ID003)");
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null); 
		
		final uart srlprt = new uart();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Serial Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 424, 130);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(12, 93, 78, 25);
		panel.add(btnOpen);
		
		JLabel lblNewLabel = new JLabel("Baud Rate:");
		lblNewLabel.setBounds(12, 36, 139, 25);
		panel.add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(250, 36, 44, 25);
		panel.add(lblPort);
		
		String[] BaudArray = {"9600","19200","38400"};
		final JComboBox<String> comboBox = new JComboBox(BaudArray);
		comboBox.setBounds(106, 36, 115, 25);
		panel.add(comboBox);
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(295, 36, 117, 25);
		panel.add(comboBox_1);
		
		JLabel lblBySydbernard = new JLabel("JCM");
		lblBySydbernard.setFont(new Font("DejaVu Serif", Font.BOLD | Font.ITALIC, 12));
		lblBySydbernard.setBounds(297, 98, 115, 15);
		panel.add(lblBySydbernard);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 154, 424, 101);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		// [+]
		JButton btnStart = new JButton("Return");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uart.accept = false;
				uart.rturn = true;
			}
		});
		btnStart.setBounds(119, 64, 104, 25);
		panel_1.add(btnStart);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uart.rturn = false;
				uart.accept = true;
			}
		});
		btnAccept.setBounds(12, 64, 95, 25);
		panel_1.add(btnAccept);
		
		JLabel lblBill = new JLabel("Bill:");
		lblBill.setBounds(231, 24, 43, 15);
		panel_1.add(lblBill);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(271, 24, 141, 15);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Firmware:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 267, 424, 44);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		final JLabel label = new JLabel("");
		label.setBounds(85, 17, 327, 15);
		panel_2.add(label);
		// [+]
		
		// [1] Event Open Serial port
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uart.baud = Integer.parseInt((String)comboBox.getSelectedItem());
				srlprt.openPort(comboBox_1.getSelectedItem().toString());	
			}
		});
		// ![1]
		
		// [2]
		MyClass c = new MyClass();
	    c.addMyEventListener(new MyEventListener() {	
	      public void myEventOccurred(MyEvent evt) {
	    	System.out.println("myEventOcurred");  
	        if(evt.getSource() == "version"){
	        	label.setText(new String(uart.version));
	        }else if (evt.getSource() == "bill"){
	        	lblNewLabel_1.setText(String.format("%d Córdobas", uart.bill));
	        }else if (evt.getSource() == "clearbill"){
	        	lblNewLabel_1.setText("");
	        }

	      }
	    });
	    // ![2]
	    
		// [0]
	    System.out.println("getPorList");
		uart.portList = CommPortIdentifier.getPortIdentifiers();
		while (uart.portList.hasMoreElements()) {
			uart.portId = (CommPortIdentifier) uart.portList.nextElement();
            	comboBox_1.addItem(uart.portId.getName().toString());
            	System.out.println(uart.portId.getName().toString());
        }
		// [0]
		
	}
}
