package sample;

import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;

public class User implements Serializable {
    private ArrayList<Ranged> PeaShooter;
    private ArrayList<Zombies> zombies;
    private ArrayList<ImageView> lawnmovers;
    private ArrayList<Plants> rest_plants;
    private ArrayList<Sunflower> sunflowers;
    private int sun_power;
    private String user_name;
    private int level;

    public ArrayList<Ranged> getPeaShooter() {
        return PeaShooter;
    }

    public ArrayList<Zombies> getZombies() {
        return zombies;
    }

    public ArrayList<ImageView> getLawnmovers() {
        return lawnmovers;
    }

    public ArrayList<Plants> getRest_plants() {
        return rest_plants;
    }

    public ArrayList<Sunflower> getSunflowers() {
        return sunflowers;
    }

    public int getSun_power() {
        return sun_power;
    }

    public int getLevel() {
        return level;
    }

    public User(int level,ArrayList<Ranged> peaShooter, ArrayList<Zombies> zombies, ArrayList<ImageView> lawnmovers, ArrayList<Plants> rest_plants, ArrayList<Sunflower> sunflowers, int sun_power, String user_name) {
        PeaShooter = peaShooter;
        this.level = level;
        this.zombies = zombies;
        this.lawnmovers = lawnmovers;
        this.rest_plants = rest_plants;
        this.sunflowers = sunflowers;
        this.sun_power = sun_power;
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public static void serialize(User user) throws Exception {
        ObjectOutputStream out = null;
        try{
            out=new ObjectOutputStream(new FileOutputStream(user.getUser_name()+".txt"));
            out.writeObject(user);
        }
        finally {
            out.close();
        }
    }
    public static User deserialize(String filename) throws IOException,ClassNotFoundException{
        ObjectInputStream in = null;
        User user;
        try{
            in=new ObjectInputStream(new FileInputStream(filename));
            user=(User) in.readObject();

        }
        finally {
            in.close();
        }
        return user;
    }
}
