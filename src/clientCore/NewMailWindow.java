package clientCore;

/**
 * @author Yuri Kalinin, Nikolay Antonov
 * the class for the input window of email
 * version 1.0.2
 */
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

import com.sun.imageio.stream.StreamCloser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMailWindow extends JFrame {

	private JScrollPane scrollPane;
	private JButton sendMail;
	private JPanel contentPanel = new JPanel();
	private JTextField emailadr;
	private JTextField ccadr;
	private JTextField bccadr;
	private JEditorPane emailbody;
	private JTextField subj;
	private JLabel labelDest;
	private JLabel labelSub;
	private JLabel labelCC;
	private JLabel labelBCC;
	private String passwordMail;

	public NewMailWindow() {
		super("New Mail");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 800);
		initialization();
		setVisible(true);
	}

	/**
	 * @author Yuri Kalinin the constructor with message parameter for the
	 *         answering of email
	 * @param message
	 */
	public NewMailWindow(MessagesDate message) {// answer selected email

		super("Answer Mail");

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 800);
		initialization();
		if (message.getSubject() != null) {
			subj.setText("Re: " + message.getSubject());
		}
		emailadr.setText(message.getAddressFrom().get(0));
		if (message.getCopyOnAddres() != null) {
			ccadr.setText(message.getCopyOnAddres().get(0));
		}
		if (message.getCopyHideOnAddress() != null) {
			bccadr.setText(message.getCopyHideOnAddress().get(0));
		}

		String ccStringTemp = "";
		if (message.getCopyOnAddres() != null) {
			for (int i = 0; i < message.getCopyOnAddres().size(); i++) {
				ccStringTemp = ccStringTemp + message.getCopyOnAddres().get(i) + "; ";
			}

		}
		String bccStringString = "";
		if (message.getCopyHideOnAddress() != null) {
			for (int i = 0; i < message.getCopyHideOnAddress().size(); i++) {
				bccStringString = bccStringString + message.getCopyHideOnAddress().get(i) + "; ";

			}
		}
		String toStringTemp = "";
		for (int i = 0; i < message.getAddressTo().size(); i++) {
			toStringTemp = toStringTemp + message.getAddressTo().get(i) + "; ";

		}
		String fromStringTemp = "";
		for (int i = 0; i < message.getAddressFrom().size(); i++) {
			fromStringTemp = fromStringTemp + message.getAddressFrom().get(i) + "; ";
		}
		if (message.getTypeMessages().equals("text")) {
			emailbody.setContentType("text");
			emailbody.setText("\n\n\n\n____________________________________________________________________________________________________________\n" + "From: " + fromStringTemp + "\nTo: " + toStringTemp + "\nCC: " + ccStringTemp + "\n" + "BCC: " + bccStringString + "\n" + "Subject: "
					+ message.getSubject() + "\nSent Date: " + message.getSentDate() + "\n" + message.getContent());
		}
		if (message.getTypeMessages().equals("html")) {
			emailbody.setContentType("text/html");
			emailbody.setText("<br><br><br><p>____________________________________________________________________________________________________________ <br> From: " + fromStringTemp + "<br>To: " + toStringTemp + "<br>CC: " + ccStringTemp + "<br>" + "BCC: " + bccStringString + "<br>"
					+ "Subject: " + message.getSubject() + "<br>Sent Date: " + message.getSentDate() + "<br></p>" + message.getContent());
		}
		setVisible(true);
	}

	private void initialization() {
		sendMail = new JButton("Send EMail");
		labelDest = new JLabel("Destination @:");
		labelSub = new JLabel("Subject:");
		labelCC = new JLabel("CC @:");
		labelBCC = new JLabel("BCC @:");
		emailadr = new JTextField(20);
		subj = new JTextField(20);
		ccadr = new JTextField(20);
		bccadr = new JTextField(20);
		emailbody = new JEditorPane();
		scrollPane = new JScrollPane();

		Container gcp = getContentPane();
		gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 125, 125, 550, 0 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 20, 20, 20, 20, 500 };

		sendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sendMail) {
					// check cc email address
					if (!ccadr.getText().equals("")) {
						if (isValidEmailAddress(ccadr.getText()) == false) {
							return;
						}
					}
					// check bcc email address
					if (!bccadr.getText().equals("")) {
						if (isValidEmailAddress(bccadr.getText()) == false) {
							return;
						}
					}
					// check the destination email address
					if (!emailadr.getText().equals("")) {
						if (isValidEmailAddress(emailadr.getText()) == false) {
							return;
						}
						try{
						passwordDialog();

						SendMail writteMail = new MailWrite(Run.getSettingProtocolSMTP(), Run.getSettingUserName(), passwordMail);
						if (writteMail.sendEmail("project_test91@mail.ru", emailadr.getText(), subj.getText(), emailbody.getText(), ccadr.getText(), bccadr.getText()) == true) {
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Please try again", "Error by sending", JOptionPane.ERROR_MESSAGE);
						}
						}catch (NullPointerException ex){
							System.err.println(ex);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please enter the correct email address", "Destination field is empty", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});

		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 0.00;
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		gcp.add(sendMail, c);

		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 0;
		gcp.add(labelDest, c);

		c.gridx = 1;
		c.gridy = 1;
		gcp.add(labelSub, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		gcp.add(emailadr, c);

		c.gridx = 2;
		c.gridy = 1;
		gcp.add(subj, c);

		c.gridx = 1;
		c.gridy = 2;
		gcp.add(labelCC, c);

		c.gridx = 2;
		c.gridy = 2;
		gcp.add(ccadr, c);

		c.gridx = 1;
		c.gridy = 3;
		gcp.add(labelBCC, c);

		c.gridx = 2;
		c.gridy = 3;
		gcp.add(bccadr, c);

		scrollPane.setViewportView(emailbody);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_END;
		c.weighty = 1.2;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.ipady = 200;
		gcp.add(scrollPane, c);

	}

	/**
	 * @author Yuri Kalinin the method for the input of password
	 */
	private void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		passwordMail = String.valueOf(passwordField.getPassword());
		if(passwordMail.equals("")){
			throw new NullPointerException("Password field is empty");
		}

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
			JOptionPane.showMessageDialog(null, "Please check  Email Addresses", "Email addresses are not correct", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return result;
	}
}
