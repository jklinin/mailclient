package func_core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.mail.MessagingException;

import utility.Run;

public class SaveReadFile {
	/**
	 * the method for serialization of class MessagesDate
	 * 
	 * @param serverFolder
	 * */
	public void saveMessages(ArrayList<MessagesDate> mList, String serverFolder) throws MessagingException, FileNotFoundException, IOException {
		if (serverFolder.equals("Inbox")) {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Run.getNameFileMessagesInboxContainer().toString()));
			for (int i = 0; i < mList.size(); i++) {
				out.writeObject(mList);

			}
			out.flush();
			out.close();

		} else {
			if (serverFolder.equals("Sent")) {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Run.getNameFileMessagesSentContainer().toString()));
				for (int i = 0; i < mList.size(); i++) {
					out.writeObject(mList);

				}
				out.flush();
				out.close();

			}
		}

	}

	/**
	 * the method read the file .ser the method for deserialization of class
	 * MessagesDate returns the array of Messages
	 * */
	public ArrayList<MessagesDate> readMessagesFile(String folder) {
		ObjectInputStream in = null;
		try {
			if (folder.equals("Inbox")) {
				in = new ObjectInputStream(new FileInputStream(Run.getNameFileMessagesInboxContainer().toString()));
			} else {
				System.out.println("++ reading from file " + Run.getNameFileMessagesSentContainer().toString());
				in = new ObjectInputStream(new FileInputStream(Run.getNameFileMessagesSentContainer().toString()));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		ArrayList<MessagesDate> array = null;
		try {
			array = (ArrayList<MessagesDate>) in.readObject();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		try {
			in.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return array;

	}

}
