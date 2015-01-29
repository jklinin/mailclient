package core_gui_and_threads;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import utility.PeopleDateBase;
import func_core.MessagesDate;
import gui_addressbook.AddressBook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Nikolay, Yuri the main window version 1.0.4
 *
 */
public class MainWindow extends JFrame implements ActionListener, ListSelectionListener, MouseListener {
	static DefaultTableModel model;
	private JButton buttonNewMail;
	private JButton buttonUpdateMail;
	private JButton buttonAnswer;
	private JButton buttonAddressBook;
	private JToggleButton toggleSentFolder;
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
	private static JLabel statuslabel;

	protected static ArrayList<MessagesDate> messagesListInbox;
	private String ccStringTemp;
	private String bccStringString;
	private String toStringTemp;
	private String fromStringTemp;
	private String subjectStringTemp;
	private String sentDateStringTemp;
	private static String passwordMail;
	protected static ArrayList<MessagesDate> messagesListSent;
	private int selectedRow;
	private String folder = "Inbox";
	private DefaultTableModel modelTemp;
	private String startFolder;

	public MainWindow(String startFolder) {
		super("Mail Client");
		this.startFolder = startFolder;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

		buttonNewMail = new JButton();
		buttonUpdateMail = new JButton();
		buttonAnswer = new JButton();
		buttonAddressBook = new JButton();
		toggleSentFolder = new JToggleButton();
		previewMail = new JTable(model);

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
		messagesListInbox = new ArrayList();
		messagesListSent = new ArrayList();
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

		// -----button New Mail--------------------------

		buttonNewMail.setText("New Mail");
		buttonNewMail.addActionListener(this);
		buttonsPanel.add(buttonNewMail);

		// --- button Answer EMail------------------------

		buttonAnswer.setText("Answer");
		buttonAnswer.addActionListener(this);
		buttonsPanel.add(buttonAnswer);
		// ---------- button Address Book
		buttonAddressBook.setText("Address Book");
		buttonAddressBook.addActionListener(this);
		buttonsPanel.add(buttonAddressBook);
		// ----Button Update EMail---------------
		buttonUpdateMail.setText("Update");
		buttonUpdateMail.addActionListener(this);
		buttonsPanel.add(buttonUpdateMail);
		// ----JToggleButton----------------------
		toggleSentFolder.setText("Sent Folder");
		toggleSentFolder.addActionListener(this);
		buttonsPanel.add(toggleSentFolder);
		// ----------------------------------------

		// --------------------------JLabels--------------------
		previewlabel.setText("Incoming mail" + "          ");
		previewlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonsPanel.add(previewlabel, 0);
		viewlabel.setText("          " + "Mail content");
		viewlabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonsPanel.add(viewlabel, 6);

		// ----Jtable--------------------------------------------

		model.addColumn("FROM");
		model.addColumn("TO");
		model.addColumn("Subject");
		model.addColumn("Sent Date");
		scrollPane1.setViewportView(previewMail);
		previewMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane1.setPreferredSize(new Dimension(780, 800));
		contentPanelLeft.add(scrollPane1, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		ListSelectionModel rowSM = previewMail.getSelectionModel();
		for (int i = 0; i < 3; i++) {
			previewMail.getColumnModel().getColumn(i).setPreferredWidth(190);
			previewMail.getColumnModel().getColumn(i).setMaxWidth(300);
			previewMail.getColumnModel().getColumn(i).setMinWidth(100);
		}
		previewMail.getColumnModel().getColumn(3).setPreferredWidth(210);
		rowSM.addListSelectionListener(this);
		previewMail.addMouseListener(this);

		// ----------JEditorPane----------------------------
		viewMail.setEditable(false); // set JEdotorPane not editable

		viewMail.setPreferredSize(new Dimension(700, 700));
		contentPanelRight.add(viewMail, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 0));
		// -------------------------------------------------

		// ----Statuslabel-----------------------------------
		statuslabel.setText("Status Bar");
		statuslabel.setPreferredSize(new Dimension(160, 15));
		contenPanelSouth.add(statuslabel);
		// -----------Constraints---------------------------
		getContentPane().add(buttonsPanel, BorderLayout.NORTH);

		scrollPane1 = new JScrollPane(contentPanelLeft);
		scrollPane1.setPreferredSize(new Dimension(800, 800));
		getContentPane().add(scrollPane1, BorderLayout.WEST);
		scrollPane2 = new JScrollPane(contentPanelRight);
		getContentPane().add(scrollPane2, BorderLayout.CENTER);
		scrollPane3 = new JScrollPane(contenPanelSouth);
		getContentPane().add(scrollPane3, BorderLayout.SOUTH);
		setLocationRelativeTo(getOwner());

		// -------------------------------------------------

		new Thread(new AddRowsThread("Inbox")).start();
		new Thread(new AddRowsThread("Sent")).start();

	}

	// ------------------Controller for Buttons-------------------

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAnswer) {
			if (selectedRow != 0) {
				NewMailWindow answerMail = new NewMailWindow(messagesListInbox.get(selectedRow));
			}
			System.out.println("testAnswer");// TODO
		} else if (e.getSource() == buttonUpdateMail) {

			System.out.println("testUpdate");// TODO
			new Thread(new UpdateEMailThread("Inbox")).start(); // for POP3 only
																// folder INBOX

		} else if (e.getSource() == buttonNewMail) {
			NewMailWindow newMail = new NewMailWindow();

		} else if (e.getSource() == buttonAddressBook) {
			AddressBook newAddressBook = new AddressBook();
		}

		if (e.getSource() == toggleSentFolder) {
			if (toggleSentFolder.getText() == "Sent Folder") {
				toggleSentFolder.setText("Inbox Folder");
				if (messagesListSent.size() != 0) {
					int k = messagesListSent.size() - 1;
					System.out.println(messagesListSent.size());// TODO Just for
																// testing

					int rowCount = model.getRowCount();
					// Remove rows one by one from the end of the table
					for (int j = rowCount - 1; j >= 0; j--) {
						model.removeRow(j);
					}
					do {
						MainWindow.model.addRow(new Object[] { MainWindow.messagesListSent.get(k).getAddressFrom(), MainWindow.messagesListSent.get(k).getAddressTo(), MainWindow.messagesListSent.get(k).getSubject(), MainWindow.messagesListSent.get(k).getSentDate() });
						k--;
					} while (k > 0);
				}
			} else {
				toggleSentFolder.setText("Sent Folder");
				folder = "Inbox";
				System.out.println(folder + " set folder");// TODO
				int j = MainWindow.messagesListInbox.size() - 1;
				int rowCount = model.getRowCount();
				// Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}

				do {
					MainWindow.model.addRow(new Object[] { MainWindow.messagesListInbox.get(j).getAddressFrom(), MainWindow.messagesListInbox.get(j).getAddressTo(), MainWindow.messagesListInbox.get(j).getSubject(), MainWindow.messagesListInbox.get(j).getSentDate() });
					j--;

				} while (j > 0);

			}
		}

	}

	// --------------Controllers for JTable----------------------
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// Ignore extra messages.
		if (e.getValueIsAdjusting())
			return;

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();

		if (!lsm.isSelectionEmpty()) {
			int lastElement = messagesListInbox.size() - 1;
			selectedRow = lastElement - lsm.getMinSelectionIndex();

			ccStringTemp = "";
			if (messagesListInbox.get(selectedRow).getCopyOnAddres() != null) {
				for (int i = 0; i < messagesListInbox.get(selectedRow).getCopyOnAddres().size(); i++) {
					ccStringTemp = ccStringTemp + messagesListInbox.get(selectedRow).getCopyOnAddres().get(i) + "; ";
				}

			}
			bccStringString = "";
			if (messagesListInbox.get(selectedRow).getCopyHideOnAddress() != null) {
				for (int i = 0; i < messagesListInbox.get(selectedRow).getCopyHideOnAddress().size(); i++) {
					bccStringString = bccStringString + messagesListInbox.get(selectedRow).getCopyHideOnAddress().get(i) + "; ";

				}
			}
			toStringTemp = "";
			for (int i = 0; i < messagesListInbox.get(selectedRow).getAddressTo().size(); i++) {
				toStringTemp = toStringTemp + messagesListInbox.get(selectedRow).getAddressTo().get(i) + "; ";

			}
			fromStringTemp = "";
			for (int i = 0; i < messagesListInbox.get(selectedRow).getAddressFrom().size(); i++) {
				fromStringTemp = fromStringTemp + messagesListInbox.get(selectedRow).getAddressFrom().get(i) + "; ";

			}
			subjectStringTemp = messagesListInbox.get(selectedRow).getSubject();
			sentDateStringTemp = messagesListInbox.get(selectedRow).getSentDate().toString();
			System.out.println(messagesListInbox.get(selectedRow).getTypeMessages() + " type of selected messages");// TODO
			// remove
			// this
			// is
			// just
			// for
			// testing
			if (messagesListInbox.get(selectedRow).getTypeMessages().equals("text")) {
				viewMail.setContentType("text");
				viewMail.setText("From: " + fromStringTemp + "\n" + "To: " + toStringTemp + "\n" + "CC: " + ccStringTemp + "\n" + "BCC: " + bccStringString + "\n" + "Subject: " + subjectStringTemp + "\n" + "Sent Date: " + sentDateStringTemp + "\n" + "\n"
						+ messagesListInbox.get(selectedRow).getContent());
			} else if (messagesListInbox.get(selectedRow).getTypeMessages().equals("html")) {
				viewMail.setContentType("text/html");
				viewMail.setText("<p> From: " + fromStringTemp + "<br>" + "To: " + toStringTemp + "<br>" + "CC: " + ccStringTemp + "<br>" + "BCC: " + bccStringString + "<br>" + "Subject: " + subjectStringTemp + "<br>" + "Sent Date: " + sentDateStringTemp + "<br>" + "</p>"
						+ messagesListInbox.get(selectedRow).getContent());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		int row = previewMail.rowAtPoint(event.getPoint());
		int col = previewMail.columnAtPoint(event.getPoint());
		if (row >= 0 && col >= 0) {
			NewMailWindow answerMail = new NewMailWindow(messagesListInbox.get(selectedRow));
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static ArrayList<MessagesDate> getMessagesListSent() {
		return messagesListSent;
	}

	// ----------------------------------------------------------

	// ----- Controller for Label Satus bar----------------------
	public static void setStatusBarLabel(String status) {
		statuslabel.setText(status);
	}

}
