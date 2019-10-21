package view;

import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.StudentControl;
import entity.Student;
import global.Constants;
import view.MainView.ActionHandler;

public class LoginView extends JDialog  {
	private static final long serialVersionUID = 1L;
	// controller
	private StudentControl studentControl;
	// sub-components
	private JLabel lbUserName, lbPassword;
	private JTextField tfUserName; 
	private JPasswordField tfPassword;
	private JButton btOk, btCancel;
	
	public LoginView(JFrame frame, ActionHandler actionHandler) throws FileNotFoundException {
		super(frame, Constants.LOGINVIEW_TITLE, true);
		// attributes
		this.setLocationRelativeTo(frame);
		this.setSize(200,150);
		this.setLayout(new FlowLayout());;
		this.setResizable(false);
		// add sub-components
		this.lbUserName = new JLabel("아이디");
		this.add(this.lbUserName);		
		this.tfUserName = new JTextField(10);
		this.add(this.tfUserName);		
		this.lbPassword = new JLabel("비밀번호");
		this.add(this.lbPassword);		
		this.tfPassword = new JPasswordField(10);
		this.add(this.tfPassword);
		
		this.btOk = new JButton("확인");
		this.add(this.btOk);
		this.btOk.setActionCommand("OK");
		this.btOk.addActionListener(actionHandler);
		
		this.btCancel = new JButton("취소");		
		this.add(this.btCancel);
		this.btCancel.setActionCommand("Cancel");
		this.btCancel.addActionListener(actionHandler);

		this.studentControl = new StudentControl();
	}
	
	public Student login() {
		String userName = this.tfUserName.getText();
		String password = 
				new String(this.tfPassword.getPassword());
		Student student = 
				this.studentControl.login(userName, password);
		if (student == null) {
			JOptionPane.showMessageDialog(this, "잘못 입력", "", JOptionPane.ERROR_MESSAGE);
		} else {
			this.dispose();
			this.tfUserName.setText(null);
			this.tfPassword.setText(null);
			return student;
		}
		this.tfPassword.setText(null);
		return null;
	}
	public void cancel() {
		this.tfUserName.setText(null);
		this.tfPassword.setText(null);
		this.dispose();
	}
	
	
}
