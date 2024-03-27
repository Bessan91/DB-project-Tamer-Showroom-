package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import javafx.scene.control.Dialog;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Reports {
	GridPane Reports_GP = new GridPane();
	MenuBar menuPane = new MenuBar();
	TabPane tabPane = new TabPane();
	Button Back_Button = new Button("← DashBord");
	Stage ReportsStage = new Stage();
	private DropShadow dropShadow = new DropShadow();
	ObservableList<Car> storagelise = FXCollections.observableArrayList();
	ObservableList<Buy> buyRecords = FXCollections.observableArrayList();
	ObservableList<ImportCar> importRecords = FXCollections.observableArrayList();
	ObservableList<Employee> empRecords = FXCollections.observableArrayList();
	ObservableList<Object[]> records = FXCollections.observableArrayList();
	BorderPane bp = new BorderPane();

	Reports() throws FileNotFoundException {
		Label Entro = new Label("Reports :");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Entro.setTextFill(Color.BLACK);

		Label datevalue = new Label("Date Of Today :" + LocalDate.now() + "");
		datevalue.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 11));
		datevalue.setTextFill(Color.BLACK);

		HBox image_pagelable = new HBox();
		image_pagelable.setSpacing(10);
		image_pagelable.getChildren().addAll(Main.AddImage(), Entro);

		HBox h = new HBox();
		h.setSpacing(500);
		h.getChildren().addAll(image_pagelable, datevalue);

		Back_Button.setPrefWidth(110);
		Back_Button.setPrefHeight(25);
		Back_Button.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		Back_Button.setEffect(dropShadow); // and //
		Back_Button.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
		Back_Button.setOnAction(back -> {
			try {
				ReportsStage.close();
				Bessan.showDashboard(new Stage());
			} catch (ClassNotFoundException | FileNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		Menu menu = new Menu("Select");
		Menu save = new Menu("Save File");

		MenuItem carStorage = new MenuItem("Car Storage");
		MenuItem carSold = new MenuItem("Car Sold");
		MenuItem carImport = new MenuItem("Car Import");
		MenuItem allEmployee = new MenuItem("Employee");
		MenuItem fullProfit = new MenuItem("Full Profit");
		MenuItem filesave = new MenuItem("Save to .csv File");

		menu.getItems().addAll(carStorage, carSold, carImport, allEmployee, fullProfit);
		save.getItems().addAll(filesave);

		menuPane.getMenus().addAll(menu, save);

		carStorage.setOnAction(e -> setPane(createCarStoragePane(), "Car Storage "));
		carSold.setOnAction(e -> setPane(createCarSoldPane(), "Car Sold "));
		carImport.setOnAction(e -> setPane(createCarImportPane(), "Car Import "));
		allEmployee.setOnAction(e -> setPane(createEmployeePane(), "Showroom Employee"));
		fullProfit.setOnAction(e -> setPane(createFullProfitPane(), "Profits "));

		filesave.setOnAction(e -> setPane(createfilecsvtosave(), "Save "));
		Reports_GP.setStyle("-fx-background-color: whitesmoke ;");
		Reports_GP.setHgap(15);
		Reports_GP.setVgap(20);
		Reports_GP.setPadding(new Insets(15, 15, 15, 15));

		Reports_GP.setAlignment(Pos.TOP_LEFT);
		Reports_GP.add(h, 0, 0);
		Reports_GP.add(menuPane, 0, 1);
		Reports_GP.add(tabPane, 0, 2);

		VBox v = new VBox(Back_Button);
		v.setStyle("-fx-background-color: whitesmoke");
		v.setPadding(new Insets(15, 15, 15, 15));
		bp.setAlignment(v, Pos.CENTER_LEFT);
		bp.setLeft(v);
		bp.setCenter(Reports_GP);
	}

	private void setPane(GridPane pane, String tabName) {
		Tab newTab = new Tab(tabName, pane);
		tabPane.getTabs().clear();
		tabPane.getTabs().add(newTab);
	}

	private GridPane createCarStoragePane() {
		GridPane carStoragePane = new GridPane();
		carStoragePane.setStyle("-fx-background-color: whitesmoke ;");
		carStoragePane.setHgap(15);
		carStoragePane.setVgap(20);
		carStoragePane.setPadding(new Insets(10, 10, 10, 10));

		Label Entro = new Label("Car Storage Reports :");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Entro.setTextFill(Color.BLACK);

//_______________________________________________________________________________________________________		
		TableView<Car> storagtable = new TableView<>();
		storagtable.setPrefWidth(850);
		// Car_id column

		TableColumn<Car, String> CarId = new TableColumn<Car, String>("CarId");
		CarId.setCellValueFactory(new PropertyValueFactory<>("car_id"));
		CarId.setPrefWidth(150);
		// Car_brand column

		TableColumn<Car, String> CarBrand = new TableColumn<Car, String>("Car Brand");
		CarBrand.setCellValueFactory(new PropertyValueFactory<Car, String>("Car_brand"));
		CarBrand.setPrefWidth(100);
		// Car_model column
		TableColumn<Car, String> CarModel = new TableColumn<Car, String>("Car Model");
		CarModel.setCellValueFactory(new PropertyValueFactory<Car, String>("Car_model"));
		CarModel.setPrefWidth(100);
		CarModel.setCellFactory(TextFieldTableCell.forTableColumn());
		// Year_Car column

		TableColumn<Car, Integer> Year_Car = new TableColumn<Car, Integer>("Year Car");
		Year_Car.setCellValueFactory(new PropertyValueFactory<Car, Integer>("Year_Car"));
		Year_Car.setPrefWidth(100);
		// color column

		TableColumn<Car, String> color = new TableColumn<Car, String>(" Color");
		color.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
		color.setPrefWidth(100);
		// Wieght_car column

		TableColumn<Car, String> Wieght_car = new TableColumn<Car, String>(" Wieght Car");
		Wieght_car.setCellValueFactory(new PropertyValueFactory<>("Wieght_car"));
		Wieght_car.setPrefWidth(100);
		// Price column

		TableColumn<Car, Integer> Price = new TableColumn<Car, Integer>("Price");
		Price.setCellValueFactory(new PropertyValueFactory<Car, Integer>("Price"));
		Price.setPrefWidth(100);

		// technical_Specificatin column

		TableColumn<Car, String> technical_Specificatin = new TableColumn<Car, String>("Technical Specificatin");
		technical_Specificatin.setCellValueFactory(new PropertyValueFactory<>("technical_Specificatin"));
		technical_Specificatin.setPrefWidth(200);

		storagtable.getColumns().clear();
		storagtable.getColumns().addAll(CarId, CarBrand, CarModel, Year_Car, color, Wieght_car, Price,
				technical_Specificatin);

		ArrayList<Car> updatedCars = getAllcarInfo(Main.con);
		storagelise.clear();
		storagelise.addAll(updatedCars);
		storagtable.setItems(storagelise);
		storagtable.refresh();
//------------------------------------------------------------------
		Label label = new Label("Number Of Cars In Storage:");
		label.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		label.setTextFill(Color.BLACK);

		Label carCountLabel = new Label();
		carCountLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13));
		carCountLabel.setTextFill(Color.BLACK);

		Button countButton = new Button("Get");
		countButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13.5));
		countButton.setPrefSize(80, 30);
		countButton.setStyle("-fx-background-color: #9fc0c7 ;;-fx-text-fill: black;");
		countButton.setOnAction(event -> {
			Map<String, Integer> brandCounts = new HashMap<>();

			int totalCarCount = 0;

			for (Car car : storagelise) {
				String brand = car.getCar_brand();
				brandCounts.put(brand, brandCounts.getOrDefault(brand, 0) + 1);
				totalCarCount++;
			}
			StringBuilder result = new StringBuilder();
			result.append("Total Cars: ").append(totalCarCount).append("\nBrands:\n");

			for (Map.Entry<String, Integer> entry : brandCounts.entrySet()) {
				result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
			}
			carCountLabel.setText(result.toString());
		});

		VBox countV = new VBox();
		countV.setSpacing(10);
		countV.getChildren().addAll(label, countButton, carCountLabel);

		HBox tableh = new HBox();
		tableh.setSpacing(20);
		tableh.getChildren().addAll(storagtable, countV);

		carStoragePane.add(Entro, 0, 0);
		carStoragePane.add(tableh, 0, 1);
		return carStoragePane;
	}

//________________________________________________________________________________________________________________________	
	public GridPane createCarSoldPane() {
		// Implement the layout for Car Sold tab
		GridPane carSoldPane = new GridPane();
		carSoldPane.setStyle("-fx-background-color: whitesmoke ;");
		carSoldPane.setHgap(15);
		carSoldPane.setVgap(20);
		carSoldPane.setPadding(new Insets(10, 10, 10, 10));

		Label titleLabel = new Label("Car Sold Reports:");
		titleLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		titleLabel.setTextFill(Color.BLACK);
		carSoldPane.add(titleLabel, 0, 0);

		TableView<Buy> soldCarsTable = new TableView<>();
		soldCarsTable.setPrefWidth(500);

		TableColumn<Buy, String> custid = new TableColumn<>("Customer ID");
		custid.setCellValueFactory(new PropertyValueFactory<Buy, String>("Customer_id"));
		custid.setPrefWidth(100);

		TableColumn<Buy, String> carid = new TableColumn<>("Car ID");
		carid.setCellValueFactory(new PropertyValueFactory<Buy, String>("Car_id"));
		carid.setPrefWidth(100);

		TableColumn<Buy, Date> buydate = new TableColumn<>("Purchase History");
		buydate.setCellValueFactory(new PropertyValueFactory<Buy, Date>("Purchase_History"));
		buydate.setPrefWidth(100);

		TableColumn<Buy, Double> countBuy = new TableColumn<>("Count Buy");
		countBuy.setCellValueFactory(new PropertyValueFactory<>("countBuy"));
		countBuy.setPrefWidth(100);

		TableColumn<Buy, Integer> countcar = new TableColumn<>("Count Car");
		countcar.setCellValueFactory(new PropertyValueFactory<>("countCar"));
		countcar.setPrefWidth(100);

		soldCarsTable.getColumns().addAll(custid, carid, buydate, countBuy, countcar);

		buyRecords.addAll(getAllBuyRecords(Main.con));
		soldCarsTable.setItems(buyRecords);

		carSoldPane.add(soldCarsTable, 0, 1);

		return carSoldPane;
	}

//________________________________________________________________________________________________________________________	

	private GridPane createCarImportPane() {
		// Implement the layout for Car Import tab
		GridPane carImportPane = new GridPane();
		carImportPane.setStyle("-fx-background-color: whitesmoke ;");
		carImportPane.setHgap(15);
		carImportPane.setVgap(20);
		carImportPane.setPadding(new Insets(10, 10, 10, 10));

		TableView<ImportCar> importCarsTable = new TableView<>();
		importCarsTable.setPrefWidth(500);

		TableColumn<ImportCar, String> id = new TableColumn<>("Import ID");
		id.setCellValueFactory(new PropertyValueFactory<>("importId"));
		id.setPrefWidth(100);

		TableColumn<ImportCar, Date> date = new TableColumn<>("Import Date");
		date.setCellValueFactory(new PropertyValueFactory<>("importDate"));
		date.setPrefWidth(100);

		TableColumn<ImportCar, Double> cost = new TableColumn<>("Import Cost");
		cost.setCellValueFactory(new PropertyValueFactory<>("importCost"));
		cost.setPrefWidth(100);

		TableColumn<ImportCar, String> origin = new TableColumn<>("Origin");
		origin.setCellValueFactory(new PropertyValueFactory<>("origin"));
		origin.setPrefWidth(100);

		importCarsTable.getColumns().addAll(id, date, cost, origin);

		importRecords.addAll(getAllimportRecords(Main.con));
		importCarsTable.setItems(importRecords);

		// ------------------------------------------------------------------
		Label label = new Label("Number Of Imported Cars:");
		label.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		label.setTextFill(Color.BLACK);

		Label carCountLabel = new Label();
		carCountLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13));
		carCountLabel.setTextFill(Color.BLACK);

		Button countButton = new Button("Get");
		countButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13.5));
		countButton.setPrefSize(80, 30);
		countButton.setStyle("-fx-background-color: #9fc0c7 ;;-fx-text-fill: black;");
		countButton.setOnAction(event -> {
			// Get the count of imported cars
			int importedCarsCount = importRecords.size();

			// Update the carCountLabel to display the count
			carCountLabel.setText("Total Imported Cars: " + importedCarsCount);
		});

		VBox countV = new VBox();
		countV.setSpacing(10);
		countV.getChildren().addAll(label, countButton, carCountLabel);

		Label Entro = new Label("Car Import Reports:");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Entro.setTextFill(Color.BLACK);
		carImportPane.add(Entro, 0, 0);
		carImportPane.add(importCarsTable, 0, 1);
		carImportPane.add(countV, 0, 2);

		return carImportPane;
	}

//________________________________________________________________________________________________________________________	

	private GridPane createEmployeePane() {
		// Implement the layout for Car Sold tab
		GridPane EmployeePane = new GridPane();
		EmployeePane.setStyle("-fx-background-color: whitesmoke ;");
		EmployeePane.setHgap(15);
		EmployeePane.setVgap(20);
		EmployeePane.setPadding(new Insets(10, 10, 10, 10));

		TableView<Employee> empTable = new TableView<>();
		empTable.setPrefWidth(500);

		TableColumn<Employee, Integer> empid = new TableColumn<>("Employee ID");
		empid.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
		empid.setPrefWidth(100);

		TableColumn<Employee, String> empname = new TableColumn<>("Employee Name");
		empname.setCellValueFactory(new PropertyValueFactory<Employee, String>("nameEmp"));
		empname.setPrefWidth(100);

		TableColumn<Employee, Integer> salary = new TableColumn<>("Salary");
		salary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));
		salary.setPrefWidth(100);

		TableColumn<Employee, String> position = new TableColumn<>("Position");
		position.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
		position.setPrefWidth(100);

		TableColumn<Employee, String> phone = new TableColumn<>("Phone");
		phone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone"));
		phone.setPrefWidth(100);

		TableColumn<Employee, Integer> age = new TableColumn<>("Age");
		age.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("age"));
		age.setPrefWidth(100);

		empTable.getColumns().addAll(empid, empname, salary, position, phone, age);

		empRecords.addAll(getAllEmployeeRecords(Main.con));
		empTable.setItems(empRecords);

		Label countLabel = new Label("Number of Employees:");
		countLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		countLabel.setTextFill(Color.BLACK);

		// Button to get the number of employees
		Button countButton = new Button("Count Employees");
		countButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13.5));
		countButton.setPrefSize(130, 30);
		countButton.setStyle("-fx-background-color: #9fc0c7 ;;-fx-text-fill: black;");

		Label count = new Label();
		count.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		count.setTextFill(Color.BLACK);

		countButton.setOnAction(event -> {
			int employeeCount = empRecords.size();
			count.setText("Number of Employees: " + employeeCount);
		});

		HBox tableh = new HBox();
		tableh.setSpacing(20);
		tableh.getChildren().addAll(countLabel, countButton);

		Label Entro = new Label("Showroom Employee Reports :");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Entro.setTextFill(Color.BLACK);
		EmployeePane.add(Entro, 0, 0);
		EmployeePane.add(empTable, 0, 1);
		EmployeePane.add(tableh, 0, 2);
		EmployeePane.add(count, 0, 3);
		return EmployeePane;

	}
//________________________________________________________________________________________________________________________	

	private GridPane createFullProfitPane() {
		// Implement the layout for Full Profit tab
		GridPane fullProfitPane = new GridPane();
		fullProfitPane.setStyle("-fx-background-color: whitesmoke ;");
		fullProfitPane.setHgap(15);
		fullProfitPane.setVgap(20);
		fullProfitPane.setPadding(new Insets(10, 10, 10, 10));

		ChoiceBox<String> choicesMenu = new ChoiceBox<>();
		choicesMenu.getItems().addAll("For A Year", "For A Month", "For A Day");
		choicesMenu.setValue("Choose");

		GridPane ProfitPane = new GridPane();
		ProfitPane.setHgap(10);
		ProfitPane.setVgap(10);
		ProfitPane.setPadding(new Insets(10, 10, 10, 10));

		TableView<Object[]> profitCarsTable = new TableView<>();
		profitCarsTable.setPrefWidth(500);

		TableColumn<Object[], String> carIdCol = new TableColumn<>("Car ID");
		TableColumn<Object[], Date> purchaseDateCol = new TableColumn<>("Purchase History");
		TableColumn<Object[], Double> purchasePriceCol = new TableColumn<>("Purchase Price");
		TableColumn<Object[], Double> importPriceCol = new TableColumn<>("Import Price");

		carIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0].toString()));
		purchaseDateCol.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>((Date) cellData.getValue()[1]));
		purchasePriceCol
				.setCellValueFactory(cellData -> new SimpleDoubleProperty((Double) cellData.getValue()[2]).asObject());
		importPriceCol
				.setCellValueFactory(cellData -> new SimpleDoubleProperty((Double) cellData.getValue()[3]).asObject());

		profitCarsTable.getColumns().addAll(carIdCol, purchaseDateCol, purchasePriceCol, importPriceCol);

		records.addAll(getAllProfitRecords(Main.con));
		profitCarsTable.setItems(records);
//-----------------------------------------------------------------------------		

		Label countLabel = new Label("Profit :");
		countLabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		countLabel.setTextFill(Color.BLACK);

		// Button to get the number of employees
		Button countButton = new Button("Calculate");
		countButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13.5));
		countButton.setPrefSize(130, 30);
		countButton.setStyle("-fx-background-color: #9fc0c7 ;;-fx-text-fill: black;");

		Label count = new Label();
		count.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		count.setTextFill(Color.BLACK);

		HBox tableh = new HBox();
		tableh.setSpacing(20);
		tableh.getChildren().addAll(countLabel, countButton);

		VBox v = new VBox();
		v.setSpacing(10);
		v.getChildren().addAll(tableh, count);

		choicesMenu.setOnAction(event -> {

			String selectedOption = (String) choicesMenu.getValue();
			double totalPurchase = 0.0;
			double totalImport = 0.0;

			switch (selectedOption) {
			case "For A Year":

				TextInputDialog yeardialog = new TextInputDialog();
				yeardialog.setTitle("Enter Year");
				yeardialog.setHeaderText("Please enter the year:");
				yeardialog.setContentText("Year:");

				Optional<String> yearresult = yeardialog.showAndWait();
				if (yearresult.isPresent()) {
					int selectedYear = Integer.parseInt(yearresult.get());

					// Calculate the profit for the selected year
					for (Object[] record : records) {
						Date purchaseDate = (Date) record[1];
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(purchaseDate);
						int year = calendar.get(Calendar.YEAR);

						if (year == selectedYear) {
							totalPurchase += (Double) record[2];
							totalImport += (Double) record[3];
						}
					}

					// Calculate the profit and update the count label
					double profit = totalPurchase - totalImport;
					count.setText("Profit for " + selectedYear + ": $" + profit);
				}
				break;

			case "For A Month":

				ChoiceDialog<String> monthDialog = new ChoiceDialog<>("January", "January", "February", "March",
						"April", "May", "June", "July", "August", "September", "October", "November", "December");
				monthDialog.setTitle("Select Month");
				monthDialog.setHeaderText("Please select the month:");
				monthDialog.setContentText("Month:");

				Optional<String> selectedMonth = monthDialog.showAndWait();
				if (selectedMonth.isPresent()) {
					String monthName = selectedMonth.get();

					// Calculate the profit for the selected month
					for (Object[] record : records) {
						Date purchaseDate = (Date) record[1];
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(purchaseDate);
						int month = calendar.get(Calendar.MONTH); // Month is zero-based

						if (monthName.equalsIgnoreCase(new DateFormatSymbols().getMonths()[month])) {
							totalPurchase += (Double) record[2];
							totalImport += (Double) record[3];
						}
					}

					// Calculate the profit and update the count label
					double profit = totalPurchase - totalImport;
					count.setText("Profit for " + monthName + ": $" + profit);
				}
				break;

			case "For A Day":
				DatePicker datePicker = new DatePicker();
				datePicker.setValue(LocalDate.now());

				// Create a dialog for selecting a date
				Dialog<LocalDate> dayDialog = new Dialog<>();
				dayDialog.setTitle("Select Day");
				dayDialog.setHeaderText("Please select the day:");
				dayDialog.getDialogPane().setContent(datePicker);

				dayDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

				Optional<LocalDate> selectedDayResult = dayDialog.showAndWait();
				if (selectedDayResult.isPresent()) {
					LocalDate selectedDate = datePicker.getValue(); // Corrected line

					for (Object[] record : records) {
						Date purchaseDate = (Date) record[1];
						LocalDate purchaseLocalDate = purchaseDate.toLocalDate();

						if (purchaseLocalDate.equals(selectedDate)) {
							totalPurchase += (Double) record[2];
							totalImport += (Double) record[3];
						}

					}

					double profit = totalPurchase - totalImport;
					count.setText("Profit for " + selectedDate + ": $" + profit);
				}

				break;
			}
		});

		Label Entro = new Label("Profit Reports :");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		Entro.setTextFill(Color.BLACK);
		fullProfitPane.add(Entro, 0, 0);
		fullProfitPane.add(choicesMenu, 0, 1);
		fullProfitPane.add(profitCarsTable, 0, 2);
		fullProfitPane.add(v, 0, 3);

		return fullProfitPane;
	}

//________________________________________________________________________________________________________________________	

	private GridPane createfilecsvtosave() {
		GridPane fileCSVToSave = new GridPane();
		fileCSVToSave.setStyle("-fx-background-color: whitesmoke ;");
		fileCSVToSave.setHgap(15);
		fileCSVToSave.setVgap(20);
		fileCSVToSave.setPadding(new Insets(10, 10, 10, 10));

		Label entro = new Label("File Save In .csv Form:");
		entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		entro.setTextFill(Color.BLACK);

		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Storage List", "Buy Records", "Employee Records", "Custom Records");
		choiceBox.setValue("Storage List"); // Default selection

		Button saveButton = new Button("Save Selected Data to CSV");
		saveButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13.5));
		saveButton.setPrefSize(190, 30);
		saveButton.setStyle("-fx-background-color: #9fc0c7 ; -fx-text-fill: black;");

		// Connect the button's action to the saveAllDataToCSV method
		saveButton.setOnAction(event -> {
			String selectedData = choiceBox.getValue();
			List<Object> selectedList = new ArrayList<>();
			switch (selectedData) {
			case "Storage List":
				selectedList.addAll(storagelise);
				break;
			case "Buy Records":
				selectedList.addAll(buyRecords);
				break;
			case "Employee Records":
				selectedList.addAll(empRecords);
				break;
			case "Import Records":
				selectedList.addAll(importRecords);
				break;

			default:
				break;
			}

			// Create a FileChooser instance
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Data to CSV");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

			// Show save dialog and get the selected file
			File selectedFile = fileChooser.showSaveDialog(new Stage());

			if (selectedFile != null) {
				try (FileWriter writer = new FileWriter(selectedFile)) {
					for (Object obj : selectedList) {
						// Convert each object to CSV representation
						String csvLine = "";
						if (obj instanceof Car) {
							csvLine = ((Car) obj).toString();
						} else if (obj instanceof Buy) {
							csvLine = ((Buy) obj).toString();
						} else if (obj instanceof Employee) {
							csvLine = ((Employee) obj).toString();
						} else if (obj instanceof ImportCar) {
							csvLine = ((ImportCar) obj).toString();
						}
						writer.write(csvLine + "\n");
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {

			}

		});

		fileCSVToSave.add(entro, 0, 0);
		fileCSVToSave.add(choiceBox, 0, 1);
		fileCSVToSave.add(saveButton, 0, 2);

		return fileCSVToSave;
	}

//_____________________________________________________________________________________________________________________________

	public static ArrayList<Car> getAllcarInfo(Connection connection) {
		ArrayList<Car> listManager = new ArrayList<>();

		String query = "SELECT * FROM car";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Car car = new Car();
				car.setCar_id(resultSet.getString("Car_id"));
				car.setCar_model(resultSet.getString("Car_model"));
				car.setColor(resultSet.getString("color"));
				car.setTechnical_Specificatin(resultSet.getString("technical_Specificatin"));
				car.setCar_brand(resultSet.getString("Car_brand"));
				car.setPrice(resultSet.getInt("Price"));
				car.setWieght_car(resultSet.getInt("Wieght_car"));
				car.setYear_Car(resultSet.getInt("Year_Car"));

				listManager.add(car);
			}
		} catch (SQLException e) {
			System.err.println("Can Not Retrieve car information: " + e.getMessage());
		}
		return listManager;
	}

//________________________________________________________________________________________________________________________	
	public ArrayList<Buy> getAllBuyRecords(Connection connection) {
		ArrayList<Buy> buyRecords = new ArrayList<>();

		String query = "SELECT * FROM Buy";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Buy buyRecord = new Buy();
				buyRecord.setCustomer_id(resultSet.getString("Customer_id"));
				buyRecord.setCar_id(resultSet.getString("Car_id"));
				buyRecord.setPurchase_History(resultSet.getDate("Purchase_History"));
				buyRecord.setCountBuy(resultSet.getDouble("countBuy"));
				buyRecord.setCountCar(resultSet.getInt("countCar"));

				buyRecords.add(buyRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return buyRecords;
	}

	public ArrayList<Employee> getAllEmployeeRecords(Connection connection) {
		ArrayList<Employee> empRecords = new ArrayList<>();

		String query = "SELECT * FROM employee";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Employee Record = new Employee();
				Record.setEmployeeId(resultSet.getInt("Employee_id"));
				Record.setNameEmp(resultSet.getString("Name_emp"));
				Record.setSalary(resultSet.getInt("Salary"));
				Record.setPosition(resultSet.getString("Position"));
				Record.setPhone(resultSet.getString("Phone"));
				Record.setAge(resultSet.getInt("Age"));

				empRecords.add(Record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empRecords;
	}

	public ArrayList<ImportCar> getAllimportRecords(Connection connection) {
		ArrayList<ImportCar> impRecords = new ArrayList<>();

		String query = "SELECT * FROM Import_car";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ImportCar Record = new ImportCar();
				Record.setImportId(resultSet.getString("import_id"));
				Record.setImportDate(resultSet.getDate("import_date"));
				Record.setImportCost(resultSet.getDouble("import_cost"));
				Record.setOrigin(resultSet.getString("origin"));

				impRecords.add(Record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return impRecords;
	}

	public List<Object[]> getAllProfitRecords(Connection connection) {
		List<Object[]> records = new ArrayList<>();

		String query = "SELECT e.Car_id, e.Purchase_History, e.countBuy, i.import_cost AS import_cost FROM Buy e "
				+ "JOIN Imported ie ON e.Car_id = ie.Car_id " + "JOIN Import_car i ON ie.import_id = i.import_id";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Object[] record = new Object[4]; // Four columns in total

				record[0] = resultSet.getString("Car_id");
				record[1] = resultSet.getDate("Purchase_History");
				record[2] = resultSet.getDouble("countBuy");
				record[3] = resultSet.getDouble("import_cost");
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return records;
	}

}
