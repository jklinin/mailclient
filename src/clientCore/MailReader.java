package clientCore;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.scenario.Settings;

/**
 * @author Yuri Kalinin read e-mails from inbox folder on the server version
 *         1.0.2
 *
 */
public class MailReader implements GetMails {

	// --------- connection parameter--------------
	private String hostName;
	private String userName;
	private String password;
	private Folder inbox;
	private Store store;
	private Message messages[];
	private ArrayList<MessagesDate> messagesList = new ArrayList();

	public MailReader(Object hostName, Object userName, String passwordMail) {

		this.hostName = hostName.toString();
		System.out.println(hostName.toString());

		this.userName = userName.toString();
		System.out.println(userName.toString());

		password = passwordMail;

		messagesList = new ArrayList();
	}

	public MailReader() {
		// TODO Auto-generated constructor stub
	}

	public void connectionInbox() throws MessagingException {

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
			inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
		} catch (NoSuchProviderException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private void getMessages() throws MessagingException, FileNotFoundException, IOException {
		// Message messages[] = null;
		String s;
		try {
			messages = inbox.getMessages();
		} catch (MessagingException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (messages.length == 0)
			System.out.println("No messages found.");
		Object content; 
		 String type; // type of messages contant
		for (int i = 0; i < messages.length; i++) {

			System.out.println("Message " + (i + 1));
			System.out.println("From : " + messages[i].getFrom().toString());
			ArrayList<String> toAddress = new ArrayList();
			Address addressTO[] = messages[i].getRecipients(Message.RecipientType.TO);
			for (int j = 0; j < addressTO.length; j++) {
				System.out.println("TO : " + addressTO[j].toString());
				toAddress.add(addressTO[j].toString());
			}

			System.out.println("Subject : " + messages[i].getSubject());
			System.out.println("Sent Date : " + messages[i].getSentDate());
			ArrayList<String> copyOnAddress = new ArrayList();
			if (messages[i].getRecipients(Message.RecipientType.CC) != null) {
				Address addressCC[] = messages[i].getRecipients(Message.RecipientType.CC);

				System.out.println(addressCC.length);
				if (addressCC.length > 0) {
					for (int j = 0; j < addressCC.length; j++) {
						System.out.println("CC : " + addressCC[j].toString());
						copyOnAddress.add(addressCC[j].toString());
					}
				}
			}
			ArrayList<String> copyHideAddress = new ArrayList();
			if ((messages[i].getRecipients(Message.RecipientType.BCC)) != null) {
				Address addressBCC[] = messages[i].getRecipients(Message.RecipientType.BCC);

				if (addressBCC.length > 0) {
					for (int j = 0; j < addressBCC.length; j++) {
						System.out.println("BCC : " + addressBCC[j].toString());
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
			System.out.println();
			 content = messages[i].getContent();
			
			if (content instanceof String) {
				type="text";
				messagesList.add(new MessagesDate(type, messages[i].getMessageNumber(), fromAddress, toAddress, messages[i].getSubject(), messages[i].getSentDate(), copyOnAddress, copyHideAddress, content.toString()));
			}else if (content instanceof Multipart) {
				for (int j = 0; j < ((Multipart) content).getCount(); j++) {
					Multipart mp = (Multipart) content;
					BodyPart bodyPart = mp.getBodyPart(j);

//					s = (String) bodyPart.getContent();
					
					type="html";
					messagesList.add(new MessagesDate(type, messages[i].getMessageNumber(), fromAddress, toAddress, messages[i].getSubject(), messages[i].getSentDate(), copyOnAddress, copyHideAddress, bodyPart.getContent().toString()));
				}
			}
			// just for test
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
						System.out.println(s + " multipart");

					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		saveMessages(messagesList);
	}

	/**
	 * This method returns array of massages( e.g for gui) this method don't
	 * read the file
	 */
	public Message[] getMassagesArray() {

		try {
			getMessages();
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

		return messages;

	}

	/**
	 * the method for serialization of class MessagesDate
	 * */
	public void saveMessages(ArrayList<MessagesDate> mList) throws MessagingException, FileNotFoundException, IOException {

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Run.getNameFileMessagesContainer().toString()));
		for (int i = 0; i < mList.size(); i++) {
			out.writeObject(mList);

		}
		out.flush();
		out.close();
		/*// just for test
		try {
			readMessagesFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 * the method read the file test.ser the method for deserialization of class
	 * MessagesDate returns the array of Messages
	 * */
	public ArrayList<MessagesDate> readMessagesFile() throws FileNotFoundException, IOException, ClassNotFoundException {


		ObjectInputStream in = new ObjectInputStream(new FileInputStream(Run.getNameFileMessagesContainer().toString()));

		ArrayList<MessagesDate> array = (ArrayList<MessagesDate>) in.readObject();

		// -------------- just for testing of
		// seril---------------------------------------
		System.out.println(array.get(0).getNumber());
		System.out.println(array.get(0).getAddressFrom());
		System.out.println(array.get(0).getSubject());
		if (array.get(0).getCopyOnAddres() != null)
			System.out.println(array.get(0).getCopyOnAddres());
		// --------------------------------------------------------------------------------
		in.close();
		return array;

	}
}
