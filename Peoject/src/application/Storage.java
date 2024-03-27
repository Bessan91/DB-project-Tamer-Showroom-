package application;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Storage {
//	private TableView<Car> details2;
	private static TextField searchField;
	private static TableView<Car> carTable = new TableView<Car>();
	static TableView<Car> details = new TableView<Car>();
	static TableView<Car> details2 = new TableView<Car>();
	static Button Back_Button = new Button("← DashBord");
	private static DropShadow dropShadow = new DropShadow();
	Stage storage = new Stage();
	BorderPane bp = new BorderPane();
	Connection con;

	Storage() throws FileNotFoundException {
		try {
			setUpUI();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUpUI() throws SQLException, FileNotFoundException {

		// Car_id column

		TableColumn<Car, String> CarId = new TableColumn<Car, String>("CarId");
		CarId.setCellValueFactory(new PropertyValueFactory<>("car_id"));

		// Car_brand column

		TableColumn<Car, String> CarBrand = new TableColumn<Car, String>("Brand");
		CarBrand.setCellValueFactory(new PropertyValueFactory<Car, String>("Car_brand"));
		CarBrand.setPrefWidth(130);

		// Car_model column
		TableColumn<Car, String> CarModel = new TableColumn<Car, String>("Model");
		CarModel.setCellValueFactory(new PropertyValueFactory<Car, String>("Car_model"));

		CarModel.setCellFactory(TextFieldTableCell.forTableColumn());
		// Year_Car column

		TableColumn<Car, Integer> Year_Car = new TableColumn<Car, Integer>("Year");
		Year_Car.setCellValueFactory(new PropertyValueFactory<Car, Integer>("Year_Car"));

		// color column

		TableColumn<Car, String> color = new TableColumn<Car, String>(" Color");
		color.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));

		// Wieght_car column

		TableColumn<Car, String> Wieght_car = new TableColumn<Car, String>("Car Wieght(kg)");
		Wieght_car.setCellValueFactory(new PropertyValueFactory<>("Wieght_car"));

		// Price column

		TableColumn<Car, Integer> Price = new TableColumn<Car, Integer>("Price");
		Price.setCellValueFactory(new PropertyValueFactory<Car, Integer>("Price"));
		// technical_Specificatin column

		TableColumn<Car, String> technical_Specificatin = new TableColumn<Car, String>("Technical Specificatin");
		technical_Specificatin.setCellValueFactory(new PropertyValueFactory<>("technical_Specificatin"));

		// Number_of_cars column

		TableColumn<Car, Integer> Number_of_cars = new TableColumn<Car, Integer>("Number Of Cars");
		Number_of_cars.setCellValueFactory(new PropertyValueFactory<Car, Integer>("Number_of_cars"));
		Number_of_cars.setPrefWidth(100);
		// details2.getColumns().addAll( CarModel,Year_Car,color);

		// Car Table:

		// TableView<Car> carTable = new TableView<Car>();
		carTable.setMaxSize(320, 230);
		details2.setMaxSize(750, 230);
		carTable.setEditable(true);

		carTable.getColumns().addAll(CarBrand, Number_of_cars);
//----------------------------------------------------------------------------------------
		Label sto = new Label("  Storage");
		sto.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 22));
		sto.setTextFill(Color.BLACK);

		Label numOfCars = new Label("Total Number of Cars = ");
		numOfCars.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		numOfCars.setTextFill(Color.BLACK);

		Label refreshlabel = new Label("Refresh Table One:");
		refreshlabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		refreshlabel.setTextFill(Color.BLACK);

		Label deletelable = new Label("Delete From Table Two:");
		deletelable.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		deletelable.setTextFill(Color.BLACK);
		deletelable.setVisible(false);

		Label numOfCars1 = new Label();
		numOfCars1.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		numOfCars1.setTextFill(Color.BLACK);
		int cars = GetData(carTable);
		String carsNum = Integer.toString(cars);
		numOfCars1.setText(carsNum);

		Label info = new Label("Choose a Row then Search for a specific: model, year or color: ");
		info.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		info.setTextFill(Color.BLACK);

		ChoiceBox<String> choiceBox = new ChoiceBox();
		choiceBox.getItems().addAll("Car_model", "Year", "color");
		choiceBox.setValue("Car_model");

		searchField = new TextField();
		searchField.setPromptText("Enter search term:");
		searchField.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		searchField.setPrefSize(150, 30);

		TextField ID = new TextField();
		ID.setPromptText(" car id");
		ID.setMaxSize(120, 50);
		ID.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField model = new TextField();
		model.setPromptText(" Car Model");
		model.setMaxSize(120, 50);
		model.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField Year = new TextField();
		Year.setPromptText(" Year Car");
		Year.setMaxSize(120, 50);
		Year.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField carcolor = new TextField();
		carcolor.setPromptText(" Color");
		carcolor.setMaxSize(120, 50);
		carcolor.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField weight = new TextField();
		weight.setPromptText("Car Weight");
		weight.setMaxSize(120, 50);
		weight.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField price = new TextField();
		price.setPromptText(" Price");
		price.setMaxSize(120, 50);
		price.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField tecnical = new TextField();
		tecnical.setPromptText(" technical Specificatin");
		tecnical.setMaxSize(120, 50);
		tecnical.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		TextField Brand = new TextField();
		Brand.setPromptText("Brand");
		Brand.setMaxSize(120, 50);
		Brand.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		Label addLabel = new Label("");
		addLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		addLabel.setTextFill(Color.BLACK);

		Button addbutton = new Button("Add Car");
		addbutton.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		addbutton.setPrefSize(120, 35);

		Label deleteLabel = new Label("");
		deleteLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		deleteLabel.setTextFill(Color.BLACK);

		Button Delete = new Button("Delete");
		Delete.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		Delete.setPrefSize(110, 32);
		Delete.setVisible(false);

		Button detals2 = new Button("Detals");
		detals2.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		detals2.setPrefSize(110, 305);

		CheckBox addcheckbox = new CheckBox("Add Car to Storage :");
		addcheckbox.setTextFill(Color.BLACK);
		addcheckbox.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 17));

		Button refresh = new Button("Refresh");
		refresh.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		refresh.setPrefSize(110, 30);

		Back_Button.setPrefWidth(110);
		Back_Button.setPrefHeight(25);
		Back_Button.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		Back_Button.setEffect(dropShadow);
		Back_Button.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
		// ----------------------------------------------------
		HBox nofcars = new HBox(numOfCars, numOfCars1);
		nofcars.setSpacing(15);

		VBox numbox = new VBox(carTable);
		numbox.setSpacing(15);
		numbox.setAlignment(Pos.CENTER_LEFT);
		numbox.setMaxSize(800, 600);

		HBox search_choice = new HBox(searchField, choiceBox);
		search_choice.setSpacing(10);
		search_choice.setAlignment(Pos.CENTER_LEFT);
		search_choice.setVisible(false);

		HBox add = new HBox(addbutton, addLabel);
		add.setSpacing(15);

		GridPane addbox = new GridPane();
		addbox.setAlignment(Pos.CENTER_LEFT);
		addbox.setVisible(false);
		addbox.setVgap(10);
		addbox.setHgap(10);
		addbox.add(ID, 0, 0);
		addbox.add(Brand, 1, 0);
		addbox.add(model, 0, 1);
		addbox.add(Year, 1, 1);
		addbox.add(carcolor, 0, 2);
		addbox.add(weight, 1, 2);
		addbox.add(price, 0, 3);
		addbox.add(tecnical, 1, 3);
		addbox.add(add, 1, 4);

		VBox addcar = new VBox(addcheckbox, addbox);
		addcar.setSpacing(15);
		addcar.setAlignment(Pos.CENTER_LEFT);

		HBox entro = new HBox(Main.AddImage(), sto);
		entro.setAlignment(Pos.CENTER_LEFT);

		HBox infoh = new HBox(info, search_choice);
		infoh.setSpacing(15);
		infoh.setAlignment(Pos.CENTER_LEFT);

		HBox table = new HBox(numbox, details2);
		table.setSpacing(30);
		table.setAlignment(Pos.CENTER_LEFT);
		details2.setVisible(false);

		HBox ref = new HBox(refreshlabel, refresh);
		ref.setSpacing(15);
		ref.setAlignment(Pos.CENTER_LEFT);

		HBox del = new HBox(deletelable, Delete, deleteLabel);
		del.setSpacing(15);
		del.setAlignment(Pos.CENTER_LEFT);

		HBox ref_del_table = new HBox(ref, del);
		ref_del_table.setSpacing(30);
		ref_del_table.setAlignment(Pos.CENTER_LEFT);

		VBox vbox = new VBox(infoh, table, ref_del_table);
		vbox.setSpacing(15);
		vbox.setAlignment(Pos.CENTER_LEFT);

		GridPane gp = new GridPane();
		gp.setStyle("-fx-background-color: #9fc0c7 ;");
		gp.setPadding(new Insets(15, 15, 15, 15));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.add(entro, 0, 0);
		gp.add(nofcars, 0, 1);
		gp.add(infoh, 0, 2);
		gp.add(vbox, 0, 3);
		gp.add(addcar, 0, 4);

		// ---------------------------------------------------
		searchField.setOnKeyReleased(event -> searchInDatabase(choiceBox, details2));

		addbutton.setOnAction(e -> {
			Car ca = new Car(ID.getText(), Brand.getText(), model.getText(), Integer.valueOf(Year.getText()),
					carcolor.getText(), Integer.valueOf(weight.getText()), Integer.valueOf(price.getText()),
					tecnical.getText()
			// Integer.valueOf(num.getText())
			);
			try {
				insertData(ca);
				carTable.getColumns().clear();
				carTable.getItems().clear();
				carTable.getColumns().addAll(CarBrand, Number_of_cars);
				int carnum = GetData(carTable);

				String carsNum1 = Integer.toString(carnum);
				numOfCars1.setText(carsNum1);
				// carTable.refresh();
				ID.clear();
				Brand.clear();
				model.clear();
				Year.clear();
				carcolor.clear();
				weight.clear();
				price.clear();
				tecnical.clear();

				addLabel.setText("Car added successfully");
			} catch (SQLException e1) {
				// TODO: Handle exception
				e1.printStackTrace();
				// Update the label text to indicate error
				addLabel.setText("Failed to add car");
			}
		});

		Delete.setOnAction(e -> {
			Car selectedItem = details2.getSelectionModel().getSelectedItem();
			details2.getItems().remove(selectedItem);
			System.out.println("M:->" + selectedItem.getCar_brand());
			System.out.println("ID:->" + selectedItem.getCar_id());

			System.out.println("click on:1");
			Car selectedItem2 = details2.getSelectionModel().getSelectedItem();
			try {
				System.out.println("click on:3");
				deletRow(selectedItem.getCar_id());
				details2.refresh();
				if (selectedItem2 == null) {
					details2.setVisible(false);
					search_choice.setVisible(false);
				}

				carTable.getItems().clear();
				carTable.getColumns().clear();
				carTable.getColumns().addAll(CarBrand, Number_of_cars);
				details2.refresh();
				int cars2 = GetData(carTable);

				String carsNum1 = Integer.toString(cars2);
				numOfCars1.setText(carsNum1);

				System.out.println("click on: ---->" + selectedItem.getCar_id());

				// Update the label text to confirm deleting
				deleteLabel.setText("Car deleted successfully");
			} catch (SQLException e1) {
				// TODO: Handle exception
				e1.printStackTrace();
				// Update the label text to indicate error
				deleteLabel.setText("Failed to delete car");
			}
		});

		detals2.setOnAction(e -> {
			details2.getItems().clear();
			details2.getColumns().clear();
			details2.getColumns().addAll(CarId, CarBrand, CarModel, Year_Car, color, Wieght_car, Price,
					technical_Specificatin);
			details2.refresh();
			Car selectedItem2 = carTable.getSelectionModel().getSelectedItem();
			System.out.println(selectedItem2.getCar_brand());
			try {
				getdata(details2, selectedItem2.getCar_brand());
				details2.setVisible(true);
				search_choice.setVisible(true);
				deletelable.setVisible(true);
				Delete.setVisible(true);
				// vbox
				// detaills2.setTitle("Details");
				// detaills2.setScene(scene);
				// detaills2.show();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		carTable.setOnMouseClicked(e -> {
			details2.getItems().clear();
			details2.getColumns().clear();
			details2.getColumns().addAll(CarId, CarBrand, CarModel, Year_Car, color, Wieght_car, Price,
					technical_Specificatin);
			details2.refresh();
			Car selectedItem2 = carTable.getSelectionModel().getSelectedItem();
			System.out.println(selectedItem2.getCar_brand());
			try {
				getdata(details2, selectedItem2.getCar_brand());
				details2.setVisible(true);
				search_choice.setVisible(true);
				deletelable.setVisible(true);
				Delete.setVisible(true);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		addcheckbox.setOnAction(e -> {

			if (addcheckbox.isSelected() == true) {
				addbox.setVisible(true);
			} else {
				addbox.setVisible(false);
			}

		});

		refresh.setOnAction(e -> {
			// carTable.refresh();
			try {
				carTable.getItems().clear();
				carTable.getColumns().clear();
				carTable.getColumns().addAll(CarBrand, Number_of_cars);
				int cars2 = GetData(carTable);
				String carsNum1 = Integer.toString(cars2);
				numOfCars1.setText(carsNum1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Back_Button.setOnAction(back -> {
			try {
				storage.close();
				Bessan.showDashboard(new Stage());
			} catch (ClassNotFoundException | FileNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		VBox v = new VBox(Back_Button);
		v.setStyle("-fx-background-color: #9fc0c7");
		v.setPadding(new Insets(15, 15, 15, 15));
		bp.setLeft(v);
		bp.setCenter(gp);
	}

//____________________________________

//Get data forTable (carTable)
	private static int GetData(TableView<Car> carTable) throws SQLException {
		DatabaseConnector.connectDB();

		// Get Data from dataBase And set it into carTable
		int ncars = 0;
		int ncars2 = 0;
		String sql = "select Car_brand from CAR order by Car_brand asc;";
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String sql2 = null;
		Statement stmt2 = null;
		String s2 = null;
		while (rs.next()) {

			String s = rs.getString(1);
			System.out.println("----->>>" + s + "<<<-----");
			System.out.println("----->>>" + s2 + "<<<-----");

			if (s.equals(s2) == true) {
				System.out.println("----->>*<<-----");

				s2 = s;
				// System.out.println("----->>>"+s2+"<<<-----");

				continue;
			} else {
				System.out.println("----->" + rs.getString(1) + "<-----");
				sql2 = "select COUNT(Car_brand) from Car where Car_brand = '" + s + "';";
				stmt2 = Main.con.createStatement();

				ResultSet rs2 = stmt2.executeQuery(sql2);
				rs2.next();
				ncars = rs2.getInt(1);
				ncars2 = ncars2 + rs2.getInt(1);
				System.out.println("----->" + (rs2.getInt(1)) + "<-----");

				carTable.getItems().addAll(new Car(s, ncars));
				s2 = s;
			}

		}
		stmt2.execute(sql2);
		stmt2.close();
		stmt.execute(sql);
		stmt.close();
		return ncars2;

	}

	private static void insertData(Car car) throws SQLException {
		System.out.println("Insert into Car values(" + " '" + car.getCar_id() + "'" + ",'" + car.getCar_brand() + "'"
				+ ",'" + car.getCar_model() + "'," + car.getYear_Car() + ",'" + car.getColor() + "'" + ","
				+ car.getWieght_car() + "," + car.getPrice() + ",'" + car.getTechnical_Specificatin() + "');");
		DatabaseConnector.connectDB();

		String sql = "Insert into Car values(" + " '" + car.getCar_id() + "'" + ",'" + car.getCar_brand() + "'" + ",'"
				+ car.getCar_model() + "'," + car.getYear_Car() + ",'" + car.getColor() + "'" + ","
				+ car.getWieght_car() + "," + car.getPrice() + ",'" + car.getTechnical_Specificatin() + "');";
		Statement stmt = Main.con.createStatement();
		stmt.execute(sql);
		stmt.close();

		System.out.println("Connection closed");
	}
	// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

	private static void deletRow(String id) throws SQLException {
		DatabaseConnector.connectDB();
		String sql = "delete from Car where Car_id= '" + id + "';";
		System.out.println(sql);
		Statement stmt = Main.con.createStatement();
		stmt.execute(sql);
		stmt.close();
		System.out.println("Connection closed");
	}

	private static void searchInDatabase(ChoiceBox<String> c, TableView<Car> d2) {
		Car selectedItem = carTable.getSelectionModel().getSelectedItem();
		System.out.println("<<--&&&-->>" + selectedItem.getCar_brand() + "<<-&&&-->>");
		System.out.println("<<------------------------------------------------------->>");
		// System.out.println("<<---->>"+d2.getItems().get(0).getCar_brand()+"<<--->>");
		String searchTerm = searchField.getText();
		String query = null;
		String brand = selectedItem.getCar_brand();

		try {
			if (c.getSelectionModel().getSelectedItem().equals("Year") == true) {
				int searchTer;
				if ((searchTer = Integer.parseInt(searchTerm)) >= 0 && searchTer <= 255) {
					System.out.println("->>>>>>" + searchTer);
					query = "SELECT * FROM Car " + "WHERE Year_car Like" + "'%" + searchTer + "%'" + ";";
					System.out.println(query);

				} else if ((searchTer = Integer.parseInt(searchTerm)) < 0 && searchTer > 255) {
					System.out.println("the input is Strig");
					query = "SELECT * FROM Car where  Car_brand ='" + brand + "';";

				} else {
					System.out.println("the input is Strig");
				}
			} else if (searchTerm == "") {
				query = "SELECT * FROM Car where  Car_brand ='" + brand + "';";
			} else {
				query = "SELECT * FROM Car " + "WHERE " + c.getSelectionModel().getSelectedItem() + " LIKE " + "'%"
						+ searchTerm + "%'" + ";";
				System.out.println(query);
			}

			Statement stmt = Main.con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			ObservableList<Car> carList = FXCollections.observableArrayList();

			while (rs.next()) {
				if (rs.getString(2).equals(brand) == true) {
					carList.add(new Car(rs.getString(1), rs.getString(2), rs.getString(3),
							Integer.parseInt(rs.getString(4)), (rs.getString(5)), Integer.parseInt(rs.getString(6)),
							Integer.parseInt(rs.getString(7)), (rs.getString(8))));
					System.out.println("<---->" + rs.getString(3) + "---" + (rs.getString(5)) + "----"
							+ Integer.parseInt(rs.getString(4)) + "<----->");
				}
			}
			d2.setItems(carList);

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception appropriately (e.g., show an error message)
		}

	}

	private static void getdata(TableView<Car> t, String brand) throws SQLException {
		DatabaseConnector.connectDB();

		String sql = "select  * from Car;";
		Statement stmt = Main.con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ObservableList<Car> carList = FXCollections.observableArrayList();
		while (rs.next()) {
			if (rs.getString(2).equals(brand) == true) {
				carList.add(new Car(rs.getString(1), rs.getString(2), rs.getString(3),
						Integer.parseInt(rs.getString(4)), (rs.getString(5)), Integer.parseInt(rs.getString(6)),
						Integer.parseInt(rs.getString(7)), (rs.getString(8))));
				System.out.println("---->" + rs.getString(3) + "---" + (rs.getString(5)) + "----"
						+ Integer.parseInt(rs.getString(4)) + "<-----");
				System.out.println("----->" + (rs.getString(2)) + "<-----");
				System.out.println("----->" + (rs.getString(3)) + "<-----");

			}
		}
		t.setItems(carList);
		stmt.execute(sql);
		stmt.close();

	}
}