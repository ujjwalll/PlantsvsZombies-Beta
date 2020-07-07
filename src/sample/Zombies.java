package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Zombies extends character{
    Image eating_zombie =new Image("res/Dance.gif");
    private double attack_damage;
    private ArrayList<Image> zombie_attack;
    private String type;
    private double speed;
    private Boolean state;
    public Zombies(double health, Image normal_image_stance, ImageView normal_imageview, location location_point,String type,double attack_damage,double speed) {
        super(health, normal_image_stance, normal_imageview, location_point);
        this.attack_damage=attack_damage;
        this.speed=speed;
        this.type=type;
        this.state=false;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public double getAttack_damage() {
        return attack_damage;
    }

    public void setAttack_damage(double attack_damage) {
        this.attack_damage = attack_damage;
    }

    public ArrayList<Image> getZombie_attack() {
        return zombie_attack;
    }

    public void setZombie_attack(ArrayList<Image> zombie_attack) {
        this.zombie_attack = zombie_attack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    abstract public void attack_plant(Plants p);
    public void set_move(){
        normal_imageview.setImage(normal_image_stance);
    }
    public void move(){
        normal_imageview.setX(normal_imageview.getX()-speed);
        location_point.setX_coordinate(location_point.getX_coordinate()-speed);
    }
    public void set_eat(){
        normal_imageview.setImage(eating_zombie);
    }
}
