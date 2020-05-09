# Messager
## Step-by-step to running in CMD
### Run Server
1. Navigate to src folder
2. Compile Parent class
	- javac Network/Network.java
3. Compile Server
	- javac -classpath . Network/Server.java
4. Run Server
	- java Network.Server <port>
### Run Client
1. Navigate to src folder
2. Compile Parent class
	- javac Network/Network.java
3. Compile Client
	- javac -classpath . Network/Server.java
4. Run Client
	- java Network.Client <ipv4><port>