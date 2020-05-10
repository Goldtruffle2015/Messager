package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	}
	
	// -- Methods -- //
	public String getFromServer() throws Exception {
		return (String) this.input.readObject();
	}
	
	public void sendToServer(String msg) throws Exception {
		this.output.writeObject(msg);
	}
}