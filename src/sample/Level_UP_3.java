package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Level_UP_3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        arcade_3 x = new arcade_3();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> {
                    try {
                        x.start(primaryStage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));


        String path = "ui.wav";

        Media m = new Media(new File(path).toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.setAutoPlay(true);
        mp.play();


        final double width = 1000;
        final double length = 610;
        final Image title = new Image("res/Level_IP.png");

        final ImageView titleScreen_node = new ImageView();
        titleScreen_node.setImage(title);
        primaryStage.setTitle("Plants vs Zombies");
        primaryStage.getIcons().add(title);

        Group root = new Group();
        root.getChildren().addAll(titleScreen_node);

        Scene theFront = new Scene(root, width, length, Color.WHITE);
        primaryStage.setScene( theFront );

        primaryStage.show();
        timeline.play();
    }
}