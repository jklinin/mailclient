package gui_addressbook;

/**
 * Created by nikolay on 13.1.2015.
 */
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import utility.FieldsCheck;
import utility.Run;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewContact extends JFrame implements ActionListener, DocumentListener {
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
		nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		nameF.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (FieldsCheck.fieldsStringCheck(nameF.getText()) == false) {
					nameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				} else {
					nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				}

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (FieldsCheck.fieldsStringCheck(nameF.getText()) == false) {
					nameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				} else {
					nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (FieldsCheck.fieldsStringCheck(nameF.getText()) == false) {
					nameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				} else {
					nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				}

			}

		});
		nameF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		surnameF = new JTextField(20);
		surnameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		surnameF.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (FieldsCheck.fieldsStringCheck(surnameF.getText()) == false) {
					surnameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				} else {
					surnameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				}

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (FieldsCheck.fieldsStringCheck(surnameF.getText()) == false) {
					surnameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				} else {
					surnameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				}

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (FieldsCheck.fieldsStringCheck(surnameF.getText()) == false) {
					surnameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				} else {
					surnameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				}

			}

		});
		surnameF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailF = new JTextField(20);
		emailF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		contentPanel = new JPanel();
		contentPanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.setLayout(new GridBagLayout());
		((GridBagLayout) contentPanel.getLayout()).columnWidths = new int[] { 0, 0 };
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
			// TODO
			if (FieldsCheck.isValidEmailAddress(emailF.getText()) == true) {
				if (FieldsCheck.fieldsStringCheck(nameF.getText()) == true) {
					if (FieldsCheck.fieldsStringCheck(surnameF.getText()) == true) {
						Run.addressBookDateBase.addNewPeople(nameF.getText(), surnameF.getText(), emailF.getText());
						dispose();
						AddressBook.updateContactTable();
					}
				}
			} else {
				emailF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
				JOptionPane.showMessageDialog(null, "Please check Email  Addresses", "Email addresses are not correct", JOptionPane.ERROR_MESSAGE);
				emailF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			}

		}

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		if (arg0.getDocument() == nameF) {
			if (FieldsCheck.fieldsStringCheck(nameF.getText()) == false) {
				nameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
			} else
				nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		}
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		if (arg0.getDocument() == nameF) {
			if (FieldsCheck.fieldsStringCheck(nameF.getText()) == false) {
				nameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
			} else
				nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		}
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		if (arg0.getDocument() == nameF) {
			if (FieldsCheck.fieldsStringCheck(nameF.getText()) == false) {
				nameF.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
			} else
				nameF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		}

	}
}