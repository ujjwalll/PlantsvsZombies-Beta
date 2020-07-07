package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Barriers extends Plants {


    public Barriers(int health, Image normal_image_stance, ImageView normal_imageview, location location_point, int sun_cost, String special, int cooldown_time_ms, ArrayList<Image> special_animation, Tile t) {
        super(health, normal_image_stance, normal_imageview, location_point, sun_cost, special, cooldown_time_ms, special_animation, t);
    }
}
