package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationFormController {

	@FXML
	private TextField addrField;

	@FXML
	private Label address;

	@FXML
	private Label bdate;

	@FXML
	private Label bdate1;

	@FXML
	private Label bdate11;

	@FXML
	private DatePicker datePicker;

	@FXML
	private Button homeButton;

	@FXML
	private TextField initialE;

	@FXML
	private TextField initialG;

	@FXML
	private Label name;

	@FXML
	private TextField nameField;

	@FXML
	private Button register;

	private Customer customer;
	private CustomerList customerlist;

	public void initialize() throws ClassNotFoundException, IOException {
		System.out.println("Initialise");
		customerlist = DataHandler.readCustomerList();  // read existing customer list from file
		Customer.setCustomerCount(customerlist.getCustomers().size());
		customer = new Customer();
	}

	@FXML
	void onRegisterClicked(ActionEvent event) throws IOException {
		String name = nameField.getText();
		double intialGReading = Double.parseDouble(initialG.getText());
		double intialEReading = Double.parseDouble(initialE.getText());
		String caddress = address.getText();
		LocalDate date = datePicker.getValue();
		// ---------------------------------------------------------
		customer.setName(name);
		customer.setGasReading(intialGReading);
		customer.setElectricReading(intialEReading);
		customer.setAddress(caddress);
		customer.setDateMovedIn(date);
//			--------------------------------
		customerlist.addCustomer(customer);
		DataHandler.writeToFile(customerlist);
	}
	//selecting date
	public void movInDateListener() {
		LocalDate date = datePicker.getValue();
		System.out.println("Selected Date: " + date);
	}
	//back to homepage
	public void BackToHomeButtonListener(ActionEvent e) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		// Build the scene graph.
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		// Display window using the scene graph.
		stage.setTitle("Home Page");
		stage.setScene(scene);
		stage.show();
	}

}