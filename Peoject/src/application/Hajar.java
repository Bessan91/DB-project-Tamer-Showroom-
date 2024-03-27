package application;
//import application.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.io.FileInputStream;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import static javafx.stage.Modality.NONE;

import java.io.FileNotFoundException;
import java.sql.*;

public class Hajar {
	private static ArrayList<Employee> data = new ArrayList<>();
	private static ObservableList<Employee> dataList = FXCollections.observableArrayList();
	private static ArrayList<Showroom> datash = new ArrayList<>();
	private static ObservableList<Showroom> dataListsh = FXCollections.observableArrayList();

	public static GridPane Sign_In(Stage primaryStage) throws FileNotFoundException {
		// primaryStage.setTitle("Sign In");

		// Create the grid pane for the form
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER_LEFT);

		// Add "Tamer" text at the top
		Text tamerText = new Text("Tamer");
		tamerText.setFont(Font.font("Edwardian Script ITC", FontWeight.BOLD, 70));
		tamerText.setFill(Color.WHITE);
		gridPane.add(tamerText, 0, 0);

		// Add "Car Showroom" text underneath "Tamer"
		Text carShowroomText = new Text("Car Showroom");
		carShowroomText.setFont(Font.font("Edwardian Script ITC", FontWeight.BOLD, 36));
		carShowroomText.setFill(Color.WHITE);
		gridPane.add(carShowroomText, 1, 1);

		// Add form elements
		Label usernameLabel = new Label("Username:");
		Label passwordLabel = new Label("Password:");
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		Button signInButton = new Button("Sign In");

		// Set label text color to white
		usernameLabel.setTextFill(Color.WHITE);
		passwordLabel.setTextFill(Color.WHITE);

		gridPane.add(usernameLabel, 0, 2);
		gridPane.add(usernameField, 1, 2);
		gridPane.add(passwordLabel, 0, 3);
		gridPane.add(passwordField, 1, 3);
		gridPane.add(signInButton, 1, 4);

		// Apply CSS styles to the button
		signInButton.setStyle("-fx-background-color: #40E0D0;" + "-fx-text-fill: black;");

		// Load the background image
//	ImageView view = null;

	ImageView backgroundImage = new ImageView(new Image(new FileInputStream("car.jpeg")));

		// Create the background image
//		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
//				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		// Set the background image to the grid pane
//		gridPane.setBackground(new Background(background));

		signInButton.setOnAction(e -> {
	    	data = new ArrayList<>();
	        String username = usernameField.getText();
	        String password = passwordField.getText();
	        try {
	            getData();
	            dataList = FXCollections.observableArrayList(data);

		        // Check if the username and password are valid
		        if (isValidAccountm(username, password)) {
		        	Bessan.showDashboard(primaryStage);
		           // showMainPage(primaryStage);
		        } else {
		            // TODO: Add error handling for invalid credentials
		        }
	            

	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        } catch (ClassNotFoundException e1) {
	            e1.printStackTrace();
	        } catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	    });
		return gridPane;

	}


	private static boolean isValidAccountm(String username, String password) throws SQLException {
	    // TODO: Implement your logic to validate the username and password
	    // For demo purposes, let's assume the username is "admin" and password is "password"
		
	
			DatabaseConnector.connectDB();
		
			Statement stmt = Main.con.createStatement();
			String SQL = "SELECT * FROM Manager WHERE Name = '" + username + "' AND ManagerID = '" + password + "'";
			ResultSet rs = stmt.executeQuery(SQL);

			if (rs.next()) {
			

					JOptionPane.showMessageDialog(null, "Login sucessfuly");
					return true;
			}
			return false;
			
				   
     	 
 
}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void showMainPage(Stage primaryStage) {
		// Create a new grid pane for the main page
		GridPane mainGridPane = new GridPane();
		mainGridPane.setPadding(new Insets(20));
		mainGridPane.setHgap(10);
		mainGridPane.setVgap(10);
		mainGridPane.setAlignment(Pos.CENTER);

		// Add "Showroom" button
		Button showroomButton = new Button("Showroom");

		showroomButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");
		showroomButton.setPrefWidth(150);
		showroomButton.setOnAction(e -> {
			Stage newStage = new Stage();
			newStage.setTitle("Showroom Data");
			datash = new ArrayList<>();

			try {
				getDatash();
				dataListsh = FXCollections.observableArrayList(datash);

				showShowroomPage(newStage);
				newStage.show();

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			// Open new stage

		});
		// Add "Employee" button
		Button employeeButton = new Button("Employee");
		employeeButton.setPrefWidth(150);
		employeeButton.setOnAction(e -> {

			Stage newStage = new Stage();
			newStage.setTitle("Employee Data");
			data = new ArrayList<>();

			try {
				getData();
				dataList = FXCollections.observableArrayList(data);

				showEmployeePage(primaryStage);
				primaryStage.show();

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

		});

		mainGridPane.add(showroomButton, 0, 0);
		mainGridPane.add(employeeButton, 1, 0);

		// Create a scene with the main grid pane
		Scene mainScene = new Scene(mainGridPane, 600, 400);

		// Set the scene to the stage
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	@SuppressWarnings("unchecked")
	public static void showEmployeePage(Stage stage) {
		TableView<Employee> myDataTable = new TableView<Employee>();

		Scene scene = new Scene(new Group());
		stage.setTitle("Employee Table");
		stage.setWidth(550);
		stage.setHeight(500);
		String backgroundColorCode = "#DCDCDC";
		scene.setFill(Color.web(backgroundColorCode)); // Set the background color

		Label label = new Label("Employee Table");
		label.setFont(new Font("Times New Roman", 20));

		// String c = "#213A5C";
		// label.setTextFill(Color.web(c));
		label.setTextFill(Color.BLACK);

		myDataTable.setEditable(true);
		myDataTable.setMaxHeight(200);
		myDataTable.setMaxWidth(505);

		TableColumn<Employee, Integer> employeeIdCol = new TableColumn<>("employeeId");
		employeeIdCol.setMinWidth(50);
		employeeIdCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());

		TableColumn<Employee, String> nameCol = new TableColumn<>("nameEmp");
		nameCol.setMinWidth(60);
		nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameEmp()));

		nameCol.setCellFactory(TextFieldTableCell.<Employee>forTableColumn());

		nameCol.setOnEditCommit((TableColumn.CellEditEvent<Employee, String> t) -> {
			((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setNameEmp(t.getNewValue()); // display
																														// only
			updateName(t.getRowValue().getEmployeeId(), t.getNewValue());
		});

		TableColumn<Employee, Integer> SalaryCol = new TableColumn<>("Salary");
		SalaryCol.setMinWidth(50);
		SalaryCol
				.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getSalary()).asObject());

		SalaryCol.setCellFactory(TextFieldTableCell.<Employee, Integer>forTableColumn(new IntegerStringConverter()));

		SalaryCol.setOnEditCommit((TableColumn.CellEditEvent<Employee, Integer> t) -> {
			((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalary(t.getNewValue());
			updateSalary(t.getRowValue().getEmployeeId(), t.getNewValue());
		});

		TableColumn<Employee, String> positionCol = new TableColumn<>("position");
		positionCol.setMinWidth(100);
		positionCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));

		positionCol.setCellFactory(TextFieldTableCell.<Employee>forTableColumn());
		positionCol.setOnEditCommit((TableColumn.CellEditEvent<Employee, String> t) -> {
			((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPosition(t.getNewValue());
			updatePosition(t.getRowValue().getEmployeeId(), t.getNewValue());
		});

		TableColumn<Employee, String> PhoneCol = new TableColumn<>("Phone");
		PhoneCol.setMinWidth(100);
		PhoneCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

		PhoneCol.setCellFactory(TextFieldTableCell.<Employee>forTableColumn());
		PhoneCol.setOnEditCommit((TableColumn.CellEditEvent<Employee, String> t) -> {
			((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPhone(t.getNewValue());
			updatePhone(t.getRowValue().getEmployeeId(), t.getNewValue());
		});

		TableColumn<Employee, Integer> ageCol = new TableColumn<>("age");
		ageCol.setMinWidth(50);
		ageCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());

		ageCol.setCellFactory(TextFieldTableCell.<Employee, Integer>forTableColumn(new IntegerStringConverter()));

		ageCol.setOnEditCommit((TableColumn.CellEditEvent<Employee, Integer> t) -> {
			((Employee) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAge(t.getNewValue());
			updateAge(t.getRowValue().getEmployeeId(), t.getNewValue());
		});

///////////////////////////////////////////////////////////////////

		myDataTable.setItems(dataList);
		myDataTable.getColumns().addAll(employeeIdCol, nameCol, SalaryCol, positionCol, PhoneCol, ageCol);
		myDataTable.refresh();
		System.out.println("Data List Contents:");
		dataList.forEach(System.out::println);

		final TextField addemployeeId = new TextField();
		addemployeeId.setPromptText("employeeId");
		addemployeeId.setMaxWidth(employeeIdCol.getPrefWidth());

		final TextField addname = new TextField();
		addname.setMaxWidth(nameCol.getPrefWidth());
		addname.setPromptText("name");

		final TextField addSalary = new TextField();
		addSalary.setMaxWidth(SalaryCol.getPrefWidth());
		addSalary.setPromptText("Salary");

		final TextField addposition = new TextField();
		addposition.setMaxWidth(positionCol.getPrefWidth());
		addposition.setPromptText("position");

		final TextField addPhone = new TextField();
		addPhone.setMaxWidth(PhoneCol.getPrefWidth());
		addPhone.setPromptText("Phone");

		final TextField addage = new TextField();
		addage.setMaxWidth(ageCol.getPrefWidth());
		addage.setPromptText("age");
///////////////////////////////////////////////////////////////
		final Button addButton = new Button("Add");
		addButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");

		addButton.setOnAction((ActionEvent e) -> {
			Employee rc;
			rc = new Employee(Integer.valueOf(addemployeeId.getText()), addname.getText(),
					Integer.valueOf(addSalary.getText()), addposition.getText(), addPhone.getText(),
					Integer.valueOf(addage.getText()));
			dataList.add(rc);
			insertData(rc);
			addemployeeId.clear();
			addname.clear();
			addSalary.clear();
			addposition.clear();
			addPhone.clear();
			addage.clear();
		});

		final HBox hb = new HBox();

//////////////////////////////////////////////////////////////////
		final Button deleteButton = new Button("Delete");
		deleteButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");

		deleteButton.setOnAction((ActionEvent e) -> {
			ObservableList<Employee> selectedRows = myDataTable.getSelectionModel().getSelectedItems();
			ArrayList<Employee> rows = new ArrayList<>(selectedRows);
			rows.forEach(row -> {
				myDataTable.getItems().remove(row);
				deleteRow(row);
				myDataTable.refresh();
			});
		});

		hb.getChildren().addAll(addemployeeId, addname, addSalary, addposition, addPhone, addage, addButton,
				deleteButton);
		hb.setSpacing(3);

/////////////////////////////////////////////////////////////
		final Button refreshButton = new Button("Refresh");
		refreshButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");
		refreshButton.setOnAction((ActionEvent e) -> {
			myDataTable.refresh();
		});

//		Button ownedNoneButton = new Button("Owned None");
//		ownedNoneButton.setOnAction(c -> );
////////////////////////////////////////////////////////////
		final Button clearButton = new Button("Clear All");
		clearButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");
		clearButton.setOnAction((ActionEvent e) -> {
			showDialog(stage, NONE, myDataTable);

		});

		final HBox hb2 = new HBox();
		hb2.getChildren().addAll(clearButton, refreshButton);
		hb2.setAlignment(Pos.CENTER_RIGHT);
		hb2.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(label, myDataTable, hb, hb2);
		((Group) scene.getRoot()).getChildren().addAll(vbox);
		stage.setScene(scene);
	}

//////////////////////////////////////////////////////////////////////////

	private static void insertData(Employee rc) {
		try {
			// Print the SQL statement to be executed
			System.out.println("Insert into employee (Employee_id, Name_emp, Salary, Position, Phone, Age) values("
					+ rc.getEmployeeId() + ", '" + rc.getNameEmp() + "', " + rc.getSalary() + ", " + rc.getPosition()
					+ ", '" + rc.getPhone() + "', " + rc.getAge() + ")");

			// Establish a connection to the database
			DatabaseConnector.connectDB();

			// Execute the SQL insert statement
			ExecuteStatement("Insert into employee (Employee_id, Name_emp, Salary, Position, Phone, Age) values("
					+ rc.getEmployeeId() + ", '" + rc.getNameEmp() + "', " + rc.getSalary() + ", '" + rc.getPosition()
					+ "', '" + rc.getPhone() + "', " + rc.getAge() + ")");

			// Close the database connection
			//Main.con.close();

			// Print a message indicating successful insertion
			System.out.println("Connection closed" + data.size());

		} catch (SQLException e) {
			// Handle SQL exceptions
			e.printStackTrace();
		}
	}

////////////////////////////////////////////////////////////////////////

	private static void getData() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub

		String SQL;

		DatabaseConnector.connectDB();
		System.out.println("Connection established");

		SQL = "select Employee_id, Name_emp, salary, position, phone, age from employee order by Employee_id";
		Statement stmt = Main.con.createStatement();
		stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next())
			data.add(new Employee(Integer.parseInt(rs.getString(1)), rs.getString(2), Integer.parseInt(rs.getString(3)),
					rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(6))));

		rs.close();
		stmt.close();

	
		System.out.println("Connection closed" + data.size());

	}

	public static void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = Main.con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
			System.out.println("SQL statement is not executed!");

		}

	}
	///////////////////////////////////////////////////////////

	public static void updateName(int employeeId, String name) {

		try {
			System.out.println("update  employee set Name_emp = '" + name + "' where Employee_id = " + employeeId);
			DatabaseConnector.connectDB();
			ExecuteStatement("update  employee set Name_emp = '" + name + "' where Employee_id = " + employeeId + ";");
			// Main.con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

///////////////////////////////////////////////////////////////
	public static void updateSalary(int employeeId, double Salary) {

		try {
			System.out.println("update  employee set salary = " + Salary + " where Employee_id = " + employeeId);
			DatabaseConnector.connectDB();
			ExecuteStatement("update  employee set salary = " + Salary + " where Employee_id = " + employeeId + ";");
			// Main.con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
///////////////////////////////////////////////////////////////

	public static void updatePosition(int employeeId, String Position) {

		try {
			System.out.println("update  employee set Position = " + Position + " where Employee_id = " + employeeId);
			DatabaseConnector.connectDB();
			ExecuteStatement(
					"update  employee set Position = " + Position + " where Employee_id = " + employeeId + ";");
			// Main.con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

/////////////////////////////////////////////////////////
	public static void updatePhone(int employeeId, String Phone) {

		try {
			System.out.println("update  employee set Phone = '" + Phone + "' where Employee_id = " + employeeId);
			DatabaseConnector.connectDB();
			ExecuteStatement("update  employee set Phone = '" + Phone + "' where Employee_id = " + employeeId + ";");
			// Main.con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

///////////////////////////////////////////////////////////////////
	public static void updateAge(int employeeId, int Age) {

		try {
			System.out.println("update  employee set Age = " + Age + " where Employee_id = " + employeeId);
			DatabaseConnector.connectDB();
			ExecuteStatement("update  employee set Age = " + Age + " where Employee_id = " + employeeId + ";");
			// Main.con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

/////////////////////////////////////////////////////////////////
	private static void deleteRow(Employee row) {
		// TODO Auto-generated method stub

		try {
			System.out.println("delete from  employee where Employee_id=" + row.getEmployeeId() + ";");
			DatabaseConnector.connectDB();
			ExecuteStatement("delete from  employee where Employee_id=" + row.getEmployeeId() + ";");
			// Main.con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

/////////////////////////////////////////////////////
	private static void showDialog(Window owner, Modality modality, TableView<Employee> table) {
		// Create a Stage with specified owner and modality
		Stage stage = new Stage();
		stage.initOwner(owner);
		stage.initModality(modality);
		// Label modalityLabel = new Label(modality.toString());

		Button yesButton = new Button("Confirm");
		yesButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");
		yesButton.setOnAction(e -> {
			for (Employee row : dataList) {
				deleteRow(row);
				table.refresh();
			}
			table.getItems().removeAll(dataList);
			stage.close();

		});

		Button noButton = new Button("Cancel");
		noButton.setStyle("-fx-background-color: #66CDAA;" + "-fx-text-fill: black;");
		noButton.setOnAction(e -> stage.close());

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);

		root.getChildren().addAll(yesButton, noButton);
		Scene scene = new Scene(root, 200, 100);
		stage.setScene(scene);
		stage.setTitle("Confirm Delete?");
		stage.show();
	}

//______________________________________________________________________________________________________________

	private static void showShowroomPage(Stage primaryStage) {
		TableView<Showroom> myDataTable = new TableView<>();
		GridPane gp = new GridPane();

		Label label = new Label("Showroom Table");
		label.setFont(new Font("Times New Roman", 20));
		label.setTextFill(Color.BLACK);

		myDataTable.setEditable(true);
		myDataTable.setMaxHeight(200);
		myDataTable.setMaxWidth(505);

		TableColumn<Showroom, Integer> licenseNumberCol = new TableColumn<>("License Number");
		licenseNumberCol.setMinWidth(50);
		licenseNumberCol.setCellValueFactory(
				cellData -> new SimpleIntegerProperty(cellData.getValue().getLicenseNumber()).asObject());

		TableColumn<Showroom, String> phoneCol = new TableColumn<>("Phone");
		phoneCol.setMinWidth(100);
		phoneCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
		phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
		phoneCol.setOnEditCommit((TableColumn.CellEditEvent<Showroom, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setPhone(t.getNewValue());
		});

		TableColumn<Showroom, String> nameCol = new TableColumn<>("Name");
		nameCol.setMinWidth(60);
		nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit((TableColumn.CellEditEvent<Showroom, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
		});

		TableColumn<Showroom, String> locationCol = new TableColumn<>("Location");
		locationCol.setMinWidth(100);
		locationCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));
		locationCol.setCellFactory(TextFieldTableCell.forTableColumn());
		locationCol.setOnEditCommit((TableColumn.CellEditEvent<Showroom, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setLocation(t.getNewValue());
		});

		TableColumn<Showroom, String> ownershipCol = new TableColumn<>("Ownership");
		ownershipCol.setMinWidth(100);
		ownershipCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOwnership()));
		ownershipCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ownershipCol.setOnEditCommit((TableColumn.CellEditEvent<Showroom, String> t) -> {
			t.getTableView().getItems().get(t.getTablePosition().getRow()).setOwnership(t.getNewValue());
		});

		myDataTable.setItems(dataListsh);
		myDataTable.getColumns().addAll(licenseNumberCol, phoneCol, nameCol, locationCol, ownershipCol);
		myDataTable.refresh();

		gp.add(myDataTable, 1, 1);
		Scene scene = new Scene(gp);
		primaryStage.setTitle("Showroom Table");
		primaryStage.setWidth(550);
		primaryStage.setHeight(500);
		String backgroundColorCode = "#DCDCDC";
		scene.setFill(Color.web(backgroundColorCode)); // Set the background color
		primaryStage.setScene(scene);
		primaryStage.show();

		System.out.println("Data List Contents:");
		dataList.forEach(System.out::println);
	}

	private static void getDatash() throws SQLException, ClassNotFoundException {
		String SQL;

		DatabaseConnector.connectDB();
		System.out.println("Connection established");

		SQL = "SELECT LicenseNumber, Phone, Name, Location, Ownership FROM Showroom ORDER BY LicenseNumber";
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next())
			datash.add(new Showroom(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

		rs.close();
		stmt.close();

		System.out.println("Connection closed" + datash.size());
	}

}
