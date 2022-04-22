package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MusicController implements Initializable{

	private File directory;
	private File[] files;
	private ArrayList<File> songList;
	private int songNum;
	private Timer timer;
	private TimerTask task;
	private boolean isRunning;
	private Media media;
	private MediaPlayer mediaPlayer;
	
    @FXML
    private Pane thepane;
	
    @FXML
    private ProgressBar bar;
	
    @FXML
    private Button previousButton;
	
    @FXML
    private ImageView image;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button reverseButton;

    @FXML
    private Button skipButton;

    @FXML
    private Label songLabel;

    @FXML
    private Slider volumeSlider;
    
    @FXML
    private AnchorPane aPane;

    @FXML
    void pauseSong(ActionEvent event) { //this is the function that will pause the music and the timer for the progress bar when you hit the pause button
    	cancelTimer();
    	mediaPlayer.pause();
    }

    @FXML
    void playSong(ActionEvent event) { // this is the function that will play the music and begin the timer for the progress bar when you hit the play button
    	beginTimer();
    	mediaPlayer.play();
    }
    
   void playNextSong(){ //  this is the same as the playSong function but without a event so that I can call it whenever I need it
	   bar.setProgress(0);
	   beginTimer();
	   mediaPlayer.setVolume(volumeSlider.getValue() * 0.01); //this is to ensure that volume stays the same no matter the song 
	   mediaPlayer.play();
    }

    @FXML
    void rewindSong(ActionEvent event) {// this simply resets the mediaPlayer and visually resets the progress bar
    	bar.setProgress(0);
    	mediaPlayer.seek(Duration.seconds(0));
    }
    
    @FXML
    void musicToHome(ActionEvent event) throws IOException { // this is a function to go home from the Music Player view
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();
    }
    
    @FXML
    void previousSong(ActionEvent event) { // this will check if its the first song and then if it is it will decrement it stop the song and timer then start 
    	if(songNum > 0 ) { // up again with the song before it in the list 
    		songNum--;
    		mediaPlayer.stop();
    		if(isRunning) {
    			cancelTimer();
    		}
    		media = new Media(songList.get(songNum).toURI().toString());
    		mediaPlayer = new MediaPlayer(media);
    		songLabel.setText(songList.get(songNum).getName());
    		playNextSong();
    	}
    	else {
    		songNum = songList.size() - 1; // this starts the list from the reverse end of the list
    		mediaPlayer.stop(); // stops the player
    		if(isRunning) {
    			cancelTimer();// stop the timer
    		}
    		media = new Media(songList.get(songNum).toURI().toString());
    		mediaPlayer = new MediaPlayer(media); // this creates the media and player with the song from the end of the list and would then work its way back to the front
    		songLabel.setText(songList.get(songNum).getName());
    		playNextSong();
    	}
    }

    @FXML
    void skipSong(ActionEvent event) { // this is the skip song function. it checks if it is at the end of the list or not and then increments it and 
    	if(songNum < songList.size() - 1 ) {// restarts it with the next song 
    		songNum++;
    		mediaPlayer.stop();
    		if(isRunning) {
    			cancelTimer();
    		}
    		media = new Media(songList.get(songNum).toURI().toString());
    		mediaPlayer = new MediaPlayer(media);
    		songLabel.setText(songList.get(songNum).getName());
    		playNextSong();
    	}
    	else {
    		songNum = 0; // this will just start the list from the  beginning assuming you reached the end
    		mediaPlayer.stop();
    		if(isRunning) {
    			cancelTimer();
    		}
    		media = new Media(songList.get(songNum).toURI().toString());
    		mediaPlayer = new MediaPlayer(media);// this creates it with the first song so you can continue skipping
    		songLabel.setText(songList.get(songNum).getName());
    		playNextSong();
    	}
    }
    
    public void beginTimer() { // this is the begin timer function that we use to track song progress
    	timer = new Timer();
    	
    	task = new TimerTask() {
    		public void run() { // within we write a anonymous function that will check if it is running then get the current spot in the song and the total 
    			isRunning = true; //length of the song and is constantly checking to see if it has reached the end by dividing until the quotient is 1
    			double current = mediaPlayer.getCurrentTime().toSeconds();
    			double end = media.getDuration().toSeconds();
    			//System.out.println(current/end);
    			bar.setProgress((double)current/end); // this will update the bar so that you see it moving visually
    			if(current/end == 1) {// this is the mathematical check
    				cancelTimer();
    			}
    		}
    	};
    	timer.scheduleAtFixedRate(task, 0, 1000);// this is what runs it every 1000ms or 1 second
    }
    public void cancelTimer() { // sets isRunning to false and stops the timer
    	isRunning=false;
    	timer.cancel();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // this is all important stuff for when we first load the view
		// TODO Auto-generated method stub
		AnchorPane.setLeftAnchor(songLabel, 0.0);
		AnchorPane.setRightAnchor(songLabel, 0.0);// these first three lines are for formatting the song names when they appear on screen
		songLabel.setAlignment(Pos.CENTER);
		songList = new ArrayList<File>(); // this is the arrayList of files that holds all the mp3s that are the songs
		
		directory = new File("src/songs"); // this creates the file path for the songs to run
		files = directory.listFiles(); //this will list all the files into a array of files called files
		
		if(files != null) {
			for(File file : files) { // then as long as the array is not null loop through and add all those files to the arrayList
				songList.add(file);
				System.out.println();
			}
		}
		media = new Media(songList.get(songNum).toURI().toString()); // creates the initial media that we will use to play this is the mp3 file
		mediaPlayer = new MediaPlayer(media);// this is the player for the media and takes in media as a argument
		songLabel.setText(songList.get(songNum).getName());// this will read the name of the mp3 and display it as a label
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() { // this is the volume sliders setup and anonymous change listener
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {  
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
				//we set the volume of the media player equal to the value of the volumeSliders current value. the default is 50
				// once it gets that value it multiplies it by 0.01 in order to make it a more granular change.
			}
		});
	}

}
