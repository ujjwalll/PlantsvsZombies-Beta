package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class CherryBomb extends Explosives {
    public CherryBomb(location location_point,Tile t) {
        super(1, new Image("res/Cherry_BombHD.png"), new ImageView(new Image("res/Cherry_BombHD.png")), location_point, 150, "none", 8, new ArrayList<>(), t, 150);
        normal_imageview.setPreserveRatio(true);
        normal_imageview.setFitHeight(80);
        normal_imageview.setFitWidth(70);
        normal_imageview.setX(location_point.getX_coordinate());
        normal_imageview.setY(location_point.getY_coordinate());
    }

    @Override
    public Timeline explode() {
        Timeline t = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                })
        );
        return t;
    }

    @Override
    public void damage_zombies(ArrayList<Zombies> z) {
        Zombies a = null;
        for (int i=0;i<z.size();i++){
            try{
                a=z.get(i);
            }catch (Exception e){
                return;
            }
            if (abs(a.getLocation_point().getX_coordinate()-this.location_point.getX_coordinate())<=70 && abs(a.getLocation_point().getY_coordinate()-this.location_point.getY_coordinate())<=70){
                z.get(i).setHealth(z.get(i).getHealth()-this.getDamage());
            }
        }

    }

    @Override
    public void activate_special_move() {

    }

    @Override
    public void deactivate_special() {

    }
}
