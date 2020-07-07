package sample;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Glow;
import javafx.scene.control.Label;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

public class play_screen_controller {

    String k;
    @FXML
    ImageView slot_4;

    @FXML
    ImageView slot_3;

    @FXML
    ImageView pea_planted;

    @FXML
    ImageView slot_2;

    @FXML
    ImageView sun_planted;

    @FXML
    private ImageView slot_1;
    private boolean is_slot_1_selected = false;
    private boolean is_slot_2_selected = false;
    private boolean is_slot_3_selected = false;
    private boolean is_slot_4_selected = false;
    public static String s = "res/43.png";

    @FXML
    public void select_deselect_slot_1() {
        Glow glow =new Glow();
        if (!is_slot_1_selected){
            glow.setLevel(0.5);
            slot_1.setEffect(glow);
            is_slot_1_selected=true;
            s = "/res/PeaShooter_Idle1.gif";
            System.out.println(s);
            Glow glow_2 = new Glow();
            glow_2.setLevel(0);
            slot_3.setEffect(glow_2);
            slot_2.setEffect(glow_2);
            slot_4.setEffect(glow_2);
            is_slot_2_selected=false;
            is_slot_3_selected=false;
            is_slot_4_selected=false;
        }
        else{
            glow.setLevel(0);
            slot_1.setEffect(glow);
            is_slot_1_selected=false;
        }
    }


    @FXML
    void select_deselect_slot_2() {
        Glow glow =new Glow();
        if (!is_slot_2_selected){
            glow.setLevel(0.5);
            slot_2.setEffect(glow);
            is_slot_2_selected=true;
            s="/res/Sunflower_Idle.gif";
            Glow glow_2 = new Glow();
            glow_2.setLevel(0);
            slot_1.setEffect(glow_2);
            slot_3.setEffect(glow_2);
            slot_4.setEffect(glow_2);
            is_slot_1_selected=false;
            is_slot_3_selected=false;
            is_slot_4_selected=false;
        }
        else{
            glow.setLevel(0);
            slot_2.setEffect(glow);
            is_slot_2_selected=false;
        }
    }

    @FXML
    void select_deselect_slot_3() {
        Glow glow =new Glow();
        if (!is_slot_3_selected){
            glow.setLevel(0.5);
            slot_3.setEffect(glow);
            is_slot_3_selected=true;
            s="/res/WallnutHD.png";
            Glow glow_2 = new Glow();
            glow_2.setLevel(0);
            slot_1.setEffect(glow_2);
            slot_2.setEffect(glow_2);
            slot_4.setEffect(glow_2);
            is_slot_2_selected=false;
            is_slot_1_selected=false;
            is_slot_4_selected=false;
        }
        else{
            glow.setLevel(0);
            slot_3.setEffect(glow);
            is_slot_3_selected=false;
        }
    }

    @FXML
    void select_deselect_slot_4() {
        Glow glow =new Glow();
        if (!is_slot_4_selected){
            glow.setLevel(0.5);
            slot_4.setEffect(glow);
            is_slot_4_selected=true;
            s = "/res/Cherry_BombHD.png";
            Glow glow_2 = new Glow();
            glow_2.setLevel(0);
            slot_1.setEffect(glow_2);
            slot_2.setEffect(glow_2);
            slot_3.setEffect(glow_2);
            is_slot_2_selected=false;
            is_slot_3_selected=false;
            is_slot_1_selected=false;
        }
        else{
            glow.setLevel(0);
            slot_4.setEffect(glow);
            is_slot_4_selected=false;
        }
    }
    public String plant()
    {
        return s;
    }
}

