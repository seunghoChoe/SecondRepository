package client.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.Client.ActionHandler;
import client.model.Student;


public class LoginView extends JDialog  {
	private static final long serialVersionUID = 1L;
	// controller
//	private StudentControl studentControl;
	// sub-components
	private JLabel lbUserName, lbPassword;
	private JTextField tfUserName; 
	private JPasswordField tfPassword;
	private JButton btOk, btCancel;
	
	public LoginView(JFrame frame){
		super(frame, "Login", true);
		// attributes
		this.setLocationRelativeTo(frame);
		this.setSize(180,150);
		this.setLayout(new FlowLayout());;
		this.setResizable(false);
		// add sub-components
		this.lbUserName = new JLabel("ID");
		this.add(this.lbUserName);		
		this.tfUserName = new JTextField(10);
		this.add(this.tfUserName);		
		this.lbPassword = new JLabel("PW");
		this.add(this.lbPassword);		
		this.tfPassword = new JPasswordField(10);
		this.add(this.tfPassword);
		
		this.btOk = new JButton("Login");
		this.btOk.setActionCommand("Login");
		this.add(this.btOk);
		
		this.btCancel = new JButton("Cancel");		
		this.btCancel.setActionCommand("Cancel");
		this.add(this.btCancel);
		
		

//		this.studentControl = new StudentControl();
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void setAllComponentVisible(boolean b) {
		this.setVisible(b);
	}

	public void setActionHandler(ActionHandler actionHandler) {
		this.btOk.addActionListener(actionHandler);
		this.btCancel.addActionListener(actionHandler);
	}

	public Student login() {
		
//		Student student = 
//				this.studentControl.login(userName, password);
//		if (student == null) {
//			JOptionPane.showMessageDialog(this, "�߸� �Է�", "", JOptionPane.ERROR_MESSAGE);
//		} else {
//			this.dispose();
//			this.tfUserName.setText(null);
//			this.tfPassword.setText(null);
//			return student;
//		}
//		this.tfPassword.setText(null);
		return null;
	}
	public void cancel() {
		this.tfUserName.setText(null);
		this.tfPassword.setText(null);
		this.dispose();
	}

	public Student getLoginData() {
		Student student = new Student();
		student.setUserName(this.tfUserName.getText());
		student.setPassword(new String(this.tfPassword.getPassword()));
		return student;
	}
	
	
}
