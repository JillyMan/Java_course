package BusinessObjects.EmployeeType;

import BusinessObjects.Employee;
import BusinessObjects.IdCard;
import BusinessObjects.PersonalInfo;
import BusinessObjects.Position;

public class Manager extends Employee{

	public Manager(PersonalInfo personaInfo, IdCard idCard, Position position) {
		super(personaInfo, idCard, position);
	}

}
