package client.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import client.Client.ActionHandler;
import client.model.Student;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;
	private LoginView loginView;
	private JButton btExit, btTest;
	
	public MainView(String name) {
		super(name);
		this.setBounds(100, 100, 300, 300);
		this.setLayout(new FlowLayout());
		
		this.loginView = new LoginView(this);
		this.btExit = new JButton("Exit");
		this.btExit.setActionCommand("Exit");
		this.add(this.btExit);
		
		this.btTest = new JButton("Test");
		this.btTest.setActionCommand("Test");
		this.add(this.btTest);
	}
	
	public void initialize() {
		this.loginView.initialize();
	}
	 public void setAllComponentVisible(boolean b) {
		this.setVisible(b);
		this.loginView.setAllComponentVisible(b);
	}

	public void setActionHandler(ActionHandler actionHandler) {
		this.btExit.addActionListener(actionHandler);
		this.btTest.addActionListener(actionHandler);
		this.loginView.setActionHandler(actionHandler);
	}

	public Student getLoginData() {
		return this.loginView.getLoginData();
	}

}
