package mod;
import java.io.InputStream;
import java.io.OutputStream;

//import com.fazecast.jSerialComm.*;

import com.fazecast.jSerialComm.SerialPort;

public class Serial {
	SerialPort comPort = null;
	
	OutputStream out;
	InputStream in;
	
	public void start(){
		System.out.println("Starting serial...");
		comPort = SerialPort.getCommPort("COM4");
		System.out.println(comPort.getSystemPortName());
		comPort.openPort();
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		out = comPort.getOutputStream();
		
		//out = comPort.getOutputStream();
		//in = comPort.getInputStream();
		/*
		comPort.addDataListener(new SerialPortDataListener() {
			   @Override
			   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
			   @Override
			   public void serialEvent(SerialPortEvent event)
			   {
			      if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
			         return;
			      try {
					System.out.print((char)in.read());
				} catch (IOException e) {
					e.printStackTrace();
				}
			   }
			});*/
	}
	
	public void send(String data){
		try
		{
			out.write(data.getBytes());
			System.out.println(data);
			
		} catch (Exception e) { e.printStackTrace(); }
	}
}
