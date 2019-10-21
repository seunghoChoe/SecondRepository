package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Student;
import global.Constants;
import view.MainView.ActionHandler;


public class SettledPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel loginoutBtPanel;
	private JPanel usernameTfPanel;
	private JButton btLogout;
	private JButton btLogin;
	private JLabel lbUserName;

	
	
	
	
	public JButton getBtLogout() {return btLogout;}
	public void setBtLogout(JButton btLogout) {this.btLogout = btLogout;}
	public JButton getBtLogin() {return btLogin;}
	public void setBtLogin(JButton btLogin) {this.btLogin = btLogin;}

	public SettledPanel(ActionHandler actionhandler) {
		this.setLayout(new GridLayout(1,5));
			JPanel emptyPanel;
			emptyPanel = new JPanel();
			this.add(emptyPanel);
			emptyPanel = new JPanel();
			this.add(emptyPanel);
			emptyPanel = new JPanel();
			this.add(emptyPanel);
			usernameTfPanel = new JPanel();
			this.add(usernameTfPanel);
				lbUserName = new JLabel();
				usernameTfPanel.add(lbUserName);
			
			loginoutBtPanel = new JPanel();
			this.add(loginoutBtPanel);
				this.btLogout = new JButton(Constants.BTITLE_LOGOUT);
				this.btLogout.addActionListener(actionhandler);
				this.btLogin = new JButton(Constants.BTITLE_LOGIN);
				this.btLogin.addActionListener(actionhandler);
				loginoutBtPanel.add(this.btLogin);
	}
	public void changeLoginoutBt() {
		if(loginoutBtPanel.getComponent(0).equals(btLogin)) {
			loginoutBtPanel.remove(btLogin);
			loginoutBtPanel.add(btLogout);
		}
		else if(loginoutBtPanel.getComponent(0).equals(btLogout)) {
			loginoutBtPanel.remove(btLogout);
			loginoutBtPanel.add(btLogin);
		}
		loginoutBtPanel.updateUI();

	}
	public void writeUserName(Student student) {
		lbUserName.setText(student.getUserName() +" ´Ô ¹Ý°©½À´Ï´Ù.");
		
	}
	public void clearUserName() {
		lbUserName.setText(null);

	}
}
