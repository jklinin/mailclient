package func_core;

/**
 * send EMail to the server 
 * @author Yuri Kalinin
 * version 1.0.1
 *
 */

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import core_gui_and_threads.MainWindow;

public class ServerMailWrite implements SendMail {

	private String userName;
	private String password;
	private String hostName;

	public ServerMailWrite(Object hostName, Object userName, String passwordMail) {
		this.hostName = hostName.toString();
		this.userName = userName.toString();
		password = passwordMail;

	}

	/**
	 * @author Yuri Kalinin this method connects to the email server and sends
	 *         the email
	 * @return true if the email is sended and false if not
	 * @exception if the Authentication is not correct
	 */

	public boolean sendEmail(String fromEmail, String toEmail, String subject, String textEmail, String ccAdr, String bccAdr) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", hostName);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});

		try {
			MainWindow.setStatusBarLabel("Email sending");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			if (!ccAdr.equals("")) {
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAdr));
			}
			if (!bccAdr.equals("")) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccAdr));
			}
			message.setSubject(subject);
			message.setText(textEmail);
			Transport.send(message);
			MainWindow.setStatusBarLabel("Status bar");
			return true;
		} catch (MessagingException e) {

			JOptionPane.showMessageDialog(null, "Password is incorrect", "Authentication Error", JOptionPane.ERROR_MESSAGE);
			MainWindow.setStatusBarLabel("Status bar");
			return false;

		}

	}

}
