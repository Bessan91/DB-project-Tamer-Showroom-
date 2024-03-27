package application;

public class Person {

	private String Customer_id;
	private String cus_name;
	private String phone;

	public Person(String customer_id, String cus_name, String phone) {
		super();
		Customer_id = customer_id;
		this.cus_name = cus_name;
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Person(String customer_id, String cus_name) {
		super();
		Customer_id = customer_id;
		this.cus_name = cus_name;
	}

	public String getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(String customer_id) {
		Customer_id = customer_id;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	@Override
	public String toString() {
		return "Person [Customer_id=" + Customer_id + ", cus_name=" + cus_name + "]";
	}

}