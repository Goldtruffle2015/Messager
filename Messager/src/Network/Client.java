package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Network {
	// -- Attributes -- //
	private String ipv4;	// Defines the ipv4 address
	private int port;	// Defines the port
	
	// -- Constructor -- //
	public Client(String ipv4, String port) {
		this.ipv4 = ipv4;
		this.port = Integer.parseInt(port);
		try {
			this.socket = new Socket(InetAddress.getByName(this.ipv4), this.port);	// Create the socket
			this.output = new PrintWriter(this.socket.getOutputStream(), true);	// Handles outputting
			this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// -- Main -- //
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("You must input the ip address and port number.");
			return;
		}
		Client client = new Client(args[0], args[1]);
	}
}
