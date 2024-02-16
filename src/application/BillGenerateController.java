package application;

import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BillGenerateController {

    @FXML
    public TextArea displayArea;

    @FXML
    public TextField eField;

    @FXML
    public TextField gField;

    @FXML
    public Button hireButton;

    @FXML
    public Button homeButton;
    @FXML
    public Button logoutButton;

    @FXML
    public ComboBox<Customer> userComboBox; // Change the type to customer

    public CustomerList cList;
    public BillList bList;
    public Bill bill;

    public void initialize() throws ClassNotFoundException, IOException {
        System.out.println("Initialize");
        cList = DataHandler.readCustomerList();
        Customer.setCustomerCount(cList.getCustomers().size());
        bList = DataHandler.readBillList();

        
        ObservableList<Customer> customerList = FXCollections.observableArrayList(cList.getCustomers());
        userComboBox.setItems(customerList);

        // Use custom cell factory for displaying only customer names
        userComboBox.setCellFactory(param -> new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        userComboBox.setButtonCell(new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

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
    //logout
    public void handleLogout(ActionEvent event) {
        try {
            
            Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login Page");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
    
    
    
    //selecting the customer in dropdown menu
    public void ComboBoxListeners() {
        userComboBox.valueProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> selected, Customer oldValue, Customer newValue) {
                // You can access the selected Customer object here (newValue)
                System.out.println("Selected Customer: " + (newValue != null ? newValue.getName() : ""));
            }
        });
    }
    	//bill generation
    public void generateButtonListener() throws IOException {
        try {
            Customer selectedCustomer = userComboBox.getValue();

            if (selectedCustomer != null) {
                double gasReading = Double.parseDouble(gField.getText());
                double electricReading = Double.parseDouble(eField.getText());
                double CurrentElectricityReading = electricReading - selectedCustomer.getElectricReading();
                double CurrentgasReading = gasReading - selectedCustomer.getGasReading();
                double totalElectricityAmount = CurrentElectricityReading * 0.19349;
                double totalGasAmount = CurrentgasReading * 0.03797;
                LocalDate currentDate = LocalDate.now();

                // Create a new Bill instance
                bill = new Bill(selectedCustomer, totalElectricityAmount + totalGasAmount, CurrentgasReading, CurrentElectricityReading, currentDate);

                // To the bill in the TextArea
                displayArea.setText(bill.printInvoice());

                // adding the bill and writing it to a file
                bList.addBill(bill);
                DataHandler.writeToFile(bList);
            } else {
                displayArea.setText("Please select a customer.");
            }
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric values)
            displayArea.setText("Invalid input. Please enter valid numeric values for readings.");
            e.printStackTrace(); // Replace with appropriate error handling
        }
        hireButton.setVisible(false);
    }
}
