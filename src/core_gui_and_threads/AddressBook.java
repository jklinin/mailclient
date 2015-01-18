package core_gui_and_threads;

/**
 * Created by nikolay on 13.1.2015.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBook extends JFrame implements ActionListener{
    private DefaultTableModel model;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JPanel buttonCont;
    private JScrollPane jsp1;
    private JTable viewBook;




    public AddressBook() {
		super("Address Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 660);
		initialization();
		setVisible(true);
	}

    public void initialization(){
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
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 60,  600 };

        buttonAdd.setText("Add contact");
		buttonAdd.addActionListener(this);
		buttonCont.add(buttonAdd);
		gcp.add(buttonCont, c);

        buttonRemove.setText("Remove");
		buttonAdd.addActionListener(this);
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
		gcp.add(jsp1, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));



    }

	// ------------------Controller for Buttons-------------------

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonAdd) {
					NewContact newNewContact = new NewContact();
				}

	}
}

