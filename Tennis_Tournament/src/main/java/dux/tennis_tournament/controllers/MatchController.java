package dux.tennis_tournament.controllers;

import dux.tennis_tournament.models.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MatchController extends Controller implements Initializable {
    @FXML
    private Button rematchButton;
    @FXML
    private Button homeButton;
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
    private final int TIME_DELAY = 200;
    private Timer timer;
    private TimerTask task;
    private ArrayList<ArrayList<Label>> setsLabels;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeLabels();
        tennisBall1.setVisible(false);
        tennisBall1.setVisible(false);

        startMatch();
    }

    private void initializeLabels(){

        ArrayList<Label> playerName = new ArrayList<>(Arrays.asList(
                namePlayer1,
                namePlayer2
        ));

        ArrayList<Label> setsLabelsLocal = new ArrayList<>(Arrays.asList(
                set1Player1, set2Player1, set3Player1, set4Player1, set5Player1
        ));

        ArrayList<Label> setsLabelsVisitant = new ArrayList<>(Arrays.asList(
                set1Player2, set2Player2, set3Player2, set4Player2, set5Player2
        ));

        setsLabels = new ArrayList<>(Arrays.asList(setsLabelsLocal, setsLabelsVisitant));

        for (int i = 0; i<match.getPlayers().size(); i++)
            playerName.get(i).setText(match.getPlayer(i).getName());

        tourNameLabel.setText(tournament.getName());
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
            } else if (match.getPlayer(0).getPoints().equals("AD") && match.getPlayer(1).getPoints().equals("AD")) {
                for (Player p: match.getPlayers()) {
                    System.out.println("in " + p.getPoints());
                }
                match.getPlayer(0).removeAdvantage();
                match.getPlayer(1).removeAdvantage();

                for (Player p: match.getPlayers()) {
                    System.out.println("out " + p.getPoints());
                }
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
        if(match.getPlayer(0).getGamesWon() == 6){
            updateSetsTable();
            match.getPlayer(0).resetGamesWon();
            match.getPlayer(1).resetGamesWon();
            match.getPlayer(0).addSetsWon();
        }
        else if (match.getPlayer(1).getGamesWon() == 6){
            updateSetsTable();
            match.getPlayer(0).resetGamesWon();
            match.getPlayer(1).resetGamesWon();
            match.getPlayer(1).addSetsWon();
        }
    }

    private void updateSetsTable(){
        int label = match.getPlayer(0).getSetsWon() + match.getPlayer(1).getSetsWon();

        for(int player=0; player<match.getPlayers().size(); player++){
            setsLabels.get(player).get(label).setVisible(true);
            setsLabels.get(player).get(label).setText(Integer.toString(match.getPlayer(player).getGamesWon()));
        }


        /*player1SetsLabels.get(n).setVisible(true);
        player2SetsLabels.get(n).setVisible(true);
        player1SetsLabels.get(n).setText(Integer.toString(match.getPlayer(0).getGamesWon()));
        player2SetsLabels.get(n).setText(Integer.toString(match.getPlayer(1).getGamesWon()));
        n++;*/
    }

    private void checkSets(){

        if((match.getPlayer(0).getSetsWon() > tournament.getNum_sets()/2
            && tournament.getNum_sets() - match.getPlayer(0).getSetsWon()
            < tournament.getNum_sets() - match.getPlayer(1).getSetsWon())
            || (match.getPlayer(1).getSetsWon() > tournament.getNum_sets()/2
                && tournament.getNum_sets() - match.getPlayer(1).getSetsWon()
                < tournament.getNum_sets() - match.getPlayer(0).getSetsWon())){

            for(Player p: match.getPlayers())
                p.reset();

            rematchButton.setVisible(true);
            homeButton.setVisible(true);
            task.cancel();
            timer.cancel();
        }
        else if(match.getPlayer(0).getSetsWon() + match.getPlayer(1).getSetsWon() == tournament.getNum_sets()){
            for(Player p: match.getPlayers())
                p.reset();

            rematchButton.setVisible(true);
            homeButton.setVisible(true);
            task.cancel();
            timer.cancel();

        }
    }
    @Override
    @FXML
    void salir(ActionEvent event){
        if(match.isMatchRunning()) {
            match.disableMatchRunning();
            task.cancel();
            timer.cancel();
        }
        System.exit(0);
    }

    public void rematch(ActionEvent event) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/match-view.fxml"));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.parent);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void backToHome(ActionEvent event) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/tournament-view.fxml"));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.parent);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

}
