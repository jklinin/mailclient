package clientCore;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author Yuri Kalinin read e-mails from inbox folder on the server version
 *         1.0.1
 *
 */
public class MailReader {

	// --------- connection parameter--------------
	private String hostName;
	private String userName;
	private String password;
	private Folder inbox;
	private Store store;
	private Message messages[];

	public MailReader(Object hostName, Object userName) {

		this.hostName = hostName.toString();
		System.out.println(hostName.toString());

		this.userName = userName.toString();
		System.out.println(userName.toString());
		// ---------testing password---------
		System.out.print("Enter your password->");
		Scanner input = new Scanner(System.in);
		password = input.nextLine();
		// -------------------------------------
	}

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
	 * This method reads the messages from server
	 * 
	 * 
	 */


	public void getMassage(Folder inbox) throws MessagingException {
//		Message messages[] = null;
		try {
			messages = inbox.getMessages();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (messages.length == 0)
			System.out.println("No messages found.");
		Object content;
		String s;
		for (int i = 0; i < messages.length; i++) {

			// --------------just for testing-----------------------
			System.out.println("Message " + (i + 1));
			System.out.println("From : " + messages[i].getFrom()[0]);
			System.out.println("Subject : " + messages[i].getSubject());
			System.out.println("Sent Date : " + messages[i].getSentDate());
			System.out.println();

			try {
				content = messages[i].getContent();
				if (content instanceof String) {
					s = (String) content;
					System.out.println(s);
				} else if (content instanceof Multipart) {
					for (int j = 0; j < ((Multipart) content).getCount(); j++) {
						Multipart mp = (Multipart) content;
						BodyPart bodyPart = mp.getBodyPart(j);

						s = (String) bodyPart.getContent();
						System.out.println(s);

					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		inbox.close(true);
		store.close();
		
	}
	/**
	 * This method returns array of massage( e.g for gui)
	 */
	public Message [] getMassagesArray(){
		return messages;
		
	}
}
