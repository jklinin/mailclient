package func_address_book;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;
/**
 * THe class for the the reading XML file with people
 * @author Yuri Kalinin
 * @return ArrayList of people from XML File
 */
public class ReadOutXML {
	static ArrayList<People> peopleList = new ArrayList<People>();
	
	public static ArrayList<People> read() {
		try {

			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RootXML.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			RootXML root = (RootXML) jaxbUnmarshaller.unmarshal(file);
			
			peopleList = root.getPeople();
			for (int i = 0; i < peopleList.size(); i++) {
				System.out.println(peopleList.get(i).getId());
			}
			
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return peopleList;

	}

}
