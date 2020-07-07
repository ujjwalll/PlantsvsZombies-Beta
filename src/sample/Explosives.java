package sample;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Explosives extends Plants {
    private int damage;

    public Explosives(int health, Image normal_image_stance, ImageView normal_imageview, location location_point, int sun_cost, String special, int cooldown_time_ms, ArrayList<Image> special_animation, Tile t, int damage) {
        super(health, normal_image_stance, normal_imageview, location_point, sun_cost, special, cooldown_time_ms, special_animation, t);
        this.damage=damage;
    }

    public int getDamage() {
        return damage;
    }

    abstract public Timeline explode();
    abstract public void damage_zombies(ArrayList<Zombies> z);

}
