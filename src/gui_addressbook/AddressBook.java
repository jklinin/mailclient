package gui_addressbook;

/**
 * GUI of address book
 * @author Nikolay, Yuri
 * version 1.0.2
 */
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import utility.PeopleDateBase;
import core_gui_and_threads.NewMailWindow;
import func_address_book.People;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author Nikolay Antonov
 * Defines the component names for the AddressBook gui.
 */

public class AddressBook extends JFrame implements ActionListener, ListSelectionListener, MouseListener {
	private static DefaultTableModel model;
	private JButton buttonAdd;
	private JButton buttonRemove;
	private JPanel buttonCont;
	private JScrollPane jspCentre;
	private JTable viewBook;
	private int selectedRow;
	private static ArrayList<People> peopleList;
	private JTextField calledCompoent;
	private JPanel panelCentre;
	private boolean checkNewMailWindow;
	
	/**
	 * @author Nikolay Antonov
	 * Creates Address Book Gui window
	 * of a certain size, disposes on closure.
	 */
	public AddressBook(JTextField component) {
		super("Address Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 600);
		peopleList = new ArrayList<People>();
		panelCentre = new JPanel();
		calledCompoent = component;
		checkNewMailWindow=true; // if new window open from new mail window
		initialization();
		setVisible(true);
	}

	public AddressBook() {
		super("Address Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 600);
		peopleList = new ArrayList<People>();
		panelCentre = new JPanel();
		initialization();
		checkNewMailWindow=false; // if the window opened not from new mail window
		setVisible(true);
	}
	
	/**
	 * @author Nikolay Antonov
	 * Adds the components to the the Address Book window,
	 * sets the window layout.
	 */
	private  void initialization() {
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		buttonAdd = new JButton();
		buttonRemove = new JButton();
		buttonCont = new JPanel();
		jspCentre = new JScrollPane();
		viewBook = new JTable(model);

		panelCentre.setLayout(new GridBagLayout());
		((GridBagLayout) panelCentre.getLayout()).columnWidths = new int[] { 0, 0, 0 };
		((GridBagLayout) panelCentre.getLayout()).rowHeights = new int[] { 0, 0 };

		JPanel buttonPane = new JPanel();
		buttonAdd.setText("Add contact");
		buttonCont.add(buttonAdd);
		buttonAdd.addActionListener(this);

		buttonRemove.setText("Remove");
		buttonRemove.addActionListener(this);
		buttonCont.add(buttonRemove);

		buttonPane.add(buttonCont);

		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("Email address");
		jspCentre.setViewportView(viewBook);
		jspCentre.setPreferredSize(new Dimension(630, 400));
		for (int i = 0; i < 3; i++) {
			viewBook.getColumnModel().getColumn(i).setPreferredWidth(210);
			viewBook.getColumnModel().getColumn(i).setMaxWidth(210);
			viewBook.getColumnModel().getColumn(i).setMinWidth(60);
		}

		peopleList = PeopleDateBase.getListPeople();
		int count;
		if (peopleList != null) {
			count = peopleList.size();
		} else {
			count = 0;
		}
		for (int i = 0; i < count; i++) {
			model.addRow(new Object[] { peopleList.get(i).getName(), peopleList.get(i).getSurname(), peopleList.get(i).getEmladr() });
		}
		// --- selection for JTable----------------------------------
		ListSelectionModel selectionRows = viewBook.getSelectionModel();
		viewBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		selectionRows.addListSelectionListener(this);
		// ----------------------------------------------------------
		// ---------mouse listner for JTable------------------------
		if (checkNewMailWindow == true) {
			viewBook.addMouseListener(this);
		}
		// ---------------------------------------------------------

		panelCentre.add(jspCentre, new GridBagConstraints(0, 1, 0, 0, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		getContentPane().add(buttonPane, BorderLayout.NORTH);
		getContentPane().add(panelCentre);

	}

	// ------------------Controller for Buttons-------------------
	/**
	 * @author Yuri Kalinin
	 * Launches window which adds new Contacts
	 * @param e event of pressing a buttonAdd button
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAdd) {
			new NewContact();

		}

		if (e.getSource() == buttonRemove) {
		
			PeopleDateBase.removePeople(selectedRow);

			int rowCount = model.getRowCount();
			// Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			peopleList = PeopleDateBase.getListPeople();
			for (int i = 0; i < peopleList.size(); i++) {
				model.addRow(new Object[] { peopleList.get(i).getName(), peopleList.get(i).getSurname(), peopleList.get(i).getEmladr() });
			}
			// dispose();
		}
	}

	// ---------------- Controller for JTable selection-----------------
	/**
	 * @author Yuri Kalinin
	 * reads the data of the email message of the selected row
	 * @param e event of selecting a row from the table
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedRow = lsm.getMinSelectionIndex();
		
		}

	}

	// ---- Controller for MouseLisner JTable--------------------
	/**
	 * @author Yuri Kalinin
	 * reads the data of the email message of the selected row
	 * @param e event of clicking a row from the table with a mouse
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		peopleList = PeopleDateBase.getListPeople();
		if (calledCompoent.getName().equals("emailadr")) {
			NewMailWindow.setDestinEMailAdt(peopleList.get(selectedRow).getEmladr(), peopleList.get(selectedRow).getName(), peopleList.get(selectedRow).getSurname());

		}
		if (calledCompoent.getName().equals("ccadr")) {
			NewMailWindow.setCC(peopleList.get(selectedRow).getEmladr(), peopleList.get(selectedRow).getName(), peopleList.get(selectedRow).getSurname());

		}
		if (calledCompoent.getName().equals("bccadr")) {
			NewMailWindow.setBCC(peopleList.get(selectedRow).getEmladr(), peopleList.get(selectedRow).getName(), peopleList.get(selectedRow).getSurname());

		}
		dispose();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
//		no action

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
//		no action

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
//		no action

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
//		no action

	}
	
	public static void updateContactTable() {

		int rowCount = model.getRowCount();
		
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		peopleList = PeopleDateBase.getListPeople();
		for (int i = 0; i < peopleList.size(); i++) {
			model.addRow(new Object[] { peopleList.get(i).getName(), peopleList.get(i).getSurname(), peopleList.get(i).getEmladr() });
		}

	}

}
