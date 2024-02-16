module Test4 {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.junit.jupiter.api;
	requires junit;
	requires javafx.swing;
	
	
	opens application to javafx.graphics, javafx.fxml;
}
