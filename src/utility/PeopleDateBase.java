package utility;

import java.util.ArrayList;

import func_address_book.*;

/**
 * contains list of all persons in the address book. this is temporary. by
 * closing of program people list will be written into xml file
 * 
 * @author Yuri version 1.0.0
 */
public class PeopleDateBase {
	private static ArrayList<People> peopleList;
	private static int lastID;
	private int firstID;

	PeopleDateBase() {
		peopleList = new ArrayList<People>();
		new Thread(new ReadPeopleThread()).start();

	}

	public void addNewPeople(String name, String surname, String emailaddr) {
		People p = new People();

		p.setId(lastID);
		lastID++;
		p.setName(name);
		p.setSurname(surname);
		p.setEmladr(emailaddr);
		peopleList.add(p);

	}

	private void setPeopleList(ArrayList<People> peopleList) {
		if(peopleList!=null){
		this.peopleList = peopleList;
		}else{
			peopleList = new ArrayList<People>();
		}
	}

	private void setFirsLastId() {
		if(peopleList!=null){
		lastID = (peopleList.size()) - 1;
		firstID = lastID;
		}else{
			lastID=0;
			firstID=0;
		}
	}

	private class ReadPeopleThread implements Runnable {

		@Override
		public void run() {

			ReadXMLAdressBook read = new ReadXMLAdressBook();
			setPeopleList(read.read());
			setFirsLastId();

		}

	}

	public static ArrayList<People> getListPeople() {
		return peopleList;
	}

	public void clocePeopleDateBase() {
		if (lastID != firstID) {

			new Thread(new WritePeopleThread()).start();
		}
	}

	public class WritePeopleThread implements Runnable {

		@Override
		public void run() {

			WriteXMLAddressBook write = new WriteXMLAddressBook();
			write.setPeopleList(peopleList);
			write.writeXMLAddressBook();

		}

	}

	public static void removePeople(int index) {
		peopleList.remove(index);
		lastID--;
	}
}
