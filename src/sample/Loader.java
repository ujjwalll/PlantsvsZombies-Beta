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

public class Loader extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

            final double width = 800;
            final double length = 610;
            final Image title = new Image("images/XDD.gif");

            Play x = new Play();

            Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> x.start(primaryStage)));


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
