package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;

public class entering extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        final double width = 1000;
        final double length = 610;
        final Image title = new Image("images/yodel.gif");

        arcade x = new arcade();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> {
                    try {
                        x.start(primaryStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));


        final ImageView titleScreen_node = new ImageView();
        titleScreen_node.setImage(title);
        primaryStage.setTitle("Plants vs Zombies");
        primaryStage.getIcons().add(title);

        Group root = new Group();
        root.getChildren().addAll(titleScreen_node);

        Scene theFront = new Scene(root, width, length, Color.BLACK);
        primaryStage.setScene( theFront );
        timeline.play();
        primaryStage.show();
    }
}