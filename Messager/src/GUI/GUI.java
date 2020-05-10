package GUI;

import Network.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
	// -- Attributes -- //
	private static Scene scene;
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	private static VBox root;	
	private static TextArea textArea;	// Displays messages
	private static TextField textField;	// Input messages
	private static Client client;	// Instantiates the client
	
	// -- Start -- //
	@Override
	public void start(Stage primaryStage) throws Exception {
		client = new Client("192.168.1.71");
		
		textArea = new TextArea();
		textArea.setPrefHeight(560);
		textField = new TextField();
		
		// Send Messages //
		textField.setOnAction(event -> {
			try {
				client.sendToServer(textField.getText());
				textField.clear();
			} catch (Exception e) {
				textArea.appendText("Exception occured...");
			}
		});
		
		// Show Messages //
		(new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						textArea.appendText(client.getFromServer());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		})).start();
		
		root = new VBox(20, textArea, textField);	// Lays out children vertically
		
		scene = new Scene(root, WIDTH, HEIGHT, true);
		
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
