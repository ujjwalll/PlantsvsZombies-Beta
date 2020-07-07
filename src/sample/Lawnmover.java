package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Lawnmover {
    private final Image lawn_mover_image = new Image("res/lawnmower.png");
    private ImageView lawn_mover_iv;
    private location loc;
    private Boolean isactivated;

    public Lawnmover(location loc) {
        this.loc = loc;
        this.isactivated = false;
    }

    public ImageView getLawn_mover_iv() {
        return lawn_mover_iv;
    }

    public void setLawn_mover_iv(ImageView lawn_mover_iv) {
        this.lawn_mover_iv = lawn_mover_iv;
    }

    public location getLoc() {
        return loc;
    }

    public void setLoc(location loc) {
        this.loc = loc;
    }

    public Boolean getIsactivated() {
        return isactivated;
    }

    public void setIsactivated(Boolean isactivated) {
        this.isactivated = isactivated;
    }
}
