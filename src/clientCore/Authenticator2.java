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

	JTextField userName;
	JPasswordField password;

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
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
		pane.add(buttonNewMail, c);

		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		buttonUpdateMail = new JButton("Update");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.ipady = 500; // make this component tall
		c.weightx = 0.5;
		c.weighty = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(scrollPane, c);

		button = new JButton("Main Window");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.ipady = 500; // make this component tall
		c.weightx = 0.5;
		c.weighty = 1.0;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(button, c);

		JLabel label;
		label = new JLabel("status bar");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.SOUTH;
		pane.add(label, c);
	}

	public Authenticator2() {
		setTitle("Login into email client");
		setLayout(new GridLayout(3, 2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		userName = new JTextField(20);
		password = new JPasswordField(20);

		login = new JButton("Login");
		cancel = new JButton("Cancel");

		add(new JLabel("userName"));
		add(userName);

		add(new JLabel("password"));
		add(password);

		add(login);
		add(cancel);

		userName.requestFocus();

		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String un = userName.getText();
				String pa = new String(password.getPassword());

				if ((un.equals("test")) && (pa.equals("test"))) {
					dispose();
					new WelcomeFrame();
				}
			}
		});

		KeyAdapter k = new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER)
					login.doClick();
			}
		};

		password.addKeyListener(k);
		userName.addKeyListener(k);

		pack();
		setLocationRelativeTo(null);

	}

	public static void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('#');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(passwordField.getPassword());
		passwordMail = String.valueOf(passwordField.getPassword());
		System.out.println(passwordMail);
	}

	class WelcomeFrame extends JFrame {
		JLabel label;

		public WelcomeFrame() {

			JFrame frame = new JFrame("Email_client");
			Dimension d = new Dimension();
			d.setSize(960, 540);
			frame.setSize(d);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			addComponentsToPane(frame.getContentPane());
			frame.setVisible(true);
		}
	}

}
