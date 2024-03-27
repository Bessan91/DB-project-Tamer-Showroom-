
package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {

	static Button chooseImageButton = new Button("License Image");
	static ImageView imageView = new ImageView();
	static Connection con;
	private static String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "";
	private static String URL = "localhost";
	private static String port = "3307";
	private static String dbName = "FinalProjectDB_TamerShowroom";
//	private static final String DBUSERNAME = "root";
//	private static final String DBPASSWORD = "";
//	private static final String URL = "localhost";
//	private static final String port = "3307";
//	private static final String DBName = "FinalProjectDB_TamerShowroom";

	@Override
	public void start(Stage primaryStage) throws SQLException, FileNotFoundException {
		DatabaseConnector dbcon = new DatabaseConnector(dbUsername, dbPassword, URL, port, dbName);
		try {
			con = dbcon.connectDB();
			System.out.println("Connection Established");

		} catch (SQLException e1) {
			System.out.println("Connection not Established");
			e1.printStackTrace();
		}
//____________________________________________________________________________________________________________________
//calling the sign in part 

		// Create a scene with the grid pane and a larger size
		Scene scene = new Scene(Hajar.Sign_In(primaryStage), 595, 332);
		// Set the scene to the stage and show the stage
		primaryStage.setTitle("Sign In");
		primaryStage.setScene(scene);
		primaryStage.show();


//________________________________________________________________________________________________________________________	

		chooseImageButton.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");
		chooseImageButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 12));
		chooseImageButton.setPrefSize(100, 32);

		imageView.setFitWidth(160);
		imageView.setFitHeight(80);

		chooseImageButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose Image File");
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));

			File selectedFile = fileChooser.showOpenDialog(primaryStage);

			if (selectedFile != null) {
//				ImageView imagelic = new ImageView(new Image(new FileInputStream(selectedFile.getPath())));
//				Image imagelic = new Image(selectedFile.getPath());
				Image imagelic;
				try {
					imagelic = new Image(new FileInputStream(selectedFile.getPath()));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

	}

	static ImageView AddImage() throws FileNotFoundException {
		Image img = new Image(new FileInputStream("Logocar.png"));

		ImageView image = new ImageView(img);
		image.setFitHeight(20);
		image.setFitWidth(60);

		return image;
	}

//__________________________________________________________________________________________________________________________________

	public static void main(String[] args) {
		launch(args);
	}

	// Method to check if the username and password are valid
	private boolean isValidAccount(String username, String password) {
		return username.equals("admin") && password.equals("password");
	}



//__________________________________________________________________________________________________________________________________

}














































































//package application;
//
//import java.awt.event.ActionEvent;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//
//import javax.swing.JOptionPane;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.stage.Stage;
//import javafx.util.Pair;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//
//public class Main extends Application {
//	private static String dbURL;
//	private static String dbUsername = "root";
//	private static String dbPassword = "";
//	private static String URL = "localhost";
//	private static String port = "3307";
//	private static String dbName = "FinalProjectDB_TamerShowroom";
//	private static Connection con;
//	private static ObservableList<Imported> dataList;
//	private static ObservableList<Car> CardataList;
//	private static ObservableList<Customer> CustomerdataList;
//	private static ObservableList<Company> CompanydataList;
//	private static ObservableList<Person> ParsondataList;
//	private static ObservableList<Buy> BuydataList;
//	private static ObservableList<maintenance_maintain> Maintenance_maintaindatalist;
//	private static ObservableList<Buy> BuyOverviewdataList;
//
//	private static void connectDB() throws ClassNotFoundException, SQLException {
//		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
//		Properties p = new Properties();
//		p.setProperty("user", dbUsername);
//		p.setProperty("password", dbPassword);
//		p.setProperty("useSSL", "false");
//		p.setProperty("autoReconnect", "true");
//		Class.forName("com.mysql.jdbc.Driver");
//		con = DriverManager.getConnection(dbURL, p);
//	}
//
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root, 400, 400);
////			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//			showDashboard(primaryStage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	public static void showDashboard(Stage primaryStage)
//			throws ClassNotFoundException, SQLException, FileNotFoundException {
//
//		primaryStage.close();
//		Stage stageDashboard = new Stage();
//		BorderPane borderPane = new BorderPane();
//
//		Label title = new Label("Dashboard Tammer Showroom");
//		title.setFont(Font.font("", FontWeight.BOLD, 22));
//
//		HBox hboxDashboard = new HBox();
//		hboxDashboard.setPadding(new Insets(30, 30, 30, 200)); // Set padding as needed
//		hboxDashboard.getChildren().add(title);
//		hboxDashboard.setStyle("-fx-background-color: #9fc0c7;");
////		===============================================
//		dashBordContent_left(borderPane, stageDashboard);
////		=================================================
//		borderPane.setTop(hboxDashboard);
////		===========================sql =============================
//
//		connectDB();
//		Statement stmt = con.createStatement();
//
//		// Query 1
//		ResultSet rs = stmt.executeQuery("select * from Customer");
//		int count = 0;
//		while (rs.next()) {
//			count++;
//		}
//		rs.close();
//
//		// Query 2
//		ResultSet sellCurrentDate = stmt.executeQuery("SELECT * FROM Buy WHERE Purchase_History = CURDATE()");
//		int countsell = 0;
//		while (sellCurrentDate.next()) {
//			countsell++;
//		}
//		sellCurrentDate.close();
//
//		// Query 3
//		ResultSet rs_numEmp = stmt.executeQuery("select * from Employee");
//		int count_numEmp = 0;
//		while (rs_numEmp.next()) {
//			count_numEmp++;
//		}
//		rs_numEmp.close();
//
//		// Query 4
//		ResultSet rs_numCar = stmt.executeQuery("select * from Car");
//		int count_numCar = 0;
//		while (rs_numCar.next()) {
//			count_numCar++;
//		}
//		rs_numCar.close();
//
//		stmt.close();
//		con.close();
//
////		===========================================
//		HBox hboxButtons = new HBox(10);
//		Button button_Count_Coustomer = new Button();
//		button_Count_Coustomer.setPrefWidth(100);
//		button_Count_Coustomer.setPrefHeight(80);
//		button_Count_Coustomer.setStyle("-fx-background-radius: 10px;");
//		hboxButtons.setMargin(button_Count_Coustomer, new Insets(10, 10, 30, 30)); // top, bottom, right and left
//		button_Count_Coustomer.setText("Customers" + "\n     " + String.valueOf(count));
//		button_Count_Coustomer.setAlignment(Pos.CENTER);
//		button_Count_Coustomer.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//
////		--------------------- button Import ==========
//
////		----------------------button car --------------------------
//		Button button_Count_car = new Button();
//		button_Count_car.setPrefWidth(100);
//		button_Count_car.setPrefHeight(80);
//		button_Count_car.setStyle("-fx-background-radius: 10px;");
//		hboxButtons.setMargin(button_Count_car, new Insets(10, 10, 30, 30));
//		button_Count_car.setText("Num Cars" + "\n         " + String.valueOf(count_numCar));
//		button_Count_car.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		button_Count_car.setAlignment(Pos.CENTER);
////		=============================================
////		----------------------butoon number of employee 
//		Button button_Count_employee = new Button();
//		button_Count_employee.setPrefWidth(100);
//		button_Count_employee.setPrefHeight(80);
//		button_Count_employee.setStyle("-fx-background-radius: 10px;");
//		hboxButtons.setMargin(button_Count_employee, new Insets(10, 10, 30, 30));
//		hboxButtons.setAlignment(Pos.CENTER);
//		button_Count_employee.setText("Employee" + "\n     " + String.valueOf(count_numEmp));
//		button_Count_employee.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		button_Count_employee.setAlignment(Pos.CENTER);
////		==================================Count of Num of mintaine Car 
//
//		Button button_Count_MaintenCar = new Button();
//		button_Count_MaintenCar.setPrefWidth(100);
//		button_Count_MaintenCar.setPrefHeight(80);
//		button_Count_MaintenCar.setStyle("-fx-background-radius: 10px;");
//		hboxButtons.setMargin(button_Count_MaintenCar, new Insets(10, 10, 30, 30));
//		hboxButtons.setAlignment(Pos.CENTER);
//		button_Count_MaintenCar.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		button_Count_MaintenCar.setText(" New Buy" + "\n     " + String.valueOf(countsell));
//
//		button_Count_MaintenCar.setAlignment(Pos.CENTER);
//		HBox Emp_Cus = new HBox(30);
//		ImageView Emp = new ImageView(new Image(new FileInputStream("Employee.png")));
//		Button EmoloyeIcon = new Button("Employee View", Emp);
//		EmoloyeIcon.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 18));
//		EmoloyeIcon.setStyle("-fx-background-radius: 10px;");
//
////		EmoloyeIcon.setPrefHeight(20);
//		ImageView Cus = new ImageView(new Image(new FileInputStream("Customer1.png")));
//		Button CustomerIcon = new Button("Customer View ",Cus);
//		CustomerIcon.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 18));
//		CustomerIcon.setStyle("-fx-background-radius: 10px;");
////f
//		CustomerIcon.setOnAction(CustomerIfo -> {
//			try {
//				customerInformationSection(stageDashboard);
////				v
//			} catch (ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		});
//
//		Emp_Cus.getChildren().addAll(EmoloyeIcon, CustomerIcon);
//		Emp_Cus.setAlignment(Pos.CENTER);
//
////		-=========================DataTable for laset imported car  //name // where  price item of this car   
//
//		Node detailsNode = detailsOfImport();
////		===============================================
//		hboxButtons.getChildren().addAll(button_Count_Coustomer, button_Count_employee, button_Count_car,
//				button_Count_MaintenCar);
////		hboxButtons.setStyle("-fx-background-color: white;");
////		borderPane.setCenter(hboxButtons);
//		HBox overviewTable = new HBox();
////	    overviewTable.setStyle("-fx-background-color: red;");
//		VBox Import_carTable = new VBox();
//		Label import_table = new Label("New Cars imported ");
//		import_table.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 18));
//		import_table.setPadding(new Insets(20, 0, 0, 90));
//
//		Import_carTable.setMaxWidth(300);
//		Import_carTable.setMaxHeight(300);
//		Import_carTable.getChildren().addAll(import_table, detailsNode);
//		VBox.setMargin(detailsNode, new Insets(10, 10, 10, 10));
//
//		VBox Import2_carTable = new VBox();
//		Label import2_table = new Label(" Older Manitens Car ");
//		import2_table.setPadding(new Insets(30, 0, 0, 90));
//		Import2_carTable.getChildren().addAll(import2_table, detailsOfMaintenance());
//		import2_table.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 18));
////		,
//
//		Import2_carTable.setMaxWidth(300);
//		Import2_carTable.setMaxHeight(300);
//
//		VBox Import3_carTable = new VBox();
//		Label import3_table = new Label("New Sell");
////		import3_table.setTextFill(Color.WHITESMOKE);
//		import3_table.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 18));
//
////		import3_table.setPadding(new Insets(30, 0, 0, 120));
//		import3_table.setPadding(new Insets(30, 0, 0, 90));
//		Import3_carTable.getChildren().addAll(import3_table, DetailsBuyBuySummaryInSellSection());
//
//		Import3_carTable.setMaxWidth(300);
//		Import3_carTable.setMaxHeight(300);
//
//		overviewTable.getChildren().addAll(Import_carTable, Import2_carTable, Import3_carTable);
//		overviewTable.setSpacing(20);
//
//		VBox centerContent = new VBox();
//		centerContent.setStyle("-fx-background-color: #9fc0c7;");
//		centerContent.getChildren().addAll(hboxButtons, Emp_Cus, overviewTable);
//
//		borderPane.setCenter(centerContent);
//		Scene scene = new Scene(borderPane, 1200, 600);
//		stageDashboard.setTitle("JavaFX Dashboard");
//		stageDashboard.setScene(scene);
//		stageDashboard.show();
//
//	}
//
//	private static void customerInformationSection(Stage stageDashboard) throws ClassNotFoundException, SQLException {
//		stageDashboard.close();
//
//		// Create your layout components
//		StackPane stack = new StackPane();
//		BorderPane pane = new BorderPane();
//		ImageView view = null;
//
//		try {
//			view = new ImageView(new Image(new FileInputStream("color.jpeg")));
//			
//			Node detailsCustomerTable = DetailsCustomerSection();
//
//			// Set properties for the image view
//			if (view != null) {
//				view.setFitWidth(700); // Set the desired width
//				view.setFitHeight(600); // Set the desired height
//			}
//			ImageView viewUpdate = new ImageView(new Image(new FileInputStream("Update details.png")));
//			ImageView viewClose = new ImageView(new Image(new FileInputStream("Exit application.png")));
//			ImageView viewDelete = new ImageView(new Image(new FileInputStream("Close.png")));
//
//			Button insertCustomer = new Button("Add Customer");
//			Button updateButton = new Button("Update",viewUpdate);
//		
//			Button CloseButton =new Button ("Close",viewClose);
//
//			// Create an HBox for the buttons
//			HBox buttonBox = new HBox(10, updateButton, insertCustomer,CloseButton);
//			buttonBox.setAlignment(Pos.CENTER);
//
//			// Create a VBox for the overview
//			VBox OverViweCustomer = new VBox(10);
//			Label OverviewCustomer = new Label("OverView Customer ");
//			OverviewCustomer.setFont(Font.font("", FontWeight.BOLD, 22));
//			
//			OverviewCustomer.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//			OverviewCustomer.setStyle("-fx-font-size: 18px; -fx-text-fill: black; -fx-font-family: 'Arial';");
//			OverViweCustomer.getChildren().addAll(OverviewCustomer, detailsCustomerTable, buttonBox);
//			OverViweCustomer.setAlignment(Pos.CENTER);
//
//			pane.setCenter(OverViweCustomer);
//
//			// Create a StackPane to layer the image view behind the BorderPane
//			stack.getChildren().addAll(view, pane);
//
//			// Create a scene
//			Scene scene = new Scene(stack, 700, 550);
//
//			// Create and show the stage
//			Stage stageCustomer = new Stage();
//			stageCustomer.setX(377);
//			stageCustomer.setY(127);
//			stageCustomer.setScene(scene);
//			stageCustomer.show();
//
//			updateButton.setOnAction(update -> {
//				try {
//					UpdateCustomer(stageCustomer);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			});
//			// On Action button -->
//			insertCustomer.setOnAction(insert -> {
//				// Handle insert action here
//				try {
//					AddCustomer(stageCustomer);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//			CloseButton.setOnAction(close ->{
//				stageCustomer.close();
//				stageDashboard.show();
//			});
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void UpdateCustomer(Stage stageCustomer) throws FileNotFoundException {
//		stageCustomer.close();
//		BorderPane pane = new BorderPane();
//		Label tital = new Label("Update Customer ");
//		tital.setFont(Font.font("", FontWeight.BOLD, 22));
//		tital.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		BorderPane.setAlignment(tital, Pos.TOP_CENTER);
//		Label ID_Customer = new Label("ID Of Customer to Update:");
//		ID_Customer.setFont(Font.font("", FontWeight.BOLD, 15));
//		ID_Customer.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		TextField ID = new TextField();
//		ID.setFont(Font.font("", FontWeight.BLACK, 12));
//		
//		HBox hID = new HBox();
//		hID.setSpacing(10);
//		hID.setAlignment(Pos.TOP_CENTER);
//		hID.getChildren().addAll(ID_Customer, ID);
//
//		Label NewUpdateID = new Label("New ID Of Customer: ");
//		NewUpdateID.setFont(Font.font("", FontWeight.BOLD, 15));
//		NewUpdateID.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		TextField tId = new TextField();
//		tId.setFont(Font.font("", FontWeight.BLACK, 12));
//
//		HBox hNewId = new HBox();
//		hNewId.setSpacing(10);
//		hNewId.setAlignment(Pos.TOP_CENTER);
//		hNewId.getChildren().addAll(NewUpdateID, tId);
//
//		Label NewUpdateName = new Label("New Name Of Customer:");
//		NewUpdateName.setFont(Font.font("", FontWeight.BOLD, 15));
//		NewUpdateName
//				.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		TextField tName = new TextField();
//		tName.setFont(Font.font("", FontWeight.BLACK, 12));
//
//		HBox hNewName = new HBox();
//		hNewName.setSpacing(10);
//		hNewName.setAlignment(Pos.TOP_CENTER);
//		hNewName.getChildren().addAll(NewUpdateName, tName);
//
//		Label NewUpdatePhone = new Label("New Phone Of Customer:");
//		NewUpdatePhone.setFont(Font.font("", FontWeight.BOLD, 15));
//		NewUpdatePhone
//				.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		TextField tphone = new TextField();
//		tphone.setFont(Font.font("", FontWeight.BLACK, 12));
//
//		HBox hNewPhone = new HBox();
//		hNewPhone.setSpacing(10);
//		hNewPhone.setAlignment(Pos.TOP_CENTER);
//		hNewPhone.getChildren().addAll(NewUpdatePhone, tphone);
//
//		ImageView viewSave = new ImageView(new Image(new FileInputStream("save.png")));
//		ImageView viewReset = new ImageView(new Image(new FileInputStream("Update details.png")));
//		ImageView viewClose = new ImageView(new Image(new FileInputStream("Exit application.png")));
//		ImageView viewsearch = new ImageView(new Image(new FileInputStream("search.png")));
//		
//		Button Update = new Button("Update", viewSave);
//		Update.setFont(Font.font("", FontWeight.BOLD, 13));
//
//		Button Reset = new Button("Reset", viewReset);
//		Reset.setFont(Font.font("", FontWeight.BOLD, 13));
//		Button Delete = new Button("Delete");
//		Delete.setFont(Font.font("", FontWeight.BOLD, 13));
//
//		Button close = new Button("close", viewClose);
//		close.setFont(Font.font("", FontWeight.BOLD, 13));
//
//		Button Search = new Button("Search", viewsearch);
//		Search.setFont(Font.font("", FontWeight.BOLD, 13));
//		Button deleteButton = new Button("Delete");
//		deleteButton.setFont(Font.font("", FontWeight.BOLD, 13));
////		b
//
//		HBox h = new HBox();
//		h.setSpacing(10);
//		h.setAlignment(Pos.CENTER);
//		h.getChildren().addAll(Update, Search, Reset,Delete, close);
//		VBox v = new VBox();
//		v.setSpacing(3);
//		v.setAlignment(Pos.CENTER);
//		v.getChildren().addAll(tital, new Label(), hID, hNewId, hNewName, hNewPhone, new Label(), h);
//		BorderPane.setAlignment(v, Pos.CENTER);
//		pane.setCenter(v);
//
//		/**************************************************************************************************/
//		StackPane stack33 = new StackPane();
//		ImageView view = new ImageView(new Image(new FileInputStream("update.png")));
//		view.setFitHeight(550);
//		view.setFitWidth(700);
//		stack33.getChildren().addAll(view, pane);
//		Stage stage2 = new Stage();
//		Scene scene = new Scene(stack33, 550, 400);
//		stage2.setScene(scene);
//		stage2.show();
//		Reset.setOnAction((reset) -> {
//			ID.setText(null);
//			tId.setText(null);
//			tName.setText(null);
//			tphone.setText(null);
//
//		});
//		Delete.setOnAction(delete ->{
//			try {
//				connectDB();
//				Statement stmt = con.createStatement();
//				String IDCustomer = ID.getText().toString();
//				stmt.executeUpdate("DELETE FROM Customer WHERE Customer_id = '" + IDCustomer + "'");
//				 
//				JOptionPane.showMessageDialog(null, "Customer has been Deleted");
//				stmt = con.createStatement();
//				ResultSet rsCustomer = stmt.executeQuery("SELECT * FROM Customer");
//
//				ObservableList<Customer> data = FXCollections.observableArrayList();
//
//				while (rsCustomer.next()) {
//					data.add(
//							new Customer(rsCustomer.getString("Customer_id"), rsCustomer.getString("FullName"), rsCustomer.getString("phone")));
//				}
//
//				rsCustomer.close();
//				CustomerdataList.setAll(data);
//				
//				
//			} catch (ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			
//		});
//		
//		close.setOnAction((closesection) -> {
//			ID.setText(null);
//			tId.setText(null);
//			stage2.close();
//			stageCustomer.show();
//		});
//		Search.setOnAction(search -> {
//			try {
//				connectDB();
//				Statement stmt = con.createStatement();
//				String IDCustomer = ID.getText().toString();
//				ResultSet rs = stmt.executeQuery("select * from Customer where Customer_id='" + IDCustomer + "'");
//				if(rs.next()) {
//					tId.setText(rs.getString(1));
//					tName.setText(rs.getString(2));
//					tphone.setText(rs.getString(3));
//
//				}
//				else {
//					JOptionPane.showMessageDialog(null, "Customer is not Exist");
//
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			} catch (ClassNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		});
//
//	Update.setOnAction((UpdateCustomer) -> {
//		 try {
//			connectDB();
//			 Statement st2 = con.createStatement();
//			   ResultSet rs = st2.executeQuery("SELECT * FROM Company");
//		        if (rs.next()) {
//		        	st2.executeUpdate(
//		 		            "UPDATE Company SET Customer_id='" + tId.getText() + "', comp_name='" + tName.getText()
//		 		            + "', phone='" + tphone.getText() + "' WHERE Customer_id='" + ID.getText() + "'");
//		        	st2.executeUpdate(
//		 		            "UPDATE Customer SET Customer_id='" + tId.getText() + "', FullName='" + tName.getText()
//		 		            + "', phone='" + tphone.getText() + "' WHERE Customer_id='" + ID.getText() + "'");
//		        	 JOptionPane.showMessageDialog(null, "Successfully "+ tName.getText()+" updating :)");
//		        	
//		        }
//		        else
//		        {
//		        	st2.executeUpdate(
//		 		            "UPDATE Person SET Customer_id='" + tId.getText() + "', cus_name='" + tName.getText()
//		 		            + "', phone='" + tphone.getText() + "' WHERE Customer_id='" + ID.getText() + "'");
//		        	st2.executeUpdate(
//		 		            "UPDATE Customer SET Customer_id='" + tId.getText() + "', FullName='" + tName.getText()
//		 		            + "', phone='" + tphone.getText() + "' WHERE Customer_id='" + ID.getText() + "'");
//		        	 JOptionPane.showMessageDialog(null, "Successfully "+ tName.getText()+" updating :)");
//		        }
//		        
//		        st2 = con.createStatement();
//				ResultSet rsCustomer = st2.executeQuery("SELECT * FROM Customer");
//
//				ObservableList<Customer> data = FXCollections.observableArrayList();
//
//				while (rsCustomer.next()) {
//					data.add(
//							new Customer(rsCustomer.getString("Customer_id"), rsCustomer.getString("FullName"), rsCustomer.getString("phone")));
//				}
//
//				rsCustomer.close();
//				CustomerdataList.setAll(data);
//				rs.close();
//				st2.close();
//				
//
//		        
//
//			
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
//	});
//	}
//
//
//	private static void AddCustomer(Stage stage) throws FileNotFoundException {
//		stage.close();
//		Stage CustomerStage = new Stage();
//		// Create layout components
//		StackPane stackPane = new StackPane();
//		ImageView view = new ImageView(new Image(new FileInputStream("color.jpeg")));
////		h
////		j
////		d
//		VBox vbox = new VBox(20);
//		
//		vbox.setAlignment(Pos.CENTER); // Set alignment to center
//		Label AddInformation = new Label("Fill All Information For New Customer");
//		AddInformation.setFont(Font.font("", FontWeight.BOLD, 22));
//		AddInformation.setTextFill(Color.WHITE);
//		
//		// Create HBox for Customer ID
//		HBox hboxCustomerId = new HBox(10);
//		hboxCustomerId.setAlignment(Pos.CENTER); // Set alignment to center
//		Label customerIdLabel = new Label("Customer ID:");
//		customerIdLabel.setFont(Font.font("", FontWeight.BOLD, 15));
//		customerIdLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		TextField customerIdField = new TextField();
//		customerIdField.setFont(Font.font("", FontWeight.BLACK, 12));
//		hboxCustomerId.getChildren().addAll(customerIdLabel, customerIdField);
//
//		// Create HBox for Full Name
//		HBox hboxFullName = new HBox(10);
//		hboxFullName.setAlignment(Pos.CENTER); // Set alignment to center
//		Label fullNameLabel = new Label("Full Name:");
//		TextField fullNameField = new TextField();
//		fullNameLabel.setFont(Font.font("", FontWeight.BOLD, 15));
//		fullNameLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		hboxFullName.getChildren().addAll(fullNameLabel, fullNameField);
//
//		// Create HBox for Phone
//		HBox hboxPhone = new HBox(10);
//		hboxPhone.setAlignment(Pos.CENTER); // Set alignment to center
//		Label phoneLabel = new Label("Customer Phone :");
//		phoneLabel.setFont(Font.font("", FontWeight.BOLD, 15));
//		TextField phoneField = new TextField();
//		phoneLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		phoneField.setFont(Font.font("", FontWeight.BLACK, 12));
//		hboxPhone.getChildren().addAll(phoneLabel, phoneField);
//
//		HBox hboxCustomerType = new HBox(10);
//		hboxCustomerType.setAlignment(Pos.CENTER); // Set alignment to center
//		ToggleGroup customerTypeGroup = new ToggleGroup();
//		RadioButton companyRadioButton = new RadioButton("Company");
//		companyRadioButton.setFont(Font.font("", FontWeight.BLACK, 12));
//		RadioButton personRadioButton = new RadioButton("Person");
//		personRadioButton.setFont(Font.font("", FontWeight.BLACK, 12));
//		Label customerTypeLabel = new Label("Customer Type:");
//		customerTypeLabel.setFont(Font.font("", FontWeight.BOLD, 15));
//		customerTypeLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5), Insets.EMPTY)));
//		
//		companyRadioButton.setToggleGroup(customerTypeGroup);
//		personRadioButton.setToggleGroup(customerTypeGroup);
//		personRadioButton.setSelected(true);
//		hboxCustomerType.getChildren().addAll(customerTypeLabel, companyRadioButton, personRadioButton);
//		// Create submit button
//		HBox Buttons = new HBox(10);
//		ImageView viewSave = new ImageView(new Image(new FileInputStream("save.png")));
//		ImageView viewReset = new ImageView(new Image(new FileInputStream("Update details.png")));
//		ImageView viewClose = new ImageView(new Image(new FileInputStream("Exit application.png")));
//		ImageView viewsearch = new ImageView(new Image(new FileInputStream("search.png")));
//		Button submitButton = new Button("Submit",viewSave);
//		submitButton.setOnAction(add -> {
//			try {
//				connectDB();
//				Statement stmt = con.createStatement();
//
////	    		stmt.executeUpdate("INSERT INTO Customer (Customer_id, FullName, phone) VALUES ('11121314', 'Link Company', '05934532')");
//				stmt.executeUpdate(
//						"INSERT INTO Customer (Customer_id, FullName, phone) VALUES ('" + customerIdField.getText()
//								+ "','" + fullNameField.getText() + "','" + phoneField.getText() + "')");
//
//				// Construct the INSERT statement for Company
////	    	        String insertCompanyQuery = ";
////
//				if (companyRadioButton.isSelected()) {
//					stmt.executeUpdate(
//							"INSERT INTO Company (Customer_id, comp_name, phone) VALUES ('" + customerIdField.getText()
//									+ "','" + fullNameField.getText() + "','" + phoneField.getText() + "')");
//					JOptionPane.showMessageDialog(null,
//							"New Company Added: " + fullNameField.getText() + " Customer added successfully.");
//				} else if (personRadioButton.isSelected()) {
//					stmt.executeUpdate(
//							"INSERT INTO Person (Customer_id, cus_name, phone) VALUES ('" + customerIdField.getText()
//									+ "','" + fullNameField.getText() + "','" + phoneField.getText() + "')");
//					JOptionPane.showMessageDialog(null,
//							"New Person Added: " + fullNameField.getText() + " Customer added successfully.");
//
//				} else
//					JOptionPane.showMessageDialog(null, "Check All Data Is Inserted :(");
//				stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
//
//				ObservableList<Customer> data = FXCollections.observableArrayList();
//
//				while (rs.next()) {
//					data.add(
//							new Customer(rs.getString("Customer_id"), rs.getString("FullName"), rs.getString("phone")));
//				}
//
//				rs.close();
//				stmt.close();
//				CustomerdataList.setAll(data);
//
//			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
//			}
//			fullNameField.setText(null);
//			customerIdField.setText(null);
//			phoneField.setText(null);
//
//		});
//		
//		stage.close();
//		Button ResetButton = new Button("Reset",viewReset);
//		ResetButton.setOnAction(reset->{
//			fullNameField.setText(null);
//			customerIdField.setText(null);
//			phoneField.setText(null);
//		
//			
//		});
//		Button BackButton = new Button("Back",viewClose);
//		BackButton.setOnAction(Back -> {
//			stage.show();
//			CustomerStage.close();
//		});
//		Buttons.getChildren().addAll(submitButton, ResetButton, BackButton);
//		Buttons.setAlignment(Pos.CENTER);
//		vbox.getChildren().addAll(AddInformation, hboxCustomerId, hboxFullName, hboxPhone, hboxCustomerType, Buttons);
//
//		// Add VBox to StackPane
//		stackPane.getChildren().addAll(view,vbox);
//
//		// Create scene
//		Scene scene = new Scene(stackPane, 700, 550);
//
//		CustomerStage.setTitle("Add Customer");
//		CustomerStage.setScene(scene);
//		CustomerStage.show();
//	}
//
//	private static Node createDataTableCustomerSummary(ObservableList<Customer> CustomerdataList) {
//		TableView<Customer> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(500);
//		myDataTable.setMaxHeight(150);
//
//		TableColumn<Customer, String> CustomerIdCol = new TableColumn<>("Customer ID");
//		CustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
//		CustomerIdCol.setPrefWidth(100);
//
//		TableColumn<Customer, String> CustomerNameCol = new TableColumn<>("Full Name ");
//		CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("FullName"));
//		CustomerNameCol.setPrefWidth(100);
//
//		TableColumn<Customer, String> CustomerPhoneCol = new TableColumn<>("Phone");
//		CustomerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
//		CustomerPhoneCol.setPrefWidth(100);
//
//		myDataTable.getColumns().addAll(CustomerIdCol, CustomerNameCol, CustomerPhoneCol);
//		myDataTable.setItems(CustomerdataList);
//
//		return myDataTable;
//	}
//
////==================================Customer Data===========================
//
////    to use the two table i make pair  in dashbord 
//	@SuppressWarnings("unused")
//	private static Node createDataTable(ObservableList<Pair<String, Pair<String, String>>> dataList) {
//		TableView<Pair<String, Pair<String, String>>> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxHeight(150);
//
//		TableColumn<Pair<String, Pair<String, String>>, String> carModelCol = new TableColumn<>("Car Model");
//		carModelCol.setMinWidth(100);
//		carModelCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
//
//		TableColumn<Pair<String, Pair<String, String>>, String> importDateCol = new TableColumn<>("Import Date");
//		importDateCol.setMinWidth(100);
//		importDateCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().getKey()));
//
//		TableColumn<Pair<String, Pair<String, String>>, String> importOriginCol = new TableColumn<>("Origin");
//		importOriginCol.setMinWidth(100);
//		importOriginCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().getValue()));
//
//		myDataTable.getColumns().addAll(carModelCol, importDateCol, importOriginCol);
//		myDataTable.setItems(dataList);
//
//		return myDataTable;
//	}
//
//	@SuppressWarnings("unused")
//	private static Node detailsOfImport() throws FileNotFoundException, SQLException, ClassNotFoundException {
//
//		connectDB();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT Car.Car_model, Import_car.import_date, Import_car.origin, "
//				+ "Imported.Car_id, Imported.import_id, Imported.import_account " + "FROM Imported "
//				+ "JOIN Car ON Imported.Car_id = Car.Car_id "
//				+ "JOIN Import_car ON Imported.import_id = Import_car.import_id "
//				+ "ORDER BY Import_car.import_date DESC");
//
//		ObservableList<Pair<String, Pair<String, String>>> data = FXCollections.observableArrayList();
//		while (rs.next()) {
//			String modelCar = rs.getString("Car_model");
//			String importDate = rs.getString("import_date");
//			String importOrigin = rs.getString("origin");
//			data.add(new Pair<>(modelCar, new Pair<>(importDate, importOrigin)));
//		}
//		rs.close();
//		stmt.close();
//		ObservableList<Pair<String, Pair<String, String>>> dataList = FXCollections.observableArrayList(data);
//
//		return createDataTable(dataList);
//	}
//
//	@SuppressWarnings("unused")
//	private static Node detailsOfMaintenance() throws FileNotFoundException, SQLException, ClassNotFoundException {
//		connectDB();
//		Statement stmt = con.createStatement();
////
//		ResultSet rs = stmt.executeQuery("SELECT  * FROM maintenance_maintain ORDER BY Mainten_date ASC");
//
//		ObservableList<maintenance_maintain> data = FXCollections.observableArrayList();
//
//		while (rs.next()) {
//			String Mainten_id = rs.getString(1);
//			System.out.println("Mainten_id: " + Mainten_id);
////			Mainten_id = rs.getString("Mainten_id");
//			Double cost = Double.parseDouble(rs.getString("cost"));
//			Date Mainten_date = rs.getDate("Mainten_date");
//			String Car_id = rs.getString("Car_id");
//			System.out.println("Mainten_id: " + Mainten_id + ", cost: " + cost + ", Mainten_date: " + Mainten_date
//					+ ", Car_id: " + Car_id);
//
//			data.add(new maintenance_maintain(Mainten_id, cost, Mainten_date, Car_id));
//		}
//		rs.close();
//		stmt.close();
//		Maintenance_maintaindatalist = FXCollections.observableArrayList(data);
//		//
////				return createDataMaintenanseTable(Maintenance_maintaindatalist);
//		return createDataMaintenanseTable(Maintenance_maintaindatalist);
//	}
//
//	private static Node createDataMaintenanseTable(ObservableList<maintenance_maintain> dataList2) {
//		TableView<maintenance_maintain> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxHeight(150);
//
//		TableColumn<maintenance_maintain, String> Mainten_idCol = new TableColumn<>("Mainten id");
//		Mainten_idCol.setCellValueFactory(new PropertyValueFactory<>("Mainten_id"));
//
//		TableColumn<maintenance_maintain, Double> costCol = new TableColumn<>("Cost");
//		costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
//
//		TableColumn<maintenance_maintain, Date> Mainten_dateCol = new TableColumn<>("Mainten date");
//		Mainten_dateCol.setCellValueFactory(new PropertyValueFactory<>("Mainten_date"));
//
////carIdCol.setMaxWidth(150);
//		myDataTable.getColumns().addAll(Mainten_idCol, costCol, Mainten_dateCol);
//		myDataTable.setItems(dataList2);
////	d
//
//		return myDataTable;
//	}
//
//	private static Node DetailsBuyBuySummaryInSellSection() throws ClassNotFoundException, SQLException {
//		connectDB();
//		Statement stmt = con.createStatement();
//
//		ResultSet rs = stmt.executeQuery("SELECT * FROM Buy");
//		ObservableList<Buy> data = FXCollections.observableArrayList();
//		while (rs.next()) {
//			data.add(new Buy((rs.getInt("Sell_ID")), rs.getString("Customer_id"), rs.getString("Car_id"),
//					rs.getDate("Purchase_History"), Double.parseDouble(rs.getString("countBuy")),
//					Integer.parseInt(rs.getString("countCar"))));
//		}
//		rs.close();
//		stmt.close();
//		BuyOverviewdataList = FXCollections.observableArrayList(data);
////j
//		return createDataTableBuySummary_inSellSection(BuyOverviewdataList);
//	}
//
//	private static Node createDataTableBuySummary_inSellSection(ObservableList<Buy> dataList) {
//		TableView<Buy> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(500);
//		myDataTable.setPrefHeight(150);
//
//		TableColumn<Buy, String> sellIdCol = new TableColumn<>("Sell ID");
//		sellIdCol.setCellValueFactory(new PropertyValueFactory<>("Sell_ID"));
//		sellIdCol.setPrefWidth(100);
//
//		TableColumn<Buy, String> CustomerNameCol = new TableColumn<>("Customer ID ");
//		CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
//		CustomerNameCol.setPrefWidth(100);
//
//		TableColumn<Buy, String> CarIdCol = new TableColumn<>("Car ID ");
//		CarIdCol.setCellValueFactory(new PropertyValueFactory<>("Car_id"));
//		CarIdCol.setPrefWidth(100);
////
////	TableColumn<Buy, String> CarNumCol = new TableColumn<>("Num Car  ");
////	CarNumCol.setCellValueFactory(new PropertyValueFactory<>("countCar"));
////	CarNumCol.setPrefWidth(10);
//
//		TableColumn<Buy, String> CarTotalPriceCol = new TableColumn<>("Total Price ");
//		CarTotalPriceCol.setCellValueFactory(new PropertyValueFactory<>("countBuy"));
//		CarTotalPriceCol.setPrefWidth(100);
//
//		TableColumn<Buy, Date> carDateCol = new TableColumn<>("Purchase");
//		carDateCol.setCellValueFactory(new PropertyValueFactory<>("Purchase_History"));
//		carDateCol.setPrefWidth(100);
//
//		myDataTable.getColumns().addAll(sellIdCol, CustomerNameCol, CarIdCol, CarTotalPriceCol, carDateCol);
//		myDataTable.setItems(dataList);
//
//		return myDataTable;
//	}
//
////	================================================
//
//	private static void dashBordContent_left(BorderPane pp, Stage s) {
//		VBox vbox_Dashbord = new VBox();
//		vbox_Dashbord.setAlignment(Pos.BASELINE_LEFT);
//		vbox_Dashbord.setSpacing(10);
//		vbox_Dashbord.setPadding(new Insets(0, 0, 0, 1));
//		vbox_Dashbord.setMinHeight(650);
//		vbox_Dashbord.setMaxHeight(1200);
////vbox_Dashbord.setMargin(vbox_Dashbord, new Insets(-1000,0,0,0));
//		// Set the desired background color
//		Button button_Overview = new Button("Overview");
//		button_Overview.setPrefWidth(150);
//		button_Overview.setPrefHeight(20);
//		button_Overview.setLineSpacing(30);
////		button_Overview.setStyle("-fx-background-color: #d9bf16; -fx-text-fill: black;"); // Set the background color
//		// and text // color
//		button_Overview.setFont(Font.font("", FontWeight.BOLD, 22));
//		button_Overview.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;");
//
//		Button button_User = new Button("Ali");
//		button_User.setPrefWidth(150);
//		button_User.setPrefHeight(20);
//		button_User.setLineSpacing(30);
////		button_Overview.setStyle("-fx-background-color: #d9bf16; -fx-text-fill: black;"); // Set the background color
//		// and text // color
//		button_User.setFont(Font.font("", FontWeight.BOLD, 22));
//		button_User.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;");
////	====================================///////////////////////
//		Button button_Newsell = new Button("New sell");
//		button_Newsell.setPrefWidth(150);
//		button_Newsell.setPrefHeight(20);
//		button_Newsell.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																								// color and
//																								// text // color
//		button_Newsell.setFont(Font.font("", FontWeight.BOLD, 22));
////	vbox_Dashbord.getChildren().addAll(button_Overview, button_Newsell);
////	vbox_Dashbord.setStyle("-fx-background-color: #d9bf16; -fx-text-fill: black;");
//
////	==================================Orders====================
//		Button button_orders = new Button("Orders");
//		button_orders.setPrefWidth(150);
//		button_orders.setPrefHeight(20);
//		button_orders.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																								// color and
//		// text // color
//		button_orders.setFont(Font.font("", FontWeight.BOLD, 22));
////	vbox_Dashbord.getChildren().addAll(button_Overview, button_Newsell,button_orders);
////	vbox_Dashbord.setStyle("-fx-background-color: #d9bf16; -fx-text-fill: black;");
//
////	=====================================End orders =================
////	=====================================Start available in store  (Storge )========
//		Button button_Storge = new Button("Storge");
//		button_Storge.setPrefWidth(150);
//		button_Storge.setPrefHeight(20);
//		button_Storge.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																								// color and
//		// text // color
//		button_Storge.setFont(Font.font("", FontWeight.BOLD, 22));
////		
////		vbox_Dashbord.getChildren().addAll(button_Overview, button_Newsell, button_orders, button_Storge);
////		vbox_Dashbord.setStyle("-fx-background-color: #d9bf16; -fx-text-fill: black;");
//
////	=====================================End of available in store =================
//
////	===================================Import section ============================
//		Button button_Import = new Button("Import");
//		button_Import.setPrefWidth(150);
//		button_Import.setPrefHeight(20);
//		button_Import.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																								// color and
//		// // text // color
//		button_Import.setFont(Font.font("", FontWeight.BOLD, 22));
////	==================================End Import section =====================
//
////	 ==================================Manitanince =================
//		Button button_maintenance = new Button("Maintenance");
//		button_maintenance.setPrefWidth(150);
//		button_maintenance.setPrefHeight(20);
//		button_maintenance.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																									// color
//																									// and // text //
//																									// color
//		button_maintenance.setFont(Font.font("", FontWeight.BOLD, 18));
//
//		Button button_Report = new Button("Reports");
//		button_Report.setPrefWidth(150);
//		button_Report.setPrefHeight(20);
//		button_Report.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																								// color // and // text
//																								// // color
//		button_Report.setFont(Font.font("", FontWeight.BOLD, 18));
//
//		Button button_Logout = new Button("Logout");
//		button_Logout.setPrefWidth(150);
//		button_Logout.setPrefHeight(20);
//		button_Logout.setStyle(" -fx-text-fill: white;  -fx-background-color: transparent;"); // Set the background
//																								// color // and // text
//																								// // color
//		button_Logout.setFont(Font.font("", FontWeight.BOLD, 18));
//
//		vbox_Dashbord.getChildren().addAll(button_User, button_Overview, button_Newsell, button_orders, button_Storge,
//				button_Import, button_maintenance, button_Report, button_Logout);
//
//		vbox_Dashbord.setStyle("-fx-background-color: black;");
//
////	==================================Fnd section maintenance  =================================
//
////	===================Set on action ======================================
//
//		button_Newsell.setOnAction(sell -> {
//			try {
//				New_sell2(s, pp);
////				New_sell(s, pp);
//			} catch (FileNotFoundException | ClassNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NumberFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//		button_Overview.setOnAction(back -> {
//			try {
//				showDashboard(s);
//			} catch (ClassNotFoundException | FileNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		});
//		pp.setLeft(vbox_Dashbord);
//
//	}
//
////	=============================================Done ==========================
//	private static void New_sell2(Stage s, BorderPane pp)
//			throws FileNotFoundException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {
//		s.close();
//		Stage newSellStage = new Stage();
//		BorderPane content_sell = new BorderPane();
//		StackPane stack = new StackPane();
//
//		newSellStage.setTitle("Sell Tammershowroom");
//
//		HBox hb = new HBox(10);
//
//		VBox search_car_by_name = new VBox(10);
//		search_car_by_name.setMinWidth(600);
//		search_car_by_name.setMinHeight(200);
//
//		TextField chose_car = new TextField();
//		chose_car.setPromptText("click  by car Name ");
//		chose_car.setPrefWidth(200);
//		chose_car.setMaxWidth(200);
//		chose_car.setPrefHeight(30);
//		Label CarName = new Label();
//		CarName.setTextFill(Color.WHITESMOKE);
//		CarName.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//
//		Label CarColor = new Label();
//		CarColor.setTextFill(Color.WHITESMOKE);
//		CarColor.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		Label CarPrice = new Label();
//		CarPrice.setTextFill(Color.WHITESMOKE);
//		CarPrice.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		Label CarWieght = new Label();
//		CarWieght.setTextFill(Color.WHITESMOKE);
//		CarWieght.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		Label technical_Specificatin = new Label();
//		technical_Specificatin.setTextFill(Color.WHITESMOKE);
//		technical_Specificatin.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		Label CarId = new Label();
////		CarName.setText("nnf");
////		CarName.setPromptText("car Name ->");
//		Label infoCar = new Label("Select Car  ");
//		infoCar.setTextFill(Color.WHITESMOKE);
//		infoCar.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
//		Node dataTable = detailsOfCars_in_sellSection(chose_car, CarId, CarName, CarColor, CarPrice, CarWieght,
//				technical_Specificatin);
//
//		search_car_by_name.getChildren().addAll(infoCar, chose_car, dataTable);
//		search_car_by_name.setStyle("-fx-background-color: #9fc0c7; -fx-background-radius: 10;");
//		search_car_by_name.setAlignment(Pos.CENTER);
////		n
//		search_car_by_name.setPadding(new Insets(3, 0, 10, 0));
//
//		VBox info = new VBox();
//		info.setMinWidth(400);
//		info.setMinHeight(100);
////		info.setStyle("-fx-background-color: red;");
//		info.setMargin(search_car_by_name, new Insets(-40, 0, 0, 0));
//		info.setAlignment(Pos.BASELINE_LEFT);
//
////		===========================All detials for Customer  section ===================
//
//		VBox Customer_details = new VBox(10);
//		Customer_details.setMinWidth(600);
//		Customer_details.setMinHeight(250);
//
//		Label label_customer = new Label("Select Customer Type:");
//		ComboBox<String> customerTypeComboBox = new ComboBox<>();
//		customerTypeComboBox.getItems().addAll("Click", "Customer", "Company", "Person");
//		customerTypeComboBox.setPromptText("Select Type");
//
////	        ============================================Done ===========================
//
//		Customer_details.setStyle("-fx-background-color: #9fc0c7; -fx-background-radius: 10;");
//		Customer_details.setAlignment(Pos.CENTER);
//		Customer_details.setPadding(new Insets(3, 0, 10, 0));
//		info.setMargin(Customer_details, new Insets(-50, 0, 0, 0));
//
//		info.getChildren().addAll(search_car_by_name, Customer_details);
////		============================Order Details Section ==================================================
//		VBox Oreder_details = new VBox();
//		Oreder_details.setSpacing(10);
//		Oreder_details.setMinWidth(550);
//		Oreder_details.setMinHeight(650);
//		HBox nn = new HBox(10);
////		nn.setMinWidth(550);
////		nn.setMinHeight(500);
//		Button UserButton = new Button("Ali");
//		UserButton.setPrefWidth(150);
//		UserButton.setPrefHeight(20);
////		UserButton.setStyle("-fx-background-color: #d9bf16; -fx-text-fill: black;");
//		UserButton.setStyle(" -fx-text-fill:#9fc0c7; -fx-background-color: transparent;");
//		// Set the background color and
//		// text // color
//		UserButton.setFont(Font.font("", FontWeight.BOLD, 22));
//
//		Button Back_Button = new Button("DashBord");
//		Back_Button.setPrefWidth(150);
//		Back_Button.setPrefHeight(20);
////k
//		Back_Button.setStyle(" -fx-text-fill:#9fc0c7; -fx-background-color: transparent;"); // Set the background color
//																							// and //
//
////		m
////		k// text // color
//		Back_Button.setFont(Font.font("", FontWeight.BOLD, 22));
//		Back_Button.setOnAction(back -> {
//			try {
//				newSellStage.close();
//				showDashboard(s);
//			} catch (ClassNotFoundException | FileNotFoundException | SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		});
//
//		nn.getChildren().addAll(Back_Button, UserButton);
//		nn.setAlignment(Pos.CENTER_RIGHT);
//
////		nn.setMargin(UserButton,new Insets(0,200,0,0));
////		nn.setStyle("-fx-background-color: white;");
//
////		=====================Content of orders ===========
//		VBox Order_Full = new VBox(10);
//		HBox OrderIntroAndDate = new HBox(100);
//		Label OrderDetails = new Label("Order Details ");
//		OrderDetails.setTextFill(Color.WHITESMOKE);
//		OrderDetails.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
////		OrderDetails.setTextFill(Color.WHITESMOKE);
////		OrderIntroAndDate.setAlignment(Pos.CENTER);
//		OrderIntroAndDate.setMargin(OrderDetails, new Insets(0, 0, 0, 50));
//
//		LocalDate currentDate = LocalDate.now();
//
//		// Format the date using a DateTimeFormatter
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		Label CurrentDate = new Label(currentDate.format(formatter));
//		CurrentDate.setTextFill(Color.WHITESMOKE);
//		CurrentDate.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
//
//		OrderIntroAndDate.getChildren().addAll(OrderDetails, CurrentDate);
//		OrderIntroAndDate.setMargin(CurrentDate, new Insets(0, 0, 0, 120));
//
////		d
//
//		VBox Car_DetailsFull = new VBox(10);
//		Label car_section_info = new Label("Information car  ");
//		car_section_info.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
//		car_section_info.setPadding(new Insets(0, 0, 0, 20));
////		car_section_info.setTextFill(Color.WHITESMOKE);
//		car_section_info.setStyle("-fx-background-color: black;");
//		car_section_info.setStyle("-fx-background-radius: 10px;");
//
//		HBox content_CarInfo = new HBox(10);
//		content_CarInfo.setPadding(new Insets(4, 0, 0, 0));
////		content_CarInfo.setStyle("-fx-background-color: black;");
////		
//		HBox hbox1 = new HBox(30); // Set spacing between elements
//		Label CarName_in_FullDetials = new Label("Car Name : ");
//		CarName_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CarName_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//
//		Label CarColor_in_FullDetials = new Label("Car Color : ");
//		CarColor_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CarColor_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//
//		hbox1.getChildren().addAll(CarName_in_FullDetials, CarName, CarColor_in_FullDetials, CarColor);
//
//		HBox hbox2 = new HBox(30); // Set spacing between elements
//		Label CarPrice_in_FullDetials = new Label("Car Price : ");
//		CarPrice_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CarPrice_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		Label CarWieght_in_FullDetials = new Label("Car Wieght : ");
//		CarWieght_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CarWieght_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		hbox2.getChildren().addAll(CarPrice_in_FullDetials, CarPrice, CarWieght_in_FullDetials, CarWieght);
//
//		Label CarTechnical_in_FullDetials = new Label("Car technical : ");
//		CarTechnical_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CarTechnical_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//
//		HBox hbox3 = new HBox(30); // Set spacing between elements
//		hbox3.getChildren().addAll(CarTechnical_in_FullDetials, technical_Specificatin);
//
//		Label CountWantCar = new Label("Number Car :     ");
//		CountWantCar.setTextFill(Color.WHITESMOKE);
//		CountWantCar.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		TextField CountCar = new TextField();
//		Button CheckAvaliableCount = new Button("Sure");
//		Avaliable_Car(CountCar, CheckAvaliableCount, CarId);
//		System.out.println("the count car is ==>" + CountCar);
////		CheckAvaliableCount.setOnAction(w->{
////			
////		});
//
//		HBox hbox4 = new HBox(); // Set spacing between elements
//		hbox4.getChildren().addAll(CountWantCar, CountCar, CheckAvaliableCount);
//
//		VBox vbox = new VBox(10); // Set spacing between rows
//		vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4);
////		vbox.setAlignment(Pos.CENTER);
//		vbox.setMargin(hbox1, new Insets(0, 0, 0, 50)); // top, right, bottom, left
//		vbox.setMargin(hbox2, new Insets(0, 0, 0, 50));
//		vbox.setMargin(hbox3, new Insets(0, 0, 0, 50));
//		vbox.setMargin(hbox4, new Insets(0, 0, 0, 50));
//		content_CarInfo.getChildren().add(vbox);
//
//		Car_DetailsFull.getChildren().addAll(car_section_info, content_CarInfo);
//		Order_Full.setStyle("-fx-background-color: #9fc0c7; -fx-background-radius: 10;");
//
//		VBox BuyBox = new VBox(15);
//		Button Confirm = new Button("Confirm Buy Cash");
//		Button ConfirmCheck = new Button("Confirm Buy checks");
//
//		Label LastBuy = new Label("New Buy");
////		g
//		LastBuy.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
//		BuyBox.setMargin(LastBuy, new Insets(0, 0, 0, 40));
////		BuyBox.setMargin(Confirm, new Insets(-40,0,0,450));
//
//		BuyBox.getChildren().addAll(LastBuy, DetailsBuyInSellSection());
//		BuyBox.setAlignment(Pos.CENTER);
//
//		HBox btnCheck = new HBox(20);
//		Button ClearData = new Button("Reset ");
//
//		btnCheck.getChildren().addAll(Confirm, ConfirmCheck, ClearData);
//
//		btnCheck.setAlignment(Pos.CENTER);
////btnCheck.setMargin(Confirm, new Insets(0,50,0,0));
////btnCheck.setMargin(ClearData, new Insets(0,0,0,350));
////j
////		=============================================
//
//		Oreder_details.getChildren().addAll(nn, Order_Full);
////		Oreder_details.setStyle("-fx-background-color: green;");
//
////		==========================
//		Label CustomerName = new Label();
//		Label CustomerPhone = new Label();
//		Label CustomerId = new Label();
//
//		customerTypeComboBox.setValue("Customer");
////	        System.out.println("the selected is "+customerTypeComboBox.getValue());
//		updateTableView(customerTypeComboBox.getValue(), CustomerName, CustomerPhone, CustomerId);
////	        System.out.println("in customer -->"+CustomerName);
//		Node dd;
//		dd = createDataTableCustomer_inSellSection(CustomerdataList, CustomerName, CustomerPhone, CustomerId);
//		System.out.println("doooooooooo" + CustomerName);
//		Customer_details.getChildren().setAll(label_customer, customerTypeComboBox, dd);
//
//		System.out.println("the cu-->" + CustomerName.getText());
//
//		customerTypeComboBox.setOnAction(e -> {
//			Node updatedData;
//			try {
//
//				if ("Click".equals(customerTypeComboBox.getValue())) {
//					System.out.println("the cussss--->" + CustomerName);
////	                	Customer_details.getChildren().clear();
//					updateTableView(customerTypeComboBox.getValue(), CustomerName, CustomerPhone, CustomerId);
//					updatedData = createDataTableCustomer_inSellSection(CustomerdataList, CustomerName, CustomerPhone,
//							CustomerId);
//
//					System.out.println("====================================");
//					System.out.println("========" + CustomerId.getText());
//					Customer_details.getChildren().setAll(label_customer, customerTypeComboBox, updatedData);
//					System.out.println("gggghhh-->" + CustomerId.getText());
////					m
//					System.out.println("car id -->" + CarId.getText());
//					System.out.println("the num car is -->" + Integer.parseInt(CountCar.getText()));
//					int numcar = Integer.parseInt(CountCar.getText());
//
//					Double total_price = (Integer.parseInt(CountCar.getText())
//							* Double.parseDouble(CarPrice.getText()));
//					System.out.println("the currentDate is -->" + CurrentDate.getText());
//					Confirm.setOnAction(event -> {
//						ConformBuy(Confirm, CustomerId.getText(), CarId.getText(), numcar, total_price);
//
//					});
//					ConfirmCheck.setOnAction(event2 -> {
//						ConformBuyByChecks(ConfirmCheck, CustomerId.getText(), CarId.getText(), numcar, total_price);
//					});
//
//				}
//
//				if ("Person".equals(customerTypeComboBox.getValue())) {
////	                	Customer_details.getChildren().clear();
//					System.out.println("the selected is " + customerTypeComboBox.getValue());
//					updateTableView(customerTypeComboBox.getValue(), CustomerName, CustomerPhone, CustomerId);
//					System.out.println("the vvvbbb -->" + CustomerName);
//
//					updatedData = DetailsPersonInSellSection(CustomerName, CustomerPhone, CustomerId);
//					Customer_details.getChildren().setAll(label_customer, customerTypeComboBox, updatedData);
//					System.out.println("the cussss--->" + CustomerName);
//					System.out.println("====================================");
//					System.out.println("========" + CustomerId.getText());
//
////	                    System.out.println("the aberr  name is "+CustomerName);
//
//				}
//				if ("Company".equals(customerTypeComboBox.getValue())) {
//					Customer_details.getChildren().clear();
//					updateTableView(customerTypeComboBox.getValue(), CustomerName, CustomerPhone, CustomerId);
//					updatedData = createDataTableCompany_inSellSection(CompanydataList, CustomerName, CustomerPhone,
//							CustomerId);
//					Customer_details.getChildren().setAll(label_customer, customerTypeComboBox, updatedData);
//					System.out.println("the cussss--->" + CustomerName);
//					System.out.println("====================================");
//					System.out.println("========" + CustomerId.getText());
//					System.out.println("gghhhhhhhh-->" + CustomerId.getText());
////					ConformBuy( Confirm, CustomerId.getText() ,  CarId.getText(), Integer.parseInt(CountCar.getText())   ,  Double.parseDouble(CarPrice.getText()), CurrentDate.getText() );
//
//				}
//
//				if ("Customer".equals(customerTypeComboBox.getValue())) {
//
////	                	Customer_details.getChildren().clear();
//					updateTableView(customerTypeComboBox.getValue(), CustomerName, CustomerPhone, CustomerId);
//					updatedData = createDataTableCustomer_inSellSection(CustomerdataList, CustomerName, CustomerPhone,
//							CustomerId);
//					Customer_details.getChildren().setAll(label_customer, customerTypeComboBox, updatedData);
//					System.out.println("the cussss--->" + CustomerName);
//					System.out.println("====================================");
//					System.out.println("========" + CustomerId.getText());
//					System.out.println("ggggggggg-->" + CustomerId.getText());
//
////					ConformBuy( Confirm, CustomerId.getText() ,  CarId.getText(), Integer.parseInt(CountCar.getText())   ,  Double.parseDouble(CarPrice.getText()), CurrentDate.getText() );
//
//				}
//
//			} catch (ClassNotFoundException | SQLException e1) {
//				e1.printStackTrace();
//			} catch (NumberFormatException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		});
//
//		VBox Customer_DetailsFull = new VBox();
//		Customer_Section(Customer_DetailsFull, CustomerName, CustomerPhone);
////		Button ClearData = new Button("Reset ");
//
////		f
//		ClearData.setOnAction(e -> {
//			CarName.setText("");
//			CarColor.setText("");
//			CarPrice.setText("");
//			CarTechnical_in_FullDetials.setText("");
//			CarWieght_in_FullDetials.setText("");
//			CountCar.setText("");
//			CustomerName.setText("");
//			CustomerPhone.setText("");
//		});
//		Customer_DetailsFull.setMargin(ClearData, new Insets(25, 0, 0, 350));
////		Customer_DetailsFull.getChildren().add(ClearData);
//
//		Order_Full.getChildren().addAll(OrderIntroAndDate, Car_DetailsFull, Customer_DetailsFull, btnCheck, BuyBox);
////=====================================================================
////		work in hb 
//
//		hb.setMargin(Oreder_details, new Insets(130, 0, 60, 0));
//		hb.setMargin(info, new Insets(30, 0, 0, 0));
//		hb.getChildren().addAll(info, Oreder_details);
//		content_sell.setAlignment(hb, Pos.CENTER);
//		content_sell.setMargin(hb, new Insets(0, 0, 0, 30)); // Adjust the values a
//		ImageView view = new ImageView(new Image(new FileInputStream("sell_Bckground.png")));
//		view.setFitHeight(750);
//		view.setFitWidth(1700);
//		// Set alignment of hb within its parent container (content_sell)
//
//		stack.getChildren().addAll(view, content_sell);
//		Scene scene = new Scene(stack, 1200, 600);
//		content_sell.setCenter(hb);
//		newSellStage.setScene(scene);
//		newSellStage.show();
//	}
//
//	private static void ConformBuyByChecks(Button confirmCheck, String Customerid, String Carid, int NumCar,
//			Double TotalPrice) {
////	check work 
//
//		System.out.println(
//				"the Customer id is --> " + Customerid + "    " + Carid + "     " + NumCar + "      " + TotalPrice);
//
//	}
//
//	private static void ConformBuy(Button Confirm, String Customerid, String Carid, int NumCar, Double TotalPrice) {
//		Confirm.setOnAction(e -> {
//			try {
//				if (NumCar <= 0) {
//					JOptionPane.showMessageDialog(null, "Check Insert Data :) ");
//
//				} else {
//					connectDB();
//					Statement stmt = con.createStatement();
//					ResultSet rs = stmt.executeQuery("SELECT MAX(Sell_ID) AS MaxSellID FROM Buy");
//					if (rs.next()) {
//						int maxSellID = rs.getInt("MaxSellID") + 1;
//
//						// Get the current date
//						Date currentDate = new Date();
//						// Insert the new record with the current date
//						stmt.executeUpdate(
//								"INSERT INTO Buy (Sell_ID,Customer_id, Car_id, Purchase_History, countBuy, countCar) VALUES ("
//										+ maxSellID + ",'" + Customerid + "','" + Carid + "','"
//										+ new java.sql.Date(currentDate.getTime()) + "','" + TotalPrice + "'," + NumCar
//										+ ")");
//
//						// Show success message
//						JOptionPane.showMessageDialog(null, "Successfully Buying :) ");
//
//						// Update the TableView with the new data
//						updateBuyDataList();
//					}
//				}
//			} catch (ClassNotFoundException | SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		});
//
//	}
//
//	private static void updateBuyDataList() {
//		try {
//			connectDB();
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt
//					.executeQuery("SELECT Sell_ID,Customer_id, Car_id, Purchase_History, countBuy, countCar FROM Buy");
//
//			ObservableList<Buy> data = FXCollections.observableArrayList();
//
//			while (rs.next()) {
//				data.add(new Buy(rs.getInt("Sell_ID"), rs.getString("Customer_id"), rs.getString("Car_id"),
//						rs.getDate("Purchase_History"), rs.getDouble("countBuy"), rs.getInt("countCar")));
//			}
//
//			rs.close();
//			stmt.close();
//			BuydataList.setAll(data);
//
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@SuppressWarnings("unused")
//	private static Node detailsOfCars_in_sellSection(TextField text, Label CarId, Label CarName, Label CarColor,
//			Label CarPrice, Label CarWieght, Label technical_Specificatin)
//			throws FileNotFoundException, SQLException, ClassNotFoundException {
//		connectDB();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(
//				"SELECT Car_id,car_model,Year_Car," + "color,Wieght_car,Price,technical_Specificatin FROM Car");
//
//		ObservableList<Car> data = FXCollections.observableArrayList();
//		while (rs.next()) {
//			data.add(new Car(rs.getString(1), rs.getString(2), Integer.parseInt(rs.getString(3)), rs.getString(4),
//					Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)), rs.getString(7)));
//		}
//		rs.close();
//		stmt.close();
//
//		ObservableList<Car> dataList = FXCollections.observableArrayList(data);
//		return createDataTableCar_inSellSection(dataList, text, CarId, CarName, CarColor, CarPrice, CarWieght,
//				technical_Specificatin);
//	}
//
//	@SuppressWarnings("unused")
//	private static Node createDataTableCar_inSellSection(ObservableList<Car> dataList, TextField ff, Label CarId,
//			Label CarName, Label CarColor, Label CarPrice, Label CarWieght, Label technical_Specificatin) {
//		TableView<Car> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(550);
//		myDataTable.setPrefHeight(170);
//
//		TableColumn<Car, String> carModelCol = new TableColumn<>("Car Model");
//		carModelCol.setCellValueFactory(new PropertyValueFactory<>("Car_model"));
//		carModelCol.setPrefWidth(250);
//		TableColumn<Car, String> carIdCol = new TableColumn<>("Car ID");
//		carIdCol.setCellValueFactory(new PropertyValueFactory<>("Car_id"));
//		carIdCol.setPrefWidth(250);
////	carIdCol.setMaxWidth(150);
//		myDataTable.getColumns().addAll(carModelCol, carIdCol);
//		myDataTable.setItems(dataList);
//
//		myDataTable.setOnMouseClicked(event -> {
//			if (event.getClickCount() == 1) {
//				Car selectedCar = myDataTable.getSelectionModel().getSelectedItem();
//				if (selectedCar != null) {
//					CarName.setText(selectedCar.getCar_model());
//					CarColor.setText(selectedCar.getColor());
//					CarPrice.setText(String.valueOf(selectedCar.getPrice()));
//					CarWieght.setText(String.valueOf(selectedCar.getWieght_car()));
//					technical_Specificatin.setText(selectedCar.getTechnical_Specificatin());
//					CarId.setText(selectedCar.getCar_id());
//					ff.setText(selectedCar.getCar_model());
//				}
//			}
//		});
//
//		return myDataTable;
//	}
//
//	private static Node DetailsCustomerInSellSection(Label name, Label phone, Label CustomerId)
//			throws ClassNotFoundException, SQLException {
//
//		connectDB();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT * FROM Customer ");
//
//		ObservableList<Customer> data = FXCollections.observableArrayList();
//
//		while (rs.next()) {
//			data.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3)));
//
//		}
//		rs.close();
//		stmt.close();
//
//		CustomerdataList = FXCollections.observableArrayList(data);
//		System.out.println("after -->" + name);
//		return createDataTableCustomer_inSellSection(CustomerdataList, name, phone, CustomerId);
//
//	}
//
//	@SuppressWarnings("unchecked")
//	private static Node createDataTableCustomer_inSellSection(ObservableList<Customer> CustomerdataList,
//			Label CustomerName, Label CustomerPhone, Label CustomerId) {
//		System.out.println("the name --->" + CustomerName);
//		TableView<Customer> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(550);
//		myDataTable.setPrefHeight(170);
//
//		TableColumn<Customer, String> customerNameCol = new TableColumn<>("Customer Name ");
//		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("FullName"));
//		customerNameCol.setPrefWidth(250);
//
//		TableColumn<Customer, String> customerPhoneCol = new TableColumn<>("Customer Phone ");
//		customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
//		customerPhoneCol.setPrefWidth(250);
//
//		myDataTable.getColumns().addAll(customerNameCol, customerPhoneCol);
//		myDataTable.setItems(CustomerdataList);
//		myDataTable.setOnMouseClicked(event -> {
//			if (event.getClickCount() == 1) {
//				Customer selectedCustomer = myDataTable.getSelectionModel().getSelectedItem();
//				if (selectedCustomer != null) {
//					// Update the labels directly based on the selected customer
//					CustomerName.setText(selectedCustomer.getFullName());
//					CustomerPhone.setText(selectedCustomer.getPhone());
//					CustomerId.setText(selectedCustomer.getCustomer_id());
//					System.out.println("name--->" + CustomerName.getText());
//				}
//			}
//
//		});
//
//		return myDataTable;
//	}
//
////	==============================
//	private static Node DetailsCustomerSection() throws ClassNotFoundException, SQLException {
////s
//		connectDB();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT * FROM Customer ");
//
//		ObservableList<Customer> data = FXCollections.observableArrayList();
//
//		while (rs.next()) {
//			data.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3)));
//
//		}
//		rs.close();
//		stmt.close();
//
//		CustomerdataList = FXCollections.observableArrayList(data);
////		System.out.println("after -->" + name);
//		return createDataTableCustomerSection(CustomerdataList);
//
//	}
//
//	@SuppressWarnings("unchecked")
//	private static Node createDataTableCustomerSection(ObservableList<Customer> CustomerdataList) {
////		System.out.println("the name --->" + CustomerName);
//		TableView<Customer> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(450);
//		myDataTable.setMaxHeight(200);
//
//		TableColumn<Customer, String> customerNameCol = new TableColumn<>("Customer Name ");
//		customerNameCol.setCellValueFactory(new PropertyValueFactory<>("FullName"));
//		customerNameCol.setPrefWidth(250);
//
//		TableColumn<Customer, String> customerPhoneCol = new TableColumn<>("Customer Phone ");
//		customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
//		customerPhoneCol.setPrefWidth(250);
//
//		TableColumn<Customer, String> customerIDCol = new TableColumn<>("Customer ID ");
//		customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
//		customerIDCol.setPrefWidth(250);
//
//		myDataTable.getColumns().addAll(customerNameCol, customerPhoneCol, customerIDCol);
//		myDataTable.setItems(CustomerdataList);
//
//
//		return myDataTable;
//	}
//
//	private static Node DetailsPersonInSellSection(Label name, Label phone, Label CustomerId)
//			throws ClassNotFoundException, SQLException {
//
//		connectDB();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(
//				"SELECT Person.Customer_id, Person.cus_name, Customer.phone FROM Person JOIN Customer ON Person.Customer_id = Customer.Customer_id");
//
//		ObservableList<Person> data = FXCollections.observableArrayList();
//		while (rs.next()) {
//			data.add(new Person(rs.getString(1), rs.getString(2), rs.getString(3)));
//		}
//		rs.close();
//		stmt.close();
//		ParsondataList = FXCollections.observableArrayList(data);
//
//		return createDataTablePerson_inSellSection(ParsondataList, name, phone, CustomerId);
//	}
//
//	private static Node createDataTablePerson_inSellSection(ObservableList<Person> dataList, Label PN, Label PP,
//			Label customerId) {
//		TableView<Person> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(550);
//		myDataTable.setPrefHeight(170);
//
//		TableColumn<Person, String> PersonNameCol = new TableColumn<>("Person Name ");
//		PersonNameCol.setCellValueFactory(new PropertyValueFactory<>("cus_name"));
//		PersonNameCol.setPrefWidth(250);
//
//		TableColumn<Person, String> PersonPhoneCol = new TableColumn<>("Person Phone ");
//		PersonPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
//		PersonPhoneCol.setPrefWidth(250);
//
//		myDataTable.getColumns().addAll(PersonNameCol, PersonPhoneCol);
//		myDataTable.setItems(dataList);
//		myDataTable.setOnMouseClicked(event -> {
//			if (event.getClickCount() == 1) {
//				Person selectedCustomer = myDataTable.getSelectionModel().getSelectedItem();
//				if (selectedCustomer != null) {
//					// Update the labels directly based on the selected customer
//					PN.setText(selectedCustomer.getCus_name());
//					PP.setText(selectedCustomer.getPhone());
//					customerId.setText(selectedCustomer.getCustomer_id());
//
//					System.out.println("name--->" + PN.getText());
//				}
//			}
//
//		});
//
//		return myDataTable;
//	}
//
//	private static Node DetailsCompanyInSellSection(Label name, Label phone, Label CustomerId)
//			throws ClassNotFoundException, SQLException {
//
//		connectDB();
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("SELECT Company.Customer_id, Company.comp_name, Customer.phone "
//				+ "FROM Company " + "JOIN Customer ON Company.Customer_id = Customer.Customer_id;");
//
//		ObservableList<Company> data = FXCollections.observableArrayList();
//
//		while (rs.next()) {
//			data.add(new Company(rs.getString(1), rs.getString(2), rs.getString(3)));
//			System.out.println("the company name is -->" + rs.getString(1) + " and phone is " + rs.getString(2));
//		}
//		rs.close();
//		stmt.close();
//
//		CompanydataList = FXCollections.observableArrayList(data);
//
//		return createDataTableCompany_inSellSection(CompanydataList, name, phone, CustomerId);
//	}
//
//	private static Node updateTableView(String customerType, Label name, Label phone, Label CustomerId)
//			throws ClassNotFoundException, SQLException {
//		Node currentDataTable = null;
//		if (customerType.equals("Person")) {
//			currentDataTable = DetailsPersonInSellSection(name, phone, CustomerId);
//			System.out.println("llllllllllll " + name);
//
//		} else if (customerType.equals("Company")) {
//			currentDataTable = DetailsCompanyInSellSection(name, phone, CustomerId);
//
//		} else {
//
//			currentDataTable = DetailsCustomerInSellSection(name, phone, CustomerId);
//
//		}
//		return currentDataTable;
//
//	}
//
//	private static Node createDataTableCompany_inSellSection(ObservableList<Company> dataList, Label PN, Label PP,
//			Label Customerid) {
//		TableView<Company> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(550);
//		myDataTable.setPrefHeight(170);
//
//		TableColumn<Company, String> CompanyNameCol = new TableColumn<>("Company Name ");
//		CompanyNameCol.setCellValueFactory(new PropertyValueFactory<>("comp_name")); // Map to the comp_name property
//		CompanyNameCol.setPrefWidth(250);
//
//		TableColumn<Company, String> CompanyPhoneCol = new TableColumn<>("Company Phone ");
//		CompanyPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone")); // Map to the phone property
//		CompanyPhoneCol.setPrefWidth(250);
//
//		myDataTable.getColumns().addAll(CompanyNameCol, CompanyPhoneCol);
//		myDataTable.setItems(dataList);
//		myDataTable.setOnMouseClicked(event -> {
//			if (event.getClickCount() == 1) {
//				Company selectedCompany = myDataTable.getSelectionModel().getSelectedItem();
//				if (selectedCompany != null) {
//					// Update the labels directly based on the selected customer
//					PN.setText(selectedCompany.getComp_name());
//					PP.setText(selectedCompany.getPhone());
//					Customerid.setText(selectedCompany.getCustomer_id());
//
//					System.out.println("name--->" + PN.getText());
//				}
//			}
//
//		});
//
//		return myDataTable;
//	}
//
//	// ================================= Customer Section
//	// =============================
//	private static void Customer_Section(VBox customer_DetailsFull, Label CustomerName, Label Customer_Phone) {
//
//		VBox Customerin_DetailsFullVbox = new VBox(10);
//		Label Customer_section_info = new Label("Information Customer: ");
//		Customer_section_info.setPadding(new Insets(0, 0, 0, 30));
////		Customer_section_info.setTextFill(Color.WHITESMOKE);
//		Customer_section_info.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 18));
////		Customer_DetailsFull.setStyle("-fx-background-color: white;");
//		HBox hboxCustomerInfo = new HBox(15); // Set spacing between elements
//		Label CustomerName_in_FullDetials = new Label("Customer Name  : ");
//		CustomerName_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CustomerName_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		Label CustomerPhone_in_FullDetials = new Label("Customer Phone  : ");
//		CustomerPhone_in_FullDetials.setTextFill(Color.WHITESMOKE);
//		CustomerPhone_in_FullDetials.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
//		hboxCustomerInfo.getChildren().addAll(CustomerName_in_FullDetials, CustomerName, CustomerPhone_in_FullDetials,
//				Customer_Phone);
//		hboxCustomerInfo.setPadding(new Insets(0, 0, 0, 50));
////		hboxCustomerInfo.setStyle("-fx-background-color: black;");
//		Customerin_DetailsFullVbox.getChildren().addAll(Customer_section_info, hboxCustomerInfo);
//		customer_DetailsFull.getChildren().add(Customerin_DetailsFullVbox);
//
//	}
//
//	private static void Avaliable_Car(TextField CountCar, Button CheckAvaliableCount, Label CarId) {
//
//		CheckAvaliableCount.setOnAction(event -> {
//			try {
//				if (CountCar.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(null, "Put Number of Car ");
//				} else {
//
//					connectDB();
//					String carId = CarId.getText();
//
//					Statement stmt = con.createStatement();
//					String query = "SELECT import_account FROM Imported WHERE Car_id = '" + carId + "'";
//					ResultSet rs = stmt.executeQuery(query);
//
//					if (rs.next()) {
//						int accountCar = rs.getInt("import_account");
//						int enteredText = Integer.parseInt(CountCar.getText());
//
//						if (accountCar >= enteredText) {
//							int countingCar = accountCar - enteredText;
//
//							Statement updateStmt = con.createStatement();
//							String updateQuery = "UPDATE Imported SET import_account = " + countingCar
//									+ " WHERE Car_id = '" + carId + "'";
//							int affectedRows = updateStmt.executeUpdate(updateQuery);
//
//							if (affectedRows > 0) {
//								JOptionPane.showMessageDialog(null, "Successfully Buying :) ");
//							} else {
//								JOptionPane.showMessageDialog(null, "Check to Storge :(");
//							}
//						} else {
//							Alert alert = new Alert(AlertType.INFORMATION);
//							alert.setTitle("Availability Check");
//							alert.setHeaderText(null);
//							alert.setContentText("Not enough available cars :)");
//							alert.showAndWait();
//						}
//					} else {
//						Alert alert = new Alert(AlertType.INFORMATION);
//						alert.setTitle("Availability Check");
//						alert.setHeaderText(null);
//						alert.setContentText("Car not found.");
//						alert.showAndWait();
//					}
//
//					rs.close();
//					stmt.close();
//				}
//			} catch (ClassNotFoundException | SQLException e) {
//				e.printStackTrace();
//			}
//		});
//	}
//
//	private static Node DetailsBuyInSellSection() throws ClassNotFoundException, SQLException {
//		connectDB();
//		Statement stmt = con.createStatement();
//
//		ResultSet rs = stmt
//				.executeQuery("SELECT Sell_ID,Customer_id, Car_id, Purchase_History, countBuy, countCar FROM Buy");
//		ObservableList<Buy> data = FXCollections.observableArrayList();
//		while (rs.next()) {
//			data.add(new Buy(rs.getInt("Sell_ID"), rs.getString("Customer_id"), rs.getString("Car_id"),
//					rs.getDate("Purchase_History"), Double.parseDouble(rs.getString("countBuy")),
//					Integer.parseInt(rs.getString("countCar"))));
//		}
//		rs.close();
//		stmt.close();
//		BuydataList = FXCollections.observableArrayList(data);
//
//		return createDataTableBuy_inSellSection(BuydataList);
//	}
//
//	private static Node createDataTableBuy_inSellSection(ObservableList<Buy> dataList) {
//		TableView<Buy> myDataTable = new TableView<>();
//		myDataTable.setEditable(true);
//		myDataTable.setMaxWidth(550);
//		myDataTable.setPrefHeight(200);
//
//		TableColumn<Buy, String> CustomerNameCol = new TableColumn<>("Customer ID ");
//		CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_id"));
//		CustomerNameCol.setPrefWidth(120);
//
//		TableColumn<Buy, String> CarIdCol = new TableColumn<>("Car ID ");
//		CarIdCol.setCellValueFactory(new PropertyValueFactory<>("Car_id"));
//		CarIdCol.setPrefWidth(120);
//
//		TableColumn<Buy, String> CarNumCol = new TableColumn<>("Num Car  ");
//		CarNumCol.setCellValueFactory(new PropertyValueFactory<>("countCar"));
//		CarNumCol.setPrefWidth(120);
//
//		TableColumn<Buy, String> CarTotalPriceCol = new TableColumn<>("Total Price ");
//		CarTotalPriceCol.setCellValueFactory(new PropertyValueFactory<>("countBuy"));
//		CarTotalPriceCol.setPrefWidth(120);
//
//		TableColumn<Buy, Date> carDateCol = new TableColumn<>("Purchase");
//		carDateCol.setCellValueFactory(new PropertyValueFactory<>("Purchase_History"));
//		carDateCol.setPrefWidth(120);
//
//		myDataTable.getColumns().addAll(CustomerNameCol, CarIdCol, CarNumCol, CarTotalPriceCol, carDateCol);
//		myDataTable.setItems(dataList);
//
//		return myDataTable;
//	}
//
//}
