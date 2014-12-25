package clientCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class NewMailWindow extends JFrame {

	private JScrollPane scrollpane;
	private JButton sendMail;
	private JPanel contentPanel = new JPanel();
	private JTextField textContent; 
	private String passwordMail;
	

	NewMailWindow() {
		super("New Mail");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		initialization();
		setVisible(true);
	}

	private void initialization() {
		//setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JPanel nofixedaddress = new JPanel();
		JTextField emailadr;
		JTextField ccadr;
		JEditorPane emailbody;
		JLabel label;



		sendMail= new JButton("Send EMail");
		sendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == sendMail) {
					passwordDialog();
					SendMail writteMail = new MailWrite(Run.getSettingProtocolSMTP(), Run.getSettingUserName(), passwordMail);
					writteMail.answerMail("project_test91@mail.ru", "project_test91@mail.ru", "test", "test, test"); // put
					// dest.
					// emailadress
					// into
					// Email
				}
			}
		});

		c.ipadx = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.weighty = 0.00;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH;
		getContentPane().add(sendMail, c);

		label = new JLabel("Destination @:");
		c.gridx = 1;
		c.gridy = 0;
		getContentPane().add(label, c);

		emailadr = new JTextField(20);
		c.gridx = 2;
		c.gridy = 0;
		getContentPane().add(emailadr, c);

		label = new JLabel("CC @:");
		c.gridx = 1;
		c.gridy = 1;
		getContentPane().add(label, c);

		ccadr = new JTextField(20);
		c.gridx = 2;
		c.gridy = 1;
		getContentPane().add(ccadr, c);

		emailbody = new JEditorPane();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_END;
		c.weighty = 1.2;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.ipady = 200;
		getContentPane().add(emailbody, c);



	}
	private void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('#');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(passwordField.getPassword());
		passwordMail = String.valueOf(passwordField.getPassword());
		System.out.println(passwordMail);
	}
}