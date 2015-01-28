package core_gui_and_threads;

import javax.mail.MessagingException;

import utility.Run;
import func_core.GetMailsServer;
import func_core.MailReader;

public class GetSentEmails implements Runnable {

	@Override
	public void run() {
		PasswordDialog password = new PasswordDialog();
		if (password.getStatus() == true) {
			MainWindow.setStatusBarLabel("Receiving emails");
			GetMailsServer readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), password.getPasswordMail());

			try {
				readMail.connectionInbox("Sent");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
