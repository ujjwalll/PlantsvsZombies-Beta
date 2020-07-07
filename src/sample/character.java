package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class character {
    private double health;
    protected Image normal_image_stance;
    protected ImageView normal_imageview;
    private String id;
    protected location location_point;

    public character(double health, Image normal_image_stance, ImageView normal_imageview, location location_point) {
        this.health = health;
        this.normal_image_stance = normal_image_stance;
        this.normal_imageview = normal_imageview;
        this.location_point = location_point;
        this.id = normal_imageview.getId();
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Image getNormal_image_stance() {
        return normal_image_stance;
    }

    public void setNormal_image_stance(Image normal_image_stance) {
        this.normal_image_stance = normal_image_stance;
    }

    public ImageView getNormal_imageview() {
        return normal_imageview;
    }

    public void setNormal_imageview(ImageView normal_imageview) {
        this.normal_imageview = normal_imageview;
    }

    public location getLocation_point() {
        return location_point;
    }

    public void setLocation_point(location location_point) {
        this.location_point = location_point;
    }
    public Boolean death(){
        if (getHealth()<=0){
            return true;
        }
        else{
            return false;
        }
    }
}
