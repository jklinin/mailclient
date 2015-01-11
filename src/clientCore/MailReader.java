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
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

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
	private ArrayList<MessagesDate> messagesList;
	
	public MailReader(Object hostName, Object userName, String passwordMail) {
		this.hostName = hostName.toString();
		this.userName = userName.toString();
		password = passwordMail;
		messagesList = new ArrayList();	
	}

	public MailReader() {
		
	}

	public void connectionInbox() {

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

			JOptionPane.showMessageDialog(null, "Error with connection to provider ", "Provider connection error", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getMessage());
			return;
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Password is not correct. ", "Password is not correct", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getMessage());
			return;
			
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
		
		String s;// temp 
		try {
			messages = inbox.getMessages();
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
			
		}
		if (messages.length == 0){
			System.out.println("No messages found.");
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
				type="text"; // set message type text
				messagesList.add(new MessagesDate(type, messages[i].getMessageNumber(), fromAddress, toAddress, messages[i].getSubject(), messages[i].getSentDate(), copyOnAddress, copyHideAddress, content.toString()));
			}else if (content instanceof Multipart) {
				for (int j = 0; j < ((Multipart) content).getCount(); j++) {
					Multipart mp = (Multipart) content;
					BodyPart bodyPart = mp.getBodyPart(j);		
					type="html"; // set message type html
					messagesList.add(new MessagesDate(type, messages[i].getMessageNumber(), fromAddress, toAddress, messages[i].getSubject(), messages[i].getSentDate(), copyOnAddress, copyHideAddress, bodyPart.getContent().toString()));
				}
			}
			


		}
		saveMessages(messagesList);
	}

	/**
	 * This method returns array of massages( e.g for gui) this method don't
	 * read the file
	 * @return the email message
	 */
	public Message[] getMassagesArray() {

		try {
			getMessages();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());

		} catch (MessagingException e) {
			System.err.println(e.getMessage());		
		} catch (IOException e) {
			System.err.println(e.getMessage());
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
		
	}

	/**
	 * the method read the file test.ser the method for deserialization of class
	 * MessagesDate returns the array of Messages
	 * */
	public ArrayList<MessagesDate> readMessagesFile() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(Run.getNameFileMessagesContainer().toString()));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		ArrayList<MessagesDate> array = null;
		try {
			array = (ArrayList<MessagesDate>) in.readObject();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		try {
			in.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return array;

	}
}
