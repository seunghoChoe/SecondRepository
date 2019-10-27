package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Session extends Thread {
	private Socket clientSocket;

	private OutputStream outputStream;
	private InputStream inputStream;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;

	public Session() throws IOException {

	}

	public void start(Socket clientSocket) {
		this.clientSocket = clientSocket;

		// create Inputstream
		try {
			inputStream = this.clientSocket.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);
			// create OutputStream
			outputStream = this.clientSocket.getOutputStream();
			printWriter = new PrintWriter(outputStream, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.start();
	}

	public void finish() {
		try {
			this.inputStreamReader.close();
			this.printWriter.close();
			this.clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		System.out.println("Server : Client Session Start");
		boolean bRunning = true;
		String message = new String();
		while (bRunning) {
			// read line
			try {
				String line = new String();
				while (!line.equals("end")) {
					line = bufferedReader.readLine();
					if (!line.equals("end")) {
						message += line + '\n';
					} else {
						message += line;
					}
				}
				PrtocolParser parse = new PrtocolParser(message);
				System.out.println(message);

				// write line
				printWriter.println(message);
				printWriter.flush();
				message = new String();

				// exit block
				if (line.equals("quit")) {
					bRunning = false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.finish();
		System.out.println("Server : Client Socket End");
	}

}
