package clientCore;

public interface SendMail {
	public boolean sendEmail(String fromEmail, String toEmail, String subject, String textEmail, String ccAdr, String bccAdr);// send new email


}
