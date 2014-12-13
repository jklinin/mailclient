package clientCore;

import javax.mail.MessagingException;

/**
 * @author Yuri Kalinin
 * version 1.0.0
 *
 */
public class Run {

	public static void main(String[] args) {
		MailReader  readMail = new MailReader();
		try {
			readMail.getMassage(readMail.connectionInbox());
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
