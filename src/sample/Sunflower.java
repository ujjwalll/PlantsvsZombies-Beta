package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Sunflower extends Producers {
    public Sunflower(location location_point,Tile t) {
        super(100, new Image("res/Sunflower_Idle.gif"), new ImageView(new Image("res/Sunflower_Idle.gif")), location_point, 50, "many sun", 10, new ArrayList<>(), t, 10, 50, new Sun(50,new location(location_point.getX_coordinate(),location_point.getY_coordinate()),false));
        normal_imageview.setFitHeight(80);
        normal_imageview.setFitWidth(70);
        normal_imageview.setX(location_point.getX_coordinate());
        normal_imageview.setY(location_point.getY_coordinate());
        normal_imageview.setPreserveRatio(true);
    }

    @Override
    public ImageView produce() {
        Image sun_image;
        ImageView sun_view;
        sun_image =new Image("res/Sun_PvZ2.png");
        sun_view = new ImageView();
        sun_view.setImage(sun_image);
        sun_view.setX(location_point.getX_coordinate());
        sun_view.setY(location_point.getY_coordinate());
        sun_view.setFitWidth(60);
        sun_view.setFitWidth(60);
        sun_view.setPreserveRatio(true);
        return sun_view;
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

