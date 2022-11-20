package dux.tennis_tournament.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MatchController extends Controller implements Initializable {
    @FXML
    private Label namePlayer1;
    @FXML
    private Label namePlayer2;
    @FXML
    private ImageView tennisBall1;
    @FXML
    private ImageView tennisBall2;
    @FXML
    private Label pointsPlayer1;
    @FXML
    private Label pointsPlayer2;
    @FXML
    private Label gamesPlayer1;
    @FXML
    private Label gamesPlayer2;
    @FXML
    private Label setsPlayer2;
    @FXML
    private Label setsPlayer1;
    @FXML
    private Label set1Player1;
    @FXML
    private Label set2Player1;
    @FXML
    private Label set3Player1;
    @FXML
    private Label set4Player1;
    @FXML
    private Label set5Player1;
    @FXML
    private Label set1Player2;
    @FXML
    private Label set2Player2;
    @FXML
    private Label set3Player2;
    @FXML
    private Label set4Player2;
    @FXML
    private Label set5Player2;
    @FXML
    private Label tourNameLabel;
    private final int TIME_DELAY = 1000;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        match.randomServe();
        initializeLabels();
        tennisBall1.setVisible(false);
        tennisBall1.setVisible(false);

        System.out.println("prob p1 " + match.getPlayer(0).getProbToWin());
        System.out.println("prob p2 " + match.getPlayer(1).getProbToWin());

        startMatch();
    }

    private void initializeLabels(){
        tourNameLabel.setText(tournament.getName());
        namePlayer1.setText(match.getPlayer(0).getName());
        namePlayer2.setText(match.getPlayer(1).getName());
    }

    private void changeAnimation(){
        if(match.getPlayer(0).isServeTurn()) {
            tennisBall1.setVisible(true);
            tennisBall2.setVisible(false);
        }
        else{
            tennisBall1.setVisible(false);
            tennisBall2.setVisible(true);
        }


    }

    private void startMatch(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> match.randomServe());
                Platform.runLater(() -> changeAnimation());
                Platform.runLater(() -> generatePoints());
                //checkGame();

            }
        };

        timer.scheduleAtFixedRate(task, 1000, TIME_DELAY);
    }
    private void generatePoints(){

        Random random = new Random();
        int rdm_int = random.nextInt(1, 99);

        if(rdm_int < match.getPlayer(0).getProbToWin()){
            match.getPlayer(0).addPoints();
            pointsPlayer1.setText(Integer.toString(match.getPlayer(0).getPoints()));
        }
        else{
            match.getPlayer(1).addPoints();
            pointsPlayer2.setText(Integer.toString(match.getPlayer(1).getPoints()));
        }
    }

    private void checkGame(){
        if (match.getPlayer(0).getPoints() == 50 && match.getPlayer(1).getPoints() < 40){
            match.getPlayer(0).addGamesWon();
            match.getPlayer(0).resetPoint();
            match.getPlayer(1).resetPoint();
        }
        else if(match.getPlayer(1).getPoints() == 50 && match.getPlayer(0).getPoints() < 40){
            match.getPlayer(1).addGamesWon();
            match.getPlayer(1).resetPoint();
            match.getPlayer(0).resetPoint();
        }
    }

    public void salir(ActionEvent event){ System.exit(0);}
}
