package gui_addressbook;

import java.util.ArrayList;

import func_address_book.People;
import func_address_book.ReadOutXML;

public class ReadPeopleThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ReadOutXML read = new ReadOutXML();
		ArrayList <People> people =read.read();
		
	}

}
