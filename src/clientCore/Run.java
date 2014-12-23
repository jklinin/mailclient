package clientCore;

/**
 * @author Yuri Kalinin 
 * version 1.0.1
 **/

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

public class Run {
	private static ArrayList<String> settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {
		ReadXML readerXML = new ReadXML();

		readerXML.readSettings();
		settings = readerXML.getSettings();
		new Authenticator2();// GUI
		// just for testing
		
		// SendMail writteMail = new MailWrite(settings.get(1),
		// settings.get(2));
		// writteMail.answerMail("project_test91@mail.ru",
		// "project_test91@mail.ru", "test", "test, test"); // put
		// dest.
		// emailadress
		// into
		// Email

	}

	

	public static Object getSettingUserName() {
		return settings.get(2);
	}

	public static Object getSettingProtocol() {
		return settings.get(0);
		
	}




}
