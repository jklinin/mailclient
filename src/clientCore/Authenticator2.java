package clientCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Authenticator2 extends JFrame {
	final static boolean RIGHT_TO_LEFT = false;
	JButton login, cancel;
	JTextField userName;
	JPasswordField password;

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}

		JButton button;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		button = new JButton("New Email");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Button 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.VERTICAL;
		c.weightx = 0.0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(button, c);

		button = new JButton("Update");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		pane.add(button, c);

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

	public static void main(String args[]) {
		new Authenticator2();
	}

}
