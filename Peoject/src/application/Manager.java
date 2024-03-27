package application;

public class Manager {
	private String managerID;
	private String name;
	private String phone;
	private int age;

	public Manager() {
		super();
	}

	// Constructor
	public Manager(String managerID, String name, String phone, int age) {
		this.managerID = managerID;
		this.name = name;
		this.phone = phone;
		this.age = age;
	}

	// Getter methods
	public String getManagerID() {
		return managerID;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public int getAge() {
		return age;
	}

	// Setter methods
	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// toString method
	@Override
	public String toString() {
		return "Manager{" + "managerID='" + managerID + '\'' + ", name='" + name + '\'' + ", phone='" + phone + '\''
				+ ", age=" + age + '}';
	}
}