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

import java.io.IOException;
import java.net.URISyntaxException;

public class Level_Select extends Application {

    public GridPane  gp = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final double CANVAS_WIDTH = 1000;
        final double CANVAS_HEIGHT = 610;

        final Image titleScreen = new Image( "res/yutt.png" ); //title screen image
        final Image _1x = new Image("res/level_1.png"); //the play button image
        final Image _2x = new Image("res/level_2.png"); //the level button image
        final Image _3x = new Image("res/level_3.jpg");
        final Image _4x = new Image("res/level_4.png");
        final Image _5x = new Image("res/level_5.png");
        final Image _6x = new Image("res/bm.png");

        gp.setLayoutX(289.0);
        gp.setLayoutY(110.0);
        gp.setMinHeight(446.0);
        gp.setMinWidth(628.0);


        final ImageView flashScreen_node = new ImageView();
        flashScreen_node.setImage(titleScreen); //set the image of the title screen

        Button _1 = new Button();
        _1.setOnAction(e -> {
            arcade game = new arcade();
            try {
                game.start(primaryStage);
                if (game.getGame_won()){
                    LEVEL_UP_2 lv2 =new LEVEL_UP_2();
                           lv2.start(primaryStage);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        ImageView one_button_node = new ImageView();

        final Button _2 = new Button();
        _2.setOnAction(e -> {
            arcade_2 game = new arcade_2();
            try {
                game.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final ImageView two_button_node = new ImageView();

        final Button _3 = new Button();
        _3.setOnAction(e -> {
            arcade_3 game = new arcade_3();
            try {
                game.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final ImageView three_button_node = new ImageView();

        final Button _4 = new Button();
        _4.setOnAction(e -> {
            arcade_4 game = new arcade_4();
            try {
                game.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final ImageView four_button_node = new ImageView();

        final Button _5 = new Button();
        _5.setOnAction(e -> {
            arcade_5 game = new arcade_5();
            try {
                game.start(primaryStage);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        final ImageView five_button_node = new ImageView();

        final Button _6 = new Button();
        _6.setOnAction(e -> {
            PLAY2 game = new PLAY2();
            game.start(primaryStage);
        });
        final ImageView six_button_node = new ImageView();

        one_button_node.setImage(_1x); //set the image of the play button
        two_button_node.setImage(_2x); //set the image of the level button
        three_button_node.setImage(_3x); //set the image of the load button
        four_button_node.setImage(_4x); //set image for the exit button
        five_button_node.setImage(_5x); //set image for the exit button
        six_button_node.setImage(_6x); //set image for the exit button

        gp.add(_1, 0,0,1,1);
        gp.add(_2, 1,0,1,1);
        gp.add(_3, 2,0,1,1);
        gp.add(_4, 0,1,1,1);
        gp.add(_5, 1,1,1,1);
        gp.add(_6, 2,1,1,1);

        _1.setGraphic(one_button_node);
        _1.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); //this is to make the button background transparent

        _2.setGraphic(two_button_node);
        _2.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        _3.setGraphic(three_button_node);
        _3.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        _4.setGraphic(four_button_node);
        _4.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        _5.setGraphic(five_button_node);
        _5.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        _6.setGraphic(six_button_node);
        _6.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setTitle( "Plants vs Zombies" );
        primaryStage.getIcons().add(titleScreen); //stage icon

        StackPane root = new StackPane();

        gp.setAlignment(Pos.CENTER);

        root.getChildren().addAll(flashScreen_node, gp); //add the title screen and button container to the stackpane
        Scene theScene = new Scene( root, CANVAS_WIDTH, CANVAS_HEIGHT, Color.CHOCOLATE );
        primaryStage.setScene( theScene );
        primaryStage.show();

    }
}
