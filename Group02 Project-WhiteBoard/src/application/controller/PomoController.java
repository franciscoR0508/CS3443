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
	
	@FXML
	public void startTimer(ActionEvent event) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    counter--;

                    seconds = counter % 60;
                    minutes = counter / 60;
                    if (seconds < 10 && minutes < 10) {
                        timerLbl.setText("0" + minutes + ":0" + seconds);
                    } else if (minutes < 10) {
                        timerLbl.setText("0" + minutes + ":" + seconds);
                    } else {
                        timerLbl.setText(minutes + ":" + seconds);
                    }

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
                    if (isBreak) {
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
                    if(motivationCount == 4) 
                    	motivationCount = 0;
                });
            }
        }, 0, 1000);
	}
	
	@FXML
	public void pauseTimer(ActionEvent event) {
		timer.cancel();
	}
	
	public void goHome(ActionEvent event) throws IOException {
		//TODO
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();
	}

}