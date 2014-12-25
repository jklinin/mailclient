package clientCore;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;





public class NewMailWindow extends JFrame {

	private JScrollPane scrollpane;
	private JButton sendMail;
	private JPanel contentPanel = new JPanel();
	private JTextField textContent; 
	private String passwordMail;
	

	NewMailWindow() {
		super("title");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		
		initialization();
		setVisible(true);
	}

	private void initialization() {
		
		JPanel p2 = new JPanel();
		JPanel panelCenter= new JPanel();
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
				}}

			
				
			});
		p2.add(sendMail);
	


		getContentPane().add(p2, BorderLayout.NORTH);
		getContentPane().add(panelCenter, BorderLayout.CENTER);



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
