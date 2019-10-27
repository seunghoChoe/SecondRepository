package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;

	public void start() {
		try {
			this.serverSocket = new ServerSocket(Constant.PORTNUMBER);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void finish() {
		try {
			this.serverSocket.close();
			System.out.println("System exit...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			boolean bRunning = true;
			while (bRunning) {
				Socket clientSocket = this.serverSocket.accept();
				Session session = new Session();
				session.start(clientSocket);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
