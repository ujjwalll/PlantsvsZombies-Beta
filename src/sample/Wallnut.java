package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Wallnut extends Barriers {
    public Wallnut(location location_point,Tile t) {
        super(3000, new Image("res/WallnutHD.png"), new ImageView(new Image("res/WallnutHD.png")), location_point, 50, "steel wallnut",15, new ArrayList<>(), t);
        normal_imageview.setPreserveRatio(true);
        normal_imageview.setFitWidth(70);
        normal_imageview.setFitHeight(80);
        normal_imageview.setX(location_point.getX_coordinate());
        normal_imageview.setY(location_point.getY_coordinate());
    }

    @Override
    public void activate_special_move() {
        //
    }

    @Override
    public void deactivate_special() {
        //
    }
}
