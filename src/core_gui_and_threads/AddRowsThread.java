package core_gui_and_threads;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;


import func_core.SaveReadFile;

/**
 * 
 * @author Yuri Kalinin this thread adds the new rows in the view table
 *
 */
public class AddRowsThread implements Runnable {
	private String folder;

	AddRowsThread(String folder) {
		this.folder = folder;

	}

	public void addNewRowTable() throws FileNotFoundException, ClassNotFoundException, IOException {
		MainWindow.model.setRowCount(0);
		SaveReadFile readMailFile = new SaveReadFile();
		if (folder.equals("Inbox")) {
			MainWindow.messagesListInbox = readMailFile.readMessagesFile(folder);

			int i = MainWindow.messagesListInbox.size() - 1;
			do {

				MainWindow.model.addRow(new Object[] { MainWindow.messagesListInbox.get(i).getAddressFrom(), MainWindow.messagesListInbox.get(i).getAddressTo(), MainWindow.messagesListInbox.get(i).getSubject(), MainWindow.messagesListInbox.get(i).getSentDate() });
				i--;

			} while (i > 0);
		} else {
			MainWindow.messagesListSent = readMailFile.readMessagesFile(folder);

		}
	}

	@Override
	public void run() {
		try {
			addNewRowTable();
		} catch (ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "The file can't be funded", "Pleae add the XML file for the Address Book", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getMessage());
		}

		MainWindow.setStatusBarLabel("Status bar");
	}
}