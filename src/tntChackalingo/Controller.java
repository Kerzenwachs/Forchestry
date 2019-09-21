package tntChackalingo;

import com.sun.deploy.ui.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLOutput;
import java.util.Random;

public class Controller {

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private TextField tf_bet;

    @FXML
    private Label l_credits;

    @FXML
    private Label tf_dice2;

    @FXML
    private Label tf_dice1;

    @FXML
    private Label tf_dice3;

    @FXML
    private Button b_roll;

    @FXML
    private Label l_win;

    @FXML
    private Button b_diceButton;

    @FXML
    private Button b_betSub1;

    @FXML
    private Button b_betAdd1;

    @FXML
    private Button b_betAdd2;

    @FXML
    private Button b_betSub2;

    @FXML
    private Button b_maxBet;

    @FXML
    private Button b_clear;

    @FXML
    private TextField tf_codes;

    @FXML
    private Button b_send;


    @FXML
    private Image dice1 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4fy6mixbxTC01jp0IzV7wK8hSYt4BoaMeExzQd5DwNfwnUlw4ZA");

    @FXML
    private Image dice2 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2MNnKjLy-QKiSDRYEcfc8Jb8xQ2R2_rmRjiqlzxSHd3jHuCBu");

    @FXML
    private Image dice3 = new Image("https://image.freepik.com/iconos-gratis/cube-de-la-vision-superior-en-la-cara-con-tres-puntos_318-59490.jpg");

    @FXML
    private Image dice4 = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Dice-4-b.svg/1024px-Dice-4-b.svg.png");

    @FXML
    private Image dice5 = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfjVXxoIj35E03Sp7HqxY5MP_U_46yxPLag5wlCqKSl7HFwmh3");

    @FXML
    private Image dice6 = new Image("http://www.jlhorst.com/publicfiles/AB7F7CD4-FF95-446F-8BCF-6BCEBA30E57B/dice-six-faces-six.png");

    int bet = 0;
    int credits = 0;


    @FXML
    private ImageView iv_dice = new ImageView();


    Random rand = new Random();

    int diceAt = 0;

    @FXML
    void b_onDiceButton(ActionEvent event) {

        diceAt = (diceAt + 1) % 6;
        System.out.println(diceAt);

        Image[] dice = {dice1, dice2, dice3, dice4, dice5, dice6};
        iv_dice.setImage(dice[diceAt]);


    }

    @FXML
    void b_onRoll(ActionEvent event) {

        bet = Integer.parseInt(tf_bet.getText());
        credits = Integer.parseInt(l_credits.getText());
        int number = diceAt + 1;
        int wins = 0;

        if (credits >= bet && bet >= 0) {

            //tf_dice1.setText(randval());
            tf_dice1.setText(randval());
            tf_dice2.setText(randval());
            tf_dice3.setText(randval());

            if (Integer.parseInt(tf_dice1.getText()) == number) {
                wins++;
            }

            if (Integer.parseInt(tf_dice2.getText()) == number) {
                wins++;
            }

            if (Integer.parseInt(tf_dice3.getText()) == number) {
                wins++;
            }

            if (wins >= 1) {
                l_win.setText(String.valueOf(bet * wins));
                // credits = Integer.parseInt(l_credits.getText());
                l_credits.setText(String.valueOf((wins * bet + credits)));
                System.out.println(bet);
                System.out.println(wins);
                System.out.println(credits);

            } else {
                l_credits.setText(String.valueOf((credits - bet)));
                l_win.setText(String.valueOf(0));
            }
        }
    }

    public String randval() {

        int n = rand.nextInt(6) + 1;
        // String p =String.valueOf(n);

        return String.valueOf(n);
    }

    @FXML
    void b_onBetAdd1(ActionEvent event) {
        bet = Integer.parseInt(tf_bet.getText());
        tf_bet.setText(String.valueOf(bet + bet1()));
    }

    @FXML
    void b_onBetAdd2(ActionEvent event) {
        bet = Integer.parseInt(tf_bet.getText());
        tf_bet.setText(String.valueOf(bet + bet2()));
    }

    @FXML
    void b_onBetSub1(ActionEvent event) {
        if (Integer.parseInt(tf_bet.getText()) > 0) {
            bet = Integer.parseInt(tf_bet.getText());
            tf_bet.setText(String.valueOf(bet - bet1()));
        }
    }

    @FXML
    void b_onBetSub2(ActionEvent event) {
        if (Integer.parseInt(tf_bet.getText()) > 0) {
            bet = Integer.parseInt(tf_bet.getText());
            tf_bet.setText(String.valueOf(bet - bet2()));
        }
    }

    @FXML
    void b_onClear(ActionEvent event) {
        tf_bet.setText(String.valueOf(0));
    }

    @FXML
    void b_onMaxBet(ActionEvent event) {
        tf_bet.setText(String.valueOf(l_credits.getText()));
    }

    @FXML
    void b_onSend(ActionEvent event) {

        if (tf_codes.getText().equals("infCred")) {

            l_credits.setText("1000000");

        }

        if (tf_codes.getText().equals("getCredPlus")) {
            credits = Integer.parseInt(l_credits.getText());
            bet = Integer.parseInt(tf_bet.getText());
            l_credits.setText(String.valueOf(bet + credits));
        }

        if (tf_codes.getText().equals("getCred")) {
            l_credits.setText(String.valueOf(tf_bet.getText()));
        }
    }

    public Integer bet2() {
        int tens = 1;
        for (int i = 1; i < tf_bet.getLength(); i++) {
            tens *= 10;
        }
        return tens;
    }

    public Integer bet1() {
        int tens = 1;
        for (int i = 2; i < tf_bet.getLength(); i++) {
            tens *= 10;
        }
        return tens;
    }


}


