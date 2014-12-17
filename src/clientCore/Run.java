

package clientCore;

*/
/**
 * @author Yuri Kalinin 
 * version 1.0.1
 **//*



import java.util.ArrayList;

import javax.mail.MessagingException;



public class Run {
	protected static ArrayList settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {
		ReadXML readerXML= new ReadXML();
		readerXML.readSettings();
		settings= readerXML.getSettings();
	
		MailReader readMail = new MailReader(settings.get(0), settings.get(2));
		MailWrite writeMail = new MailWrite(settings.get(1), settings.get(2));
		readMail.getMassage(readMail.connectionInbox());
		 writeMail.sendEmail("project_test91@mail.ru","TODO", "test", "test, test");

	}
}


