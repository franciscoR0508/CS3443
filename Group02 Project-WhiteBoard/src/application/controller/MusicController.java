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
    void pauseSong(ActionEvent event) {
    	cancelTimer();
    	mediaPlayer.pause();
    }

    @FXML
    void playSong(ActionEvent event) {
    	beginTimer();
    	mediaPlayer.play();
    }
    
   void playNextSong(){
	   bar.setProgress(0);
	   beginTimer();
	   mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
	   mediaPlayer.play();
    }

    @FXML
    void rewindSong(ActionEvent event) {
    	bar.setProgress(0);
    	mediaPlayer.seek(Duration.seconds(0));
    }
    
    @FXML
    void musicToHome(ActionEvent event) throws IOException {
    	URL url = new File("src/application/view/Home.fxml").toURI().toURL();
    	aPane = FXMLLoader.load(url);
    	Scene scene = new Scene(aPane);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.setTitle("WhiteBoard - A Planner App");
    	window.show();
    }
    
    @FXML
    void previousSong(ActionEvent event) {
    	if(songNum > 0 ) {
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
    		songNum = songList.size() - 1;
    		mediaPlayer.stop();
    		if(isRunning) {
    			cancelTimer();
    		}
    		media = new Media(songList.get(songNum).toURI().toString());
    		mediaPlayer = new MediaPlayer(media);
    		songLabel.setText(songList.get(songNum).getName());
    		playNextSong();
    	}
    }

    @FXML
    void skipSong(ActionEvent event) {
    	if(songNum < songList.size() - 1 ) {
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
    		songNum = 0;
    		mediaPlayer.stop();
    		if(isRunning) {
    			cancelTimer();
    		}
    		media = new Media(songList.get(songNum).toURI().toString());
    		mediaPlayer = new MediaPlayer(media);
    		songLabel.setText(songList.get(songNum).getName());
    		playNextSong();
    	}
    }
    
    public void beginTimer() {
    	timer = new Timer();
    	
    	task = new TimerTask() {
    		public void run() {
    			isRunning = true;
    			double current = mediaPlayer.getCurrentTime().toSeconds();
    			double end = media.getDuration().toSeconds();
    			//System.out.println(current/end);
    			bar.setProgress((double)current/end);
    			if(current/end == 1) {
    				cancelTimer();
    			}
    		}
    	};
    	timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public void cancelTimer() {
    	isRunning=false;
    	timer.cancel();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		AnchorPane.setLeftAnchor(songLabel, 0.0);
		AnchorPane.setRightAnchor(songLabel, 0.0);
		songLabel.setAlignment(Pos.CENTER);
		songList = new ArrayList<File>();
		
		directory = new File("src/songs");
		files = directory.listFiles();
		
		if(files != null) {
			for(File file : files) {
				songList.add(file);
				System.out.println();
			}
		}
		media = new Media(songList.get(songNum).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		songLabel.setText(songList.get(songNum).getName());
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
				
			}
		});
	}

}
