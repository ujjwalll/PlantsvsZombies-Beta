package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import  javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class arcade_2 extends Application {

    private TranslateTransition pe = new TranslateTransition();
    private TranslateTransition t = new TranslateTransition();
    private Group root = new Group();
    play_screen_controller f = new play_screen_controller();
    int sun_power = 0;
    TextField tf = new TextField("0");
    Button sf = new Button("Score");
    ProgressBar pb = new ProgressBar();
    double progress = 0;
    public GridPane gp = new GridPane();
    String hope;
    private static volatile ArrayList<Projectile> pea_proj = new ArrayList<>();
    private static volatile ArrayList<Zombies> z;
    private static volatile ArrayList<Ranged> Pea_shooter;
    private static volatile ArrayList<Sunflower> sunflowers = new ArrayList<>();
    private static volatile ArrayList<Plants> rest_plants = new ArrayList<>();
    private static volatile ArrayList<ImageView> pea = new ArrayList<>();
    private static volatile ArrayList<Integer> arr = new ArrayList<>();
    private static volatile ArrayList<ImageView> lawnmovers = new ArrayList<>();
    long start_time_of_peashooter = System.currentTimeMillis();
    long end_time_of_peashooter = System.currentTimeMillis();
    long start_time_of_wallnut = System.currentTimeMillis();
    long end_time_of_wallnut = System.currentTimeMillis();
    long start_time_of_sunflower = System.currentTimeMillis();
    long end_time_of_sunflower = System.currentTimeMillis();
    long start_time_of_cherrybomb = System.currentTimeMillis();
    long end_time_of_cherrybomb = System.currentTimeMillis();
    final Boolean pea_availability =true;
    final Boolean sun_flower_availability =true;
    final Boolean wallnut_availability =false;
    final Boolean cherry_bomb_availability =false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, URISyntaxException, InterruptedException {

        String path = "ui.wav";

        Media m = new Media(new File(path).toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mp.setAutoPlay(true);
        mp.play();

        Random rand = new Random();
        Parent p = FXMLLoader.load(getClass().getResource("play_screen.fxml"));
        primaryStage.setTitle("Plants vs Zombies");
        Button pause_button = new Button("Pause");
        pause_button.setOnAction(e -> {
            pause game = new pause();
            game.start(primaryStage);
        });

        Timeline timeline_sun_fall = new Timeline(
                new KeyFrame(Duration.seconds(10), e -> {
                    System.out.println("ghusa");
                    ImageView s = sun_maker(rand.nextInt(500) + 200, 0);
                    TranslateTransition sun_fall = new TranslateTransition();
                    root.getChildren().add(s);
                    sun_fall.setNode(s);
                    sun_fall.setDuration(Duration.seconds(8));
                    sun_fall.setToY(400);
                    sun_fall.play();
                    // code to execute here...
                })
        );
        arr.add(0);
        arr.add(0);
        arr.add(0);
        arr.add(0);
        arr.add(0);
        primaryStage.setTitle("Plants vs Zombies");
        location loc1 = new location(290, 280);
        location loc2 = new location(290, 380);
        location loc3 = new location(290, 480);
        location loc4 = new location(290, 180);
        location loc5 = new location(290, 80);
        Tile t2 = new Tile(loc1);
        root.getChildren().add(p);
        Pea_shooter = new ArrayList<>();
        for (int i=0;i<5;i++){
            ImageView lv= new ImageView(new Image("res/lawnmower.png"));
            lv.setX(210);
            lv.setY(110+(i*90));
            lv.setFitWidth(104);
            lv.setFitHeight(65);
            lv.setPreserveRatio(true);
            root.getChildren().add(lv);

        }
//        Pea_shooter.add(new PeaShooter(loc1, new Tile(loc1)));
//        Pea_shooter.add(new PeaShooter(loc2, new Tile(loc2)));
//        Pea_shooter.add(new PeaShooter(loc3, new Tile(loc3)));
//        Pea_shooter.add(new PeaShooter(loc4, new Tile(loc4)));
//        Pea_shooter.add(new PeaShooter(loc5, new Tile(loc5)));

        location locz1 = new location(900, 280);
        location locz2 = new location(900, 150);
        location locz3 = new location(900, 330);
        location locz4 = new location(900, 420);
        location locz5 = new location(900, 60);
        z = new ArrayList<>();
        z.add(new basic_zombie(locz1));
//        z.add(new basic_zombie(locz2));
//        z.add(new basic_zombie(locz3));
//        z.add(new basic_zombie(locz4));
//        z.add(new basic_zombie(locz5));
        Collider c = new Collider();


        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int i = 0;
                    Ranged a = null;
                    Zombies b = null;
                    while (i < Pea_shooter.size()) {
                        int j = 0;
                        while (j < z.size()) {
                            final int ind = i;
                            final int jnd = j;
                            try {
                                a = Pea_shooter.get(i);
                                b = z.get(j);
                            } catch (Exception e) {
                                return;
                            }
                            if (b.getLocation_point().getX_coordinate()<180){
                                System.out.println("game lost");
                            }
                            if (c.checkCollision(a, b)) {

                                Zombies finalB = b;
                                Ranged finalA = a;
                                Platform.runLater(() -> {
                                    c.On_Collision(finalA, finalB);
                                    if (!finalB.getState()) {
                                        finalB.setState(true);
                                        finalB.set_eat();
                                    }
                                });


                            } else {
                                Zombies finalB1 = b;
                                Platform.runLater(() -> {
                                    finalB1.move();
                                    if (finalB1.getState()) {
                                        finalB1.setState(false);
                                        finalB1.set_move();
                                    }
                                });
                            }
                            j = j + 1;
                        }
                        i = i + 1;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Ranged a = null;
                    int i = 0;
                    System.out.println("r2 chalta hain");
                    while (i < Pea_shooter.size()) {
                        try {
                            a = Pea_shooter.get(i);
                        } catch (Exception e) {
                            return;
                        }
                        if (a.death()) {
                            final int j = i;
                            System.out.println(i + " plant gya");
                            ImageView io = a.getNormal_imageview();
                            Platform.runLater(() -> {
                                root.getChildren().remove(io);

                            });
                            Pea_shooter.remove(i);
                            i = i - 1;
                        }
                        i = i + 1;
                    }
                    int k = 0;
                    Zombies b = null;
                    while (k < z.size()) {
                        try {
                            b = z.get(k);
                        } catch (Exception e) {
                            return;
                        }
                        if (b.death()) {
                            final int j = k;
                            System.out.println(z.size());
                            ImageView io = b.getNormal_imageview();
                            Platform.runLater(() -> {
                                root.getChildren().remove(io);

                            });
                            z.remove(k);
                            k = k - 1;
                        }
                        k = k + 1;
                    }
                    i=0;
                    Plants c =null;
                    while (i < rest_plants.size()) {
                        try {
                            c = rest_plants.get(i);
                        } catch (Exception e) {
                            return;
                        }
                        if (c.death()) {
                            final int j = i;
                            System.out.println(i + " plant gya");
                            ImageView io = c.getNormal_imageview();
                            Platform.runLater(() -> {
                                root.getChildren().remove(io);

                            });
                            rest_plants.remove(i);
                            i = i - 1;
                        }
                        i = i + 1;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k = 0;
                    Plants d = null;
                    while (k < sunflowers.size()) {
                        try {
                            d = sunflowers.get(k);
                        } catch (Exception e) {
                            return;
                        }
                        if (d.death()) {
                            final int j = k;
                            System.out.println(sunflowers.size());
                            ImageView io = d.getNormal_imageview();
                            Platform.runLater(() -> {
                                root.getChildren().remove(io);

                            });
                            sunflowers.remove(k);
                            k = k - 1;
                        }
                        k = k + 1;
                    }
                }
            }
        };
        Timeline t3 = new Timeline(
                new KeyFrame(Duration.millis(10000), e -> {
                    System.out.println("t3 chalta hain");
                    for (int i = 0; i < sunflowers.size(); i++) {
                        ImageView sun = sunflowers.get(i).produce();
                        sun.setOnMouseClicked(mouseEvent -> {
                            sun.setVisible(false);
                            sun_power = sun_power + 50;
                            String d = Integer.toString(sun_power);
                            tf.setText(d);
                        });
                        root.getChildren().add(sun);
                    }
                })
        );
        Timeline main_zombie = new Timeline(
                new KeyFrame(Duration.seconds(12), e -> {
                    location locz = new location(900,rand.nextInt(5)*90+100);
                    Tile tile= new Tile(locz) ;
                    Zombies zu =new basic_zombie(locz);
                    root.getChildren().add(zu.getNormal_imageview());
                    z.add(zu);

                })
        );

        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int i = 0;
                    Plants a = null;
                    Zombies b = null;
                    System.out.println("r3 chalta hain");
                    while (i < rest_plants.size()) {
                        int j = 0;
                        while (j < z.size()) {
                            final int ind = i;
                            final int jnd = j;
                            try {
                                a = rest_plants.get(i);
                                b = z.get(j);
                            } catch (Exception e) {
                                return;
                            }
                            if (c.checkCollision(a, b)) {

                                Zombies finalB = b;
                                Plants finalA = a;
                                Platform.runLater(() -> {
                                    c.On_Collision(finalA, finalB);
                                    if (!finalB.getState()) {
                                        finalB.setState(true);
                                        finalB.set_eat();
                                    }
                                });


                            } else {
                                Zombies finalB1 = b;
                                Platform.runLater(() -> {
                                    finalB1.move();
                                    if (finalB1.getState()) {
                                        finalB1.setState(false);
                                        finalB1.set_move();
                                    }
                                });
                            }
                            j = j + 1;
                        }
                        i = i + 1;
                    }
                    i = 0;
                    while (i < sunflowers.size()) {
                        int j = 0;
                        while (j < z.size()) {
                            final int ind = i;
                            final int jnd = j;
                            try {
                                a = sunflowers.get(i);
                                b = z.get(j);
                            } catch (Exception e) {
                                return;
                            }
                            if (c.checkCollision(a, b)) {

                                Zombies finalB2 = b;
                                Plants finalA1 = a;
                                Platform.runLater(() -> {
                                    c.On_Collision(finalA1, finalB2);
                                    if (!finalB2.getState()) {
                                        finalB2.setState(true);
                                        finalB2.set_eat();
                                    }
                                });


                            } else {
                                Zombies finalB3 = b;
                                Platform.runLater(() -> {
                                    finalB3.move();
                                    if (finalB3.getState()) {
                                        finalB3.setState(false);
                                        finalB3.set_move();
                                    }
                                });
                            }
                            j = j + 1;
                        }
                        i = i + 1;
                    }
                    ImageView lvu = null;
                    i = 0;
                    while (i < lawnmovers.size()) {
                        int j = 0;
                        while (j < z.size()) {
                            final int ind = i;
                            final int jnd = j;
                            try {
                                lvu = lawnmovers.get(i);
                                b = z.get(j);
                            } catch (Exception e) {
                                return;
                            }
                            if (c.checkCollision(lvu, b)) {
                                if (lvu.getX()+lvu.getTranslateX()<=210) {

                                    Zombies finalB2 = b;
                                    ImageView finalA1 = lvu;
                                    Platform.runLater(() -> {
                                        TranslateTransition tlv = new TranslateTransition();
                                        tlv.setNode(finalA1);
                                        tlv.setToX(1000);
                                        tlv.setDuration(Duration.millis(2000));
                                        tlv.setOnFinished(event -> {
                                            root.getChildren().remove(finalA1);
                                        });
                                        tlv.play();
                                    });
                                }
                                else{
                                    b.setHealth(0);
                                }


                            }
                            j = j + 1;
                        }
                        i = i + 1;
                    }

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Zombies a = null;
                    System.out.println("r4 chalta hain");
                    int i = 0;
                    arr = new ArrayList<>();
                    arr.add(0);
                    arr.add(0);
                    arr.add(0);
                    arr.add(0);
                    arr.add(0);
                    while (i < z.size()) {
                        try {
                            a = z.get(i);
                        } catch (Exception e) {
                            return;
                        }
                        if ((a.getLocation_point().getY_coordinate() > 30) && (a.getLocation_point().getY_coordinate()<=130)) {
                            arr.set(4, 1);
                        }
                        if ((a.getLocation_point().getY_coordinate() > 130) && (a.getLocation_point().getY_coordinate()<=230)) {
                            arr.set(3, 1);
                        }
                        if ((a.getLocation_point().getY_coordinate() > 230) && (a.getLocation_point().getY_coordinate()<=330)) {
                            arr.set(2, 1);
                        }
                        if ((a.getLocation_point().getY_coordinate() > 330) && (a.getLocation_point().getY_coordinate()<=430)) {
                            arr.set(1, 1);
                        }
                        if ((a.getLocation_point().getY_coordinate() > 430) && (a.getLocation_point().getY_coordinate()<=530)) {
                            arr.set(0, 1);
                        }
                        i = i + 1;
                    }
                    for (int k = 0; k < pea.size(); k++) {
                        final int l = k;
                        Platform.runLater(() -> {
                            root.getChildren().remove(pea.get(l));
                        });
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        Runnable r6 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("r6 chalta hain");
                    ImageView a = null;
                    Zombies b = null;
                    int i = 0;
                    while (i < pea.size()) {
                        int j = 0;
                        while (j < z.size()) {
                            final int ind = i;
                            final int jnd = j;
                            try {
                                a = pea.get(i);
                                b = z.get(j);
                            } catch (Exception e) {
                                return;
                            }
                            if (c.checkCollision(a, b) && a.isVisible()) {
                                System.out.println("touch " + i + " " + j);
                                Zombies finalB = b;
                                ImageView finalA = a;
                                Platform.runLater(() -> {
                                    c.On_Collision(finalA, finalB);
                                    finalA.setVisible(false);
                                });
                            }

                            j = j + 1;
                        }
                        i = i + 1;
                    }
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        pb.setLayoutX(615);
        pb.setLayoutY(23);
        pb.setOpacity(0.52);
        pb.setMinHeight(36);
        pb.setMinWidth(246);

        pb.setProgress(0.0);

        Timeline progress_bar = new Timeline(
                new KeyFrame(Duration.seconds(1000000000),
                        e -> {
                            double t = progress + 0.0000000000000000001;
                            pb.setProgress(t);
                        })
        );

        sf.setLayoutX(174.0);
        sf.setLayoutY(20.0);
        sf.setMinHeight(56);
        sf.setMinWidth(45.5);
        tf.setLayoutX(219.5);
        tf.setLayoutY(20.0);
        tf.setMinHeight(56);
        tf.setMinWidth(25.5);
        tf.setDisable(true);
        sf.setStyle("-fx-border-color: yellow; -fx-border-width: 4px; -fx-background-color: #EA9C00");
        tf.setStyle("-fx-border-color: yellow; -fx-border-width: 4px; -fx-background-color: #EA9C00");


        gp.setLayoutX(289.0);
        gp.setLayoutY(110.0);
//        gp.setMaxHeight(446.0);
//        gp.setMaxWidth(628.0);
        gp.setMinHeight(446.0);
        gp.setMinWidth(628.0);
        gp.setPrefSize(628,446);

        Button po1 = new Button();
        po1.setMaxHeight(90);
        po1.setMaxWidth(70);
        po1.setMinHeight(89);
        po1.setMinWidth(69);

        try {
            po1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(290,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }
                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po1.setStyle("-fx-background-color: transparent;");
        Button po2 = new Button();
        po2.setMaxHeight(90);
        po2.setMaxWidth(70);
        po2.setMinHeight(89);
        po2.setMinWidth(69);
        try {
            po2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(360,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po2.setStyle("-fx-background-color: transparent;");
        Button po3 = new Button();
        po3.setMaxHeight(90);
        po3.setMaxWidth(70);
        po3.setMinHeight(89);
        po3.setMinWidth(69);
        try {
            po3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(430,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po3.setStyle("-fx-background-color: transparent;");
        Button po4 = new Button();
        po4.setMaxHeight(90);
        po4.setMaxWidth(70);
        po4.setMinHeight(89);
        po4.setMinWidth(69);
        try {
            po4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(500,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po4.setStyle("-fx-background-color: transparent;");
        Button po5 = new Button();
        po5.setMaxHeight(90);
        po5.setMaxWidth(70);
        po5.setMinHeight(89);
        po5.setMinWidth(69);
        try {
            po5.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(570,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po5.setStyle("-fx-background-color: transparent;");
        Button po6 = new Button();
        po6.setMaxHeight(90);
        po6.setMaxWidth(70);
        po6.setMinHeight(89);
        po6.setMinWidth(69);
        try {
            po6.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(640,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po6.setStyle("-fx-background-color: transparent;");
        Button po7 = new Button();
        po7.setMaxHeight(90);
        po7.setMaxWidth(70);
        po7.setMinHeight(89);
        po7.setMinWidth(69);
        try {
            po7.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(710,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po7.setStyle("-fx-background-color: transparent;");
        Button po8 = new Button();
        po8.setMaxHeight(90);
        po8.setMaxWidth(70);
        po8.setMinHeight(89);
        po8.setMinWidth(69);
        try {
            po8.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(780,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po8.setStyle("-fx-background-color: transparent;");
        Button po9 = new Button();
        po9.setMaxHeight(90);
        po9.setMaxWidth(70);
        po9.setMinHeight(89);
        po9.setMinWidth(69);
        try {
            po9.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(850,110);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po9.setStyle("-fx-background-color: transparent;");
        Button po10 = new Button();
        po10.setMaxHeight(90);
        po10.setMaxWidth(70);
        po10.setMinHeight(89);
        po10.setMinWidth(69);
        try {
            po10.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(290,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po10.setStyle("-fx-background-color: transparent;");
        Button po11 = new Button();
        po11.setMaxHeight(90);
        po11.setMaxWidth(70);
        po11.setMinHeight(89);
        po11.setMinWidth(69);
        try {
            po11.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(360,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po11.setStyle("-fx-background-color: transparent;");
        Button po12 = new Button();
        po12.setMaxHeight(90);
        po12.setMaxWidth(70);
        po12.setMinHeight(89);
        po12.setMinWidth(69);
        try {
            po12.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(430,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po12.setStyle("-fx-background-color: transparent;");
        Button po13 = new Button();
        po13.setMaxHeight(90);
        po13.setMaxWidth(70);
        po13.setMinHeight(89);
        po13.setMinWidth(69);
        try {
            po13.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(500,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po13.setStyle("-fx-background-color: transparent;");
        Button po14 = new Button();
        po14.setMaxHeight(90);
        po14.setMaxWidth(70);
        po14.setMinHeight(89);
        po14.setMinWidth(69);
        try {
            po14.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(570,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po14.setStyle("-fx-background-color: transparent;");
        Button po15 = new Button();
        po15.setMaxHeight(90);
        po15.setMaxWidth(70);
        po15.setMinHeight(89);
        po15.setMinWidth(69);
        try {
            po15.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(640,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po15.setStyle("-fx-background-color: transparent;");
        Button po16 = new Button();
        po16.setMaxHeight(90);
        po16.setMaxWidth(70);
        po16.setMinHeight(89);
        po16.setMinWidth(69);
        try {
            po16.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(710,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po16.setStyle("-fx-background-color: transparent;");
        Button po17 = new Button();
        po17.setMaxHeight(90);
        po17.setMaxWidth(70);
        po17.setMinHeight(89);
        po17.setMinWidth(69);
        try {
            po17.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(780,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po17.setStyle("-fx-background-color: transparent;");
        Button po18 = new Button();
        po18.setMaxHeight(90);
        po18.setMaxWidth(70);
        po18.setMinHeight(89);
        po18.setMinWidth(69);
        try {
            po18.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(850,200);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po18.setStyle("-fx-background-color: transparent;");
        Button po19 = new Button();
        po19.setMaxHeight(90);
        po19.setMaxWidth(70);
        po19.setMinHeight(89);
        po19.setMinWidth(69);
        try {
            po19.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(290,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po19.setStyle("-fx-background-color: transparent;");
        Button po20 = new Button();
        po20.setMaxHeight(90);
        po20.setMaxWidth(70);
        po20.setMinHeight(89);
        po20.setMinWidth(69);
        try {
            po20.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(360,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po20.setStyle("-fx-background-color: transparent;");
        Button po21 = new Button();
        po21.setMaxHeight(90);
        po21.setMaxWidth(70);
        po2.setMinHeight(89);
        po2.setMinWidth(69);
        try {
            po21.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(430,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po21.setStyle("-fx-background-color: transparent;");
        Button po22 = new Button();
        po22.setMaxHeight(90);
        po22.setMaxWidth(70);
        po22.setMinHeight(89);
        po22.setMinWidth(69);
        try {
            po22.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(500,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po22.setStyle("-fx-background-color: transparent;");
        Button po23 = new Button();
        po23.setMaxHeight(90);
        po23.setMaxWidth(70);
        po23.setMinHeight(89);
        po23.setMinWidth(69);
        try {
            po23.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(570,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po23.setStyle("-fx-background-color: transparent;");
        Button po24 = new Button();
        po24.setMaxHeight(90);
        po24.setMaxWidth(70);
        po24.setMinHeight(89);
        po24.setMinWidth(69);
        try {
            po24.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(640,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po24.setStyle("-fx-background-color: transparent;");
        Button po25 = new Button();
        po25.setMaxHeight(90);
        po25.setMaxWidth(70);
        po25.setMinHeight(89);
        po25.setMinWidth(69);
        try {
            po25.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(710,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po25.setStyle("-fx-background-color: transparent;");
        Button po26 = new Button();
        po26.setMaxHeight(90);
        po26.setMaxWidth(70);
        po26.setMinHeight(89);
        po26.setMinWidth(69);
        try {
            po26.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(780,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po26.setStyle("-fx-background-color: transparent;");
        Button po27 = new Button();
        po27.setMaxHeight(90);
        po27.setMaxWidth(70);
        po27.setMinHeight(89);
        po27.setMinWidth(69);
        try {
            po27.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(850,290);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po27.setStyle("-fx-background-color: transparent;");
        Button po28 = new Button();
        po28.setMaxHeight(90);
        po28.setMaxWidth(70);
        po28.setMinHeight(89);
        po28.setMinWidth(69);
        try {
            po28.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(290,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po28.setStyle("-fx-background-color: transparent;");
        Button po29 = new Button();
        po29.setMaxHeight(90);
        po29.setMaxWidth(70);
        po29.setMinHeight(89);
        po29.setMinWidth(69);
        try {
            po29.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(360,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po29.setStyle("-fx-background-color: transparent;");
        Button po30 = new Button();
        po30.setMaxHeight(90);
        po30.setMaxWidth(70);
        po30.setMinHeight(89);
        po30.setMinWidth(69);
        try {
            po30.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(430,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po30.setStyle("-fx-background-color: transparent;");
        Button po31 = new Button();
        po31.setMaxHeight(90);
        po31.setMaxWidth(70);
        po31.setMinHeight(89);
        po31.setMinWidth(69);
        try {
            po31.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(500,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po31.setStyle("-fx-background-color: transparent;");
        Button po32 = new Button();
        po32.setMaxHeight(90);
        po32.setMaxWidth(70);
        po32.setMinHeight(89);
        po32.setMinWidth(69);
        try {
            po32.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(570,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po32.setStyle("-fx-background-color: transparent;");
        Button po33 = new Button();
        po33.setMaxHeight(90);
        po33.setMaxWidth(70);
        po33.setMinHeight(89);
        po33.setMinWidth(69);
        try {
            po33.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(640,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po33.setStyle("-fx-background-color: transparent;");
        Button po34 = new Button();
        po34.setMaxHeight(90);
        po34.setMaxWidth(70);
        po34.setMinHeight(89);
        po34.setMinWidth(69);
        try {
            po34.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(710,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po34.setStyle("-fx-background-color: transparent;");
        Button po35 = new Button();
        po35.setMaxHeight(90);
        po35.setMaxWidth(70);
        po35.setMinHeight(89);
        po35.setMinWidth(69);
        try {
            po35.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(780,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po35.setStyle("-fx-background-color: transparent;");
        Button po36 = new Button();
        po36.setMaxHeight(90);
        po36.setMaxWidth(70);
        po36.setMinHeight(89);
        po36.setMinWidth(69);
        try {
            po36.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(850,380);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po36.setStyle("-fx-background-color: transparent;");
        Button po37 = new Button();
        po37.setMaxHeight(90);
        po37.setMaxWidth(70);
        po37.setMinHeight(89);
        po37.setMinWidth(69);
        try {
            po37.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(290,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po37.setStyle("-fx-background-color: transparent;");
        Button po38 = new Button();
        po38.setMaxHeight(90);
        po38.setMaxWidth(70);
        po38.setMinHeight(89);
        po38.setMinWidth(69);
        try {
            po38.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(360,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po38.setStyle("-fx-background-color: transparent;");
        Button po39 = new Button();
        po39.setMaxHeight(90);
        po39.setMaxWidth(70);
        po39.setMinHeight(89);
        po39.setMinWidth(69);
        try {
            po39.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(430,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po39.setStyle("-fx-background-color: transparent;");
        Button po40 = new Button();
        po40.setMaxHeight(90);
        po40.setMaxWidth(70);
        po40.setMinHeight(89);
        po40.setMinWidth(69);
        try {
            po40.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(500,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po40.setStyle("-fx-background-color: transparent;");
        Button po41 = new Button();
        po41.setMaxHeight(90);
        po41.setMaxWidth(70);
        po41.setMinHeight(89);
        po41.setMinWidth(69);
        try {
            po41.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(570,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po41.setStyle("-fx-background-color: transparent;");
        Button po42 = new Button();
        po42.setMaxHeight(90);
        po42.setMaxWidth(70);
        po42.setMinHeight(89);
        po42.setMinWidth(69);
        try {
            po42.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(640,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po42.setStyle("-fx-background-color: transparent;");
        Button po43 = new Button();
        po43.setMaxHeight(90);
        po43.setMaxWidth(70);
        po43.setMinHeight(89);
        po43.setMinWidth(69);
        try {
            po43.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(710,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po43.setStyle("-fx-background-color: transparent;");
        Button po44 = new Button();
        po44.setMaxHeight(90);
        po44.setMaxWidth(70);
        po44.setMinHeight(89);
        po44.setMinWidth(69);
        try {
            po44.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(780,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }

                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po44.setStyle("-fx-background-color: transparent;");
        Button po45 = new Button();
        po45.setMaxHeight(90);
        po45.setMaxWidth(70);
        po45.setMinHeight(89);
        po45.setMinWidth(69);
        try {
            po45.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    hope = f.plant();
                    ImageView image = new ImageView((hope));
                    Button button = (Button) e.getSource();
                    image.setFitWidth(69);
                    image.setFitHeight(72);
                    location l = new location(850,470);
                    Tile t= new Tile(l);
                    if (hope.equals("/res/PeaShooter_Idle1.gif")){
                        end_time_of_peashooter=System.currentTimeMillis();
                        if (end_time_of_peashooter-start_time_of_peashooter>5000 && pea_availability && sun_power>=100) {
                            Ranged pea_shooter = new PeaShooter(l, t);
                            Pea_shooter.add(pea_shooter); sun_power =sun_power-100; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(pea_shooter.getNormal_imageview());
                            start_time_of_peashooter=end_time_of_peashooter;
                        }
                    }
                    else if (hope.equals("/res/Sunflower_Idle.gif")){
                        end_time_of_sunflower =System.currentTimeMillis();
                        if (end_time_of_sunflower-start_time_of_sunflower>10000 && sun_flower_availability && sun_power>=50) {
                            Sunflower sunflower = new Sunflower(l, t);
                            sunflowers.add(sunflower); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(sunflower.getNormal_imageview());
                            start_time_of_sunflower=end_time_of_sunflower;
                        }
                    }
                    else if (hope.equals("/res/WallnutHD.png")){
                        end_time_of_wallnut=System.currentTimeMillis();
                        if (end_time_of_wallnut-start_time_of_wallnut>15000 && wallnut_availability && sun_power>=50) {
                            Wallnut nut = new Wallnut(l, t);
                            rest_plants.add(nut); sun_power =sun_power-50; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(nut.getNormal_imageview());
                            start_time_of_wallnut=end_time_of_wallnut;
                        }
                    }
                    else if (hope.equals("/res/Cherry_BombHD.png")){
                        end_time_of_cherrybomb=System.currentTimeMillis();
                        if (end_time_of_cherrybomb-start_time_of_cherrybomb>10000 && cherry_bomb_availability && sun_power>=150) {
                            CherryBomb bomb = new CherryBomb(l, t); sun_power =sun_power-150; String d = Integer.toString(sun_power);
                            tf.setText(d);
                            root.getChildren().add(bomb.getNormal_imageview());
                            Timeline time_bomb = bomb.explode();
                            time_bomb.setOnFinished(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    bomb.damage_zombies(z);
                                    root.getChildren().remove(bomb.getNormal_imageview());
                                }
                            });
                            time_bomb.setCycleCount(1);
                            time_bomb.play();
                            time_bomb = null;
                            start_time_of_cherrybomb=end_time_of_cherrybomb;
                        }

                    }


                }
            });
        } catch (NullPointerException ex) {
            System.out.println("No Value");
        }
        po45.setStyle("-fx-background-color: transparent;");

        gp.add(po1, 0, 0, 1, 1);
        gp.add(po2, 1, 0, 1, 1);
        gp.add(po3, 2, 0, 1, 1);
        gp.add(po4, 3, 0, 1, 1);
        gp.add(po5, 4, 0, 1, 1);
        gp.add(po6, 5, 0, 1, 1);
        gp.add(po7, 6, 0, 1, 1);
        gp.add(po8, 7, 0, 1, 1);
        gp.add(po9, 8, 0, 1, 1);
        gp.add(po10, 0, 1, 1, 1);
        gp.add(po11, 1, 1, 1, 1);
        gp.add(po12, 2, 1, 1, 1);
        gp.add(po13, 3, 1, 1, 1);
        gp.add(po14, 4, 1, 1, 1);
        gp.add(po15, 5, 1, 1, 1);
        gp.add(po16, 6, 1, 1, 1);
        gp.add(po17, 7, 1, 1, 1);
        gp.add(po18, 8, 1, 1, 1);
        gp.add(po19, 0, 2, 1, 1);
        gp.add(po20, 1, 2, 1, 1);
        gp.add(po21, 2, 2, 1, 1);
        gp.add(po22, 3, 2, 1, 1);
        gp.add(po23, 4, 2, 1, 1);
        gp.add(po24, 5, 2, 1, 1);
        gp.add(po25, 6, 2, 1, 1);
        gp.add(po26, 7, 2, 1, 1);
        gp.add(po27, 8, 2, 1, 1);
        gp.add(po28, 0, 3, 1, 1);
        gp.add(po29, 1, 3, 1, 1);
        gp.add(po30, 2, 3, 1, 1);
        gp.add(po31, 3, 3, 1, 1);
        gp.add(po32, 4, 3, 1, 1);
        gp.add(po33, 5, 3, 1, 1);
        gp.add(po34, 6, 3, 1, 1);
        gp.add(po35, 7, 3, 1, 1);
        gp.add(po36, 8, 3, 1, 1);
        gp.add(po37, 0, 4, 1, 1);
        gp.add(po38, 1, 4, 1, 1);
        gp.add(po39, 2, 4, 1, 1);
        gp.add(po40, 3, 4, 1, 1);
        gp.add(po41, 4, 4, 1, 1);
        gp.add(po42, 5, 4, 1, 1);
        gp.add(po43, 6, 4, 1, 1);
        gp.add(po44, 7, 4, 1, 1);
        gp.add(po45, 8, 4, 1, 1);
        gp.setGridLinesVisible(true);


        root.getChildren().addAll(pause_button, sf, tf, pb, gp);
        Thread t_pvz= new Thread(r);
        Thread t_d= new Thread(r2);
        Thread t_rpvz = new Thread(r3);
        Thread t_rc = new Thread(r4);
        Thread t_peavz = new Thread(r6);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Ranged a =null;
                pea = new ArrayList<>();

                System.out.println("timer chalta hain");
                for(int k=0;k<arr.size();k++){
                    System.out.println(arr.get(k));
                }
                for (int i = 0; i < Pea_shooter.size(); i++) {
                    try{
                        a =Pea_shooter.get(i);
                    }
                    catch (Exception e){
                        return;
                    }
                    if ((a.getLocation_point().getY_coordinate() > 30) && (a.getLocation_point().getY_coordinate()<=130)) {
                        if (arr.get(4) == 1) {
                            final int j = i;
                            Ranged finalA4 = a;
                            Platform.runLater(() -> {
                                finalA4.setProjectile(new Pea(finalA4.getLocation_point(),48,14,10,2,100));
                                TranslateTransition tt = finalA4.getProjectile().trav();
                                ImageView p = (ImageView) tt.getNode();
                                root.getChildren().add(p);
                                p.setVisible(false);
                                tt.setOnFinished(event -> {
                                    p.setTranslateX(0);
                                });
                                pea.add(p);
                                tt.setNode(p);
                                tt.setDelay(Duration.millis(1300));
                                Timeline timeline_1 = new Timeline(
                                        new KeyFrame(Duration.millis(1300), e -> {

                                            System.out.println("marne ke liye ghusa");
                                            Image suno =new Image("res/PeaShooter_Spit.gif");
                                            finalA4.getNormal_imageview().setImage(suno);
                                        })
                                );
                                timeline_1.setCycleCount(1);
                                timeline_1.setOnFinished(e->{
                                    p.setVisible(true);
                                    finalA4.getNormal_imageview().setImage(new Image("res/PeaShooter_Idle1.gif"));
                                });
                                tt.play();
                                timeline_1.setDelay(Duration.millis(100));
                                timeline_1.play();



                            });


                        }
                    }
                    if ((a.getLocation_point().getY_coordinate() > 130) && (a.getLocation_point().getY_coordinate()<=230)) {
                        if (arr.get(3) == 1) {
                            final int j = i;
                            Ranged finalA3 = a;
                            Platform.runLater(() -> {
                                finalA3.setProjectile(new Pea(finalA3.getLocation_point(),48,14,10,2,100));
                                TranslateTransition tt = finalA3.getProjectile().trav();
                                ImageView p = (ImageView) tt.getNode();
                                root.getChildren().add(p);
                                tt.setOnFinished(event -> {
                                    p.setTranslateX(0);
                                });
                                p.setVisible(false);
                                pea.add(p);
                                tt.setNode(p);
                                tt.setDelay(Duration.millis(1300));

                                Timeline timeline_1 = new Timeline(
                                        new KeyFrame(Duration.millis(1300), e -> {
                                            System.out.println("marne ke liye ghusa");
                                            Image suno =new Image("res/PeaShooter_Spit.gif");
                                            finalA3.getNormal_imageview().setImage(suno);
                                        })
                                );
                                timeline_1.setCycleCount(1);
                                timeline_1.setOnFinished(e->{
                                    p.setVisible(true);
                                    finalA3.getNormal_imageview().setImage(new Image("res/PeaShooter_Idle1.gif"));
                                });
                                tt.play();
                                timeline_1.setDelay(Duration.millis(100));
                                timeline_1.play();
                            });
                        }
                    }
                    if ((a.getLocation_point().getY_coordinate() > 230) && (a.getLocation_point().getY_coordinate()<=330)) {
                        if (arr.get(2) == 1) {
                            final int j = i;
                            Ranged finalA2 = a;
                            Platform.runLater(() -> {
                                finalA2.setProjectile(new Pea(finalA2.getLocation_point(),48,14,10,2,100));
                                TranslateTransition tt = finalA2.getProjectile().trav();
                                ImageView p = (ImageView) tt.getNode();
                                root.getChildren().add(p);
                                p.setVisible(false);
                                tt.setOnFinished(event -> {
                                    p.setTranslateX(0);
                                });
                                pea.add(p);
                                tt.setNode(p);
                                tt.setDelay(Duration.millis(1300));
                                Timeline timeline_1 = new Timeline(
                                        new KeyFrame(Duration.millis(1300), e -> {
                                            System.out.println("marne ke liye ghusa");
                                            Image suno =new Image("res/PeaShooter_Spit.gif");
                                            finalA2.getNormal_imageview().setImage(suno);
                                        })
                                );
                                timeline_1.setCycleCount(1);
                                timeline_1.setOnFinished(e->{
                                    p.setVisible(true);
                                    finalA2.getNormal_imageview().setImage(new Image("res/PeaShooter_Idle1.gif"));
                                });
                                tt.play();
                                timeline_1.setDelay(Duration.millis(100));
                                timeline_1.play();
                            });
                        }
                    }
                    if ((a.getLocation_point().getY_coordinate() > 330) && (a.getLocation_point().getY_coordinate()<=430)) {
                        if (arr.get(1) == 1) {
                            final int j = i;
                            Ranged finalA1 = a;
                            Platform.runLater(() -> {
                                finalA1.setProjectile(new Pea(finalA1.getLocation_point(),48,14,10,2,100));
                                TranslateTransition tt = finalA1.getProjectile().trav();
                                ImageView p = (ImageView) tt.getNode();
                                root.getChildren().add(p);
                                p.setVisible(false);
                                tt.setOnFinished(event -> {
                                    p.setTranslateX(0);
                                });
                                pea.add(p);
                                tt.setNode(p);
                                tt.setDelay(Duration.millis(1300));
                                Timeline timeline_1 = new Timeline(
                                        new KeyFrame(Duration.millis(1300), e -> {
                                            System.out.println("marne ke liye ghusa");
                                            Image suno =new Image("res/PeaShooter_Spit.gif");
                                            finalA1.getNormal_imageview().setImage(suno);
                                        })
                                );
                                timeline_1.setCycleCount(1);
                                timeline_1.setOnFinished(e->{
                                    p.setVisible(true);
                                    finalA1.getNormal_imageview().setImage(new Image("res/PeaShooter_Idle1.gif"));
                                });
                                tt.play();
                                timeline_1.setDelay(Duration.millis(100));
                                timeline_1.play();
                            });
                        }
                    }
                    if ((a.getLocation_point().getY_coordinate() > 430) && (a.getLocation_point().getY_coordinate()<=530)) {
                        if (arr.get(0) == 1) {
                            final int j = i;
                            Ranged finalA = a;
                            Platform.runLater(() -> {
                                finalA.setProjectile(new Pea(finalA.getLocation_point(),48,14,10,2,100));
                                TranslateTransition tt = finalA.getProjectile().trav();
                                ImageView p = (ImageView) tt.getNode();
                                root.getChildren().add(p);
                                p.setVisible(false);
                                tt.setOnFinished(event -> {
                                    p.setTranslateX(0);
                                });
                                pea.add(p);
                                tt.setNode(p);
                                tt.setDelay(Duration.millis(1300));
                                Timeline timeline_1 = new Timeline(
                                        new KeyFrame(Duration.millis(1300), e -> {
                                            System.out.println("marne ke liye ghusa");
                                            Image suno =new Image("res/PeaShooter_Spit.gif");
                                            finalA.getNormal_imageview().setImage(suno);
                                        })
                                );
                                timeline_1.setCycleCount(1);
                                timeline_1.setOnFinished(e->{
                                    p.setVisible(true);
                                    finalA.getNormal_imageview().setImage(new Image("res/PeaShooter_Idle1.gif"));
                                });
                                tt.play();
                                timeline_1.setDelay(Duration.millis(100));
                                timeline_1.play();
                            });
                        }
                    }

                }
            }
        },1000,5000);
        t3.setCycleCount(Animation.INDEFINITE);
        Timeline timeline_check_winner = new Timeline(
                new KeyFrame(Duration.millis(500), e -> {
                    if (z.size()==0){
                        System.out.println("game won");
                        t3.stop();
                        t_pvz.interrupt();
                        t_d.interrupt();
                        t_rpvz.interrupt();
                        t_rc.interrupt();
                        t_peavz.interrupt();
                        Level_UP_3 lv3 =new Level_UP_3();
                        lv3.start(primaryStage);
                        //level change
                    }
                })
        );
        t3.play();
        t_pvz.start();
        t_d.start();
        t_rpvz.start();
        t_rc.start();
        t_peavz.start();
        for (int i = 0; i < Pea_shooter.size(); i++) {
            root.getChildren().add(Pea_shooter.get(i).getNormal_imageview());
        }
        for (int i = 0; i < z.size(); i++) {
            root.getChildren().add(z.get(i).getNormal_imageview());
        }

        timeline_sun_fall.setCycleCount(Animation.INDEFINITE);
        progress_bar.play();
        timeline_sun_fall.play();
        Thread th = new Thread(new bg_thread());
        th.start();
        t.play();
        pe.setDelay(Duration.millis(630));
        pe.play();
        main_zombie.setDelay(Duration.millis(2000));
        main_zombie.setCycleCount(8);
        main_zombie.play();
        timeline_check_winner.setDelay(Duration.seconds(100));
        timeline_check_winner.setCycleCount(Animation.INDEFINITE);
        timeline_check_winner.play();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();

    }

    class bg_thread implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 100; i++)

                try {
                    pb.setProgress(i / 100.0);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(play_screen_controller.class.getName());
                }
        }
    }

    public ImageView sun_maker(double x, double y) {
        Image sun_image;
        ImageView sun_view;
        sun_image = new Image("res/Sun_PvZ2.png");
        sun_view = new ImageView();
        sun_view.setImage(sun_image);
        sun_view.setX(x);
        sun_view.setY(y);
        sun_view.setFitWidth(60);
        sun_view.setFitWidth(60);
        sun_view.setPreserveRatio(true);
        sun_view.setOnMouseClicked(mouseEvent -> {
            sun_view.setVisible(false);
            sun_power = sun_power + 50;
            String d = Integer.toString(sun_power);
            tf.setText(d);
        });
        return sun_view;
    }
}
