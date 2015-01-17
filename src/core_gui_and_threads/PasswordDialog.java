package core_gui_and_threads;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * @author Yuri sKalinin the method for the input of password
 */
public class PasswordDialog {

	private String passwordMail=null;

	PasswordDialog() {
		
		JPasswordField passwordField = new JPasswordField(10);
		while(!passwordMail.equals("")){
			
		passwordField.setEchoChar('*');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		
			passwordMail = String.valueOf(passwordField.getPassword());
			
		}
	}

	public String getPasswordMail() {
		return passwordMail;
	}
}
