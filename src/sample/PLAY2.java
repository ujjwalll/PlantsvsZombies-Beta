package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PLAY2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static StackPane guiStage;

    public Pane getStage() {
        return guiStage;
    }

    @Override
    public void start(Stage primaryStage) {

        final double CANVAS_WIDTH = 1000;
        final double CANVAS_HEIGHT = 610;

        final Image titleScreen = new Image( "res/PVZX2.png" ); //title screen image
        final Image playButton = new Image("images/play.png"); //the play button image
        final Image levelButton = new Image("res/levels.png"); //the level button image
        final Image loadButton = new Image("res/load.png");
        final Image ExitButton = new Image("res/exit.png");


        final ImageView flashScreen_node = new ImageView();
        flashScreen_node.setImage(titleScreen); //set the image of the title screen

        Button play_button  = new Button();

        play_button.setOnAction(e -> {
            entering game = new entering();
            game.start(primaryStage);
        });

        ImageView play_button_node = new ImageView();

        final Button level_button = new Button();
        level_button.setOnAction(e -> {
            Level_Select game = new Level_Select();
            game.start(primaryStage);
        });
        final ImageView level_button_node = new ImageView();

        final Button load_button = new Button();
        final ImageView load_button_node = new ImageView();

        final Button exit_button = new Button();
        final ImageView exit_button_node = new ImageView();

        play_button_node.setImage(playButton); //set the image of the play button
        level_button_node.setImage(levelButton); //set the image of the level button
        load_button_node.setImage(loadButton); //set the image of the load button
        exit_button_node.setImage(ExitButton); //set image for the exit button

        play_button.setGraphic(play_button_node);
        play_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); //this is to make the button background transparent

        exit_button.setGraphic(exit_button_node);
        exit_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        load_button.setGraphic(load_button_node);
        load_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        level_button.setGraphic(level_button_node);
        level_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        /*
         * load the container of those buttons
         */
        final VBox buttonContainer = new VBox(1);
        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT);
        Insets buttonContainerPadding = new Insets(180, 1, 1, 1);
        buttonContainer.setPadding(buttonContainerPadding);
        buttonContainer.getChildren().addAll(play_button,level_button, load_button, exit_button);

        primaryStage.setTitle( "Plants vs Zombies" );
        primaryStage.getIcons().add(titleScreen); //stage icon

        StackPane root = new StackPane();

        root.getChildren().addAll(flashScreen_node, buttonContainer); //add the title screen and button container to the stackpane
        Scene theScene = new Scene( root, CANVAS_WIDTH, CANVAS_HEIGHT, Color.CHOCOLATE );
        primaryStage.setScene( theScene );
        primaryStage.show();
        guiStage = root;



    }
}
