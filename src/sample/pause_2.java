package sample;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class pause_2 extends Application {
    Gson gson = new Gson();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final double CANVAS_WIDTH = 1000;
        final double CANVAS_HEIGHT = 610;

        String path = "ui.wav";

        Media m = new Media(new File(path).toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.setAutoPlay(true);
        mp.play();

        final Image titleScreen = new Image( "images/titleScreen.png" ); //title screen image
        final Image playButton = new Image("images/4.png"); //the play button image
        final Image scoreButton = new Image("images/play.png"); //the score button image
        final Image CreateButton = new Image("images/6.png");


        final ImageView flashScreen_node = new ImageView();
        flashScreen_node.setImage(titleScreen); //set the image of the title screen

        Button play_button  = new Button();
        play_button.setOnAction(e -> {
            arcade_2 ga = new arcade_2();
            String save = gson.toJson(ga);
            try {
                Files.write(Paths.get("res/files1.json"), save.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        });
        ImageView play_button_node = new ImageView();

        final Button score_button = new Button();
        score_button.setOnAction(e -> {
            arcade_2 game = new arcade_2();
            try {
                game.start(primaryStage);
            } catch (IOException | URISyntaxException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final ImageView score_button_node = new ImageView();

        final Button create_button = new Button();
        create_button.setOnAction(e -> {
            System.exit(0);
        });
        final ImageView create_button_node = new ImageView();

        final Button exit_button = new Button();
        final ImageView exit_button_node = new ImageView();

        play_button_node.setImage(playButton); //set the image of the play button
        score_button_node.setImage(scoreButton); //set the image of the score button
        create_button_node.setImage(CreateButton); //set the image of the create button

        play_button.setGraphic(play_button_node);
        play_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); //this is to make the button background transparent

        create_button.setGraphic(create_button_node);
        create_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        score_button.setGraphic(score_button_node);
        score_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        /*
         * create the container of those buttons
         */
        final VBox buttonContainer = new VBox(1);
        buttonContainer.setAlignment(Pos.TOP_CENTER);
        Insets buttonContainerPadding = new Insets(180, 1, 1, 1);
        buttonContainer.setPadding(buttonContainerPadding);
        buttonContainer.getChildren().addAll(play_button,score_button, create_button, exit_button);

        primaryStage.setTitle( "Plants vs Zombies" );
        primaryStage.getIcons().add(titleScreen); //stage icon

        StackPane root = new StackPane();

        root.getChildren().addAll(flashScreen_node, buttonContainer); //add the title screen and button container to the stackpane
        Scene theScene = new Scene( root, CANVAS_WIDTH, CANVAS_HEIGHT, Color.CHOCOLATE );
        primaryStage.setScene( theScene );
        primaryStage.show();

    }
}
