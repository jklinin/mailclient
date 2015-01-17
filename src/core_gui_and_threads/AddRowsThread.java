package core_gui_and_threads;

import java.io.FileNotFoundException;
import java.io.IOException;

import func_core.GetMails;
import func_core.MailReader;



/**
	 * 
	 * @author Yuri Kalinin this thread adds the new rows in the view table
	 *
	 */
	public class AddRowsThread implements Runnable {
		public void addNewRowTable() throws FileNotFoundException, ClassNotFoundException, IOException {
			MainWindow.model.setRowCount(0);
			GetMails readMail = new MailReader();

			MainWindow.messagesList = readMail.readMessagesFile();
			System.out.println(MainWindow.messagesList.size());//TODO
			int i = MainWindow.messagesList.size() -1;
			do {

				MainWindow.model.addRow(new Object[] { MainWindow.messagesList.get(i).getAddressFrom(), MainWindow.messagesList.get(i).getAddressTo(), MainWindow.messagesList.get(i).getSubject(), MainWindow.messagesList.get(i).getSentDate() });
				i--;

			} while (i > 0);
		}

		@Override
		public void run() {
			try {
				addNewRowTable();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MainWindow.statuslabel.setText("");
		}
	}