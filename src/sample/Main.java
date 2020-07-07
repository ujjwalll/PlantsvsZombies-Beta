package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


public class Main extends Application {

    @Override
        public void start(Stage primaryStage) throws Exception{


            Loader x = new Loader();
            Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> x.start(primaryStage)));


        String path = "ui.wav";

        Media m = new Media(new File(path).toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.setAutoPlay(true);
        mp.play();


            final double width = 1000;
            final double length = 610;
            final Image title = new Image("images/Pvz_logo.png");

            final ImageView titleScreen_node = new ImageView();
            titleScreen_node.setImage(title);
            primaryStage.setTitle("Plants vs Zombies");
            primaryStage.getIcons().add(title);

            Group root = new Group();
            root.getChildren().addAll(titleScreen_node);

            Scene theFront = new Scene(root, width, length, Color.GREEN);
            primaryStage.setScene( theFront );

            primaryStage.show();
            timeline.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
