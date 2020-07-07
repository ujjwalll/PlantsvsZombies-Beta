package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Plants extends character {
    private int sun_cost;
    private int removal_cost;
    private String special;
    private int cooldown_time_ms;
    private ArrayList<Image> special_animation;
    private Tile t;
    public void Plant(int x, int y){

    }
    public void destroy_plant(){

    }
    abstract public void activate_special_move();
    abstract public void deactivate_special();

    public Plants(double health, Image normal_image_stance, ImageView normal_imageview, location location_point, int sun_cost, String special, int cooldown_time_ms, ArrayList<Image> special_animation, Tile t) {
        super(health,normal_image_stance,normal_imageview, location_point);
        this.t=t;
        this.sun_cost = sun_cost;
        this.special = special;
        this.cooldown_time_ms = cooldown_time_ms;
        this.special_animation = special_animation;
    }

    public int getSun_cost() {
        return sun_cost;
    }

    public void setSun_cost(int sun_cost) {
        this.sun_cost = sun_cost;
    }

    public int getRemoval_cost() {
        return removal_cost;
    }

    public void setRemoval_cost(int removal_cost) {
        this.removal_cost = removal_cost;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public int getCooldown_time_ms() {
        return cooldown_time_ms;
    }

    public void setCooldown_time_ms(int cooldown_time_ms) {
        this.cooldown_time_ms = cooldown_time_ms;
    }

    public ArrayList<Image> getSpecial_animation() {
        return special_animation;
    }

    public void setSpecial_animation(ArrayList<Image> special_animation) {
        this.special_animation = special_animation;
    }


    public Tile getT() {
        return t;
    }

    public void setT(Tile t) {
        this.t = t;
    }

}
