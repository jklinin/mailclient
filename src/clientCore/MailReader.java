package clientCore;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author Yuri Kalinin 
 * read e-mails from inbox folder on the server 
 * version 1.0.0
 *
 */
public class MailReader {
	// --------- connection parameter--------------
	private String hostName = ""; // TODO add the host name
	private String userName = ""; // TODO add the user name
	private String password = ""; // TODO add the password
	private Folder inbox;
	private Store store;

	public Folder connectionInbox() throws MessagingException {

		Properties properties = System.getProperties();
		properties.setProperty("mail.pop3.host", hostName);
		properties.setProperty("mail.pop3.user", userName);
		properties.setProperty("mail.pop3.password", password);
		properties.setProperty("mail.pop3.port", "995");
		properties.setProperty("mail.pop3.auth", "true");
		properties.setProperty("mail.pop3.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(properties);
		try {
			store = session.getStore("pop3");
			store.connect(hostName, userName, password);
			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inbox;

	}

	/**
	 * This method returns array of messages in the form
	 * 
	 * 
	 */

	public Message[] getMassage(Folder inbox) throws MessagingException {
		Message messages[] = null;
		try {
			messages = inbox.getMessages();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (messages.length == 0)
			System.out.println("No messages found.");

		for (int i = 0; i < messages.length; i++) {

			inbox.close(true);
			store.close();

			// --------------just for testing-----------------------
			System.out.println("Message " + (i + 1));
			System.out.println("From : " + messages[i].getFrom()[0]);
			System.out.println("Subject : " + messages[i].getSubject());
			System.out.println("Sent Date : " + messages[i].getSentDate());
			System.out.println();
		}
		return messages;
	}
}
