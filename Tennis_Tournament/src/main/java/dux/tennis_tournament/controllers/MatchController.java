package dux.tennis_tournament.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;

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
    private final int TIME_DELAY = 250;
    private Timer timer;
    private TimerTask task;
    private ArrayList<Label> player1SetsLabels;
    private ArrayList<Label> player2SetsLabels;
    private int n = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        player1SetsLabels = new ArrayList<>();
        player1SetsLabels.add(set1Player1);
        player1SetsLabels.add(set2Player1);
        player1SetsLabels.add(set3Player1);
        player1SetsLabels.add(set4Player1);
        player1SetsLabels.add(set5Player1);

        player2SetsLabels = new ArrayList<>();
        player2SetsLabels.add(set1Player2);
        player2SetsLabels.add(set2Player2);
        player2SetsLabels.add(set3Player2);
        player2SetsLabels.add(set4Player2);
        player2SetsLabels.add(set5Player2);
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
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> checkSets());
                Platform.runLater(() -> match.randomServe());
                Platform.runLater(() -> changeAnimation());
                Platform.runLater(() -> updateTable());
                Platform.runLater(() -> generatePoints());
                Platform.runLater(() -> checkPoints());
                Platform.runLater(() -> checkGames());
            }
        };

        timer.scheduleAtFixedRate(task, 1000, TIME_DELAY);
    }
    private void checkPoints(){
        if (match.getPlayer(0).getPoints().equals("40") && match.getPlayer(1).getPoints().equals("40")){
            match.setDeuce(true);
            System.out.println("deuce");
        }

        if(!match.isDeuce()){
            if (match.getPlayer(0).getPoints().equals("AD") && !match.getPlayer(1).getPoints().equals("AD")){
                match.getPlayer(0).resetPoint();
                match.getPlayer(1).resetPoint();
                match.getPlayer(0).addGamesWon();
            }
            else if(match.getPlayer(1).getPoints().equals("AD") && !match.getPlayer(0).getPoints().equals("AD")){
                match.getPlayer(1).resetPoint();
                match.getPlayer(0).resetPoint();
                match.getPlayer(1).addGamesWon();
            }
        }
        else {
            if (match.getPlayer(0).getPoints().equals("-") && !match.getPlayer(1).getPoints().equals("-")){
                match.getPlayer(0).resetPoint();
                match.getPlayer(1).resetPoint();
                match.getPlayer(0).addGamesWon();
                match.setDeuce(false);
            }
            else if(match.getPlayer(1).getPoints().equals("-") && !match.getPlayer(0).getPoints().equals("-")){
                match.getPlayer(1).resetPoint();
                match.getPlayer(0).resetPoint();
                match.getPlayer(1).addGamesWon();
                match.setDeuce(false);
            }
        }

    }
    private void generatePoints(){

        Random random = new Random();
        int rdm_int = random.nextInt(1, 99);

        if(rdm_int < match.getPlayer(0).getProbToWin()){
            match.getPlayer(0).addPoints();
        }
        else{
            match.getPlayer(1).addPoints();
        }
    }

    private void updateTable(){
        setsPlayer1.setText(Integer.toString(match.getPlayer(0).getSetsWon()));
        gamesPlayer1.setText(Integer.toString(match.getPlayer(0).getGamesWon()));
        pointsPlayer1.setText(match.getPlayer(0).getPoints());

        setsPlayer2.setText(Integer.toString(match.getPlayer(1).getSetsWon()));
        gamesPlayer2.setText(Integer.toString(match.getPlayer(1).getGamesWon()));
        pointsPlayer2.setText(match.getPlayer(1).getPoints());



    }

    private void checkGames(){
        if(match.getPlayer(0).getGamesWon() == 7){
            updateSetsTable();
            match.getPlayer(0).resetGamesWon();
            match.getPlayer(1).resetGamesWon();
            match.getPlayer(0).addSetsWon();
        }
        else if (match.getPlayer(1).getGamesWon() == 7){
            updateSetsTable();
            match.getPlayer(0).resetGamesWon();
            match.getPlayer(1).resetGamesWon();
            match.getPlayer(1).addSetsWon();
        }
    }

    private void updateSetsTable(){
        player1SetsLabels.get(n).setVisible(true);
        player2SetsLabels.get(n).setVisible(true);
        player1SetsLabels.get(n).setText(Integer.toString(match.getPlayer(0).getGamesWon()));
        player2SetsLabels.get(n).setText(Integer.toString(match.getPlayer(1).getGamesWon()));
        n++;
    }

    private void checkSets(){

        if((match.getPlayer(0).getSetsWon() > tournament.getNum_sets()/2
            && tournament.getNum_sets() - match.getPlayer(0).getSetsWon()
            < tournament.getNum_sets() - match.getPlayer(1).getSetsWon())
            || (match.getPlayer(1).getSetsWon() > tournament.getNum_sets()/2
                && tournament.getNum_sets() - match.getPlayer(1).getSetsWon()
                < tournament.getNum_sets() - match.getPlayer(0).getSetsWon())){

            task.cancel();
            timer.cancel();
            System.out.println("yah, already finished");
        }
        else if(match.getPlayer(0).getSetsWon() + match.getPlayer(1).getSetsWon() == tournament.getNum_sets()){
            task.cancel();
            timer.cancel();
            System.out.println("yah, already finished");
        }


    }

    public void salir(ActionEvent event){ System.exit(0);}
}
