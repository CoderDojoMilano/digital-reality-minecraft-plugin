package mod;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_connection extends Thread{
	

	//private int port = 25566;
	
	private DataOutputStream outToClient;
	
	public TCP_connection(String ip, int port){
		System.out.println(ip + ":" + port);
	}
	
	//Makes new thread so as not to stop the server with an infinite loop
	@Override
    public void run() {
    	try{
	    	String clientSentence;
	        String capitalizedSentence;
	        @SuppressWarnings("resource")
			ServerSocket welcomeSocket = new ServerSocket(25566);
	        
	        while(true)
	        {
	           Socket connectionSocket = welcomeSocket.accept();
	           BufferedReader inFromClient =
	              new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	           outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	           clientSentence = inFromClient.readLine();
	           System.out.println("Received: " + clientSentence);
	           capitalizedSentence = clientSentence.toUpperCase() + '\n';
	           outToClient.writeBytes(capitalizedSentence);
	        }
    	}catch(IOException e){
    		System.out.println(e);
    	}
    }

	
	public void send(String output){
		try {
			System.out.println("sending " + output);
			outToClient.writeBytes(output + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


