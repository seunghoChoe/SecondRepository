package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import entity.Student;
import global.Constants;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	private RegistrationPanel registrationPanel;
	private LoginView loginView;
	private SettledPanel settledpanel;
	


	
	public MainView() {
		ActionHandler actionhandler = new ActionHandler();
		
		// set attributes
		this.setLocation(Constants.MAINVIEW_X, Constants.MAINVIEW_Y);
		this.setSize(Constants.MAINVIEW_W, Constants.MAINVIEW_H);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		// components
		this.registrationPanel = new RegistrationPanel(this.getWidth(), this.getHeight());
		this.registrationPanel.setVisible(false);
		this.add(this.registrationPanel,BorderLayout.CENTER);
		
		this.settledpanel = new SettledPanel(actionhandler);
		this.add(this.settledpanel,BorderLayout.NORTH);

		try {
			loginView = new LoginView(this, actionhandler);
			loginView.setVisible(true);
			
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	public void initialize() {
		this.registrationPanel.initialize();
	}
	
	class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent actionEvent) {
			if (actionEvent.getActionCommand().equals("OK")) {
				Student student = loginView.login();
				if(student != null) {
					registrationPanel.setVisible(true);
					settledpanel.changeLoginoutBt();
					settledpanel.writeUserName(student);
				}
			}
			else if (actionEvent.getActionCommand().equals("Cancel")) {
				loginView.cancel();
			}
			else if (actionEvent.getSource().equals(settledpanel.getBtLogout())) {
				registrationPanel.setVisible(false);
				settledpanel.changeLoginoutBt();
				settledpanel.clearUserName();
				
			}
			else if (actionEvent.getSource().equals(settledpanel.getBtLogin())) {
				loginView.setVisible(true);
			}
		}		
	}
}
