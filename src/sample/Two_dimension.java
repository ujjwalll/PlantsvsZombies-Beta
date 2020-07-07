package sample;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public abstract class Two_dimension extends Projectile {
    private double length;

    public Two_dimension(location loc, Double current_X, Double current_Y, int damage_of_projectile, double speed_X, double length) {
        super(loc, current_X, current_Y, damage_of_projectile, speed_X);
        this.length = length;
    }
    @Override
    public void traverse(){
        t = new TranslateTransition();
        t.setToX(length);
        t.setDuration(Duration.millis(length/getSpeed_X()));
        t.interpolatorProperty().set(Interpolator.SPLINE(2,3,-1,0));
    }
}
