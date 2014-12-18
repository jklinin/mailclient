
package clientCore;



/**
 * send EMail to the server 
 * @author Yuri Kalinin
 * version 1.0.1
 *
 */


import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailWrite implements SendMail{

	private String userName;
	private String password;
	private String hostName ;

	public MailWrite(Object hostName, Object userName) {

		this.hostName = hostName.toString();
		this.userName = userName.toString();
		// ---------testing password---------
		System.out.print("Enter your password->");
		Scanner input = new Scanner(System.in);
		password = input.nextLine();
		// -------------------------------------
	}

	public void sendEmail(String fromEmail, String toEmail, String subject,
			String textEmail) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", hostName);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(userName, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText(textEmail);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

