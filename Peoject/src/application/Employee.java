package application;

public class Employee {
	private int employeeId;
	private String nameEmp;
	private int salary;
	private String position;
	private String phone;
	private int age;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String nameEmp, int salary, String position, String phone, int age) {
		this.employeeId = employeeId;
		this.nameEmp = nameEmp;
		this.salary = salary;
		this.position = position;
		this.phone = phone;
		this.age = age;

	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getNameEmp() {
		return nameEmp;
	}

	public void setNameEmp(String nameEmp) {
		this.nameEmp = nameEmp;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return employeeId + "," + position + "," + salary + "," + nameEmp + "," + age + "," + phone;

	}
}