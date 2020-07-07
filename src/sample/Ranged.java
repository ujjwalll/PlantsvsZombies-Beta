package sample;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Ranged extends Attacker_plants {
    protected TranslateTransition t;
    private Projectile projectile;
    private Boolean zombies_in_lane=false;
    private Timeline timeline;
    private Timeline timeline_2;
    private Timeline timeline_proj;
    private Timeline timeline_3;

    public TranslateTransition getTr() {
        return t;
    }

    public Ranged(int health, Image normal_image_stance, ImageView normal_imageview, location location_point, int sun_cost, String special, int cooldown_time_ms, ArrayList<Image> special_animation, Tile t, int damage, int attack_cool_down_time, Timeline timeline, Timeline timeline_2,Timeline timeline_proj, Timeline timeline_3) {
        super(health, normal_image_stance, normal_imageview, location_point, sun_cost, special, cooldown_time_ms, special_animation, t, damage, attack_cool_down_time);
        this.timeline=timeline;
        this.timeline_2=timeline_2;
        this.timeline_3=timeline_3;
        this.timeline_proj=timeline_proj;
    }

    public Timeline getTimeline_proj() {
        return timeline_proj;
    }

    public Timeline getTimeline_3() {
        return timeline_3;
    }

    public void setTimeline_3(Timeline timeline_3) {
        this.timeline_3 = timeline_3;
    }

    public void setTimeline_proj(Timeline timeline_proj) {
        this.timeline_proj = timeline_proj;
    }

    public Timeline getTimeline_1() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Timeline getTimeline_2() {
        return timeline_2;
    }

    public void setTimeline_2(Timeline timeline_2) {
        this.timeline_2 = timeline_2;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }
    public Boolean getZombies_in_lane() {
        return zombies_in_lane;
    }

    public void setZombies_in_lane(Boolean zombies_in_lane) {
        this.zombies_in_lane = zombies_in_lane;
    }
    abstract public void attack();
}
