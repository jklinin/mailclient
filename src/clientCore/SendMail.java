package clientCore;

public interface SendMail {
	public boolean sendEmail(String fromEmail, String toEmail, String subject, String textEmail);// send new email
	public void answerMail(String fromEmail, String toEmail, String subject, String textEmail); // answer the email

}
