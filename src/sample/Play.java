package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Play extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final double CANVAS_WIDTH = 1000;
        final double CANVAS_HEIGHT = 610;

        final Image titleScreen = new Image( "images/titleScreen.png" ); //title screen image
        final Image playButton = new Image("images/play.png"); //the play button image
        //final Image scoreButton = new Image("images/HS.png"); //the score button image

        final ImageView flashScreen_node = new ImageView();
        flashScreen_node.setImage(titleScreen); //set the image of the title screen

        Button play_button  = new Button();

        play_button.setOnAction(e -> {
            Level game = new Level();
            game.start(primaryStage);
        });

        ImageView play_button_node = new ImageView();

        final Button score_button = new Button();
        final ImageView score_button_node = new ImageView();

        play_button_node.setImage(playButton); //set the image of the play button
        //score_button_node.setImage(scoreButton); //set the image of the score button

        play_button.setGraphic(play_button_node);
        play_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); //this is to make the button background transparent

        score_button.setGraphic(score_button_node);
        score_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        /*
         * create the container of those buttons
         */
        final VBox buttonContainer = new VBox(1);
        buttonContainer.setAlignment(Pos.TOP_CENTER);
        Insets buttonContainerPadding = new Insets(220, 1, 1, 1);
        buttonContainer.setPadding(buttonContainerPadding);
        buttonContainer.getChildren().addAll(play_button,score_button);

        primaryStage.setTitle( "Plants vs Zombies" );
        primaryStage.getIcons().add(titleScreen); //stage icon

        StackPane root = new StackPane();

        root.getChildren().addAll(flashScreen_node, buttonContainer); //add the title screen and button container to the stackpane
        Scene theScene = new Scene( root, CANVAS_WIDTH, CANVAS_HEIGHT, Color.CHOCOLATE );
        primaryStage.setScene( theScene );
        primaryStage.show();


    }
}
