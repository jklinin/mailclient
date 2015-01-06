package clientCore;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class MainWindow extends JFrame {
	private DefaultTableModel model;
	private static String passwordMail;
	private JButton buttonNewMail;
	private JButton buttonUpdateMail;
	private JButton buttonAnswer;
	private JTable previewMail;
	private JEditorPane viewMail;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JLabel previewlabel;
	private JLabel viewlabel;
	private JLabel statuslabel;
	private Message[] messages = null;
	private ArrayList<MessagesDate> messagesList;
	private String ccStringTemp;
	private String bccStringString;
	private String toStringTemp;
	private String fromStringTemp;
	private String subjectStringTemp;
	private String sentDateStringTemp;
	int selectedRow;

	public MainWindow() {
		super("Mail Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponentsToPane();

		setVisible(true);

	}

	public void addComponentsToPane() {
		model = new DefaultTableModel();
		previewMail = new JTable(model);
		buttonNewMail = new JButton();
		buttonUpdateMail = new JButton();
		buttonAnswer = new JButton();
		viewMail = new JEditorPane();
		scrollPane1 = new JScrollPane();
		scrollPane2 = new JScrollPane();
		previewlabel = new JLabel();
		viewlabel = new JLabel();
		statuslabel = new JLabel();
		messagesList = new ArrayList();
		Container gcp = getContentPane();
		gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 800, 400, 400, 0 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 0, 0, 600, 40, 0 };

		buttonNewMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonNewMail) {
					NewMailWindow newMail = new NewMailWindow();

				}
			}
		});
		buttonNewMail.setText("New Mail");

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.VERTICAL;
		c.ipadx = 0;
		c.ipady = 0;
		gcp.add(buttonNewMail, c);

		buttonUpdateMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonUpdateMail) {
					passwordDialog();
					new Thread(new UpdateEMailThread()).start();

				}
			}

		});

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		// --- button Answer EMail------------------------
		buttonAnswer.setText("Answer");
		buttonAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonAnswer) {
					NewMailWindow answerMail = new NewMailWindow(messagesList.get(selectedRow));
				}
			}
		});
		gcp.add(buttonAnswer, c);
		// -------------------------------------------------
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		buttonUpdateMail.setText("Update");
		gcp.add(buttonUpdateMail, c);

		previewlabel.setText("Incoming mail");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		gcp.add(previewlabel, c);
		viewlabel.setText("Mail content");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		gcp.add(viewlabel, c);
		// ----Jtable----------------
		scrollPane1.setViewportView(previewMail);
		viewMail.setEditable(false); // set JEdotorPane not editable
		model.addColumn("FROM");
		model.addColumn("TO");
		model.addColumn("Subject");
		model.addColumn("Sent Date");
		previewMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel rowSM = previewMail.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				// Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();

				if (!lsm.isSelectionEmpty()) {
					int lastElement = messagesList.size() - 1;
					selectedRow = lastElement - lsm.getMinSelectionIndex();

					ccStringTemp = "";
					if (messagesList.get(selectedRow).getCopyOnAddres() != null) {
						for (int i = 0; i < messagesList.get(selectedRow).getCopyOnAddres().size(); i++) {
							ccStringTemp = ccStringTemp + messagesList.get(selectedRow).getCopyOnAddres().get(i) + "; ";
						}

					}
					bccStringString = "";
					if (messagesList.get(selectedRow).getCopyHideOnAddress() != null) {
						for (int i = 0; i < messagesList.get(selectedRow).getCopyHideOnAddress().size(); i++) {
							bccStringString = bccStringString + messagesList.get(selectedRow).getCopyHideOnAddress().get(i) + "; ";

						}
					}
					toStringTemp = "";
					for (int i = 0; i < messagesList.get(selectedRow).getAddressTo().size(); i++) {
						toStringTemp = toStringTemp + messagesList.get(selectedRow).getAddressTo().get(i) + "; ";

					}
					fromStringTemp = "";
					for (int i = 0; i < messagesList.get(selectedRow).getAddressFrom().size(); i++) {
						fromStringTemp = fromStringTemp + messagesList.get(selectedRow).getAddressFrom().get(i) + "; ";

					}
					subjectStringTemp = messagesList.get(selectedRow).getSubject();
					sentDateStringTemp = messagesList.get(selectedRow).getSentDate().toString();
					System.out.println(messagesList.get(selectedRow).getTypeMessages() + " type of selected messages");// TODO
																														// remove
																														// this
																														// is
																														// just
																														// for
																														// testing
					if (messagesList.get(selectedRow).getTypeMessages().equals("text")) {
						viewMail.setContentType("text");
						viewMail.setText("From: " + fromStringTemp + "\n" + "To: " + toStringTemp + "\n" + "CC: " + ccStringTemp + "\n" + "BCC: " + bccStringString + "\n" + "Subject: " + subjectStringTemp + "\n" + "Sent Date: " + sentDateStringTemp + "\n" + "\n"
								+ messagesList.get(selectedRow).getContent());
					} else if (messagesList.get(selectedRow).getTypeMessages().equals("html")) {
						viewMail.setContentType("text/html");
						viewMail.setText("<p> From: " + fromStringTemp + "<br>" + "To: " + toStringTemp + "<br>" + "CC: " + ccStringTemp + "<br>" + "BCC: " + bccStringString + "<br>" + "Subject: " + subjectStringTemp + "<br>" + "Sent Date: " + sentDateStringTemp + "<br>" + "</p>"
								+ messagesList.get(selectedRow).getContent());
					}
				}
			}
		});

		for (int i = 0; i < 4; i++) {
			previewMail.getColumnModel().getColumn(i).setPreferredWidth(200);
			previewMail.getColumnModel().getColumn(i).setMaxWidth(200);
			previewMail.getColumnModel().getColumn(i).setMinWidth(200);
		}
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		gcp.add(scrollPane1, c);
		// ---------------------------------------------------
		// thread for the adding of rows
		new Thread(new AddRowsThread()).start();

		// ----------JEditorPane----------------------------
		scrollPane2.setViewportView(viewMail);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		gcp.add(scrollPane2, c);
		// -------------------------------------------------

		statuslabel.setText("Status Bar");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		gcp.add(statuslabel, c);
		/*
		 * gcp.add(statuslabel, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
		 * GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0,
		 * 0, 0, 0), 0, 0));
		 */
		pack();
		setLocationRelativeTo(getOwner());
	}

	public static void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(passwordField.getPassword());
		passwordMail = String.valueOf(passwordField.getPassword());
		System.out.println(passwordMail);
	}

	/**
	 * 
	 * @author Yuri Kalinin this thread adds the new rows in the view table
	 *
	 */
	class AddRowsThread implements Runnable {
		public void addNewRowTable() throws FileNotFoundException, ClassNotFoundException, IOException {
			model.setRowCount(0);
			GetMails readMail = new MailReader();

			messagesList = readMail.readMessagesFile();
			int i = messagesList.size() - 1;
			do {

				model.addRow(new Object[] { messagesList.get(i).getAddressFrom(), messagesList.get(i).getAddressTo(), messagesList.get(i).getSubject(), messagesList.get(i).getSentDate() });
				i--;

			} while (i > 0);
		}

		@Override
		public void run() {
			try {
				addNewRowTable();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statuslabel.setText("");
		}
	}

	/**
	 * @author Yuri Kalinin This is the thread this thread updates the emails in
	 *         view table
	 *
	 */
	class UpdateEMailThread implements Runnable {

		@Override
		public void run() {
			statuslabel.setText("Receiving emails");
			GetMails readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), passwordMail);
			try {
				readMail.connectionInbox();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			readMail.getMassagesArray();
			new Thread(new AddRowsThread()).start();

		}

	}

}
