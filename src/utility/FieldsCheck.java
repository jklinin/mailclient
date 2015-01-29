package utility;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

public class FieldsCheck {

	private String ccadr;
	private String bccadr;
	private String emailadr;
	
	public FieldsCheck(String ccadr, String bccadr, String emailadr){
		this.ccadr = ccadr;
		this.bccadr = bccadr;
		this.emailadr = emailadr;
}
	

	public boolean checkEMailAddress() {
		if (!ccadr.equals("")) {
			if (isValidEmailAddress(ccadr) == false) {
				JOptionPane.showMessageDialog(null, "Please check CC Email  Addresses", "Email addresses are not correct", JOptionPane.ERROR_MESSAGE);

			}
		}
		// check bcc email address
		if (!bccadr.equals("")) {
			if (isValidEmailAddress(bccadr) == false) {
				JOptionPane.showMessageDialog(null, "Please check  BCC Email Addresses", "Email addresses are not correct", JOptionPane.ERROR_MESSAGE);

			}
		}
		// check the destination email address
		if (!emailadr.equals("")) {

			if (isValidEmailAddress(emailadr) == false) {
				JOptionPane.showMessageDialog(null, "Please check  Destination Email Addresses", "Email addresses is not correct", JOptionPane.ERROR_MESSAGE);

			} else {
				return true;
			}

		}
		return false;
	}

	/**
	 * @author Yuri Kalinin
	 * @param email
	 * @return the check value of email address. if the email address is
	 *         incorrect, the method returns false.
	 * 
	 */
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
/**
 * check string value for the condition A_Z, a-z and length 1-8
 * @param value
 * @return 	true if the value is correct else false
 */
	public static boolean fieldsStringCheck(String value){
		if (value.matches("[a-zA-Z]{1,8}") == true){
		return true;
		}else{
			return false;
		}
		
	}
}
