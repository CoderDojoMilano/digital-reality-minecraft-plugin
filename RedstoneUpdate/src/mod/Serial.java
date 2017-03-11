package mod;
import java.io.*;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortList;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import java.io.Console;

public class Serial {
	
	static String output = "13;";
	
	static boolean waiting = true;

    public static SerialPort serialPort;
    

    public static void Start(){
    	// getting serial ports list into the array
    	String[] portNames = SerialPortList.getPortNames();
    	        
    	if (portNames.length == 0) {
    	    System.out.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
    	    System.out.println("Press Enter to exit...");
    	    try {
    	        System.in.read();
    	    } catch (IOException e) {
    	          e.printStackTrace();
    	    }
    	    return;
    	}

    	for (int i = 0; i < portNames.length; i++){
    	    System.out.println(portNames[i]);
    	}

    	
    	serialPort = new SerialPort("/dev/tty.usbmodem1421");
    	try {
    	    serialPort.openPort();

    	    serialPort.setParams(SerialPort.BAUDRATE_9600,
    	                         SerialPort.DATABITS_8,
    	                         SerialPort.STOPBITS_1,
    	                         SerialPort.PARITY_NONE);

    	    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
    	                                  SerialPort.FLOWCONTROL_RTSCTS_OUT);

    	    serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
    	    
	    	//serialPort.writeString("Hurrah!");
    	}
    	catch (SerialPortException ex) {
    	    System.out.println("Error wile writing string to port: " + ex);
    	}
    }
    
    public static void write(String str){
			try {
				System.out.println("Out: " + str);
				serialPort.writeString(str);
			} catch (SerialPortException e) {
				e.printStackTrace();
			}
    }
    
    private static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    String receivedData = serialPort.readString(event.getEventValue());
                    System.out.println("Received response: " + receivedData);
                    
                    if(receivedData.charAt(0) == '!'){
                    	 System.out.println("Reciving input: " + receivedData);
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving string from COM-port: " + ex);
                }
            }
        }

    }
}
