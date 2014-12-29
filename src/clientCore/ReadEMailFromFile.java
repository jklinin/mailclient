package clientCore;

import java.util.ArrayList;
import java.util.Date;

public interface ReadEMailFromFile {
	public int getNumber(); // returns the number of email in the box

	public String getSubject();// returns the subject of email

	public Date getSentDate();// returns the sent date of mail

	public String getContent();// returns text from email(only text)

	public ArrayList<String> getAddressFrom();// returns the addresses array of sender

	public ArrayList<String> getCopyOnAddres();  // if the address exist returns array of the addresses of copy

	public ArrayList<String> getCopyHideOnAddress(); // if the address exist returns array of the addresses of hide copy

	public ArrayList<String> getAddressTo();// returns array of addresses of receiver

}
