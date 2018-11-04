package BusinessObjects;

public abstract class Employee {
	private PersonalInfo personaInfo;
	private IdCard idCard;
	private Position position;

	public Employee(PersonalInfo personaInfo, IdCard idCard, Position position) {
		super();
		this.personaInfo = personaInfo;
		this.idCard = idCard;
		this.position = position;
	}

	public PersonalInfo getPersonaInfo() {
		return personaInfo;
	}

	public void setPersonaInfo(PersonalInfo personaInfo) {
		this.personaInfo = personaInfo;
	}

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
