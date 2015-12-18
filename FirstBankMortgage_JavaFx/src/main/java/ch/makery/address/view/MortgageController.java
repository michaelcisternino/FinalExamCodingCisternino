package ch.makery.address.view;

import java.lang.*;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {

	@FXML
	public TextField txtIncome;
	
	@FXML
	public TextField txtExpenses;
	
	@FXML
	public TextField txtCreditScore;
	
	@FXML
	public TextField txtHouseCost;
	
	@FXML
	public Label incomeLbl;
	
	@FXML
	public Label expensesLbl;
	
	@FXML
	public Label creditScoreLbl;
	
	@FXML
	public Label houseCostLbl;
	
	@FXML
	public Label mortgagePaymentLbl;
	
	@FXML
	public Label maxMortgageLbl;
	
	@FXML
	public Label affordLbl;
	
	@FXML
	public Label termLbl;
	
	@FXML
	public ComboBox cmbTerm;
	
	@FXML
	public Button calculateButton;
	
	@FXML
	public Button affordButton;

	@FXML
	public Button resetButton;
	
    // Reference to the main application.
    private MainApp mainApp;
    

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    
    public void setMainApp(MainApp mainApp) {
    	this.cmbTerm.setPromptText("Choose Term");
        this.mainApp = mainApp;
    }
    
    @FXML
    public void calculatePMT() {
    	if (cmbTerm.getSelectionModel().isEmpty() | txtCreditScore.getText().isEmpty() | txtHouseCost.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Warning!");
    		alert.setHeaderText("Some fields left blank.");
    		alert.setContentText("Fill in empty fields!");
    		alert.showAndWait();
    	}
    	else {
    	Rate r = new Rate();
    	String term = (String)cmbTerm.getSelectionModel().getSelectedItem();
    	int t = Integer.parseInt(term);
    	String s = txtCreditScore.getText();
    	int credScore = Integer.parseInt(s);
    	String h = txtHouseCost.getText();
    	double houseCost = Double.parseDouble(h);
    	double payment = r.getPayment(t, credScore, houseCost);
    	String paymentString = (String.format("$%.2f", payment));
    		if (credScore < 600) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Warning!");
    		alert.setHeaderText("Interest rate for given credit score: " +credScore+ " cannot be found in database.");
    		alert.setContentText("Interest Rate set to 5% by default.");
    		alert.showAndWait();
    		mortgagePaymentLbl.setText(paymentString);
    	}
    	else {
    	mortgagePaymentLbl.setText(paymentString);
    	}
    	}
    }
    
    @FXML
    public void handleAfford() {
    	if (txtIncome.getText().isEmpty() | txtExpenses.getText().isEmpty() | txtHouseCost.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Warning!");
    		alert.setHeaderText("Some fields left blank.");
    		alert.setContentText("Fill in empty fields!");
    		alert.showAndWait();
    	}
    	else {
    	String i = txtIncome.getText();
    	double income = Double.parseDouble(i);
    	String e = txtExpenses.getText();
    	double expenses = Double.parseDouble(e) * 12;
    	String p = mortgagePaymentLbl.getText().substring(1);
    	double payment = Double.parseDouble(p);
    	double constraint1 = income * .36;
    	double constraint2 = ((income + expenses) * .28);
    	double maxPayment;
    	if (constraint1 > constraint2) {
    		maxPayment = constraint1;
    	}
    	else {
    		maxPayment = constraint2;
    	}
    	maxMortgageLbl.setText(String.format("Max mortgage payment: $%.2f", maxPayment));
    	if (payment <= constraint1 && payment <= constraint2) {
    		affordLbl.setText("Yes! Congratulations!");
    	}
    	else {
    		affordLbl.setText("Sorry, house is too expensive!");
    	}
    	}
    }
    
    @FXML
    public void reset() {
    	txtIncome.clear();
    	txtExpenses.clear();
    	txtCreditScore.clear();
    	txtHouseCost.clear();
    	cmbTerm.getSelectionModel().clearSelection();
    	affordLbl.setText("");
    	mortgagePaymentLbl.setText("");
    	maxMortgageLbl.setText("");
    }
    
    
   
}