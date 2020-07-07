package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sun {
    private int sun_value;
    private Image sun_image;
    private ImageView sun_image_view;
    private location coor;
    private final Boolean set_animation;
    private TranslateTransition sun_animation;
    public Sun(int value , location loc, Boolean set_animation){
        this.sun_value = value;
        this.set_animation = set_animation;
        this.coor = loc;
        sun_image = new Image("res/Sun_PvZ2.png");
        sun_image_view= new ImageView();
        sun_image_view.setImage(sun_image);
        if (sun_value==25){
            sun_image_view.setFitHeight(40);
            sun_image_view.setFitWidth(40);
        }
        else if (sun_value ==50){
            sun_image_view.setFitHeight(60);
            sun_image_view.setFitWidth(60);
        }
        else if (sun_value == 75){
            sun_image_view.setFitHeight(80);
            sun_image_view.setFitWidth(80);
        }
        sun_image_view.setX((Double) loc.getX_coordinate());
        sun_image_view.setY((Double) loc.getY_coordinate());
        sun_image_view.setPreserveRatio(true);
        sun_image_view.setOnMouseClicked(MouseEvent->{
            sun_animation.stop();
            //update scoreboard
            //delete this sun
        });
        if (set_animation){
            set_animation_for_sun();
        }
    }
    public void set_animation_for_sun(){
        sun_animation = new TranslateTransition();
        sun_animation.setNode(sun_image_view);
        sun_animation.setDuration(Duration.millis(8000));
        sun_animation.setCycleCount(1);
        sun_animation.setFromX((Double) coor.getX_coordinate());
        sun_animation.setFromY((Double) coor.getY_coordinate());
        sun_animation.setToY((Double) coor.getY_coordinate()+400);
        sun_animation.play();
    }

}
