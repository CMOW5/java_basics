import java.io.*;
import java.util.*;
//import javax.comm.*;
import gnu.io.*;

public class uart extends protocol implements Runnable, SerialPortEventListener{
	
	static Enumeration portList;
	static CommPortIdentifier portId;
	static SerialPort serialPort;
    static OutputStream outputStream;
    static InputStream inputStream;
    static String messageString = "Hello, world!\n";
    static int baud;
    
	Thread readThread;
    
    private byte[] bty = new byte[100];
    private int itm = 0;
    private int lng= 0; 
	  
	private enum state{ SYNC, LENGTH, DATA, CHK}
	private state flg1 = state.SYNC;
	
	uart(){

	}
	
	public void run() {
		
		
		while(true){
			System.out.println("uart run");
			try {
				serialTx(uart.msgmaster); //manda trama
				
				String str = Arrays.toString(uart.msgmaster);
				System.out.println(str);
				//System.out.println(uart.msgmaster)
				for(int i=0; i < uart.msgmaster.length; i++){
				    //stringArray[i] = Integer.toHexString(uart.msgmaster[i]);
					System.out.print(Integer.toHexString(uart.msgmaster[i]));
					System.out.print(" , ");
				}
				System.out.println();
				
				if(uart.msgmaster[2] == 0x50 ){ //0x50 ==  ACK
					this.id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
					System.out.println("ACK");
				}
				Thread.sleep(200);
			} catch (InterruptedException e) {System.out.println(e);}
		}
	}
	
	public void serialTx(byte[] msg) {

        try {
        
        	outputStream.write(msg,0,msg[1]);
        
        } catch (IOException e) {}
       
	}
	
	public void openPort (String prt){
		
		uart.portList =  CommPortIdentifier.getPortIdentifiers();
		
		while (uart.portList.hasMoreElements()) {
			uart.portId = (CommPortIdentifier) uart.portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(prt)) {
                	
                	// [0]          
                    try {
                        serialPort = (SerialPort) portId.open("srlport", 2000);
                        System.out.println("Serial port "+portId.getName()+ " opened");
                    } catch (PortInUseException e) {}
                    try {
                        inputStream = serialPort.getInputStream();
                        System.out.println("InputStream created");
                    } catch (IOException e) {System.out.println(e);}
                    try {
                        outputStream = serialPort.getOutputStream();
                        System.out.println("OutputStream created");
                    } catch (IOException e) {}
                    
                    /*
                     * Expresses interest in receiving notification 
                     * when input data is available. This may be used 
                     * to drive asynchronous input. When data is available
                     *  in the input buffer, this event is propagated to 
                     *  the listener registered using addEventListener. 
                     */
                    
                    serialPort.notifyOnDataAvailable(true);
                    try {
                        serialPort.setSerialPortParams(uart.baud/*9600*/,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_EVEN);
                        System.out.println("Port configured");
                    } catch (UnsupportedCommOperationException e) {}
                    // [0]         
                    try {
            			serialPort.addEventListener(this);
            			System.out.println("Port Listening");
            		} catch (TooManyListenersException e) {
            			e.printStackTrace();
            		}
                    
                    readThread = new Thread(this);
                    System.out.println("Before start");
                    readThread.start();
                    
                }
            }
        }
	  
     }
	
	public void serialEvent(SerialPortEvent event){
		// System.out.println("Serial event");
	       switch(event.getEventType()) {
	        case SerialPortEvent.BI:
	        case SerialPortEvent.OE:
	        case SerialPortEvent.FE:
	        case SerialPortEvent.PE:
	        case SerialPortEvent.CD:
	        case SerialPortEvent.CTS:
	        case SerialPortEvent.DSR:
	        case SerialPortEvent.RI:
	        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
	            break;
	        case SerialPortEvent.DATA_AVAILABLE:
	            byte[] readBuffer = new byte[100];

	            try {
	                while (inputStream.available() > 0) {
	                	
	                	/* returns the total number of bytes read into
	                	 * the buffer 
	                	 */
	                    int numBytes = inputStream.read(readBuffer);
	                    
	                    // [TEST]

	                    for(int j=0; j < numBytes ;j++){
	                    	
		                    if (flg1 == state.DATA) {
		                    	lng--;
		                    	bty[itm++] = readBuffer[j];
		                    	
		                    	if(lng <= 0){
		                    		flg1 = state.SYNC;
		                    		lng = 0;
		                    		itm = 0;
		                    		this.receiving(bty);
		                    		
		                    	}
		                    	continue;
		                    }
		                    
		                    if (flg1 == state.LENGTH) {	
		                    	flg1 = state.DATA;
		                    	bty[itm++] = readBuffer[j];
		                    	lng = (int)readBuffer[j] - 2;
		                    	continue;
		                    }
	                    	
	                    	if ((readBuffer[j] == (byte) 0xFC) && (flg1 == state.SYNC)) {
	                    		flg1 = state.LENGTH;
	                    		bty[itm++] = readBuffer[j];
	                    		continue;
	                    	}
	                    	       	
	                    }
	                    // ![TEST]
	                    
	                }

	            } catch (IOException e) {System.out.println(e);}
	            break;
	        }
	}

	
}
