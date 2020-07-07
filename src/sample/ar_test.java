//package sample;
//
//import javafx.animation.Animation;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.concurrent.atomic.AtomicReference;
//
//public class ar_test extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    private Group root = new Group();
//    private ArrayList<Projectile> pea_proj= new ArrayList<>();
//    private Zombies z;
//
//    @Override
//    public void start(Stage primaryStage) throws IOException , URISyntaxException {
//        Parent p = FXMLLoader.load(getClass().getResource("play_screen.fxml"));
//        primaryStage.setTitle("Plants vs Zombies");
//        Image pea_shooter_idle = new Image("res/PeaShooter_Idle1.gif");
//        location loc1= new location(290,300);
//        Tile t2= new Tile(loc1);
//        Ranged Pea_shooter = new PeaShooter(loc1,t2);
//        location loc2 = new location(850,250);
//        z= new basic_zombie(loc2);
//        Collider c= new Collider();
//        AtomicReference<Boolean> stop = new AtomicReference<>(false);
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.millis(50), e -> {
//                    if (z!=null) {
//                        if (c.checkCollision(Pea_shooter, z)) {
//                            c.On_Collision(Pea_shooter, z);
//                            if (!z.getState()) {
//                                z.setState(true);
//                                z.set_eat();
//                                stop.set(false);
//                            } else {
//                                Boolean r = c.On_Collision(Pea_shooter, z);
//                                if (r) {
//                                    root.getChildren().remove(Pea_shooter.getNormal_imageview());
//                                }
//                            }
//                        } else {
//                            z.move();
//                            if (z.getState()) {
//                                z.setState(false);
//                                z.set_move();
//                            }
//                        }
//                        if (Pea_shooter instanceof Ranged) {
//                            if (c.checkCollision(Pea_shooter, z, 1100)) {
//                                System.out.println("same row");
//                                if (!Pea_shooter.getZombies_in_lane()) {
//                                    System.out.println("marna activated");
//                                    pea_proj.add(Pea_shooter.getProjectile());
//                                    root.getChildren().addAll(pea_proj.get(pea_proj.size() - 1).getProjectile_image_view());
//                                    Pea_shooter.setZombies_in_lane(true);
//                                    Pea_shooter.getTimeline_1().playFrom(Duration.millis(4000));
//                                    stop.set(false);
//                                } else {
//
//                                }
//                            } else {
//                                if (Pea_shooter.getZombies_in_lane()) {
//                                    Pea_shooter.setZombies_in_lane(false);
//                                    stop.set(true);
//                                }
//                            }
//                        } else {
//                            System.out.println("kyun nhi ghusa");
//                        }
//                        if (pea_proj.size() != 0) {
//                            if (c.checkCollision(pea_proj.get(0), z)) {
//                                Boolean b = c.On_Collision(pea_proj.get(0), z);
//
//                                Pea_shooter.getTimeline_proj().stop();
//                                root.getChildren().remove(pea_proj.get(0).getProjectile_image_view());
//                                pea_proj.remove(0);
//                                Pea_shooter.getProjectile().setLoc(new location(Pea_shooter.getLocation_point().getX_coordinate(), Pea_shooter.getLocation_point().getY_coordinate()));
//                                Pea_shooter.getProjectile().reset();
//                                Pea_shooter.getProjectile().getProjectile_image_view().setVisible(false);
//                                if (!b) {
//                                    Pea_shooter.setZombies_in_lane(false);
//
//                                } else {
//                                    root.getChildren().remove(z.getNormal_imageview());
//                                    stop.set(true);
//                                }
//                            }
//                        }
//                        if (stop.get()) {
//                            Pea_shooter.getTimeline_1().pause();
//                            z=null;
//                            Pea_shooter.getNormal_imageview().setImage(new Image("res/PeaShooter_Idle1.gif"));
//                        }
//                    }
//                })
//        );
//
//        root.getChildren().addAll(p,Pea_shooter.getNormal_imageview(),z.getNormal_imageview());
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
//
//
//
//
//
//    }
//}
