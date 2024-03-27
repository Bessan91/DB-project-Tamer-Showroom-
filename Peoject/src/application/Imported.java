
package application;

public class Imported {

	private String Car_id;
	private String import_id;
	private int import_account;
	public Imported(String car_id, String import_id, int import_account) {
		super();
		Car_id = car_id;
		this.import_id = import_id;
		this.import_account = import_account;
	}
	public String getCar_id() {
		return Car_id;
	}
	public void setCar_id(String car_id) {
		Car_id = car_id;
	}
	public String getImport_id() {
		return import_id;
	}
	public void setImport_id(String import_id) {
		this.import_id = import_id;
	}
	public int getImport_account() {
		return import_account;
	}
	public void setImport_account(int import_account) {
		this.import_account = import_account;
	}
	
	

}
