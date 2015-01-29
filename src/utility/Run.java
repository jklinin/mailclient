package utility;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import core_gui_and_threads.MainWindow;
import func_core.SaveReadFile;

public class Run {
	public static PeopleDateBase addressBookDateBase = new PeopleDateBase();

	private static ArrayList<String> settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {
		// ------settings-------------------
		ReadXMLSettings readerXML = new ReadXMLSettings();
		readerXML.readSettings();
		settings = readerXML.getSettings();
		// ---------------------------------
		
MainWindow	mainWindow = new MainWindow("Inbox");// GUI
mainWindow.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {

        // do other stuff....
    	
    	if(MainWindow.getMessagesListSent()!=null){
    	try {
			new SaveReadFile().saveMessages(MainWindow.getMessagesListSent(), "Sent");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	}
    	
		addressBookDateBase.clocePeopleDateBase();
		
    	mainWindow.dispose();
    }
});
		
			
		}
	

	public static Object getSettingUserName() {
		return settings.get(2);
	}

	public static Object getSettingProtocolPOP() {
		return settings.get(0);

	}

	public static Object getSettingProtocolSMTP() {
		return settings.get(1);

	}

	public static Object getNameFileMessagesInboxContainer() {
		return settings.get(3);

	}

	public static Object getNameFileMessagesSentContainer() {
		return settings.get(4);

	}

}
