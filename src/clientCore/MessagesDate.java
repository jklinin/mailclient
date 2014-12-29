package clientCore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * version 1.0.0 this is serizable class version
 * 
 * @author Yuri Kalinin
 * @serial number, subject, sent date, sender address, copy to address, hide
 *         copy to address, content, addresses to copies
 */
public class MessagesDate implements Serializable, ReadEMailFromFile {
	private int number;
	private ArrayList<String> addressFromArray= new ArrayList();
	private String subject;
	private Date sentDate;
	private ArrayList<String> addressToArray = new ArrayList();
	private ArrayList<String> copyOnAddressArray = new ArrayList();
	private ArrayList<String> copyHideAddressArray = new ArrayList();
	private String content;

	MessagesDate(int messageNum, ArrayList<String> addressFrom, ArrayList<String> addressTo, String subject, Date sentDate, ArrayList<String> copyOnAddress, ArrayList<String> copyHideAddress, String content) {
		
		this.number = messageNum;
		this.sentDate = sentDate;
		this.subject = subject;
		addressFromArray = addressFrom;
		addressToArray = addressTo;
		copyOnAddressArray = copyOnAddress;
		copyHideAddressArray = copyHideAddress;
		this.content = content;

	}
	MessagesDate(){};
	public int getNumber() {
		return number;
	}

	public String getSubject() {

		return subject;
	}

	public Date getSentDate() {
		return sentDate;

	}

	public String getContent() {
		return content;
	}

	public ArrayList<String> getAddressFrom() {
		return addressFromArray;
	}

	/**
	 * 
	 * @return array of addresses of copies. if array ==null returns null
	 */
	public ArrayList<String> getCopyOnAddres() {
		if (copyOnAddressArray.size() > 0) {
			return copyOnAddressArray;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return array of hide copies of email. if array == null returns null
	 */
	public ArrayList<String> getCopyHideOnAddress() {
		if (copyHideAddressArray.size() > 0) {
			return copyHideAddressArray;
		} else {
			return null;
		}
	}

	public ArrayList<String> getAddressTo() {
		return addressToArray;
	}

}
