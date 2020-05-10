package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {
	// -- Attributes -- //
	private final int WIDTH = 600;
	private final int HEIGHT = 600;
	
	// -- Start -- //
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();	// Lays out children vertically
		Scene scene = new Scene(root, this.WIDTH, this.HEIGHT, true);
		
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
