package utility;

import java.io.IOException;
import java.util.ArrayList;


import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Yuri Kalinin read settings from XML file version 1.0.0
 *
 */

public class ReadXMLSettings {

	private static ArrayList settings = new ArrayList();
	private static String[] nodeSetting = { "hostNamePop", "hostNameSmtp", "userName", "messagesFileInbox",  "messagesFileSent" };

	public static void readSettings() {
		String setting = null;
		final String XMLfileName = "settings.xml";
		for (int i = 0; i < nodeSetting.length; i++) {
			String nodeName = nodeSetting[i];
			try {

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(XMLfileName);
				NodeList tableNameNodeList = document.getElementsByTagName(nodeName);

				setting = tableNameNodeList.item(0).getChildNodes().item(0).getTextContent();

			} catch (ParserConfigurationException e) {
				JOptionPane.showMessageDialog(null, "The settings file is not correct. The program will be finished", "Settings file Error", JOptionPane.ERROR_MESSAGE);

				System.exit(0);

			} catch (SAXException e) {
				JOptionPane.showMessageDialog(null, "The settings file is not correct. The program will be finished", "Settings file Error", JOptionPane.ERROR_MESSAGE);

				System.exit(0);
			} catch (IOException e) {

				JOptionPane.showMessageDialog(null, "The settings file can't be find. The program will be finished", "Settings file Error", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
				System.exit(0);

			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "The settings file can't be find. The program will be finished", "Settings file Error", JOptionPane.ERROR_MESSAGE);
				System.err.println(e.getMessage());
				System.exit(0);

			}

			settings.add(setting);
		}
	}

	public ArrayList getSettings() {
		return settings;
	}
}
