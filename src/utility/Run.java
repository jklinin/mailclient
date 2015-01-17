package utility;

import java.util.ArrayList;

import javax.mail.MessagingException;

import core_gui_and_threads.MainWindow;
import func_core.ReadXML;






public class Run {
	
	private static ArrayList<String> settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {

		ReadCSVRunnable(); // settings

		new MainWindow();// GUI

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

	public static Object getNameFileMessagesContainer() {
		return settings.get(3);

	}

	public static void ReadCSVRunnable() {

		ReadXML readerXML = new ReadXML();
		readerXML.readSettings();
		settings = readerXML.getSettings();
		
	}

}
