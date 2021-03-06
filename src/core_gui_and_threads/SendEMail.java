package core_gui_and_threads;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import utility.Run;
import func_core.GetMailsServer;
import func_core.ServerMailReader;

public class SendEMail implements Runnable {

	@Override
	public void run() {
		PasswordDialog password = new PasswordDialog();
		if (password.getStatus() == true) {
			MainWindow.setStatusBarLabel("Receiving emails");
			GetMailsServer readMail = new ServerMailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), password.getPasswordMail());

			try {
				readMail.connectionInbox("Sent");
			} catch (MessagingException e) {
				JOptionPane.showMessageDialog(null, "Connection error", "Please check settings", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
			}

		}

	}
}
