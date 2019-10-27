package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import client.model.Student;
import client.view.MainView;
import server.Constant;

public class Client {
	// attribute : 해당 객체가 갖는 고유의 값.
	// attribute - server
	private String IP;
	private int portNumber;

	// components
	private BufferedReader keyBoard;

	private Socket socket;
	private PrintWriter printWriter;
	private BufferedReader bufferedReader;

	// view
	private MainView mainFrame;
	// Eventhandler
	private ActionHandler actionHandler;

	public Client() {
		// set Attributes
		this.IP = Constant.IP;
		this.portNumber = Constant.PORTNUMBER;

		try {
			this.socket = new Socket(this.IP, portNumber);
			System.out.println("Client : Server is connected");
			// initialize Stream
			this.keyBoard = new BufferedReader(new InputStreamReader(System.in));
			this.printWriter = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

			// initialize View
			this.mainFrame = new MainView("Client");
			// initialize EventListener
			this.actionHandler = new ActionHandler();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize() {
		this.mainFrame.initialize();
		this.mainFrame.setActionHandler(this.actionHandler);
	}

	public void setAllComponentVisible(boolean b) {
		this.mainFrame.setAllComponentVisible(b);

	}

	public void finish() {
		try {
			this.mainFrame.setVisible(false);
			this.mainFrame.dispose();
			// close
			this.keyBoard.close();
			this.printWriter.close();
			this.bufferedReader.close();

			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void test(String message) throws IOException {
		printWriter.println(message);
		printWriter.flush();
		System.out.println("Client PrintWriter : " + message);

		System.out.println("Client : " + bufferedReader.readLine());
	}

	public void getLoginData() throws IOException {
		Student student = this.mainFrame.getLoginData();
		printWriter.println("method=login");
		printWriter.println("userName=" + student.getUserName());
		printWriter.println("password=" + student.getPassword());
		printWriter.println("end");
		printWriter.flush();
		String line = new String();
		String message = new String();
		while(!line.equals("end")) {
			line = bufferedReader.readLine();
			if(!line.equals("end")) {
				message += line + '\n';
			}else {
				message += line;
			}
			
		}
		System.out.println(message);
		System.out.println(student.getUserName() + "=" + student.getPassword());
	}

	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("action");
			try {
				
				if (e.getActionCommand().equals("Exit")) {
					System.out.println("action : finish");
					finish();
				} else if (e.getActionCommand().equals("Test")) {
					System.out.println("action : test");
					test("Test");
				} else if (e.getActionCommand().equals("Login")) {
					System.out.println("action : Login");
					getLoginData();
				}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
