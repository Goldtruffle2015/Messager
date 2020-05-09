package Network;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Network {
	// -- Attributes -- //
	private ServerSocket serverSocket;	// Creates a server socket
	private int port;	// Defines the port
	
	// -- Constructor -- //
	public Server(String port) {
		this.port = Integer.parseInt(port);
		try {
			this.serverSocket = new ServerSocket(this.port);
			System.out.println("Listening...");
			this.socket = this.serverSocket.accept();
			System.out.println("Connected...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// -- Main -- //
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("You must input the port number.");
			return;
		}
		Server server = new Server(args[0]);
	}
}
