package utility;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.mail.MessagingException;

import core_gui_and_threads.MainWindow;
import func_address_book.People;

public class Run {
	public static PeopleDateBase addressBookDateBase = new PeopleDateBase();

	private static ArrayList<String> settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {
		// ------settings-------------------
		ReadXML readerXML = new ReadXML();
		readerXML.readSettings();
		settings = readerXML.getSettings();
		// ---------------------------------
		
MainWindow	mainWindow = new MainWindow();// GUI
mainWindow.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {

        // do other stuff....
    	System.out.println("Window is closed"); //TODO remove this 

    	System.out.println("close main frame");//TODO remove this just for testing
//		addressBookDateBase.addNewPeople("test1", "surname1", "emailaddr");
//		addressBookDateBase.addNewPeople("test1", "surname2", "emailaddr");
		addressBookDateBase.clocePeopleDateBase();
		for (int i = 0; i < addressBookDateBase.getListPeople().size(); i++) {
			System.out.println(((addressBookDateBase.getListPeople()).get(i)).getId());
		}
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
