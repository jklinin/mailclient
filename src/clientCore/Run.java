package clientCore;

/**
 * @author Yuri Kalinin 
 * version 1.0.1
 **/

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

public class Run {
	private static ArrayList<String> settings = new ArrayList();

	public static void main(String[] args) throws MessagingException {

		new Thread(new ReadCSVRunnable()).start();

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

	public static class ReadCSVRunnable implements Runnable {
		@Override
		public void run() {
			ReadXML readerXML = new ReadXML();
			readerXML.readSettings();
			settings = readerXML.getSettings();
		}
	}

}
