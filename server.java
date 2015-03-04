package lab4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server{ 
	
	
	public static void run (String filename) throws IOException{
		ServerSocket serverSocket = new ServerSocket(55556);
		Socket socket = serverSocket.accept(); 
	
		System.out.println("Connection accepted: " + socket);
		
		File transferFile = new File (filename); 
		byte [] bytearray = new byte [(int)transferFile.length()]; 
		FileInputStream input = new FileInputStream(transferFile); 
		BufferedInputStream binput = new BufferedInputStream(input); 
		binput.read(bytearray,0,bytearray.length); 
		OutputStream output = socket.getOutputStream();
		
		System.out.println("Sending File");
		
		System.out.println(" bytes "+bytearray.length);
		
		output.write(bytearray,0,bytearray.length); 
		output.flush(); 
		
		socket.close(); 
		System.out.println("File successfuly sent");
		
	}
	
} 
