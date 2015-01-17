package clientCore;

/**
 * Created by nikolay on 13.1.2015.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AddressBook extends JFrame {
    private DefaultTableModel model;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JPanel buttonCont = new JPanel();
    private JScrollPane jsp = new JScrollPane();
    private JScrollPane jsp2 = new JScrollPane();
    private JTable viewBook;




    public AddressBook() {
		super("Address Book");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 641);
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
        jsp = new JScrollPane(buttonCont);
        jsp2 = new JScrollPane();
        viewBook = new JTable(model);
        Container gcp = getContentPane();
		gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 200, 200, 200 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 40,  600 };

        buttonAdd.setText("Add");
		gcp.add(jsp, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTH,
				GridBagConstraints.VERTICAL,
				new Insets(0, 0, 0, 0), 0, 0));
		buttonCont.add(buttonAdd);

        buttonRemove.setText("Remove");
		c.gridx = 1;
		c.gridy = 0;
		buttonCont.add(buttonRemove);

        jsp2.setViewportView(viewBook);
    	model.addColumn("Name");
    	model.addColumn("Surname");
    	model.addColumn("Email_addr");
		for (int i = 0; i < 3; i++) {
			viewBook.getColumnModel().getColumn(i).setPreferredWidth(200);
			viewBook.getColumnModel().getColumn(i).setMaxWidth(200);
			viewBook.getColumnModel().getColumn(i).setMinWidth(100);
		}
		gcp.add(jsp2, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
				GridBagConstraints.CENTER,
				GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0));


    }
}
