package func_address_book;

import java.util.ArrayList;

/**
 * abstract class for the reading and writing of address book xml file
 * 
 * @author Yuri Kalinin
 *
 */
public abstract class ReadWriteXMLAddressBook {
	protected String fileName;
	protected ArrayList<People> peopleList;

	ReadWriteXMLAddressBook() {
		this.fileName = "file.xml";
		peopleList = new ArrayList<People>();

	}

	public void setPeopleList(ArrayList<People> peopleList) {
		this.peopleList = peopleList;
	}
}
