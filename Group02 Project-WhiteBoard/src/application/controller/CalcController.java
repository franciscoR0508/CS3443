package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import application.model.CalculatorModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;



public class CalcController {
	@FXML// tags fxml file with the label
	private Label result;
	private long num1 = 0;       //initializing variables
	private String operations = "";
	private boolean start = true;
	private CalculatorModel model = new CalculatorModel ();
	
	@FXML
	private AnchorPane aPane;
	
	@FXML //tags fxml file with the number buttons 0-9
	public void Numbers(ActionEvent event ) // This method is set for 0-9 number buttons
	{
		if(start) {//create empty string 
			result.setText("");//label is blank
			start = false;
		}
		String total = ((Button)event.getSource()).getText();// get the numbers 0-9 that are being pressed 
		result.setText(result.getText()+ total);//numbers appear on the label
	}
	@FXML// tags fxml file with the Clear button
	public void Reset(ActionEvent event) {//This method is set for the clear button 
		result.setText("");//result label empty
		operations= "";
		start= true;
	}
	@FXML // tags fxml file with the operations button 
	public void Operators(ActionEvent event)//This method is set for the operation buttons -,+,x,/
	{
		String total = ((Button)event.getSource()).getText();//get the operation that is being pressed
		if (!total.equals("="))//not equal to =
		{
			if (!operations.isEmpty())// operation is being defined 
				return;
			operations = total;//equate operations
			num1 = Long.parseLong(result.getText());//first number defined
			result.setText("");//result is empty 
			
		}
		else {
			if (operations.isEmpty())//operation is empty
				return;
			long num2 =  Long.parseLong(result.getText());//define our second number 
			float output = model.Calculate(num1, num2, operations); //calling the calculate method from the calculatorModel class 
			result.setText(String.valueOf(output));//label prints out the total
			operations = "";
			start = true;
		}

	
	}
    @FXML
    void calcToHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();
    }

}
