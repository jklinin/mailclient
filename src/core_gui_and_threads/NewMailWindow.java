package core_gui_and_threads;

/**
 * GUI 
 * @author Yuri Kalinin, Nikolay Antonov
 * the class for the input window of email
 * version 1.0.3
 */

import javax.swing.*;

import utility.FieldsCheck;
import utility.Run;
import func_core.ServerMailWrite;
import func_core.MessagesDate;
import func_core.SendMail;
import gui_addressbook.AddressBook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;

public class NewMailWindow extends JFrame implements ActionListener, MouseListener {

	private JScrollPane scrollPane;
	private JButton sendMail;
	private static JTextField emailadr;
	private static JTextField ccadr;
	private static JTextField bccadr;
	private JEditorPane emailbody;
	private JTextField subj;
	private JLabel labelDest;
	private JLabel labelSub;
	private JLabel labelCC;
	private JLabel labelBCC;
	private String contentType="text";

	public NewMailWindow() {
		super("New Mail");
		contentType="text";
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 800);
		initialization();
		setResizable(false);
		setVisible(true);
	}

	/**
	 * @author Yuri Kalinin the constructor with message parameter for the answering of email
	 * @param message
	 */
	public NewMailWindow(MessagesDate message) {// answer selected email

		super("Answer Mail");
		System.out.println(message.getTypeMessages());//TODO remove this
//		if(message.getTypeMessages().equals("html")){
//			contentType="text/html";
//		}
		contentType="text";
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 800);
		setResizable(false);
		initialization();
		setFieldsAnswer(message);
		setVisible(true);
	}

	/**
	 * @author Nikolay Antonov
	 * Adds the components to the the Address Book window,
	 * sets the window layout.
	 */
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
		emailadr.setName("emailadr");
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
		ccadr.setName("ccadr");
		gcp.add(ccadr, c);

		c.gridx = 1;
		c.gridy = 3;
		gcp.add(labelBCC, c);

		c.gridx = 2;
		c.gridy = 3;
		gcp.add(bccadr, c);
		bccadr.setName("bccadr");
		bccadr.addMouseListener(this);

		scrollPane.setViewportView(emailbody);
		emailbody.setContentType(contentType);
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
	 * Set fields in the answer- window
	 * @param message
	 */
	private void setFieldsAnswer(MessagesDate message) {
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
		if(contentType.equals("text")){
//		if (message.getTypeMessages().equals("text")) {
//			emailbody.setContentType("text");
			emailbody.setText("\n\n\n\n____________________________________________________________________________________________________________\n" + "From: " + fromStringTemp + "\nTo: " + toStringTemp + "\nCC: " + ccStringTemp + "\n" + "BCC: " + bccStringString + "\n" + "Subject: "
					+ message.getSubject() + "\nSent Date: " + message.getSentDate() + "\n" + message.getContent());
		}
		if(contentType.equals("text/html")){
//		if (message.getTypeMessages().equals("html")) {
//			contentType = "text/html";
//			emailbody.setContentType("text/html");
			emailbody.setText("<p></p><p>____________________________________________________________________________________________________________ <br> From: " + fromStringTemp + "<br>To: " + toStringTemp + "<br>CC: " + ccStringTemp + "<br>" + "BCC: " + bccStringString + "<br>"
					+ "Subject: " + message.getSubject() + "<br>Sent Date: " + message.getSentDate() + "<br></p>" + "<p>"+message.getContent()+"/p");
		}
	}

	// -------------Controller for buttons-------------------
	/**
	 * @author Yuri Kalinin
	 * Check the validity of an input email address
	 * Sends the Mail to the Server
	 * @param e event of pressing a sendMail button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendMail) {
			FieldsCheck checkEMailAddress = new FieldsCheck(ccadr.getText(), bccadr.getText(), emailadr.getText());
			if (checkEMailAddress.checkEMailAddress() == true) {
				PasswordDialog password = new PasswordDialog();
				if (password.getStatus() == true) {
					SendMail writteMail = new ServerMailWrite(Run.getSettingProtocolSMTP(), Run.getSettingUserName(), password.getPasswordMail());
					ArrayList emlAdrTemp = new ArrayList();
					emlAdrTemp.add(emailadr.getText());
					ArrayList ccadrTemp = new ArrayList();
					ccadrTemp.add(ccadr.getText());
					ArrayList bccadrTemp = new ArrayList();
					bccadrTemp.add(bccadr.getText());
					Date dateTemp = new Date();
					ArrayList adrFromTemp = new ArrayList();
					adrFromTemp.add("project_test91@mail.ru");
					if (writteMail.sendEmail("project_test91@mail.ru", emailadr.getText(), subj.getText(), emailbody.getText(), ccadr.getText(), bccadr.getText()) == true) {
						if(MainWindow.messagesListSent!=null){
							System.out.println(contentType);//TODO remove this
						MainWindow.messagesListSent.add(new MessagesDate(contentType, MainWindow.messagesListSent.size()+1, adrFromTemp, emlAdrTemp, subj.getText(), dateTemp.toGMTString(), ccadrTemp, bccadrTemp, emailbody.getText()));
						}else{
							MainWindow.messagesListSent= new ArrayList();
							MainWindow.messagesListSent.add(new MessagesDate(contentType, MainWindow.messagesListSent.size()+1, adrFromTemp, emlAdrTemp, subj.getText(), dateTemp.toGMTString(), ccadrTemp, bccadrTemp, emailbody.getText()));
						}
						dispose();
					}
				}
			}

		}

	}

	// ----------------------------------------------------------------------------------

	// -------- Controller for Mouse Click Address Fields----------------------

	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField component = new JTextField();
		component = (JTextField) e.getComponent();
		new AddressBook(component);// start GUI Address book

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// no action 

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// no action 
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// no action 

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// no action 
	}

	public static void setDestinEMailAdt(String emlAddr, String name, String surname) {

		emailadr.setText(name + " " + surname + "<" + emlAddr + ">");

	}

	public static void setBCC(String emlAddr, String name, String surname) {

		bccadr.setText(name + " " + surname + "<" + emlAddr + ">");

	}

	public static void setCC(String emlAddr, String name, String surname) {

		ccadr.setText(name + " " + surname + "<" + emlAddr + ">");

	}
}
