package gui_addressbook;

/**
 * Created by nikolay on 13.1.2015.
 */
import javax.swing.*;

import utility.Run;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewContact extends JFrame implements ActionListener {
	private JPanel jp1;
	private JScrollPane jsp1;
	private JPanel jp2;
	private JButton saveContact;
	private JLabel labelName;
	private JLabel labelSurname;
	private JLabel labelEmailAddr;
	private JTextField nameF;
	private JTextField surnameF;
	private JTextField emailF;

	public NewContact() {
		super("New Contact");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(550, 340);
		initialization();
		setVisible(true);
	}

	public void initialization() {
		jp1 = new JPanel();
		jp2 = new JPanel();
		jsp1 = new JScrollPane(jp2);
		saveContact = new JButton();
		labelName = new JLabel("Name: ");
		labelSurname = new JLabel("Surname: ");
		labelEmailAddr = new JLabel("Email Add.:");
		nameF = new JTextField(20);
		surnameF = new JTextField(20);
		emailF = new JTextField(20);
		Container gcp = getContentPane();
		gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 150, 400 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 60, 60, 60, 60, 100 };

		saveContact.setText("Save contact");
		saveContact.addActionListener(this);
		jp1.add(saveContact);
		gcp.add(jp1, c);

		gcp.add(labelName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 20, 0, 0), 0, 0));

		gcp.add(labelSurname, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 20, 0, 0), 0, 0));

		gcp.add(labelEmailAddr, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(0, 20, 0, 0), 0, 0));

		gcp.add(nameF, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		gcp.add(surnameF, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		gcp.add(emailF, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	}

	// ------------------Controller for Buttons-------------------

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveContact) {
                //TODO 
			Run.addressBookDateBase.addNewPeople(nameF.getText(), surnameF.getText(), emailF.getText());
			dispose();
                
				}

	}
}