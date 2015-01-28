package core_gui_and_threads;

import java.io.FileNotFoundException;
import java.io.IOException;

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
			GetMailsServer readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), password.getPasswordMail());

			try {
				readMail.connectionInbox(folder);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			readMail.getMassagesArray();
			try {
				readMail.getMessages();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new Thread(new AddRowsThread(folder)).start();

	}

}
