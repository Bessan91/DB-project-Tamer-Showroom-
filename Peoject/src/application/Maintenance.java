package application;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Maintenance {
	GridPane Maint_GP1 = new GridPane();
	GridPane Maint_GP2 = new GridPane();
	private DropShadow dropShadow = new DropShadow();
	private Manager manager;
	Button Back_Button = new Button("← DashBord");
	Button applybutt = new Button("Book");
	Button MentButt2 = new Button("Manager Maintenance");
	static TableView<maintenance_maintain> MainttableView = new TableView<>();
	static ObservableList<maintenance_maintain> data = FXCollections.observableArrayList();
	static TableView<maintenance_maintain> MainttableViewManeger = new TableView<>();
	static ObservableList<maintenance_maintain> dataManeger = FXCollections.observableArrayList();
	Stage MaintenanceStage = new Stage();
	Stage MaintenanceStageManager = new Stage();

	Maintenance() {
		MainttableView.setStyle("-fx-background-color: #9fc0c7 ;-fx-text-fill: white;");
		MainttableView.setEffect(dropShadow);
		MainttableViewManeger.setStyle("-fx-background-color: #9fc0c7 ;-fx-text-fill: white;");
		MainttableViewManeger.setEffect(dropShadow);

		Maint_GP1.setStyle("-fx-background-color: #9fc0c7;");
		Maint_GP1.setHgap(10);
		Maint_GP1.setVgap(15);
		Maint_GP1.setPadding(new Insets(15, 15, 15, 15));
		Maint_GP1.setAlignment(Pos.TOP_LEFT);

		Maint_GP2.setStyle("-fx-background-color: #9fc0c7;");
		Maint_GP2.setHgap(10);
		Maint_GP2.setVgap(15);
		Maint_GP2.setPadding(new Insets(15, 15, 15, 15));
		Maint_GP2.setAlignment(Pos.TOP_LEFT);

		MentButt2.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13));
		MentButt2.setPrefSize(180, 55);
		MentButt2.setEffect(dropShadow);
		MentButt2.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");
		
		MentButt2.setOnAction(e -> {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Manager Login");
			dialog.setHeaderText("Enter Maintenance Page:");
			dialog.setContentText("Username:");

			Optional<String> result = dialog.showAndWait();
			result.ifPresent(username -> {
				PasswordDialog passwordDialog = new PasswordDialog();
				passwordDialog.setTitle("Manager Login"); 
				passwordDialog.setHeaderText("Enter Maintenance Page:");
				passwordDialog.setContentText("Password:");
				Optional<String> passwordResult = passwordDialog.showAndWait();
				passwordResult.ifPresent(password -> loginManager(username, password));
			});

			if (manager != null) {
				Scene scenem;
				try {
					scenem = new Scene(ManegareInterface(), 950, 635);
					MaintenanceStageManager.setScene(scenem);
					MaintenanceStageManager.show();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
	 

		Back_Button.setPrefWidth(110);
		Back_Button.setPrefHeight(25);
		Back_Button.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		Back_Button.setEffect(dropShadow);																// and //
		Back_Button.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
		Back_Button.setOnAction(back -> {
			try {
				MaintenanceStage.close();
				Bessan.showDashboard(new Stage());
			} catch (ClassNotFoundException | FileNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}
//*******************************************************************************************************************************	

	public BorderPane UserInterface() throws FileNotFoundException {
//info for user 
		Label Entro = new Label("Maintaining your Car:");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 23));
		Entro.setTextFill(Color.BLACK);
		Label datevalue = new Label("Date Of Today :" + LocalDate.now() + "");
		datevalue.setFont(Font.font("Georgia", FontWeight.MEDIUM, FontPosture.ITALIC, 13));
		datevalue.setTextFill(Color.BLACK);

		HBox image_pagelable = new HBox();
		image_pagelable.setSpacing(10);
		image_pagelable.getChildren().addAll(Main.AddImage(), Entro);

		HBox h = new HBox();
		h.setSpacing(500);
		h.getChildren().addAll(image_pagelable, datevalue);
// -----------------------------------------
//labels to give info for user 
		Label mainInfo = new Label("Book a Maintanance Appointment For Your Car:");
		mainInfo.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		mainInfo.setTextFill(Color.BLACK);

		Label idlabel = new Label(" Client   ID :");
		idlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		idlabel.setTextFill(Color.BLACK);

		Label namelabel = new Label("Client Name:");
		namelabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		namelabel.setTextFill(Color.BLACK);

		Label phonelabel = new Label("Client Phone:");
		phonelabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 14.5));
		phonelabel.setTextFill(Color.BLACK);

		Label datelabel = new Label(" Proper  Date : ");
		datelabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		datelabel.setTextFill(Color.BLACK);

		Label modelcarlabel = new Label("Car  Model :");
		modelcarlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		modelcarlabel.setTextFill(Color.BLACK);

		Label yearcarlabel = new Label("  Car    Year:");
		yearcarlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15.5));
		yearcarlabel.setTextFill(Color.BLACK);

		Label info = new Label("Description Of Your Problem Or Choose Your Problem From Table:");
		info.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		info.setTextFill(Color.BLACK);

		Label caridlabel = new Label("Car ID:");
		caridlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		caridlabel.setTextFill(Color.BLACK);

		Label descriptionlabel = new Label("Description:");
		descriptionlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		descriptionlabel.setTextFill(Color.BLACK);

		Label resultlabel = new Label();
		resultlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		resultlabel.setTextFill(Color.BLACK);
		resultlabel.setVisible(false);
// -----------------------------------------
//	TextField to get user data	
		TextField idTF = new TextField();
		idTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		idTF.setPrefSize(150, 30);
		idTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		TextField nameTF = new TextField();
		nameTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		nameTF.setPrefSize(150, 30);
		nameTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		TextField phoneTF = new TextField();
		phoneTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		phoneTF.setPrefSize(150, 30);
		phoneTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		DatePicker dateTF = new DatePicker();
		dateTF.setPrefSize(150, 30);
		dateTF.setPromptText("yyyy-MM-dd");
		dateTF.setStyle("-fx-background-color: white  ;-fx-text-fill: #5f7377;");

		TextField modelcarTF = new TextField();
		modelcarTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		modelcarTF.setPrefSize(150, 30);
		modelcarTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		TextField yearcarTF = new TextField();
		yearcarTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		yearcarTF.setPrefSize(150, 30);
		yearcarTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		TextField caridTF = new TextField();
		caridTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		caridTF.setPrefSize(150, 30);
		caridTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		TextField descriptionTF = new TextField();
		descriptionTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		descriptionTF.setPrefSize(180, 110);
		descriptionTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

// -----------------------------------------

		Line line = new Line(50, 50, 620, 50);
		line.setStroke(Color.BLACK);
//button for actions
		applybutt.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13.5));
		applybutt.setPrefSize(100, 55);
		applybutt.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");
// -----------------------------------------
//actions of button 
		// it books an appointment for the customer in addition to adding the
		// appointment to the manager’s table

		applybutt.setOnAction(e -> {
			try {
				String customerId = idTF.getText().trim();
				String customerName = nameTF.getText().trim();
				String customerPhone = phoneTF.getText().trim();
				LocalDate date = dateTF.getValue();
				DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String description = descriptionTF.getText().trim();
				String carId = caridTF.getText().trim();
				String modelCar = modelcarTF.getText().trim();
				int yearCar = Integer.parseInt(yearcarTF.getText().trim());

				if (customerId.isEmpty() || customerName.isEmpty() || customerPhone.isEmpty() || date == null
						|| carId.isEmpty() || modelCar.isEmpty() || yearCar <= 1885 || yearCar > Year.now().getValue()
						|| description.isEmpty()) {
					resultlabel.setText("Please fill in all fields or Year is not correct.");
					resultlabel.setVisible(true);
					return;
				}

				if (!customerPhone.matches("\\d{10}")) {
					resultlabel.setText("Invalid phone number. Please enter a 10-digit number.");
					resultlabel.setVisible(true);
					return;
				}

				try {
					LocalDate selectedDate = LocalDate.parse(date.toString(), dateForm);

					if (!date.equals(selectedDate)) {
						resultlabel.setText("Invalid date. Please use date form yyyy-MM-dd.");
						resultlabel.setVisible(true);
						return;
					}

					if (selectedDate.isBefore(LocalDate.now())) {
						resultlabel.setText("Invalid date. Please select a future date.");
						resultlabel.setVisible(true);
						return;
					}
					if (carId.length() != 17 || !carId.matches("[0-9A-Z]{17}")) {
						resultlabel.setText(
								"Invalid car ID. Please enter a 17-character ID with\ndigits (0-9) and uppercase letters (A-Z).");
						resultlabel.setVisible(true);
						return;
					}

					maintenance_maintain mainteInstance = new maintenance_maintain();

					mainteInstance.insertMaintenanceInfo("", customerId, customerName, customerPhone, description,
							selectedDate, 0, carId, modelCar, yearCar, Main.con);

					resultlabel.setText("Appointment booked successfully!");
					resultlabel.setVisible(true);

				} catch (DateTimeParseException ex) {
					resultlabel.setText("Invalid date format. Please use the format yyyy-MM-dd.");
					resultlabel.setVisible(true);
				}
			} catch (Exception ex) {
				resultlabel.setText("An error occurred. Please Try again.");
				resultlabel.setVisible(true);
			}
		});
// -----------------------------------------
// organize the GUI 
		BorderPane bp = new BorderPane();
		
		HBox idh = new HBox();
		idh.setSpacing(10);
		idh.getChildren().addAll(idlabel, idTF, namelabel, nameTF);

		HBox phonedateh = new HBox();
		phonedateh.setSpacing(5);
		phonedateh.getChildren().addAll(phonelabel, phoneTF, datelabel, dateTF);

		HBox carh = new HBox();
		carh.setSpacing(10);
		carh.getChildren().addAll(modelcarlabel, modelcarTF, yearcarlabel, yearcarTF);

		HBox imagebox = new HBox();
		imagebox.getChildren().addAll(Main.chooseImageButton, Main.imageView);

		HBox desc_checkh = new HBox();
		desc_checkh.setSpacing(10);
		desc_checkh.getChildren().addAll(caridlabel, caridTF, descriptionlabel, descriptionTF, imagebox);

		HBox butth = new HBox();
		butth.setSpacing(10);
		butth.getChildren().addAll(applybutt, resultlabel);

// -----------------------------------------

		Maint_GP1.add(h, 0, 0);
		Maint_GP1.add(ShowUserTable(), 0, 1);
		Maint_GP1.add(mainInfo, 0, 2);
		Maint_GP1.add(idh, 0, 3);
		Maint_GP1.add(phonedateh, 0, 4);
		Maint_GP1.add(carh, 0, 5);
		Maint_GP1.add(line, 0, 6);
		Maint_GP1.add(info, 0, 7);
		Maint_GP1.add(desc_checkh, 0, 8);
		Maint_GP1.add(butth, 0, 9);
		Maint_GP1.add(MentButt2, 1, 9);
		
		VBox v = new VBox(Back_Button);
		v.setStyle("-fx-background-color: #9fc0c7");
		v.setPadding(new Insets(15,15,15,15));
		bp.setAlignment(v, Pos.CENTER_LEFT);
		bp.setLeft(v);
		bp.setCenter(Maint_GP1);

		return bp;
	}

//*******************************************************************************************************************************	

	private HBox ShowUserTable() {

		MainttableView.setPrefSize(570, 250);

		TableColumn<maintenance_maintain, String> maintdescription = new TableColumn<>("Maintenance Description ");
		maintdescription
				.setCellValueFactory(new PropertyValueFactory<maintenance_maintain, String>("Mainten_Description"));
		maintdescription.setPrefWidth(480);

		TableColumn<maintenance_maintain, Double> maintcost = new TableColumn<>("Cost");
		maintcost.setCellValueFactory(new PropertyValueFactory<maintenance_maintain, Double>("cost"));
		maintcost.setPrefWidth(85);

		MainttableView.getColumns().clear();
		MainttableView.getColumns().addAll(maintdescription, maintcost);

		data.addAll(maintenance_maintain.getAllUserMaintenanceInfo(Main.con));
		MainttableView.setItems(data);
		MainttableView.refresh();

		HBox htable = new HBox();
		htable.setSpacing(15);
		htable.getChildren().addAll(MainttableView);
		return htable;

	}

//*******************************************************************************************************************************	
	private VBox ShowManegerTable() {
		MainttableViewManeger.setPrefSize(700, 250);

		TableColumn<maintenance_maintain, String> Maintenid = new TableColumn<>("Maintenance ID");
		Maintenid.setCellValueFactory(new PropertyValueFactory<>("Mainten_id"));
		Maintenid.setPrefWidth(100);

		TableColumn<maintenance_maintain, String> custid = new TableColumn<>("Customer ID");
		custid.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
		custid.setPrefWidth(100);

		TableColumn<maintenance_maintain, String> custname = new TableColumn<>("Name");
		custname.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
		custname.setPrefWidth(90);

		TableColumn<maintenance_maintain, String> custphone = new TableColumn<>("Phone");
		custphone.setCellValueFactory(new PropertyValueFactory<>("customer_phone"));
		custphone.setPrefWidth(90);

		TableColumn<maintenance_maintain, String> maintdescription = new TableColumn<>("Description ");
		maintdescription.setCellValueFactory(new PropertyValueFactory<>("Mainten_Description"));
		maintdescription.setPrefWidth(180);

		TableColumn<maintenance_maintain, LocalDate> maintdate = new TableColumn<>("Date Of Maintenance");
		maintdate.setCellValueFactory(new PropertyValueFactory<>("Mainten_date"));
		maintdate.setPrefWidth(130);

		TableColumn<maintenance_maintain, Double> maintcost = new TableColumn<>("Cost");
		maintcost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		maintcost.setPrefWidth(90);

		TableColumn<maintenance_maintain, String> carid = new TableColumn<>("Car ID");
		carid.setCellValueFactory(new PropertyValueFactory<>("Car_id"));
		carid.setPrefWidth(120);

		TableColumn<maintenance_maintain, String> carmodle = new TableColumn<>("Car Model ");
		carmodle.setCellValueFactory(new PropertyValueFactory<>("CarModel"));
		carmodle.setPrefWidth(120);

		TableColumn<maintenance_maintain, Double> caryear = new TableColumn<>("Car Year");
		caryear.setCellValueFactory(new PropertyValueFactory<>("CarYear"));
		caryear.setPrefWidth(90);

		MainttableViewManeger.getColumns().clear();
		MainttableViewManeger.getColumns().addAll(custid, custname, custphone, maintdescription, maintdate, Maintenid,
				maintcost, carid, carmodle, caryear);
		dataManeger.clear();
		dataManeger.addAll(maintenance_maintain.getAllManagerMaintenanceInfo(Main.con));
		MainttableViewManeger.setItems(dataManeger);

		VBox vtable = new VBox();
		vtable.setSpacing(15);
		vtable.getChildren().addAll(MainttableViewManeger);
		return vtable;
	}

//*******************************************************************************************************************************	
//user manager interface
	public BorderPane ManegareInterface() throws FileNotFoundException {

// info for manager
		Label Entro = new Label("Maintaining Our Cars:");
		Entro.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 18));
		Entro.setTextFill(Color.BLACK);

		Label datevalue = new Label("Date Of Today :" + LocalDate.now() + "");
		datevalue.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 11));
		datevalue.setTextFill(Color.BLACK);

		HBox image_pagelable = new HBox();
		image_pagelable.setSpacing(10);
		image_pagelable.getChildren().addAll(Main.AddImage(), Entro);

		HBox h = new HBox();
		h.setSpacing(400);
		h.getChildren().addAll(image_pagelable, datevalue);
//-----------------------------------------------------------------
		Label Manegarlabel = new Label("Search For a Customer ID to Get Informathion :");
		Manegarlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		Manegarlabel.setTextFill(Color.BLACK);

		Label custidlabel = new Label("Customer ID :");
		custidlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.REGULAR, 14));
		custidlabel.setTextFill(Color.BLACK);

		TextField custidTF = new TextField();
		custidTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		custidTF.setPrefSize(150, 30);
		custidTF.setPromptText("Your ID");
		custidTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		Button searchButton = new Button("Search");
		searchButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 12));
		searchButton.setPrefSize(90, 27);
		searchButton.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");

		Label checklabel = new Label("Check reservation dates, if passed then delet or modify:");
		checklabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.REGULAR, 14));
		checklabel.setTextFill(Color.BLACK);

		Button checkButton = new Button("Check");
		checkButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 12));
		checkButton.setPrefSize(90, 27);
		checkButton.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");

		Label deletelabel = new Label("Delete Reservation:");
		deletelabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.REGULAR, 14));
		deletelabel.setTextFill(Color.BLACK);

		Button deleteButton = new Button("Delete");
		deleteButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 12));
		deleteButton.setPrefSize(90, 27);
		deleteButton.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");

		Label updatelabel = new Label("Update Reservation:");
		updatelabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.REGULAR, 14));
		updatelabel.setTextFill(Color.BLACK);

		Button updateButton = new Button("Update");
		updateButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 12));
		updateButton.setPrefSize(90, 27);
		updateButton.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");

		DatePicker updatedateTF = new DatePicker();
		updatedateTF.setPrefSize(130, 30);
		updatedateTF.setPromptText("(year/month/day)");
		updatedateTF.setStyle("-fx-background-color: black ;-fx-text-fill: #5f7377;");

		Label custresultlabel = new Label();
		custresultlabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 14));
		custresultlabel.setTextFill(Color.BLACK);
		custresultlabel.setVisible(false);

		TextArea checkresultta = new TextArea();
		checkresultta.setPrefSize(350, 175);
		checkresultta.setVisible(false);

		// button action
		searchButton.setOnAction(e -> {
			try {
				String searchforclienttid = custidTF.getText().trim();

				if (searchforclienttid.length() == 8 && searchforclienttid.matches("\\d+")) {
					boolean found = false;

					for (maintenance_maintain info : MainttableViewManeger.getItems()) {
						if (info.getCustomerId().equals(searchforclienttid)) {
							MainttableViewManeger.getSelectionModel().select(info);
							MainttableViewManeger.scrollTo(info);
							found = true;
							break;
						}
					}

					if (found) {
						custresultlabel.setText("Customer found!");
					} else {
						custresultlabel.setText("Customer not found");
					}
				} else {
					custresultlabel.setText("Invalid customer ID. Please enter 8 digits.");
				}

				custresultlabel.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				custresultlabel.setText("Error occurred during search: " + ex.getMessage());
				custresultlabel.setVisible(true);
			}
		});

		checkButton.setOnAction(e -> {
			try {

				StringBuilder showresult = new StringBuilder();

				for (maintenance_maintain maintenanceInfo : dataManeger) {
					LocalDate reservationDate = maintenanceInfo.getMainten_date();

					if (reservationDate.isBefore(LocalDate.now())) {
						showresult.append("Reservation has passed for Customer: ")
								.append(maintenanceInfo.getCustomerId() + "\n");
					}
				}
				checkresultta.setText(showresult.toString());

				if (checkresultta.getText().isEmpty()) {
					checkresultta.setText("No reservations with passed dates found.");
				}

				checkresultta.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
				checkresultta.setText("Error occurred during date check: " + ex.getMessage());
				checkresultta.setVisible(true);
			}
		});

		deleteButton.setOnAction(e -> {
			try {
				maintenance_maintain rowselected = MainttableViewManeger.getSelectionModel().getSelectedItem();

				if (rowselected != null) {
					// Call the deleteMaintenanceInfo method to delete from the database
					rowselected.deleteMaintenanceInfo(rowselected.getCustomerId(), Main.con);

					// Remove from the TableView
					boolean extraction = MainttableViewManeger.getItems().remove(rowselected);

					if (extraction) {
						MainttableViewManeger.getSelectionModel().clearSelection();
						checkresultta.setText(
								"Reservation deleted successfully for Customer ID: " + rowselected.getCustomerId());
					} else {
						checkresultta.setText(
								"Failed to delete reservation for Customer ID: " + rowselected.getCustomerId());
					}

					checkresultta.setVisible(true);
				} else {
					checkresultta.setText("Select a customer to delete");
					checkresultta.setVisible(true);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				checkresultta.setText("Error occurred during deletion: " + ex.getMessage());
				checkresultta.setVisible(true);
			}
		});

		updateButton.setOnAction(e -> {
			try {
				maintenance_maintain rowselected = MainttableViewManeger.getSelectionModel().getSelectedItem();

				if (rowselected != null) {
					LocalDate newDate = updatedateTF.getValue();

					if (newDate != null && newDate.isAfter(LocalDate.now())) {
						rowselected.updateDate(rowselected.getCustomerId(), newDate, Main.con);
						MainttableViewManeger.refresh();

						checkresultta.setText(
								"Reservation updated successfully for Customer ID: " + rowselected.getCustomerId());
					} else {
						checkresultta.setText("Invalid new date. Please enter a future date.");
					}

					checkresultta.setVisible(true);
				} else {
					checkresultta.setText("Select a customer to update");
					checkresultta.setVisible(true);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				checkresultta.setText("Error occurred during update: " + ex.getMessage());
				checkresultta.setVisible(true);
			}
		});

		HBox custh = new HBox();
		custh.setSpacing(10);
		custh.getChildren().addAll(custidlabel, custidTF, searchButton, custresultlabel);

		HBox checkh = new HBox();
		checkh.setSpacing(10);
		checkh.getChildren().addAll(checklabel, checkButton, deleteButton, updateButton, updatedateTF);

		HBox updateh = new HBox();
		updateh.setSpacing(10);
		updateh.getChildren().addAll(updatelabel, updatedateTF, updateButton);

		HBox deleteh = new HBox();
		deleteh.setSpacing(10);
		deleteh.getChildren().addAll(deletelabel, deleteButton);

		VBox check_update_deletev = new VBox();
		check_update_deletev.setSpacing(20);
		check_update_deletev.getChildren().addAll(checkh, updateh, deleteh);

		HBox allguih = new HBox();
		allguih.setSpacing(15);
		allguih.getChildren().addAll(check_update_deletev, checkresultta);

		Line line = new Line(50, 50, 620, 50);
		line.setStroke(Color.BLACK);

		Maint_GP2.add(h, 0, 0);
		Maint_GP2.add(Manegarlabel, 0, 1);
		Maint_GP2.add(ShowManegerTable(), 0, 2);
		Maint_GP2.add(custh, 0, 3);
		Maint_GP2.add(allguih, 0, 4);
		Maint_GP2.add(line, 0, 5);
		Maint_GP2.add(Repairaddedvalue(), 0, 6);

		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color: #9fc0c7");
		bp.setPadding(new Insets(10,10,10,10));
		bp.setLeft(Maint_GP2);
		
		return bp;

	}
//*******************************************************************************************************************************	

	private HBox Repairaddedvalue() {
		Label idcostLabel = new Label("ID Maintenance & Cost Value:");
		idcostLabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		idcostLabel.setTextFill(Color.BLACK);

		TextField costvalueTF = new TextField();
		costvalueTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		costvalueTF.setPrefSize(100, 30);
		costvalueTF.setPromptText("Repair Cost");
		costvalueTF.setStyle("-fx-background-color: white;-fx-text-fill: #5f7377;");

		TextField idvalueTF = new TextField();
		idvalueTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		idvalueTF.setPrefSize(120, 30);
		idvalueTF.setPromptText("ID Maintenance");
		idvalueTF.setStyle("-fx-background-color: white;-fx-text-fill: #5f7377;");

		Button idcostButt = new Button("Add");
		idcostButt.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 12));
		idcostButt.setPrefSize(90, 27);
		idcostButt.setStyle("-fx-background-color: black ;;-fx-text-fill: whitesmoke;");

		Label resultlabel = new Label();
		resultlabel.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 14));
		resultlabel.setTextFill(Color.BLACK);
		resultlabel.setVisible(false);

		idcostButt.setOnAction(q -> {
			maintenance_maintain selectedMaintenanceInfo = MainttableViewManeger.getSelectionModel().getSelectedItem();

			if (selectedMaintenanceInfo != null) {
				String repairCostText = costvalueTF.getText();
				String idMaintenanceText = idvalueTF.getText();

				// Validate ID Maintenance
				if (idMaintenanceText.length() != 8) {
					resultlabel.setText("ID Maintenance must have exactly 8 characters.");
					resultlabel.setVisible(true);
					return;
				}

				if (repairCostText.isEmpty()) {
					resultlabel.setText("Please enter a repair cost.");
					resultlabel.setVisible(true);
				} else {
					try {
						double repairCost = Double.parseDouble(repairCostText);

						if (repairCost >= 0) {
							// Call the updateMaintenanceInfoCost_MaintenId method to update the database
							selectedMaintenanceInfo.updateCost_MaintenId(selectedMaintenanceInfo.getCustomerId(),
									repairCost, idMaintenanceText, Main.con);

							// Update the UI
							selectedMaintenanceInfo.setCost(repairCost);
							selectedMaintenanceInfo.setMainten_id(idMaintenanceText);

							resultlabel.setText("Cost and ID added successfully!");
							resultlabel.setVisible(true);
							MainttableViewManeger.refresh();
						} else {
							resultlabel.setText("Cost must be >= 0.");
							resultlabel.setVisible(true);
						}
					} catch (NumberFormatException e) {
						resultlabel.setText("Invalid cost format. Please enter a valid number.");
						resultlabel.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				resultlabel.setText("Select a row before adding the cost.");
				resultlabel.setVisible(true);
			}
		});

		HBox costvalueh = new HBox();
		costvalueh.setSpacing(10);
		costvalueh.getChildren().addAll(idcostLabel, idvalueTF, costvalueTF, idcostButt, resultlabel);
		return costvalueh;
	}

//__________________________________________________________________________________________________________________________
	
	private void loginManager(String enteredUsername, String enteredPassword) {
		if ("1211577".equals(enteredUsername) && "1211577".equals(enteredPassword)) {
			manager = new Manager();
			System.out.println("Manager logged in");
		} else {
			System.out.println("Invalid credentials");
		}
	}

	class PasswordDialog extends TextInputDialog {
		public PasswordDialog() {
			super();
			getDialogPane().getChildren().stream().filter(node -> node instanceof TextField)
					.map(node -> (TextField) node).forEach(textField -> textField.setPromptText("Password"));
		}
	}

}
