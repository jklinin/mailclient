package clientCore;
/**
 * @author Yuri Kalinin 
 * version 1.0.0
 **/ 

import javax.mail.MessagingException;

public class Run {

	public static void main(String[] args) throws MessagingException {
		MailReader readMail = new MailReader();
		MailWrite writeMail = new MailWrite();
		readMail.getMassage(readMail.connectionInbox());
		writeMail.sendEmail();

	}

}
