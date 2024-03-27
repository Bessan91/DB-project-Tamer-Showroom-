package application;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Paying_Off {
	static TableView<PayingOffInfo> DebtstableView = new TableView<>();
	private DropShadow dropShadow = new DropShadow();
	Button Back_Button = new Button("← DashBord");
	static GridPane PayOff_GP = new GridPane();
	static BorderPane bp = new BorderPane();
	static Stage pay = new Stage();

	Paying_Off() throws FileNotFoundException {
		DebtstableView.setStyle("-fx-background-color: #9fc0c7 ;-fx-text-fill: black;");
		DebtstableView.setEffect(dropShadow);

		Label Entro = new Label("Paying Off :");
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
//---------------------------------------------------------------------------------------------------------------
		Label custInfo = new Label("Search for a Customer TO Deal With Debt :");
		custInfo.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		custInfo.setTextFill(Color.BLACK);

		Label custidlabel = new Label("Customer ID :");
		custidlabel.setFont(Font.font("Georgia", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		custidlabel.setTextFill(Color.BLACK);

		TextField custidTF = new TextField();
		custidTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		custidTF.setPrefSize(150, 30);
		custidTF.setPromptText("Your ID");
		custidTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		Button searchButton = new Button("Search");
		searchButton.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13));
		searchButton.setPrefSize(100, 30);
		searchButton.setStyle("-fx-background-color: #9fc0c7 ;;-fx-text-fill: black;");

		Label custresultlabel = new Label();
		custresultlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		custresultlabel.setTextFill(Color.BLACK);
		custresultlabel.setVisible(false);

		HBox custh = new HBox();
		custh.setSpacing(15);
		custh.getChildren().addAll(custidlabel, custidTF, searchButton, custresultlabel);

//---------------------------------------------------------------------------------------------------------------
		Label payInfo = new Label("Choose a Customer From Table TO Deal With Debt :");
		payInfo.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 15));
		payInfo.setTextFill(Color.BLACK);

		Label paylabel = new Label("Pay :");
		paylabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		paylabel.setTextFill(Color.BLACK);

		TextField payTF = new TextField();
		payTF.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		payTF.setPrefSize(150, 30);
		payTF.setPromptText("In Shekel ₪");
		payTF.setStyle("-fx-background-color: white ;-fx-text-fill: #5f7377;");

		Button applybutt = new Button("Apply");
		applybutt.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, FontPosture.ITALIC, 13));
		applybutt.setPrefSize(100, 30);
		applybutt.setStyle("-fx-background-color: #9fc0c7 ;;-fx-text-fill: black;");

		Label payresultlabel = new Label();
		payresultlabel.setFont(Font.font("Palatino Linotype", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		payresultlabel.setTextFill(Color.BLACK);
		payresultlabel.setVisible(false);

		HBox hpay = new HBox();
		hpay.setSpacing(15);
		hpay.getChildren().addAll(paylabel, payTF, applybutt, payresultlabel);
//---------------------------------------------------------------------------------------------------------------

		Line line = new Line(50, 50, 550, 50);
		line.setStroke(Color.BLACK);
//********************************************
		PayOff_GP.setStyle("-fx-background-color: whitesmoke ;");
		PayOff_GP.setHgap(15);
		PayOff_GP.setVgap(20);
		PayOff_GP.setPadding(new Insets(15, 15, 15, 15));

		PayOff_GP.setAlignment(Pos.TOP_LEFT);
		PayOff_GP.add(h, 0, 0);
		PayOff_GP.add(ShowTable(), 0, 1);
		PayOff_GP.add(line, 0, 2);
		PayOff_GP.add(custInfo, 0, 3);
		PayOff_GP.add(custh, 0, 4);
		PayOff_GP.add(payInfo, 0, 6);
		PayOff_GP.add(hpay, 0, 7);
		Back_Button.setPrefWidth(110);
		Back_Button.setPrefHeight(25);
		Back_Button.setStyle(" -fx-text-fill:white; -fx-background-color: black;"); // Set the background color
		Back_Button.setEffect(dropShadow); // and //
		Back_Button.setFont(Font.font("Palatino Linotype", FontWeight.BOLD, 14));
		Back_Button.setOnAction(back -> {
			try {
				pay.close();
				Bessan.showDashboard(new Stage());
			} catch (ClassNotFoundException | FileNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		VBox v = new VBox(Back_Button);
		v.setStyle("-fx-background-color: whitesmoke");
		v.setPadding(new Insets(15, 15, 15, 15));
		bp.setAlignment(v, Pos.CENTER_LEFT);
		bp.setLeft(v);
		bp.setCenter(PayOff_GP);

//********************************************
		// button action
		searchButton.setOnAction(e -> {
			try {
				String customerIdToSearch = custidTF.getText();

				if (customerIdToSearch.length() == 8 && customerIdToSearch.matches("\\d+")) {
					boolean customerFound = false;

					for (PayingOffInfo info : DebtstableView.getItems()) {
						if (info.getCustomerId().equals(customerIdToSearch)) {
							DebtstableView.getSelectionModel().select(info);
							DebtstableView.scrollTo(info); // Scroll to the selected row
							customerFound = true;
							break;
						}
					}

					if (customerFound) {
						custresultlabel.setText("Customer found!");
					} else {
						custresultlabel.setText("Customer not found");
					}
				} else {
					custresultlabel.setText("Invalid customer ID.\nPlease enter 8 digits.");
				}

				custresultlabel.setVisible(true);
			} catch (Exception ex) {
				custresultlabel.setText("Error occurred during search");
				custresultlabel.setVisible(true);
			}
		});

		applybutt.setOnAction(e -> {
			PayingOffInfo Rowselected = DebtstableView.getSelectionModel().getSelectedItem();

			if (Rowselected != null) {
				try {
					double Inputvalue = Double.parseDouble(payTF.getText());

					if (Inputvalue >= 100) {
						double originalAmount = Rowselected.getDebts();
						double updatedAmount = Math.max(0, originalAmount - Inputvalue);

						Rowselected.updatePayingOffInfo(Rowselected.getCustomerId(), updatedAmount, Main.con);

						Rowselected.setDebts(updatedAmount);
						DebtstableView.refresh();

						if (updatedAmount == 0) {
							payresultlabel.setText("Debt cleared!");
						} else {
							payresultlabel.setText("Modified successfully ^-^");
						}

						if (Inputvalue > originalAmount) {
							double remainingAmount = Math.max(0, Inputvalue - originalAmount);
							payresultlabel.setText(payresultlabel.getText() + "\nRemaining amount: " + remainingAmount);
						}

						payresultlabel.setVisible(true);
					} else {
						payresultlabel.setText("Pay amount must be greater than or equal to 100");
						payresultlabel.setVisible(true);
					}
				} catch (NumberFormatException ex) {
					payresultlabel.setText("Invalid Amount Entered or Empty Field");
					payresultlabel.setVisible(true);
				}
			} else {
				payresultlabel.setText("Select a Customer");
				payresultlabel.setVisible(true);
			}
		});

	}
//********************************************

	private HBox ShowTable() {

		TableColumn<PayingOffInfo, String> Custid = new TableColumn<>("Customer ID");
		Custid.setCellValueFactory(new PropertyValueFactory<PayingOffInfo, String>("customerId"));
		Custid.setPrefWidth(160);

		TableColumn<PayingOffInfo, String> CustName = new TableColumn<>("Customer Name ");
		CustName.setCellValueFactory(new PropertyValueFactory<PayingOffInfo, String>("customerName"));
		CustName.setPrefWidth(180);

		TableColumn<Car, Integer> price = new TableColumn<>("Car Price");
		price.setCellValueFactory(new PropertyValueFactory<Car, Integer>("Price"));
		price.setPrefWidth(156);

		TableColumn<PayingOffInfo, Double> AmountOfDebt = new TableColumn<>("Amount Of Debt ");
		AmountOfDebt.setCellValueFactory(new PropertyValueFactory<PayingOffInfo, Double>("Debts"));
		AmountOfDebt.setPrefWidth(156);
		DebtstableView.getColumns().clear();
		DebtstableView.getColumns().addAll(Custid, CustName, AmountOfDebt);
		DebtstableView.getItems().clear();

		ObservableList<PayingOffInfo> data = FXCollections
				.observableArrayList(PayingOffInfo.getAllManegarPayingOffInfo(Main.con));

		DebtstableView.setItems(data);
		DebtstableView.setPrefSize(500, 200);

		HBox h = new HBox();
		h.setSpacing(20);
		h.getChildren().addAll(DebtstableView);
		return h;

	}
//********************************************

}