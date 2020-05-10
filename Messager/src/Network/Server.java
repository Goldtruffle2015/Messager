package Network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	// -- Attributes -- // 
	private String[] args;
	private ServerSocket serverSocket;
	private Socket socket;
	private ClientHandler[] clients = new ClientHandler[2];
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	// -- Constructor -- //
	public Server() throws IOException {
		// Server is listening on port 22 //
		this.serverSocket = new ServerSocket(22);
		System.out.println("Listening...");
		
		// Run infinite loop for getting client request //
		for (int i=0;i<2;i++) {
			this.socket = null;	// Predefines the socket 
			try {
				// Connect to client //
				this.socket = this.serverSocket.accept();	
				System.out.println("Connected to " + socket.getInetAddress().getHostName());
				
				// Get input and output //
				this.input = new ObjectInputStream(socket.getInputStream());	// Sets up pathway to send stuff out
				this.output = new ObjectOutputStream(socket.getOutputStream());	// Sets up pathway to send stuff in
				this.output.flush();
				System.out.println("Streams setup...");
				
				System.out.println("Assigning new thread...");
				
				// Create thread //
				this.clients[i] = new ClientHandler(this, this.socket, this.input, this.output);
				
				// Invoke start //
				this.clients[i].start();
			} catch (Exception e) {
				this.socket.close();
			}
		}
	}
	
	// -- Methods -- //
	public void sendAll(String msg) {
		for (ClientHandler current: this.clients) {
			try {
				current.writeMessage(msg);;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// -- Main -- //
	public static void main(String[] args) throws IOException {
		new Server();
	}
}

class ClientHandler extends Thread {
	// -- Attributes -- //
	final Server server;
	final Socket socket;
	final ObjectInputStream input;
	final ObjectOutputStream output;
	private String rec;	// Defines received primitive
	private String send;	// Defines sent primitive
	
	// -- Constructor -- //
	public ClientHandler(Server server, Socket socket, ObjectInputStream input, ObjectOutputStream output) {
		this.server = server;
		this.socket = socket;
		this.input = input;
		this.output = output;
	}
	
	// -- Methods -- //
	public void writeMessage(String msg) throws IOException {
		this.output.writeUTF(msg);;
	}
	
	@Override
	public void run() {
		// Infinite Loop //
		try {
			this.output.writeObject("Connected to Server...");;
			this.output.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while (true) {
			try {
				this.rec = (String) this.input.readObject();	// Reads input from client
				this.send = this.rec;	// Prepares message to be sent
				this.server.sendAll(this.send);	// Sends message to all clients connected to server
			} catch (IOException | ClassNotFoundException e) {
				;
			}
		}
	}
}
