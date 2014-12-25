package clientCore;

import javax.mail.MessagingException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class Authenticator2 extends JFrame {
	final static boolean RIGHT_TO_LEFT = false;
	private JButton login, cancel;
	private static JButton buttonUpdateMail;
	private static JButton buttonNewMail;
	private static String passwordMail;

	public Authenticator2() {

		JFrame frame = new JFrame("Email_client");
		Dimension d = new Dimension();
		d.setSize(960, 540);
		frame.setSize(d);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentsToPane(frame.getContentPane());
		frame.setVisible(true);

	}

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		JEditorPane viewbody;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		buttonNewMail = new JButton("New Email");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		buttonNewMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonNewMail) {
					NewMailWindow newMail= new NewMailWindow();
//					passwordDialog();
//					SendMail writteMail = new MailWrite(Run.getSettingProtocolSMTP(), Run.getSettingUserName(), passwordMail);
//					writteMail.answerMail("project_test91@mail.ru", "project_test91@mail.ru", "test", "test, test"); // put
																														// dest.
																														// emailadress
																														// into
																														// Email

				}
			}
		});
		pane.add(buttonNewMail, c);

		button = new JButton("Button 2");
		//c.weightx = 0.0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		buttonUpdateMail = new JButton("Update");
		c.gridx = 2;
		c.gridy = 0;
		buttonUpdateMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonUpdateMail) {
					
					
					passwordDialog();
					UpdateMail readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), passwordMail);
					try {
						readMail.connectionInbox();
					} catch (MessagingException e1) {

						JOptionPane.showMessageDialog(null, "Password is incorrect", "Authentication Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					readMail.getMassagesArray();
				}
			}

		});
		pane.add(buttonUpdateMail, c);

		JScrollPane scrollPane = new JScrollPane();
		c.anchor = GridBagConstraints.CENTER;
		c.ipady = 500; // make this component tall
		c.weightx = 0.3;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(scrollPane, c);

		viewbody = new JEditorPane();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(viewbody, c);

		JLabel label;
		label = new JLabel("status bar");
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.SOUTH;
		c.weighty = 4;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(label, c);
	}

	public static void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('#');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(passwordField.getPassword());
		passwordMail = String.valueOf(passwordField.getPassword());
		System.out.println(passwordMail);
	}

}