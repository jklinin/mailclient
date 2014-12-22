package clientCore;

public interface SendMail {
	public void sendEmail(String fromEmail, String toEmail, String subject,
			String textEmail);
	public void answerMail(String fromEmail, String toEmail, String subject, String textEmail); // answer the email

}
