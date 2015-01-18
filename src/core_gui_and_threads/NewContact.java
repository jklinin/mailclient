package core_gui_and_threads;

/**
 * Created by nikolay on 13.1.2015.
 */
import javax.swing.*;
import java.awt.*;


public class NewContact extends JFrame {
    private JScrollPane jsp1;
    private JPanel jp1;
    private JScrollPane jsp2;
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
        setSize(550, 330);
        initialization();
        setVisible(true);
    }

    public void initialization() {
        JPanel jp1 = new JPanel();
        JScrollPane jsp1 = new JScrollPane(jp1);
        JPanel jp2 = new JPanel();
        JScrollPane jsp2 = new JScrollPane(jp2);
        JButton saveContact = new JButton();
        JLabel labelName = new JLabel("Name: ");
        JLabel labelSurname = new JLabel("Surname: ");
        JLabel labelEmailAddr = new JLabel("Email Add.:");
        JTextField nameF = new JTextField(20);
        JTextField surnameF = new JTextField(20);
        JTextField emailF = new JTextField(20);
        Container gcp = getContentPane();
        gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
        ((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 150, 400 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 50,60,60,60,100 };

        saveContact.setText("Save contact");
        gcp.add(jsp1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.NORTHWEST,
				GridBagConstraints.VERTICAL,
				new Insets(0, 0, 0, 0), 0, 0));
		jp1.add(saveContact);

        gcp.add(labelName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST,
				GridBagConstraints.VERTICAL,
				new Insets(0, 20, 0, 0), 0, 0));

        gcp.add(labelSurname, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST,
				GridBagConstraints.VERTICAL,
				new Insets(0, 20, 0, 0), 0, 0));

        gcp.add(labelEmailAddr, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST,
				GridBagConstraints.VERTICAL,
				new Insets(0, 20, 0, 0), 0, 0));

        gcp.add(nameF, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

        gcp.add(surnameF, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));

        gcp.add(emailF, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
    }
}