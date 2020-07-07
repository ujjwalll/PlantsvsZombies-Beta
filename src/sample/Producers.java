package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Producers extends Plants {
    private int cooldown_time_produce;
    private int sun_produced_per_time;
    private Sun sun;

    public Producers(int health, Image normal_image_stance, ImageView normal_imageview, location location_point, int sun_cost, String special, int cooldown_time_ms, ArrayList<Image> special_animation, Tile t, int cooldown_time_produce, int sun_produced_per_time, Sun sun) {
        super(health, normal_image_stance, normal_imageview, location_point, sun_cost, special, cooldown_time_ms, special_animation, t);
        this.cooldown_time_produce = cooldown_time_produce;
        this.sun_produced_per_time = sun_produced_per_time;
        this.sun = sun;
    }

    abstract public ImageView produce();
}
