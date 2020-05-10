package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	// -- Attributes -- //
	private String ipv4;	// Defines the ipv4 address
	private int port;	// Defines the port
	private Socket socket;
	private ObjectInputStream input;	// Handles inputting
	private ObjectOutputStream output;	// Handles outputting
	
	// -- Constructor -- //
	public Client(String ipv4) throws UnknownHostException, IOException, ClassNotFoundException {
		this.ipv4 = ipv4;
		this.port = 22;
		this.socket = new Socket(InetAddress.getByName(this.ipv4), this.port);	// Create the socket
		this.output = new ObjectOutputStream(this.socket.getOutputStream());
		this.input = new ObjectInputStream(this.socket.getInputStream());
		System.out.println(this.input.readObject());
	}
	
	// -- Main -- //
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		if (args.length != 1) {
			System.out.println("You must input the ip address and port number.");
			return;
		}
		new Client(args[0]);
	}
}