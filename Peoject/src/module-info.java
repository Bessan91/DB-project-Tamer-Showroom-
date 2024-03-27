module Peoject {
	requires javafx.controls;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
