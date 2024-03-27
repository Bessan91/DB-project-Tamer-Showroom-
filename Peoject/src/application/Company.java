package application;

public class Company {
	private String Customer_id;
	private String comp_name;
	private String phone;

	public Company(String customer_id, String comp_name, String phone) {
		super();
		Customer_id = customer_id;
		this.comp_name = comp_name;
		this.phone = phone;
	}

	public Company(String customer_id, String comp_name) {
		super();
		Customer_id = customer_id;
		this.comp_name = comp_name;
	}

//    public Company(String comp_name, String phone) {
//		super();
//		this.comp_name = comp_name;
//		this.phone = phone;
//	}

	public String getCustomer_id() {
		return Customer_id;
	}

	public void setCustomer_id(String customer_id) {
		Customer_id = customer_id;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Company [Customer_id=" + Customer_id + ", comp_name=" + comp_name + "]";
	}
}