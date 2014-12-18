package clientCore;

public interface SendMail {
	public void sendEmail(String fromEmail, String toEmail, String subject,
			String textEmail);

}
