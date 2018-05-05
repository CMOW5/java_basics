import java.util.Arrays;

public class protocol extends kermit{
	public static byte[] msgmaster = new byte[10];
	public static int bill;
	static byte[] version =new byte[50];
	
	static boolean accept = false;
	static boolean rturn = false;

	protocol (){
		msgmaster[0]= (byte)0xFC;
		msgmaster[1]= (byte)0x05;
		msgmaster[2]= (byte)0x11;
		msgmaster[3]= (byte)0x27;
		msgmaster[4]= (byte)0x56;
	}
	public void receiving(byte[] bty){
		byte[] byteArray = Arrays.copyOf(bty, bty[1]);

		if(this.compareCRC(byteArray)){
			System.out.print("Yes");
			processing(bty);
		}else {
			System.out.print("No");
			return;
		}
	}
	
	void id003_format(byte LNG, byte CMD, byte[] DATA)
	{
		DATA[0] = (byte)0xFC;
		DATA[1] = LNG;
		DATA[2] = CMD;
		DATA[3] = 0;
		DATA[4] = 0;
		int chkres = this.crc_kermit(DATA, (byte)(LNG - 2));
		
		DATA[LNG-2] = (byte) (chkres & 0xFF);
		DATA[LNG-1] = (byte) ((chkres >> 8) & 0xFF);
	}
	
	int bill_value(byte _bill_)
	{
		int bll = 0;
        switch(_bill_)
        {
            case 0x61: 
            	bll = 10; // 10 Córdobas
            	break;
            case 0x62:
            	bll = 20; // 20 Córdobas
            	break;
            case 0x63: 
            	bll = 50; // 50 Córdobas
            	break;
            case 0x64:
            	bll = 100; // 100 Córdobas
            	break;
            case 0x65: 
            	bll = 200; // 200 Córdobas
            	break;
            case 0x66:
            	bll = 500; // 500 Córdobas
            	break;
            default:
            	
            	break;
        }
        return bll;
	}
	
    public void processing(byte[] b)
    {
        switch(b[2])
        {
            case 0x50: // ACK
            	id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	break;
            case 0x1B: // INITIALIZE
            	id003_format((byte)5, (byte) 0x88, msgmaster); //SSR_VERSION_REQUEST
            	break;
            case 0x11: // IDLING
            	id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	break;
            case 0x12: // ACCEPTING
            	id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	break;
            case 0x13: // ESCROW
            	bill = bill_value(b[3]); //bill = b[3];
            	
            	MyClass.fireMyEvent(new MyEvent("bill"));
            	protocol.accept = false;
            	protocol.rturn = false;
            	id003_format((byte)5, (byte) 0x44, msgmaster); //HOLD
            	//id003_format((byte)5, (byte) 0x41, msgmaster); //STACK1
            	break;
            case 0x14: // STACKING
            	id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	break;
            case 0x15: // VEND_VALID
            	id003_format((byte)5, (byte) 0x50, msgmaster); //ACK
            	
            	//id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	
            	break;
            case 0x16: // STACKED
            	id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	MyClass.fireMyEvent(new MyEvent("clearbill"));
            	break;
            case 0x17: // REJECTING
            	break;
            case 0x18: // RETURNING
            	id003_format((byte)5, (byte) 0x11, msgmaster); //STATUS_REQUEST
            	MyClass.fireMyEvent(new MyEvent("clearbill"));
            	break;
            case 0x19: // HOLDING
            	if( (protocol.accept == false) && (protocol.rturn == false) ){
            		id003_format((byte)5, (byte) 0x44, msgmaster); //HOLD
            	}else if(protocol.rturn == true){
            		id003_format((byte)5, (byte) 0x43, msgmaster); //RETURN
            	}else if(protocol.accept == true){
            		id003_format((byte)5, (byte) 0x41, msgmaster); //STACK1
            	}
            	protocol.accept = false;
            	protocol.rturn = false;
            	break;
            case 0x43: // STACKER_FULL
            	break;
            case 0x45: // JAM_IN_ACCEPTOR
            	break;
            case (byte)0x83: // SSR_INHIBIT_ACCEPTOR
            	id003_format((byte)5, (byte) 0x11, msgmaster); // STATUS_REQUEST
            	break;
            case (byte)0xC3: // CMD_INHIBIT_ACCEPTOR
            	id003_format((byte)5, (byte) 0x83, msgmaster); // SSR_INHIBIT_ACCEPTOR
            	break;
            case 0x40: // POWER_UP
            	id003_format((byte)5, (byte) 0x40, msgmaster); // RESET_ 
            	break;
            case 0x41: case 0x42: // POWER_UP_WITH_BILL_IN_ACCEPTOR * POWER_UP_WITH_BILL_IN_STACKER
            	break;
            case (byte)0x88: // SSR_VERSION_INFORMATION
            	id003_format((byte)5, (byte) 0x89, msgmaster); // SSR_BOOT_VERSION_REQUEST
            	System.arraycopy(b, 3, protocol.version, 0,b[1]-5);

            	MyClass.fireMyEvent(new MyEvent("version"));
            	break;
            case (byte)0x80: // SSR_EN_DIS_DENOMI
            	id003_format((byte)5, (byte) 0x81, msgmaster); // SSR_SECURITY_DENOMI
            	break;
            case (byte)0x81: // SSR_SECURITY_DENOMI
            	id003_format((byte)5, (byte) 0x85, msgmaster); // SSR_OPTIONAL_FUNCTION
            	break;
            case (byte)0x85: // SSR_OPTIONAL_FUNCTION
            	id003_format((byte)5, (byte) 0x84, msgmaster); // SSR_DIRECTION
            	break;
            case (byte)0x84: // SSR_DIRECTION
            	id003_format((byte)6, (byte) 0xC3, msgmaster); // CMD_INHIBIT_ACCEPTOR
            	break;
            case (byte)0xC5: // CMD_OPTIONAL_FUNCTION
            	id003_format((byte)5, (byte) 0x80, msgmaster); // SSR_EN_DIS_DENOMI
            	break;
            case (byte)0x89: // SSR_BOOT_VERSION_INFORMATION
            	id003_format((byte)5, (byte) 0x8A, msgmaster); // SSR_BILL_TABLE
            	break;
            case (byte)0x8A: // SSR_BILL_TABLE
            	id003_format((byte)7, (byte) 0xC0, msgmaster); // CMD_EN_DIS_DENOMI
            	break;
            case (byte)0xC0: // CMD_EN_DIS_DENOMI
            	id003_format((byte)7, (byte) 0xC1, msgmaster); // CMD_SECURITY_DENOMI
            	break;
            case (byte)0xC1: // CMD_SECURITY_DENOMI
            	id003_format((byte)6, (byte) 0xC4, msgmaster); // CMD_DIRECTION
            	break;
            case (byte)0xC4: // CMD_DIRECTION
            	id003_format((byte)7, (byte) 0xC5, msgmaster); // CMD_OPTIONAL_FUNCTION
            	break;
            default:
            	id003_format((byte)5, (byte) 0x11, msgmaster); // STATUS_REQUEST
            	break;
        }

    }
	
}
