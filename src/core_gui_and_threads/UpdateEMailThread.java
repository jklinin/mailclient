package core_gui_and_threads;

import javax.mail.MessagingException;

import utility.Run;
import func_core.*;

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
			GetMails readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), password.getPasswordMail());

			try {
				readMail.connectionInbox(folder);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			readMail.getMassagesArray();
		}
		new Thread(new AddRowsThread(folder)).start();

	}

}
