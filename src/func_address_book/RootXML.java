package func_address_book;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RootXML {

	private ArrayList<People> people;

	@XmlElement
	public void setPeople(ArrayList<People> p) {
		people=p;
		
		
	}

	public ArrayList<People> getPeople() {
		return people;
	}

	
		
	

}