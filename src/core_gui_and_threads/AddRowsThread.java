package core_gui_and_threads;

import java.io.FileNotFoundException;
import java.io.IOException;

import func_core.GetMailsServer;
import func_core.MailReader;
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
		System.out.println(this.folder + " AddRowsThread");// TODO
	}

	public void addNewRowTable() throws FileNotFoundException, ClassNotFoundException, IOException {
		MainWindow.model.setRowCount(0);
		SaveReadFile readMailFile = new SaveReadFile();
		if (folder.equals("Inbox")) {
			MainWindow.messagesListInbox = readMailFile.readMessagesFile(folder);
			System.out.println(MainWindow.messagesListInbox.size());// TODO
			int i = MainWindow.messagesListInbox.size() - 1;
			do {

				MainWindow.model.addRow(new Object[] { MainWindow.messagesListInbox.get(i).getAddressFrom(), MainWindow.messagesListInbox.get(i).getAddressTo(), MainWindow.messagesListInbox.get(i).getSubject(), MainWindow.messagesListInbox.get(i).getSentDate() });
				i--;

			} while (i > 0);
		}else{
			MainWindow.messagesListSent = readMailFile.readMessagesFile(folder);

			
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
			
		MainWindow.setStatusBarLabel("Status bar");
		}
	}