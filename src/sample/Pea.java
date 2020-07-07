package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pea extends One_dimension{
    public Pea(location loc, double current_X, double current_Y, int damage_of_projectile, double speed_X, double length) {
        super(loc, current_X, current_Y, damage_of_projectile, speed_X, length);
        Image i = new Image("res/Pea_2.png");
        setProjectile_image(i);
        ImageView iv = new ImageView(i);
        iv.setFitWidth(26);
        iv.setFitHeight(26);
        iv.setX(loc.getX_coordinate()+current_X);
        iv.setY(loc.getY_coordinate()+current_Y);
        this.setProjectile_image(i);
        this.setProjectile_image_view(iv);
    }
    @Override
    public void move(){
        getProjectile_image_view().setX(getProjectile_image_view().getX()+getSpeed_X());
        getLoc().setX_coordinate(getLoc().getX_coordinate()+getSpeed_X());
    }
    @Override
    public void reset(){
        getProjectile_image_view().setX(getLoc().getX_coordinate()+getCurrent_X());
        getProjectile_image_view().setY(getLoc().getY_coordinate()+getCurrent_Y());
    }
}
