package func_address_book;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * The method write from ArrayList Name, Surname and Email
 * Address into the XML file 
 * @author Yuri Kalinin 
 * @version 1.0.0
 */
public class WriteXML {

	public void writeXMLAddressBook(ArrayList<People> peopleList) {
		RootXML people = new RootXML();

		for (int i = 0; i < peopleList.size(); i++) {
			people.setPeople(peopleList);
		}
		try {

			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RootXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(people, file);
			jaxbMarshaller.marshal(people, System.out); // TODO Just for testing

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}