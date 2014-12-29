package clientCore;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class Authenticator2 extends JFrame {
	private DefaultTableModel model;
	private static String passwordMail;
	private JButton buttonNewMail;
	private JButton buttonUpdateMail;
	private JButton buttonAnswer;
	private JTable previewMail;
	private JEditorPane viewMail;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JLabel previewlabel;
	private JLabel viewlabel;
	private JLabel statuslabel;
	private Message[] messages = null;
	ArrayList<MessagesDate> messagesList ;
	public Authenticator2() {
		super("Main Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponentsToPane();

		setVisible(true);

	}

	public void addComponentsToPane() {
		model = new DefaultTableModel();
		previewMail = new JTable(model);
		buttonNewMail = new JButton();
		buttonUpdateMail = new JButton();
		buttonAnswer = new JButton();
		viewMail = new JEditorPane();
		scrollPane1 = new JScrollPane();
		scrollPane2 = new JScrollPane();
		previewlabel = new JLabel();
		viewlabel = new JLabel();
		statuslabel = new JLabel();
		messagesList = new ArrayList();
		Container gcp = getContentPane();
		gcp.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		((GridBagLayout) gcp.getLayout()).columnWidths = new int[] { 800, 400, 400, 0 };
		((GridBagLayout) gcp.getLayout()).rowHeights = new int[] { 0, 0, 600, 40, 0 };

		buttonNewMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonNewMail) {
					NewMailWindow newMail = new NewMailWindow();
					// passwordDialog();
					// SendMail writteMail = new
					// MailWrite(Run.getSettingProtocolSMTP(),
					// Run.getSettingUserName(), passwordMail);
					// writteMail.answerMail("project_test91@mail.ru",
					// "project_test91@mail.ru", "test", "test, test"); // put
					// dest.
					// emailadress
					// into
					// Email

				}
			}
		});
		buttonNewMail.setText("New Mail");

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.VERTICAL;
		c.ipadx = 0;
		c.ipady = 0;
		gcp.add(buttonNewMail, c);

		buttonUpdateMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == buttonUpdateMail) {
					passwordDialog();
					new Thread(new UpdateEMailThread()).start();

				}
			}

		});

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		buttonAnswer.setText("Answer");
		gcp.add(buttonAnswer, c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		buttonUpdateMail.setText("Update");
		gcp.add(buttonUpdateMail, c);

		previewlabel.setText("Incoming mail");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		gcp.add(previewlabel, c);
		viewlabel.setText("Mail content");
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		gcp.add(viewlabel, c);
        //----Jtable----------------
		scrollPane1.setViewportView(previewMail);
		model.addColumn("FROM");
		model.addColumn("TO");
		model.addColumn("Subject");
		model.addColumn("Sent Date");
		previewMail.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		ListSelectionModel rowSM = previewMail.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;

				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				if (!lsm.isSelectionEmpty()) {
					int selectedRow = lsm.getMinSelectionIndex();
					viewMail.setText(messagesList.get(selectedRow).getContent());
				}
			}
		});




        for(int i=0; i<4; i++){
			previewMail.getColumnModel().getColumn(i).setPreferredWidth(200);
			previewMail.getColumnModel().getColumn(i).setMaxWidth(200);
			previewMail.getColumnModel().getColumn(i).setMinWidth(200);
         }
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		gcp.add(scrollPane1, c);
		//---------------------------------------------------
		 // thread for the adding of rows
		new Thread(new AddRowsThread()).start();
		// ----------JEditorPane----------------------------
		scrollPane2.setViewportView(viewMail);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		gcp.add(scrollPane2, c);
		//-------------------------------------------------

		statuslabel.setText("Status Bar");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.VERTICAL;
		gcp.add(statuslabel, c);
		/*
		 * gcp.add(statuslabel, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0,
		 * GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0,
		 * 0, 0, 0), 0, 0));
		 */
		pack();
		setLocationRelativeTo(getOwner());
	}

	public static void passwordDialog() {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.setEchoChar('#');
		JOptionPane.showMessageDialog(null, passwordField, "Enter password", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(passwordField.getPassword());
		passwordMail = String.valueOf(passwordField.getPassword());
		System.out.println(passwordMail);
	}

	class AddRowsThread implements Runnable {
		public void addNewRowTable() throws FileNotFoundException, ClassNotFoundException, IOException {
			model.setRowCount(0);
			UpdateMail readMail = new MailReader();

			messagesList = readMail.readMessagesFile();
			for (int i = 0; i < messagesList.size(); i++) {

				model.addRow(new Object[] { messagesList.get(i).getAddressFrom(), messagesList.get(i).getAdressTo(), messagesList.get(i).getSubject(), messagesList.get(i).getSentDate() });
			}
		}

		@Override
		public void run() {
			try {
				addNewRowTable();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	class UpdateEMailThread implements Runnable {

		@Override
		public void run() {
			UpdateMail readMail = new MailReader(Run.getSettingProtocolPOP(), Run.getSettingUserName(), passwordMail);
			try {
				readMail.connectionInbox();
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			readMail.getMassagesArray();
			new Thread(new AddRowsThread()).start();

		}

	}


}
