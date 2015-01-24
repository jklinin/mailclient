package utility;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;

import func_address_book.*;

/**
 * contains list of all persons in the address book. this is temporary. by
 * closing of program people list will be written into xml file
 * 
 * @author Yuri 
 * version 1.0.0
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
		System.out.println(name);// TODO just for testing
	}

	private void setPeopleList(ArrayList<People> peopleList) {
		this.peopleList = peopleList;
	}

	private void setFirsLastId() {
		lastID = (peopleList.size()) - 1;
		firstID = lastID;
	}

	private class ReadPeopleThread implements Runnable {

		@Override
		public void run() {
			System.out.println("Thread start "); // TODO this just for testing
													// remove this
			ReadOutXML read = new ReadOutXML();
			setPeopleList(read.read());
			setFirsLastId();

		}

	}

	public static ArrayList<People> getListPeople() {
		return peopleList;
	}

	public void clocePeopleDateBase() {
		if (lastID != firstID) {
			System.out.println("Write XML");// TODO Remove this just for testing
			new Thread(new WritePeopleThread()).start();
		}
	}

	public class WritePeopleThread implements Runnable {

		@Override
		public void run() {

			WriteXML write = new WriteXML();
			write.writeXMLAddressBook(peopleList);

		}

	}
	
	public static void removePeople(int index){
		peopleList.remove(index);
		lastID--;
	}
}
