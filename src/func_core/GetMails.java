package func_core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface GetMails {
	/**
	 * gets emails from the server
	 * @param folder is the on the server

	 */
	public void connectionInbox(String folder) throws MessagingException; 

	public Message[] getMassagesArray(); // returns array of massages;

	public ArrayList<MessagesDate> readMessagesFile() throws FileNotFoundException, IOException, ClassNotFoundException;

}
