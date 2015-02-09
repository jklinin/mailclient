package func_address_book;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * The method write from ArrayList Name, Surname and Email
 * Address into the XML file 
 * @author Yuri Kalinin 
 * @version 1.0.0
 */
public class WriteXMLAddressBook extends ReadWriteXMLAddressBook {

	public void writeXMLAddressBook() {
		RootXML people = new RootXML();

		for (int i = 0; i < peopleList.size(); i++) {
			people.setPeople(peopleList);
		}
		try {

			File file = new File(fileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(RootXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(people, file);
			

		} catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, "Errorr: XML Writting", "Error in the write E Mail", JOptionPane.ERROR_MESSAGE);
			System.err.println(e.getMessage());
		}

	}
}