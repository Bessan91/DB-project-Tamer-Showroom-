package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

public class PayingOffInfo {

	private String CustomerId;
	private String CustomerName;
	private double debts;

	public PayingOffInfo() {
		super();
	}

	public PayingOffInfo(String customerId, String customerName, double debts) {
		super();
		CustomerId = customerId;
		CustomerName = customerName;
		this.debts = debts;
	}

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public double getDebts() {
		return debts;
	}

	public void setDebts(double debts) {
		this.debts = debts;
	}

	@Override
	public String toString() {
		return "PayingOffInfo [CustomerId=" + CustomerId + ", CustomerName=" + CustomerName + ", debts=" + debts + "]";
	}

//________________________________________________________________________________________________________________________________	

	public static ArrayList<PayingOffInfo> getAllManegarPayingOffInfo(Connection connection) {
		ArrayList<PayingOffInfo> Listcustomer = new ArrayList<>();

		String query = "SELECT * FROM paying_off";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PayingOffInfo paying_offInfo = new PayingOffInfo();
				paying_offInfo.setCustomerId(resultSet.getString("CustomerId"));
				paying_offInfo.setCustomerName(resultSet.getString("customer_name"));
				paying_offInfo.setDebts(resultSet.getDouble("debts"));

				Listcustomer.add(paying_offInfo);
			}
		} catch (SQLException e) {
			System.err.println("Can Not Retrieve maintenance information: " + e.getMessage());
		}
		return Listcustomer;
	}

	public static void updatePayingOffInfo(String customerId, double debts, Connection connection) {
		String query = "UPDATE paying_off SET debts = COALESCE(?, debts) WHERE CustomerId = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			if (debts > 0) {
				statement.setDouble(1, debts);
			} else {
				statement.setNull(1, Types.DOUBLE);
			}

			statement.setString(2, customerId);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
