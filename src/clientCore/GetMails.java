package clientCore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface GetMails {
	public void connectionInbox() throws MessagingException; // update emails
																// from server

	public Message[] getMassagesArray(); // returns array of massages;
	public ArrayList<MessagesDate> readMessagesFile() throws FileNotFoundException, IOException, ClassNotFoundException;

}
