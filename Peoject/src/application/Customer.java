package application;

public class Customer {
	private String Customer_id;
	private String FullName;
//	 private String Last_Name ;
	private String phone;

	public Customer(String customer_id, String fullName, String phone) {
		super();
		Customer_id = customer_id;
		FullName = fullName;
		this.phone = phone;

	}

//	public Customer(String customer_id, String FullName, String phone) {
//		super();
//		Customer_id = customer_id;
//		this.phone = phone;
//	}
	public String getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(String customer_id) {
		Customer_id = customer_id;
	}
//	public String getFirst_Name() {
//		return First_Name;
//	}
//	public void setFirst_Name(String first_Name) {
//		First_Name = first_Name;
//	}
//	public String getLast_Name() {
//		return Last_Name;
//	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}