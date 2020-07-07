package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Attacker_plants extends Plants {
    private int damage;
    private int attack_cool_down_time;

    public Attacker_plants(int health, Image normal_image_stance, ImageView normal_imageview, location location_point, int sun_cost, String special, int cooldown_time_ms, ArrayList<Image> special_animation, Tile t,int damage,int attack_cool_down_time) {
        super(health, normal_image_stance, normal_imageview, location_point, sun_cost, special, cooldown_time_ms, special_animation, t);
        this.damage=damage;
        this.attack_cool_down_time=attack_cool_down_time;
    }


    public void damage_zombie(Zombies z){

    }
}
