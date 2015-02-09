package core_gui_and_threads;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * @author Yuri sKalinin the method for the input of password
 */
public class PasswordDialog {
	private boolean status = false;
	private String passwordMail;

	PasswordDialog() {

		JPasswordField passwordField = new JPasswordField(10);

		passwordField.setEchoChar('*');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		passwordMail = String.valueOf(passwordField.getPassword());
		if (!passwordMail.equals("")) {
			status = true;
		} else {
			status = false;
		}
	}

	public String getPasswordMail() {
		return passwordMail;
	}
	public boolean getStatus(){
		return status;
	}
}
