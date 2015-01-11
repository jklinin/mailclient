
package clientCore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Yuri Kalinin
 * read settings from XML file
 * version 1.0.0
 *
 */


public class ReadXML {

	private static ArrayList settings = new ArrayList();
	private static String[] nodeSetting = { "hostNamePop","hostNameSmtp", "userName", "messagesFile" };

	public static void readSettings() {
		String setting = null;
		final String XMLfileName = "settings.xml";
		for (int i = 0; i < nodeSetting.length; i++) {
			String nodeName=nodeSetting[i];
			try {

				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(XMLfileName);
				NodeList tableNameNodeList = document
						.getElementsByTagName(nodeName);

				setting = tableNameNodeList.item(0).getChildNodes().item(0)
						.getTextContent();

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			settings.add(setting);
		}
	}
public ArrayList getSettings(){
	return settings;
}
}


