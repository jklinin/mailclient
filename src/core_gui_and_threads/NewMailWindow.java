package core_gui_and_threads;

/**
 * @author Yuri Kalinin, Nikolay Antonov
 * the class for the input window of email
 * version 1.0.2
 */
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

import utility.Run;
import func_core.EMailAddressCheck;
import func_core.MailWrite;
import func_core.MessagesDate;
import func_core.SendMail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class NewMailWindow extends JFrame implements ActionListener, MouseListener {

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
		setResizable(false);
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
		setResizable(false);
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
//GUI
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

		sendMail.addActionListener(this);

		gcp.add(sendMail, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.00, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));

		gcp.add(labelDest, new GridBagConstraints(1, 0, 1, 1, 0.5, 0.00, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));

		gcp.add(labelSub, new GridBagConstraints(1, 1, 1, 1, 0.5, 0.00, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		emailadr.addMouseListener(this);
		gcp.add(emailadr, c);

		c.gridx = 2;
		c.gridy = 1;
		gcp.add(subj, c);

		c.gridx = 1;
		c.gridy = 2;
		gcp.add(labelCC, c);

		c.gridx = 2;
		c.gridy = 2;
		ccadr.addMouseListener(this);
		gcp.add(ccadr, c);

		c.gridx = 1;
		c.gridy = 3;
		gcp.add(labelBCC, c);

		c.gridx = 2;
		c.gridy = 3;
		gcp.add(bccadr, c);
		bccadr.addMouseListener(this);

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

	// Aktion

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendMail) {
			EMailAddressCheck checkEMailAddress = new EMailAddressCheck(ccadr.getText(), bccadr.getText(), emailadr.getText());
			if (checkEMailAddress.checkAddress() == true) {
				SendMail writteMail = new MailWrite(Run.getSettingProtocolSMTP(), Run.getSettingUserName(), new PasswordDialog().getPasswordMail());
				if (writteMail.sendEmail("project_test91@mail.ru", emailadr.getText(), subj.getText(), emailbody.getText(), ccadr.getText(), bccadr.getText()) == true)
					dispose();
			}

		}
		

	}
// TODO Mouse CKlick

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}