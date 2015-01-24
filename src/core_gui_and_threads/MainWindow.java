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

/**
 * @author Nikolay, Yuri
 * the main window 
 * version 1.0.4
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

	static ArrayList<MessagesDate> messagesList;
	private String ccStringTemp;
	private String bccStringString;
	private String toStringTemp;
	private String fromStringTemp;
	private String subjectStringTemp;
	private String sentDateStringTemp;
	private static String passwordMail;
	private int selectedRow;
	private String folder = "Inbox";

	public MainWindow() {
		super("Mail Client");
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

		previewMail = new JTable(model);
		previewMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPanelLeft.add(previewMail, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 0, 0, 0), 0, 0));
		ListSelectionModel rowSM = previewMail.getSelectionModel();
		for (int i = 0; i < 4; i++) {
			previewMail.getColumnModel().getColumn(i).setPreferredWidth(190);
			previewMail.getColumnModel().getColumn(i).setMaxWidth(180);
			previewMail.getColumnModel().getColumn(i).setMinWidth(100);
		}
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

	}

	// ------------------Controller for Buttons-------------------

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAnswer) {
			NewMailWindow answerMail = new NewMailWindow(messagesList.get(selectedRow));
			System.out.println("testAnswer");// TODO
		} else if (e.getSource() == buttonUpdateMail) {
			// passwordDialog();
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
				folder = "Send";
				System.out.println(folder + " set folder");// TODO
				new Thread(new AddRowsThread("Sent")).start();
			} else {
				toggleSentFolder.setText("Sent Folder");
				folder = "Inbox";
				System.out.println(folder + " set folder");// TODO

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
				viewMail.setText("From: " + fromStringTemp + "\n" + "To: " + toStringTemp + "\n" + "CC: " + ccStringTemp + "\n" + "BCC: " + bccStringString + "\n" + "Subject: " + subjectStringTemp + "\n" + "Sent Date: " + sentDateStringTemp + "\n" + "\n" + messagesList.get(selectedRow).getContent());
			} else if (messagesList.get(selectedRow).getTypeMessages().equals("html")) {
				viewMail.setContentType("text/html");
				viewMail.setText("<p> From: " + fromStringTemp + "<br>" + "To: " + toStringTemp + "<br>" + "CC: " + ccStringTemp + "<br>" + "BCC: " + bccStringString + "<br>" + "Subject: " + subjectStringTemp + "<br>" + "Sent Date: " + sentDateStringTemp + "<br>" + "</p>"
						+ messagesList.get(selectedRow).getContent());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		int row = previewMail.rowAtPoint(event.getPoint());
		int col = previewMail.columnAtPoint(event.getPoint());
		if (row >= 0 && col >= 0) {
			NewMailWindow answerMail = new NewMailWindow(messagesList.get(selectedRow));
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

	// ----------------------------------------------------------

	// ----- Controller for Label Satus bar----------------------
	public static void setStatusBarLabel(String status) {
		statuslabel.setText(status);
	}
}
