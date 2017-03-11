package mod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SerialCom {
	static Process process = null;
	static OutputStream os = null;
	static InputStream is = null;
	
	
	public static void start(){
		if(process == null){
			String[] cmd = {
		      "python",
		      System.getProperty("user.dir") + "\\plugins\\redstone\\test.py",
		    };
			
			try {
				process = Runtime.getRuntime().exec(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
			is = process.getInputStream();
		}
	}
	
	public static void write(String command){		
			
		//Write:
		
		try{
		    PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "\\plugins\\redstone\\com.txt", "UTF-8");
		    writer.print(command);
		    writer.close();
		} catch (Exception e) {
		   // do something
		}
		
		//Read:
		
		
		//Read:
		/*
		 * 
		 * int b;
		try{
			while((b=is.read())!=-1)
		   	{
		      // converts integer to character
		      System.out.print((char)b);
	   		}
		}catch (Exception ex){
	         ex.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	    
		try{
			// new input stream created
			is = process.getInputStream();
		   
		   	System.out.println("Characters printed:");
		     
		   	// reads till the end of the stream
		   	while((i=is.read())!=-1)
		   	{
		      // converts integer to character
		      c=(char)i;
		      
		      // prints character
		      System.out.print(c);
	   		}
		   	System.out.println("Done");
		}catch(Exception e){
		   e.printStackTrace();
		}finally{
			if(is!=null){
				try {
					is.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
		
		
	}
	
}
