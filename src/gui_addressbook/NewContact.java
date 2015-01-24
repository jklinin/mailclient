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
	private JPanel buttonPane;
	private JScrollPane jsp1;
	private JPanel jp2;
	private JButton saveContact;
	private JLabel labelName;
	private JLabel labelSurname;
	private JLabel labelEmailAddr;
	private JTextField nameF;
	private JTextField surnameF;
	private JTextField emailF;
	private JPanel contentPanel;
	private JScrollPane paneWest;
	public NewContact() {
		super("New Contact");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 340);
		initialization();
		setVisible(true);
	}

	public void initialization() {
		buttonPane = new JPanel();
		jp2 = new JPanel();
		jsp1 = new JScrollPane(jp2);
		saveContact = new JButton();
		labelName = new JLabel("Name: ");
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelSurname = new JLabel("Surname: ");
		labelSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelEmailAddr = new JLabel("Email Add.:");
		labelEmailAddr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameF = new JTextField(20);
		nameF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		surnameF = new JTextField(20);
		surnameF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailF = new JTextField(20);
		emailF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel = new JPanel();
		contentPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.setLayout(new GridBagLayout());
		((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[] { 0, 0};
		((GridBagLayout) contentPanel.getLayout()).rowHeights = new int[] { 0, 0, 0, 0, 0 };

		saveContact.setText("Save contact");
		saveContact.addActionListener(this);
	
		buttonPane.add(saveContact);
		getContentPane().add(buttonPane, BorderLayout.NORTH);

		contentPanel.add(labelName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 20), 0, 0));

		contentPanel.add(labelSurname, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(20, 0, 0, 20), 0, 0));

		contentPanel.add(labelEmailAddr, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(20, 0, 0, 20), 0, 0));

		contentPanel.add(nameF, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 15));

		contentPanel.add(surnameF, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0), 0, 15));

		contentPanel.add(emailF, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(20, 0, 0, 0), 0, 15));
		paneWest = new JScrollPane(contentPanel);
		getContentPane().add(paneWest, BorderLayout.CENTER);
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