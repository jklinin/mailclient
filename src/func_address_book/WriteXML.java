package func_address_book;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * 
 * 
 * @author Yuri Kalinin
 * The method writte into the XML file from ArrayList with Name, Surname and Emailaddress
 * 
 *
 */
public class WriteXML  {
	
public void writeXMLAddressBook (ArrayList <String> peopleInform){
		RootXML people = new RootXML();
		ArrayList<People> peopleList = new ArrayList<People>();
			for(int i=0; i<peopleInform.size()-1;i++){
			People p = new People();
			p.setId(i);
			p.setName(peopleInform.get(i));
			p.setSurname(peopleInform.get(i));
			p.setEmladr(peopleInform.get(i));

			peopleList.add(p);
			people.setPeople(peopleList);
			}

		try {

			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RootXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(people, file);
			jaxbMarshaller.marshal(people, System.out);

		

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}