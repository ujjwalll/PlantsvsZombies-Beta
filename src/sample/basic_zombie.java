package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class basic_zombie extends Zombies {

//    Timeline timeline = new Timeline(
//            new KeyFrame(Duration.seconds(2), e -> {
//
//
//                // code to execute here...
//            })
//    );

    public basic_zombie(location location_point) {
        super(50, new Image("res/zombie_normal.gif"), new ImageView(new Image("res/zombie_normal.gif")), location_point, "basic", 5, 0.08);
        normal_imageview.setFitWidth(80);
        normal_imageview.setFitHeight(100);
        normal_imageview.setPreserveRatio(true);
        normal_imageview.setX(location_point.getX_coordinate());
        normal_imageview.setY(location_point.getY_coordinate());
    }


    @Override
    public void attack_plant(Plants p) {
        p.setHealth(p.getHealth()-this.getAttack_damage());

    }

}
