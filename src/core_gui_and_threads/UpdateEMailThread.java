package core_gui_and_threads;

import javax.mail.MessagingException;

import utility.Run;
import func_core.*;

public class UpdateEMailThread implements Runnable {

	@Override
	public void run() {
		
		PasswordDialog password = new PasswordDialog();
		MainWindow.statuslabel.setText("Receiving emails");
		GetMails readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), password.getPasswordMail());
		try {
			readMail.connectionInbox();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			System.out.println("error");
			MainWindow.statuslabel.setText("");
			e1.printStackTrace();
		}
		readMail.getMassagesArray();
		new Thread(new AddRowsThread()).start();

	}

}
