package clientCore;


import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class MainWindow extends JFrame {
	private DefaultTableModel model;
	private JButton buttonNewMail;
	private JButton buttonUpdateMail;
	private JButton buttonAnswer;
	private JButton buttonAddressBook;
	private JTable previewMail;
	private JEditorPane viewMail;
	
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JPanel contenPanelSouth;
	private JPanel buttonsPanel;
	private JPanel contentPanelLeft;
	private JPanel contentPanelRight;
	
	private JLabel previewlabel;
	private JLabel viewlabel;
	private JLabel statuslabel;
	
	private ArrayList<MessagesDate> messagesList;
	private String ccStringTemp;
	private String bccStringString;
	private String toStringTemp;
	private String fromStringTemp;
	private String subjectStringTemp;
	private String sentDateStringTemp;
	private static String passwordMail;
	int selectedRow;

	public MainWindow() {
		super("Mail Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1500, 800);
		addComponentsToPane();
		setVisible(true);

	}

	public void addComponentsToPane() {
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		previewMail = new JTable(model);
		buttonNewMail = new JButton();
		buttonUpdateMail = new JButton();
		buttonAnswer = new JButton();
		buttonAddressBook = new JButton();
		viewMail = new JEditorPane();
		
		scrollPane1 = new JScrollPane();
		scrollPane2 = new JScrollPane();
		scrollPane3 = new JScrollPane();
		buttonsPanel = new JPanel();
		contenPanelSouth = new JPanel();
		contentPanelLeft = new JPanel();
		contentPanelRight = new JPanel();
		
		previewlabel = new JLabel();
		viewlabel = new JLabel();
		statuslabel = new JLabel();
		messagesList = new ArrayList();
		

		contentPanelLeft = new JPanel();
		contentPanelLeft.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanelLeft.setLayout(new GridBagLayout());
		((GridBagLayout) contentPanelLeft.getLayout()).columnWidths = new int[] { 0, 0 };
		((GridBagLayout) contentPanelLeft.getLayout()).rowHeights = new int[] { 0, 0, 0, 0 };
		((GridBagLayout) contentPanelLeft.getLayout()).columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		((GridBagLayout) contentPanelLeft.getLayout()).rowWeights = new double[] { 00.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		contentPanelRight = new JPanel();
		contentPanelRight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanelRight.setLayout(new GridBagLayout());
		((GridBagLayout) contentPanelRight.getLayout()).columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		((GridBagLayout) contentPanelRight.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		((GridBagLayout) contentPanelLeft.getLayout()).columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		((GridBagLayout) contentPanelRight.getLayout()).rowWeights = new double[] { 00.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		buttonNewMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonNewMail) {
					NewMailWindow newMail = new NewMailWindow();

				}
			}
		});

		buttonNewMail.setText("New Mail");

		buttonsPanel.add(buttonNewMail);

		// --- button Answer EMail------------------------

		buttonAnswer.setText("Answer");
		buttonAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonAnswer) {
					NewMailWindow answerMail = new NewMailWindow(messagesList.get(selectedRow));
				}
			}
		});
		buttonsPanel.add(buttonAnswer);

		// -------------------------------------------------
		buttonUpdateMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonUpdateMail) {
					try {
						passwordDialog();
					} catch (NullPointerException ex) {
						System.err.println(ex.getMessage());
						return;
					}
					new Thread(new UpdateEMailThread()).start();

				}
			}

		});

		buttonUpdateMail.setText("Update");
		buttonsPanel.add(buttonUpdateMail);

		buttonAddressBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonAddressBook) {
					AddressBook newAddressBook = new AddressBook();
				}
			}
		});

		buttonAddressBook.setText("Address Book");
		buttonsPanel.add(buttonAddressBook);
		// --------------------------JLabels--------------------
		previewlabel.setText("Incoming mail"+"          ");
		previewlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));		
		buttonsPanel.add(previewlabel, 0);
		viewlabel.setText("          "+"Mail content");
		viewlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonsPanel.add(viewlabel, 5);
		//-----------------------------------------------------
		// ----Jtable----------------
		model.addColumn("FROM");
		model.addColumn("TO");
		model.addColumn("Subject");
		model.addColumn("Sent Date");
		previewMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPanelLeft.add(previewMail, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(25, 0, 0, 0), 0, 0));
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
				
				if(viewMail.getSize().getHeight()<1792){
					viewMail.setPreferredSize(new Dimension (700, 700));
				}else{
					viewMail.setPreferredSize(new Dimension(700, 1792));
				}
			}
		});

		for (int i = 0; i < 4; i++) {
			previewMail.getColumnModel().getColumn(i).setPreferredWidth(180);
			previewMail.getColumnModel().getColumn(i).setMaxWidth(180);
			previewMail.getColumnModel().getColumn(i).setMinWidth(100);
		}

		// ---------------------------------------------------
		// thread for the adding of rows
		new Thread(new AddRowsThread()).start();
		// ----------JEditorPane----------------------------
		viewMail.setEditable(false); // set JEdotorPane not editable

		viewMail.setPreferredSize(new Dimension(700, 700));
		contentPanelRight.add(viewMail, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 0));
		// -------------------------------------------------
		statuslabel.setText("Status Bar");
		statuslabel.setPreferredSize(new Dimension(160, 15));
		contenPanelSouth.add(statuslabel);
		//-----------Constraints---------------------------
		getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		scrollPane1 = new JScrollPane(contentPanelLeft);
		getContentPane().add(scrollPane1, BorderLayout.WEST);
		scrollPane2 = new JScrollPane(contentPanelRight);
		getContentPane().add(scrollPane2, BorderLayout.CENTER);
		scrollPane3=new JScrollPane(contenPanelSouth);
		getContentPane().add(scrollPane3, BorderLayout.SOUTH);
		setLocationRelativeTo(getOwner());
		//-------------------------------------------------
	}

	public static void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		passwordMail = String.valueOf(passwordField.getPassword());
		if (passwordMail.equals("")) {
			throw new NullPointerException("The field password is empty");
		}
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
			} catch (FileNotFoundException e) {
				// this ok hear
			} catch (ClassNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());

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
			} catch (MessagingException e) {
				System.err.println(e.getMessage());

			}
			try {
				readMail.getMassagesArray();
			} catch (NullPointerException ex) {
				statuslabel.setText("");
				return;
			}
			new Thread(new AddRowsThread()).start();

		}

	}

}
