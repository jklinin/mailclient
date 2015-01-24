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

import core_gui_and_threads.NewMailWindow;
import utility.PeopleDateBase;
import func_address_book.People;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AddressBook extends JFrame implements ActionListener, ListSelectionListener, MouseListener {
	private DefaultTableModel model;
	private JButton buttonAdd;
	private JButton buttonRemove;
	private JPanel buttonCont;
	private JScrollPane jsp1;
	private JTable viewBook;
	private int selectedRow;
	private ArrayList<People> peopleList = new ArrayList<People>();
	private JTextField calledCompoent;

	public AddressBook(JTextField component) {
		super("Address Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 660);
		initialization();
		calledCompoent = component;
		System.out.println(component.getName());// TODO Just for testing
		setVisible(true);
	}

	public AddressBook() {
		super("Address Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 660);
		initialization();

		setVisible(true);
	}

	public void initialization() {
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		buttonAdd = new JButton();
		buttonRemove = new JButton();
		buttonCont = new JPanel();
		jsp1 = new JScrollPane();
		viewBook = new JTable(model);
		Container gcp = getContentPane();
		gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 200, 200, 200 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 60, 600 };

		buttonAdd.setText("Add contact");
		buttonCont.add(buttonAdd);
		buttonAdd.addActionListener(this);
		gcp.add(buttonCont, c);

		buttonRemove.setText("Remove");
		buttonRemove.addActionListener(this);
		buttonCont.add(buttonRemove);
		c.gridx = 1;
		gcp.add(buttonCont, c);

		jsp1.setViewportView(viewBook);
		model.addColumn("Name");
		model.addColumn("Surname");
		model.addColumn("Email_addr");
		for (int i = 0; i < 3; i++) {
			viewBook.getColumnModel().getColumn(i).setPreferredWidth(210);
			viewBook.getColumnModel().getColumn(i).setMaxWidth(210);
			viewBook.getColumnModel().getColumn(i).setMinWidth(60);
		}

		peopleList = PeopleDateBase.getListPeople();
		for (int i = 0; i < peopleList.size(); i++) {
			model.addRow(new Object[] { peopleList.get(i).getName(), peopleList.get(i).getSurname(), peopleList.get(i).getEmladr() });
		}
		// --- selection for JTable----------------------------------
		ListSelectionModel selectionRows = viewBook.getSelectionModel();
		selectionRows.addListSelectionListener(this);
		// ----------------------------------------------------------
		// ---------mouse listner for JTable------------------------
		viewBook.addMouseListener(this);
		// ---------------------------------------------------------
		gcp.add(jsp1, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

	}

	// ------------------Controller for Buttons-------------------

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAdd) {
			NewContact newNewContact = new NewContact();
		}
		if (e.getSource() == buttonRemove) {
			System.out.println(selectedRow);//TODO just for testing
			PeopleDateBase.removePeople(selectedRow);
			dispose();
		}

	}

	// ---------------- Controller for JTable selection-----------------
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedRow = lsm.getMinSelectionIndex();
			System.out.println(selectedRow);// TODO remove this just for testing
		}

	}

	// ---- Controller for MouseLisner JTable--------------------

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(selectedRow);// just for testing
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
