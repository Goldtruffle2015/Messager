# Messager9
## Step-by-step to running in CMD
### Run Server
1. Run Server
	
### Run Client
1. Navigate to src folder
2. Set the PATH_TO_FX
3. Compile the GUI class
	- javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml GUI/GUI.java
4. Run the GUI class
	- java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml GUI/GUI.java