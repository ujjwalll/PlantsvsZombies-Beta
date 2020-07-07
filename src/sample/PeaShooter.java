package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class PeaShooter extends Ranged {
    Projectile pea;

    public PeaShooter(location loc,Tile t) {
        super(100,new Image("res/PeaShooter_Idle1.gif"),new ImageView(new Image("res/PeaShooter_Idle1.gif")),loc,100,"machine_gun",5000,new ArrayList<>(),t,10,5,new Timeline(),new Timeline(),new Timeline(),new Timeline());
        Timeline timeline_proj = new Timeline(
                new KeyFrame(Duration.millis(50), e -> {
                    pea.getProjectile_image_view().setVisible(true);
                    pea.move();

                    // code to execute here...
                })
        );
        Timeline timeline_1 = new Timeline(
                new KeyFrame(Duration.millis(5000), e -> {
                    System.out.println("marne ke liye ghusa");
                    Image suno =new Image("res/PeaShooter_Spit.gif");
                    normal_imageview.setImage(suno);
                    timeline_proj.play();
                    getTimeline_2().playFrom(Duration.millis(4370));
                })
        );

        Timeline timeline_2 = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                   System.out.println("ghusa");
                   Image suno =new Image("res/PeaShooter_Idle1.gif");
                   normal_imageview.setImage(suno);

//                    // code to execute here...
                })
       );
//        Timeline timeline_3 = new Timeline(
//                new KeyFrame(Duration.seconds(5), e -> {
//                    System.out.println("ghusa");
//                    Image suno =new Image("res/PeaShooter_Idle1.gif");
//                    normal_imageview.setImage(suno);
//
//                    // code to execute here...
//                })
//        );


        timeline_proj.setDelay(Duration.millis(630));
//        timeline_3.setDelay(Duration.millis(0));
//        timeline_2.setDelay(Duration.millis(0));
        timeline_proj.setCycleCount(Animation.INDEFINITE);
        normal_imageview.setFitHeight(72);
        normal_imageview.setFitWidth(69);
        normal_imageview.setX(loc.getX_coordinate());
        normal_imageview.setY(loc.getY_coordinate());
        normal_imageview.setPickOnBounds(true);
        location loc_proj = new location(loc.getX_coordinate(),loc.getY_coordinate());
        pea = new Pea(loc_proj,48,14,10,5,100);
        pea.getProjectile_image_view().setVisible(false);
        this.setProjectile(pea);
        timeline_1.setCycleCount(1);
        timeline_2.setCycleCount(1);
        //timeline_1.setDelay();
        //timeline_1.setDelay();
        this.setTimeline(timeline_1);
        this.setTimeline_2(timeline_2);
        this.setTimeline_proj(timeline_proj);
//        this.setTimeline_3(timeline_3);
//        getTimeline_2().playFrom(Duration.millis(4000));


    }
    @Override
    public void attack() {
        System.out.println("attack hua");
        t= new TranslateTransition(Duration.seconds(7));
        t.setToX(1000-location_point.getX_coordinate());
        t.setCycleCount(1);
        t.setNode(pea.getProjectile_image_view());
        t.playFromStart();
    }

    public Projectile getPea() {
        return pea;
    }

    public void setPea(Projectile pea) {
        this.pea = pea;
    }



    @Override
    public void activate_special_move() {
        //
    }

    @Override
    public void deactivate_special() {
        //
    }
}
