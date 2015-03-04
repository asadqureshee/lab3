package lab4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class client { 
	public static void run () throws IOException {
		
		int filesize=6022386; 
		int bytesRead;
		int currentTot = 0;
		
		
		Socket socket = new Socket("172.16.69.1",55556); 
		
		byte [] bytearray = new byte [filesize];
		
		InputStream inputStream = socket.getInputStream();
		
		FileOutputStream fileos = new FileOutputStream("/Users/asadqureshi/Downloads/sample88.txt"); 
		BufferedOutputStream output = new BufferedOutputStream(fileos);
		bytesRead = inputStream.read(bytearray,0,bytearray.length);
		//current = bytesRead;
		
		currentTot = bytesRead;
		do { 
			bytesRead = inputStream.read(bytearray, currentTot, (bytearray.length-currentTot)); 
			if(bytesRead >= 0) 
				currentTot += bytesRead; 
			} 	while(bytesRead > -1); 
		
		
		
		output.write(bytearray, 0 , currentTot);
		output.flush();
		System.out.println("Bytes Read "+currentTot);
		output.close(); 
		socket.close();
		}
	}
