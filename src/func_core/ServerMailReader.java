package func_core;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;

import core_gui_and_threads.MainWindow;

;

/**
 * @author Yuri Kalinin gets e-mails from the server for inbox folder and for
 *         the sent folder version 1.0.3
 *
 */
public class ServerMailReader implements GetMailsServer {

	// --------- connection parameter--------------
	private String hostName;
	private String userName;
	private String password;
	private Folder folder;
	private Store store;
	private Message messages[];
	private ArrayList<MessagesDate> messagesListInbox;

	public ServerMailReader(Object hostName, Object userName, String passwordMail) {
		this.hostName = hostName.toString();
		this.userName = userName.toString();
		password = passwordMail;
		messagesListInbox = new ArrayList();
	}

	public ServerMailReader() {

	}

	public void connectionInbox(String serverFolder) {
		if (!password.equals("")) {

			Properties properties = System.getProperties();
			properties.setProperty("mail.pop3.host", hostName);
			properties.setProperty("mail.pop3.user", userName);
			properties.setProperty("mail.pop3.password", password);
			properties.setProperty("mail.pop3.port", "995");
			properties.setProperty("mail.pop3.auth", "true");
			properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			Session session = Session.getDefaultInstance(properties);
			try {
				store = session.getStore("pop3");
				store.connect(hostName, userName, password);
				folder = store.getFolder("Inbox");
				folder.open(Folder.READ_ONLY);
			} catch (NoSuchProviderException e) {

				JOptionPane.showMessageDialog(null, "Error with connection to provider ", "Provider connection error", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
				return;
			} catch (MessagingException e) {
				JOptionPane.showMessageDialog(null, "Password is not correct. ", "Password is not correct", JOptionPane.ERROR_MESSAGE);
				MainWindow.setStatusBarLabel("Status bar");
				System.err.println(e.getMessage());
				return;

			}
		}

	}

	/**
	 * This method reads the messages from server
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 * 
	 */

	public Message[] getMessages() throws MessagingException, FileNotFoundException, IOException {


		try {
			messages = folder.getMessages();
		} catch (MessagingException e) {
			System.err.println(e.getMessage());

		}
		
		Object content;
		String type; // type of messages contant
		for (int i = 0; i < messages.length; i++) {

			ArrayList<String> toAddress = new ArrayList();
			Address addressTO[] = messages[i].getRecipients(Message.RecipientType.TO);
			for (int j = 0; j < addressTO.length; j++) {

				toAddress.add(addressTO[j].toString());
			}

			ArrayList<String> copyOnAddress = new ArrayList();
			if (messages[i].getRecipients(Message.RecipientType.CC) != null) {
				Address addressCC[] = messages[i].getRecipients(Message.RecipientType.CC);
				if (addressCC.length > 0) {
					for (int j = 0; j < addressCC.length; j++) {

						copyOnAddress.add(addressCC[j].toString());
					}
				}
			}
			ArrayList<String> copyHideAddress = new ArrayList();
			if ((messages[i].getRecipients(Message.RecipientType.BCC)) != null) {
				Address addressBCC[] = messages[i].getRecipients(Message.RecipientType.BCC);

				if (addressBCC.length > 0) {
					for (int j = 0; j < addressBCC.length; j++) {

						copyHideAddress.add(addressBCC[j].toString());
					}
				}
			}

			ArrayList<String> fromAddress = new ArrayList();
			if (messages[i].getFrom() != null) {
				Address addressFrom[] = messages[i].getFrom();

				if (addressFrom.length > 0) {
					for (int j = 0; j < addressFrom.length; j++) {
						fromAddress.add(addressFrom[j].toString());
					}
				}
			}
			content = messages[i].getContent();
			if (content instanceof String) {
				type = "text"; // set message type text
				
				messagesListInbox.add(new MessagesDate(type, messages[i].getMessageNumber(), fromAddress, toAddress, messages[i].getSubject(), messages[i].getSentDate().toString(), copyOnAddress, copyHideAddress, content.toString()));
			}
			if (content instanceof Multipart) {
				Multipart mp;
				BodyPart bodyPart = null;
				type = "html";
				for (int j = 0; j < ((Multipart) content).getCount(); j++) {
					mp = (Multipart) content;
					bodyPart = mp.getBodyPart(j);
				}
				messagesListInbox.add(new MessagesDate(type, messages[i].getMessageNumber(), fromAddress, toAddress, messages[i].getSubject(), messages[i].getSentDate().toString(), copyOnAddress, copyHideAddress, bodyPart.getContent().toString()));
				
			}

		}

		new SaveReadFile().saveMessages(messagesListInbox, "Inbox");
		return messages;
	}

}
