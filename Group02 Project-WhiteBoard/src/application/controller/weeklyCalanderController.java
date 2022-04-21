package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.week;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class weeklyCalanderController implements Initializable{
	
	@FXML
	private Button addToCalander;
	@FXML
	private TextField titleField;
	@FXML
	private TextField description;
	@FXML
	private TextField timeField;
	@FXML
	private TextArea monText;
	@FXML
	private TextArea tuesText;
	@FXML
	private TextArea wedText;
	@FXML
	private TextArea thurText;
	@FXML
	private TextArea friText;
	@FXML
	private ChoiceBox<String> dayChoice;
	@FXML
	private AnchorPane aPane;
	
	private String[]days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
	
	/*initializes choicepicker and runs database method to read exisiting entries
	 *refreshes text on week to display existing entries*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dayChoice.getItems().addAll(days);
		week.database();
		refreshText();
		
	}
	
	//method to refresh day entires with current entries
	public void refreshText() {

		monText.setText(week.listToString(week.mon));
		tuesText.setText(week.listToString(week.tues));
		wedText.setText(week.listToString(week.wed));
		thurText.setText(week.listToString(week.thur));
		friText.setText(week.listToString(week.fri));
	}
	
	/*reads data from fields and uses it to create new event
	 *adds new event to corresponding arraylist
	 *writes to database file and refreshes text
	 */
	public void addCalanderEvent(ActionEvent event) {
		
		String chosenDay = dayChoice.getValue();
		String title = titleField.getText();
		String descriptionStr = description.getText();
		String time = timeField.getText();
		
		String csv = title + "," + time + "," + descriptionStr + "," + chosenDay;
		
		week.createEvent(csv);
		week.writeFile();
		refreshText();
		
	}
    @FXML
    void calendarToHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();

    }
	
	

}
