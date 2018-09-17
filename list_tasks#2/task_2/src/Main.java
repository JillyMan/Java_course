import java.util.Date;

import BusinessObjects.Address;
import BusinessObjects.ContactInfo;
import BusinessObjects.Employee;
import BusinessObjects.IdCard;
import BusinessObjects.PersonalInfo;
import BusinessObjects.Position;
import BusinessObjects.EmployeeType.Manager;

public class Main {
	public static void main(String[] args) { 
		Address address = new Address(10, 2, "wall street");
		ContactInfo ci = new ContactInfo("sahd@gamin.com", "375-33-6886-155");
		PersonalInfo pi = new PersonalInfo("bob", address, new Date(1999, 12, 1), ci);
		Position pos = new Position("Manager");
		IdCard idCard = new IdCard(13);	
		Manager manager = new Manager(pi, idCard, pos);
	}
}
