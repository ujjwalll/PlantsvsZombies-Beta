package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

abstract public class Projectile {
    private Image projectile_image;
    private ImageView projectile_image_view;
    private location loc;
    private Double current_X;
    private Double current_Y;
    private int damage_of_projectile;
    private Double speed_X;
    protected TranslateTransition t;
    private Ranged plant;
    public Projectile(location loc, Double current_X, Double current_Y, int damage_of_projectile, Double speed_X) {
        this.loc = loc;
        this.current_X = current_X;
        this.current_Y = current_Y;
        this.damage_of_projectile = damage_of_projectile;
        this.speed_X = speed_X;
    }

    public Ranged getPlant() {
        return plant;
    }

    public void setPlant(Ranged plant) {
        this.plant = plant;
    }

    public Image getProjectile_image() {
        return projectile_image;
    }

    public void setProjectile_image(Image projectile_image) {
        this.projectile_image = projectile_image;
    }

    public ImageView getProjectile_image_view() {
        return projectile_image_view;
    }

    public void setProjectile_image_view(ImageView projectile_image_view) {
        this.projectile_image_view = projectile_image_view;
    }

    public void setLoc(location loc) {
        this.loc = loc;
    }

    public void setCurrent_X(Double current_X) {
        this.current_X = current_X;
    }

    public void setCurrent_Y(Double current_Y) {
        this.current_Y = current_Y;
    }

    public void setDamage_of_projectile(int damage_of_projectile) {
        this.damage_of_projectile = damage_of_projectile;
    }

    public void setSpeed_X(Double speed_X) {
        this.speed_X = speed_X;
    }

    public location getLoc() {
        return loc;
    }

    public Double getCurrent_X() {
        return current_X;
    }

    public Double getCurrent_Y() {
        return current_Y;
    }

    public int getDamage_of_projectile() {
        return damage_of_projectile;
    }

    public double getSpeed_X() {
        return speed_X;
    }
    abstract public void traverse();
    public void doDamage(Zombies z){
        z.setHealth(z.getHealth()-damage_of_projectile);
    }
    abstract public void move();
    abstract public void reset();
    public TranslateTransition trav(){
        TranslateTransition t= new TranslateTransition();
        t.setNode(getProjectile_image_view());
        t.setToX(1300);
        t.setDuration(Duration.millis(5000));
        t.setCycleCount(1);
        return t;
    }
}
