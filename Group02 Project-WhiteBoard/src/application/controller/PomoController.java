/**
 * PomoController is a Java class containing a startTimer method, pauseTimer method, and a goHome method.
 * 
 * @author Isabella Montemayor (ynu611)
 * UTSA CS 3443 - Semester Project
 * Spring 2022
 */
package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PomoController {
    @FXML
    private Button startBtn;
	
    @FXML
    private Button pauseBtn;
	
    private Timer timer = new Timer();
    
    private int counter = 60 * 25;
    
    @FXML
    private Label timerLbl;
    
    @FXML   
    private Label studySessionLbl;
    
    @FXML
    private Label whatsNextLbl; 
    
    @FXML
    private Button homebtn;
    
    @FXML
    private AnchorPane aPane;
    
    private boolean isRunning = false;
    
    private boolean isBreak = false;
    
    private int seconds;
    private int minutes;
    private int studySessionNum =1;
    int motivationCount = 0;
	//When startBtn button is clicked it will start the time and based on the study session count and isBreak boolean
	@FXML
	public void startTimer(ActionEvent event) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    counter--;
		    //Calculations for the default time
                    seconds = counter % 60;
                    minutes = counter / 60;
		    //If-statement block that will set the timerLbl text with the specified seconds and minutes and formatted accordingly
                    if (seconds < 10 && minutes < 10) {
                        timerLbl.setText("0" + minutes + ":0" + seconds);
                    } else if (minutes < 10) {
                        timerLbl.setText("0" + minutes + ":" + seconds);
                    } else {
                        timerLbl.setText(minutes + ":" + seconds);
                    }
		    //If-statement block that calculates and updates the counter depending on which study session or break the user is on
                    if (counter == 0 && studySessionNum == 4 && !isBreak) { 
                        isBreak = true;
                        counter = 15 * 60; 
                        studySessionNum = 0;
                    } else if (counter == 0 && !isBreak) {
                        counter = 5 * 60; 
                        isBreak = true;
                    } else if (counter == 0) { 
                        counter = 25 * 60; 
                        studySessionNum++;
                        isBreak = false;
                    }
                    
                    studySessionLbl.setText("Study Session #" + studySessionNum);
		    //If-statement to check if the user is on a break or study session to display that the next task is to study else
                    if (isBreak) {
			//Switch statement to set the whatsNextLbl with a motivational message based on the motivationCount
                    	switch(motivationCount) {
                    	case 1:
                    		whatsNextLbl.setText("Next: Study for 25 minutes - You got this!");
                    		break;
                    	case 2: 
                    		whatsNextLbl.setText("Next: Study for 25 minutes - Keep going!");
                    		break;
                    	case 3: 
                    		whatsNextLbl.setText("Next: Study for 25 minutes - Almost there!");
                    		break;
                    	default:
                    		whatsNextLbl.setText("Next: Study for 25 minutes - Stay focused!");
                    		break;
       
                    	}    
                    } else if (studySessionNum == 4) {
                        whatsNextLbl.setText("Next: Long break for 15 minutes!");
                    } else {
                        whatsNextLbl.setText("Next: Short break 5 minutes!");
                    }
                    motivationCount++;
	            //Resets the motivationCount back to zero if four study session have gone by
                    if(motivationCount == 4) 
                    	motivationCount = 0;
                });
            }
        }, 0, 1000);
	}
	
	//When the pauseBtn is clicked the timer will pause
	@FXML
	public void pauseTimer(ActionEvent event) {
		timer.cancel();
	}
	
	//When homebtn Button is clicked goHome redirects the user back to the home screen
	public void goHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();
	}

}
