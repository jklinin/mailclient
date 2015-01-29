package func_address_book;

import java.io.File;
import java.util.ArrayList;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Unmarshaller;

/**
 *  reading XML file with people information
 * @author Yuri Kalinin
 * @return ArrayList of type People, which contains id, name, surname and emailaddress
 */
public class ReadXMLAdressBook {
	static ArrayList<People> peopleList = new ArrayList<People>();
	
	public static ArrayList<People> read() {
		try {

			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RootXML.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			RootXML root = (RootXML) jaxbUnmarshaller.unmarshal(file);
			
			peopleList = root.getPeople();
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return peopleList;

	}

}
