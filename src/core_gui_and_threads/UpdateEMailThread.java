package core_gui_and_threads;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import utility.Run;
import func_core.*;

	/**
	 * @author Yuri Kalinin method for updating locally saved emails,
         * requires user password,
         * handles exceptions
	 */
public class UpdateEMailThread implements Runnable {
private String folder;
UpdateEMailThread(String folder){
	this.folder=folder;
}
	@Override
	public void run() {
		PasswordDialog password = new PasswordDialog();
		if (password.getStatus() == true) {
			MainWindow.setStatusBarLabel("Receiving emails");
			GetMailsServer readMail = new ServerMailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), password.getPasswordMail());

			try {
				readMail.connectionInbox(folder);
			} catch (MessagingException e) {
				JOptionPane.showMessageDialog(null, "Connection error", "Please check settings", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
				
			}

			try {
				readMail.getMessages();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Connection error", "Please check settings", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
				
			} catch (MessagingException e) {
				JOptionPane.showMessageDialog(null, "Connection error", "Please check settings", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Connection error", "Please check settings", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
			}
		}
		new Thread(new AddRowsThread(folder)).start();

	}

}
