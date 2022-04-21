/**
 *
 *This is the controller for the home screen. It contains buttons to go to the calendar, pomodoro timer, to-do list, 
 *calculator, and music player. 
 * 
 * @author Gia German (dnp645)
 * UTSA CS 3443 - Group Project (Group 2)
 * Spring 2022
 */
package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button calendarButton;

    @FXML
    private Button pomButton;

    @FXML
    private Button plannerButton;

    @FXML
    private Button calcButton;

    @FXML
    private Button musicButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private VBox vBox;
    @FXML
    private AnchorPane izzyPane;
    @FXML
    private AnchorPane petePane;
    @FXML
    private AnchorPane fPane;

    @FXML
    void toCalc(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/Calc.fxml").toURI().toURL();
    	vBox = FXMLLoader.load(url);
    	Scene scene = new Scene(vBox);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - Calculator");
    	window.show();

    }

    @FXML
    void toCalendar(ActionEvent event) throws IOException {
       	URL url = new File("src/application/view/weeklyCalander.fxml").toURI().toURL();
    	fPane = FXMLLoader.load(url);
    	Scene scene = new Scene(fPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - Calendar");
    	window.show();
    	
    }

    @FXML
    void toMusic(ActionEvent event) throws IOException {
       	URL url = new File("src/application/view/MusicPlayer.fxml").toURI().toURL();
    	petePane = FXMLLoader.load(url);
    	Scene scene = new Scene(petePane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - Music Player");
    	window.show();

    }

    @FXML
    void toPom(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/pomo.fxml").toURI().toURL();
    	izzyPane = FXMLLoader.load(url);
    	Scene scene = new Scene(izzyPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - Pomodoro Timer");
    	window.show();

    }

    @FXML
    void toPlanner(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/ToDoList.fxml").toURI().toURL();
    	mainPane = FXMLLoader.load(url);
    	Scene scene = new Scene(mainPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - To-Do List");
    	window.show();
    	
    }
}
