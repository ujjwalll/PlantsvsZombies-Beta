package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class Level extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String path = "iot.mp4";

        PLAY2 x = new PLAY2();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(73000),
                ae -> x.start(primaryStage)));


        //Converts media to string URL
        Media media =  new Media(new File(path).toURI().toString());
        javafx.scene.media.MediaPlayer player = new   javafx.scene.media.MediaPlayer(media);
        MediaView viewer = new MediaView(player);

        //change width and height to fit video
        viewer.setFitHeight(610);
        viewer.setFitWidth(1000);
        viewer.setPreserveRatio(true);


        StackPane root = new StackPane();
        root.getChildren().add(viewer);

        //set the Scene
        Scene scenes = new Scene(root, 1000, 610, Color.BLACK);
        primaryStage.setScene(scenes);
        primaryStage.setTitle("Riddle Game");
        primaryStage.show();
        timeline.play();
        player.play();

    }
}
