

package clientCore;


/**
 * @author Yuri Kalinin 
 * version 1.0.1
 **/



import java.util.ArrayList;

import javax.mail.MessagingException;



public class Run {
	protected static ArrayList settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {
		ReadXML readerXML= new ReadXML();
		readerXML.readSettings();
		settings= readerXML.getSettings();
//	just for testing
		UpdateMail readMail = new MailReader(settings.get(0), settings.get(2));
		readMail.connectionInbox();
		System.out.println("=====");
		readMail.connectionInbox();
		readMail.getMassagesArray();
		MailWrite writeMail = new MailWrite(settings.get(1), settings.get(2)); 
		
		 writeMail.sendEmail("project_test91@mail.ru","project_test91@mail.ru", "test", "test, test"); //put dest. emailadress into Email

	}
}


