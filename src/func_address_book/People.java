package func_address_book;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * this keep the information about people
 * contains information for creation of XML file
 * @author Yuri Kalinin
 * @version 1.0.0
 *
 */
@XmlType(propOrder = { "id", "name", "surname", "emladr" })
public class People {
	private int id;
	private String name;
	private String surname;
	private String emladr;

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSurname() {
		return surname;
	}

	@XmlElement
	public void setEmladr(String emladr) {
		this.emladr = emladr;
	}

	public String getEmladr() {
		return emladr;
	}
}
