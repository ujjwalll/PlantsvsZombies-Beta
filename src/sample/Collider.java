package sample;


import javafx.scene.image.ImageView;

import static java.lang.Math.abs;

public class Collider {
    public Boolean checkCollision(Plants p , Zombies z){
        System.out.println("pvz "+(abs(p.getT().getLoc().getX_coordinate()-z.getLocation_point().getX_coordinate())+"  "+abs(p.getT().getLoc().getY_coordinate()-z.getLocation_point().getY_coordinate())));
        if ( abs(p.getT().getLoc().getX_coordinate()-z.getLocation_point().getX_coordinate())<=25 && abs(p.getT().getLoc().getY_coordinate()-z.getLocation_point().getY_coordinate())<=40) {
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkCollision(ImageView p , Zombies z){
        System.out.println("pro z"+(abs(p.getX()+p.getTranslateX()-z.getLocation_point().getX_coordinate())+"  "+abs(p.getY()+p.getTranslateY()-z.getLocation_point().getY_coordinate())));
        if ( abs(p.getX()+p.getTranslateX()-z.getLocation_point().getX_coordinate())<=20 && abs(p.getY()+p.getTranslateY()-z.getLocation_point().getY_coordinate())<=40) {
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkCollision(Lawnmover l , Zombies z){
        if ( abs(l.getLoc().getX_coordinate()-z.getLocation_point().getX_coordinate())<=25 && abs(l.getLoc().getY_coordinate()-z.getLocation_point().getY_coordinate())<=40) {
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkCollision(Plants p ,Zombies z, int near){
        System.out.println("range "+(abs(p.getT().getLoc().getX_coordinate()-z.getLocation_point().getX_coordinate())+"  "+abs(p.getT().getLoc().getY_coordinate()-z.getLocation_point().getY_coordinate())));
        if ( abs(p.getLocation_point().getX_coordinate()-z.getLocation_point().getX_coordinate())<=(10+near) && abs(p.getLocation_point().getY_coordinate()-z.getLocation_point().getY_coordinate())<=40){
            return true;
        }
        return false;
    }
    public Boolean On_Collision(Plants p , Zombies z){

        p.setHealth(p.getHealth()-z.getAttack_damage());
        if(p.death()){
            return true;
        }
        return false;
    }
    public Boolean On_Collision(ImageView p, Zombies z){
        z.setHealth(z.getHealth()-10);
        if(z.death()){
            return true;
        }
        return false;
    }
    public Boolean On_Collision(Lawnmover l, Zombies z){
        if(l.getIsactivated()){
            return true;
        }
        else {
            l.setIsactivated(true);
            return false;
        }
    }

    public void Initiate_death_photo(Zombies z) {
//        int x = z.getLocation_point().getX_coordinate();
//        int y = z.getLocation_point().getY_coordinate();
//        Image i = new Image("");
//        ImageView iv = new ImageView(i);
//        iv.setX();
//        iv.setY();
//        iv.setFitWidth();
//        iv.setFitHeight();


    }
}
