package clientCore;

import java.util.ArrayList;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface UpdateMail {
	public void connectionInbox() throws MessagingException; // update emails
																// from server

	public Message[] getMassagesArray(); // returns array of massages;

}
