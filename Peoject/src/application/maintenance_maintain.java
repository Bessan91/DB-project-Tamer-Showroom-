//package application;
//
//import java.util.Date;
//
//public class maintenance_maintain {
//	
//	private String  Mainten_id ;
//	  private double cost ;
//	 private Date  Mainten_date ;
//	 private String  Car_id ;
//	  private String Mainten_Description ;
//	  private String CustomerId ;
//	  private String customer_name ;
//	  private String customer_phone ;
//	 private String  CarModel;
//	 private int  CarYear ;
//	 
//public String getMainten_Description() {
//		return Mainten_Description;
//	}
//	public void setMainten_Description(String mainten_Description) {
//		Mainten_Description = mainten_Description;
//	}
//	public String getCustomerId() {
//		return CustomerId;
//	}
//	public void setCustomerId(String customerId) {
//		CustomerId = customerId;
//	}
//	public String getCustomer_name() {
//		return customer_name;
//	}
//	public void setCustomer_name(String customer_name) {
//		this.customer_name = customer_name;
//	}
//	public String getCustomer_phone() {
//		return customer_phone;
//	}
//	public void setCustomer_phone(String customer_phone) {
//		this.customer_phone = customer_phone;
//	}
//	public String getCarModel() {
//		return CarModel;
//	}
//	public void setCarModel(String carModel) {
//		CarModel = carModel;
//	}
//	public int getCarYear() {
//		return CarYear;
//	}
//	public void setCarYear(int carYear) {
//		CarYear = carYear;
//	}
//	//	
////	private String Mainten_id;
////	private double cost;
////	private Date  Mainten_date;
////	private String Car_id;
//	public String getMainten_id() {
//		return Mainten_id;
//	}
//	public void setMainten_id(String mainten_id) {
//		Mainten_id = mainten_id;
//	}
//	public double getCost() {
//		return cost;
//	}
//	public void setCost(double cost) {
//		this.cost = cost;
//	}
//	public Date getMainten_date() {
//		return Mainten_date;
//	}
//	public void setMainten_date(Date mainten_date) {
//		Mainten_date = mainten_date;
//	}
//	public String getCar_id() {
//		return Car_id;
//	}
//	public void setCar_id(String car_id) {
//		Car_id = car_id;
//	}
//	public maintenance_maintain(String mainten_id, double cost, Date mainten_date, String car_id) {
//		super();
//		Mainten_id = mainten_id;
//		this.cost = cost;
//		Mainten_date = mainten_date;
//		Car_id = car_id;
//	}
//	public maintenance_maintain(String mainten_id, double cost, Date mainten_date, String car_id,
//			String mainten_Description, String customerId, String customer_name, String customer_phone, String carModel,
//			int carYear) {
//		super();
//		Mainten_id = mainten_id;
//		this.cost = cost;
//		Mainten_date = mainten_date;
//		Car_id = car_id;
//		Mainten_Description = mainten_Description;
//		CustomerId = customerId;
//		this.customer_name = customer_name;
//		this.customer_phone = customer_phone;
//		CarModel = carModel;
//		CarYear = carYear;
//	}
//	@Override
//	public String toString() {
//		return "Maintenance_maintain [Mainten_id=" + Mainten_id + ", cost=" + cost + ", Mainten_date=" + Mainten_date
//				+ ", Car_id=" + Car_id + ", Mainten_Description=" + Mainten_Description + ", CustomerId=" + CustomerId
//				+ ", customer_name=" + customer_name + ", customer_phone=" + customer_phone + ", CarModel=" + CarModel
//				+ ", CarYear=" + CarYear + "]";
//	}
//	
//	
//	
//}


package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;


public class maintenance_maintain {

	private String Mainten_id;
	private double cost;
	private LocalDate Mainten_date;
	private String Car_id;
	private String Mainten_Description;
	private String CustomerId;
	private String customer_name;
	private String customer_phone;
	private String CarModel;
	private int CarYear;

	
	public maintenance_maintain(String mainten_id, double cost, LocalDate mainten_date, String car_id,
			String mainten_Description, String customerId, String customer_name, String customer_phone, String carModel,
			int carYear) {

		Mainten_id = mainten_id;
		this.cost = cost;
		Mainten_date = mainten_date;
		Car_id = car_id;
		Mainten_Description = mainten_Description;
		CustomerId = customerId;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		CarModel = carModel;
		CarYear = carYear;
	}

	public maintenance_maintain(String mainten_Description, double cost) {

		Mainten_Description = mainten_Description;
		this.cost = cost;
	}

	public maintenance_maintain(String customerId, String customerName, String customerPhone, String mainten_Description,
			LocalDate mainten_date, double cost, String carId, String carModel, int carYear) {
		super();
		Mainten_Description = mainten_Description;
		this.cost = cost;
		Mainten_date = mainten_date;
		CustomerId = customerId;
		customer_name = customerName;
		customer_phone = customerPhone;
		Car_id = carId;
		CarModel = carModel;
		CarYear = carYear;
	}
	public maintenance_maintain(String mainten_id, double cost, LocalDate mainten_date, String car_id) {
	
		Mainten_id = mainten_id;
		this.cost = cost;
		Mainten_date = mainten_date;
		Car_id = car_id;
	}


	
	public maintenance_maintain() {
		super();
	}

	public String getMainten_Description() {
		return Mainten_Description;
	}

	public void setMainten_Description(String mainten_Description) {
		Mainten_Description = mainten_Description;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCarModel() {
		return CarModel;
	}

	public void setCarModel(String carModel) {
		CarModel = carModel;
	}

	public int getCarYear() {
		return CarYear;
	}

	public void setCarYear(int carYear) {
		CarYear = carYear;
	}

	public String getMainten_id() {
		return Mainten_id;
	}

	public void setMainten_id(String mainten_id) {
		Mainten_id = mainten_id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LocalDate getMainten_date() {
		return Mainten_date;
	}

	public void setMainten_date(LocalDate mainten_date) {
		Mainten_date = mainten_date;
	}

	public String getCar_id() {
		return Car_id;
	}

	public void setCar_id(String car_id) {
		Car_id = car_id;
	}


	

	@Override
	public String toString() {
		return "Maintenance_maintain [Mainten_id=" + Mainten_id + ", cost=" + cost + ", Mainten_date=" + Mainten_date
				+ ", Car_id=" + Car_id + ", Mainten_Description=" + Mainten_Description + ", CustomerId=" + CustomerId
				+ ", customer_name=" + customer_name + ", customer_phone=" + customer_phone + ", CarModel=" + CarModel
				+ ", CarYear=" + CarYear + "]";
	}
	//________________________________________________________________________________________________________________

		public static ArrayList<maintenance_maintain> getAllUserMaintenanceInfo(Connection connection) {
			ArrayList<maintenance_maintain> Listcustomer = new ArrayList<>();

			String query = "SELECT * FROM maintenance_maintain";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					maintenance_maintain maintenanceInfo = new maintenance_maintain();
					maintenanceInfo.setMainten_Description(resultSet.getString("Mainten_Description"));
					maintenanceInfo.setCost(resultSet.getDouble("Cost"));

					Listcustomer.add(maintenanceInfo);
				}
			} catch (SQLException e) {
				System.err.println("Can Not Retrieve maintenance information: " + e.getMessage());
			}
			return Listcustomer;
		}

		public static ArrayList<maintenance_maintain> getAllManagerMaintenanceInfo(Connection connection) {
			ArrayList<maintenance_maintain> listManager = new ArrayList<>();

			String query = "SELECT * FROM maintenance_maintain";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					maintenance_maintain maintenanceInfo = new maintenance_maintain();

					// Retrieve customer information
					maintenanceInfo.setCustomerId(resultSet.getString("CustomerId"));
					maintenanceInfo.setCustomer_name(resultSet.getString("customer_name"));
					maintenanceInfo.setCustomer_phone(resultSet.getString("customer_phone"));
					maintenanceInfo.setMainten_Description(resultSet.getString("Mainten_Description"));
					maintenanceInfo.setMainten_date(resultSet.getDate("Mainten_date").toLocalDate());
					maintenanceInfo.setCost(resultSet.getDouble("Cost"));
					maintenanceInfo.setCar_id(resultSet.getString("Car_id"));
					maintenanceInfo.setCarModel(resultSet.getString("CarModel"));
					maintenanceInfo.setCarYear(resultSet.getInt("CarYear"));

					listManager.add(maintenanceInfo);
				}
			} catch (SQLException e) {
				System.err.println("Can Not Retrieve maintenance information: " + e.getMessage());
			}
			return listManager;
		}

		public static void deleteMaintenanceInfo(String customerId, Connection connection) throws SQLException {
			String query = "DELETE FROM maintenance_maintain WHERE CustomerId = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setString(1, customerId);

				statement.executeUpdate();
			}
		}

		public static void insertMaintenanceInfo(String mainten_id,String customerId, String customerName, String customerPhone,
				String mainten_Description, LocalDate mainten_date, double cost, String carId, String carModel, int carYear,
				Connection connection) {
			String query = "INSERT INTO maintenance_maintain (Mainten_id, CustomerId, customer_name, customer_phone, Mainten_Description, Mainten_date, Cost, Car_id, CarModel, CarYear) VALUES ( ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setString(1, mainten_id);
				statement.setString(2, customerId);
				if (customerName.isEmpty()) {
					statement.setNull(3, Types.VARCHAR);
				} else {
					statement.setString(3, customerName);
				}

				if (!customerPhone.isEmpty())
					statement.setString(4, customerPhone);
				if (!mainten_Description.isEmpty())
					statement.setString(5, mainten_Description);
				if (mainten_date != null)
					statement.setObject(6, mainten_date);

				if (cost >= 0) {
					statement.setDouble(7, cost);
				} else {
					statement.setNull(7, Types.DOUBLE);
				}
				if (!carId.isEmpty())
					statement.setString(8, carId);
				if (!carModel.isEmpty())
					statement.setString(9, carModel);
				if (carYear > 1885 && carYear <= Year.now().getValue()) {
					statement.setInt(10, carYear);
				} else {
					statement.setNull(10, Types.INTEGER);
				}
				statement.executeUpdate();
				maintenance_maintain newMaintenanceInfo = new maintenance_maintain(customerId, customerName, customerPhone,
							mainten_Description, mainten_date,cost,  carId, carModel, carYear);

				if (!Maintenance.dataManeger.contains(newMaintenanceInfo)) {
					Maintenance.dataManeger.add(newMaintenanceInfo);
					Maintenance.MainttableViewManeger.refresh();
					Maintenance.MainttableView.refresh();

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static void updateDate(String customerId, LocalDate newDate, Connection connection)
				throws SQLException {
			String query = "UPDATE maintenance_maintain SET Mainten_date = COALESCE(?, Mainten_date) WHERE CustomerId = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				if (newDate != null) {
					statement.setObject(1,newDate);
				} else {
					statement.setNull(1, Types.DATE);
				}

				statement.setString(2, customerId);

				statement.executeUpdate();
			}
		}

		public static void updateCost_MaintenId(String customerId, double cost, String maintenId, Connection connection)
				throws SQLException {
			String query = "UPDATE maintenance_maintain SET Cost = COALESCE(?, Cost), Mainten_id = COALESCE(?, Mainten_id) WHERE CustomerId = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				if (cost > 0) {
					statement.setDouble(1, cost);
				} else {
					statement.setNull(1, Types.DOUBLE);
				}

				statement.setString(2, maintenId);
				statement.setString(3, customerId);

				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
