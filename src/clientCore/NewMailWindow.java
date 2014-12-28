package clientCore;
/**
 * the class for the input window of email
 * version 1.0.2
 */
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

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
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private String passwordMail;
	

	NewMailWindow() {
		super("New Mail");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 800);
		initialization();
		setVisible(true);
	}

	private void initialization() {
		sendMail = new JButton("Send EMail");
		label1 = new JLabel("Destination @:");
		label2 = new JLabel("Subject:");
		label3 = new JLabel("CC @:");
		label4 = new JLabel("BCC @:");
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
							JOptionPane.showMessageDialog(null, "Please check  Email Addresses", "Email addresses are not correct", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					// check bcc email address
					if (!bccadr.getText().equals("")) {
						if (isValidEmailAddress(bccadr.getText()) == false) {
							JOptionPane.showMessageDialog(null, "Please check  Email Addresses", "Email addresses are not correct", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					// check the destination email address
					if (!emailadr.getText().equals("")) {

						if (isValidEmailAddress(emailadr.getText()) == false) {
							JOptionPane.showMessageDialog(null, "Please check  Email Addresses", "Email addresses is not correct", JOptionPane.ERROR_MESSAGE);
							return;
						}

						passwordDialog();

						SendMail writteMail = new MailWrite(Run.getSettingProtocolSMTP(), Run.getSettingUserName(), passwordMail);
						if (writteMail.sendEmail("project_test91@mail.ru", emailadr.getText(), subj.getText(), emailbody.getText(), ccadr.getText(), bccadr.getText()) == true) {
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Please try again", "Error by sending", JOptionPane.ERROR_MESSAGE);
						}
					}else{
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
		gcp.add(label1, c);

		c.gridx = 1;
		c.gridy = 1;
		gcp.add(label2, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		gcp.add(emailadr, c);

		c.gridx = 2;
		c.gridy = 1;
		gcp.add(subj, c);

		c.gridx = 1;
		c.gridy = 2;
		gcp.add(label3, c);


		c.gridx = 2;
		c.gridy = 2;
		gcp.add(ccadr, c);

		c.gridx = 1;
		c.gridy = 3;
		gcp.add(label4, c);

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
 * @author Yuri Kalinin
 * the method for the input of password 
 */
	private void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('#');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(passwordField.getPassword());
		passwordMail = String.valueOf(passwordField.getPassword());
		System.out.println(passwordMail);
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
}

