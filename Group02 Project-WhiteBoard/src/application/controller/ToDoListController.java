/**
 *
 *This is the controller for the to-do list. It calls upon the SaveEvent model to save and set the user
 *input for each event in order to add it to the list. This class also contains the button to delete an 
 *event once it has been completed.
 * 
 * @author Gia German (dnp645)
 * UTSA CS 3443 - Group Project (Group 02)
 * Spring 2022
 */
package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.model.SaveEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ToDoListController implements Initializable{

    @FXML
    private DatePicker date;
    
    @FXML
    private ListView<SaveEvent> eventList;


    @FXML
    private TextField description;

    @FXML
    private Button addEventButton;

    @FXML
    private AnchorPane aPane;
    
    @FXML
    private Button deleteEventButton;
    
    ObservableList<SaveEvent> list=FXCollections.observableArrayList();

    //add the event the user gives
    @FXML
    void addEvent(ActionEvent event) {
    	list.add(new SaveEvent(description.getText(), date.getValue()));
    	eventList.setItems(list);
    	refresh();
    }
    //clear the text and set the date back to the current after each event addition 
    private void refresh() {
    	date.setValue(LocalDate.now());
    	description.setText(null);
    }

    @FXML
    void addDate(ActionEvent event) {

    }

    @FXML
    void addDescription(ActionEvent event) {

    }
    
    //this deletes the selected cell in the listview 
    @FXML
    void deleteEvent(ActionEvent event) {
    	eventList.getItems().removeAll(eventList.getSelectionModel().getSelectedItem());
    }
    
    //home button
    @FXML
    void toHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();
    }
    
    //date is set to the current date
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setValue(LocalDate.now());
		
	}

}
