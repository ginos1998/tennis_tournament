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
    // ---------------- private @FXML fields ---------------- //
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

    // ---------------- private fields ---------------- //
    private final int TIME_DELAY = 200;
    private final int MAX_GAMES = 6;
    private Timer timer;
    private TimerTask task;
    private ArrayList<ArrayList<Label>> setsLabels;
    private ArrayList<Label> playerGamesLabel;
    private ArrayList<Label> playerPointsLabel;
    private ArrayList<Label> playerSetsLabel;
    private ArrayList<ImageView> tennisBallImage;

    /**
     * Invoked when this controller initialize
     * after configure labels properties,
     * start the match simulation
     *
     * @param url idk
     * @param resourceBundle idk
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeLabels();
        tennisBall1.setVisible(false);
        tennisBall1.setVisible(false);

        startMatch();
    }

    /**
     * Creates an ArrayList for each label group
     * and initialize labels fields
     */
    private void initializeLabels(){

        ArrayList<Label> playerName = new ArrayList<>(Arrays.asList(
                namePlayer1, namePlayer2
        ));

        ArrayList<Label> setsLabelsLocal = new ArrayList<>(Arrays.asList(
                set1Player1, set2Player1, set3Player1, set4Player1, set5Player1
        ));

        ArrayList<Label> setsLabelsVisitant = new ArrayList<>(Arrays.asList(
                set1Player2, set2Player2, set3Player2, set4Player2, set5Player2
        ));

        setsLabels = new ArrayList<>(Arrays.asList(setsLabelsLocal, setsLabelsVisitant));
        playerGamesLabel = new ArrayList<>(Arrays.asList(gamesPlayer1, gamesPlayer2));
        playerPointsLabel = new ArrayList<>(Arrays.asList(pointsPlayer1, pointsPlayer2));
        playerSetsLabel = new ArrayList<>(Arrays.asList(setsPlayer1, setsPlayer2));
        tennisBallImage = new ArrayList<>(Arrays.asList(tennisBall1, tennisBall2));

        for (int i = 0; i<match.getPlayers().size(); i++)
            playerName.get(i).setText(match.getPlayer(i).getName());

        tourNameLabel.setText(tournament.getName());
    }

    /**
     * Initialize a Timer to simulate the match (i.e, task)
     * with an init delay of 1 second and run() method repeats every TIME_DELAY
     */
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

    /**
     * Set visible (or not) tennis ball image,
     * depending on witch player has the serve turn
     */
    private void changeAnimation(){
        for(int index=0; index<match.getPlayers().size(); index++){
            tennisBallImage.get(index).setVisible(match.getPlayer(index).isServeTurn());
        }
    }

    /**
     * Check players points
     * When some player reach 40 points and game is not in deuce,
     * reset player points and increase winner games
     * In deuce case, the next iteration checks if there is a winner
     * but in case AD-AD, remove advantage (remove points) from both players
     * and its repeats until one player get advantage of 2 over the other
     */
    private void checkPoints(){
        if (match.getPlayer(0).getPoints().equals("40") && match.getPlayer(1).getPoints().equals("40")){
            match.setDeuce(true);
        }

        if(!match.isDeuce()){
            if (match.getPlayer(0).getPoints().equals("AD") && !match.getPlayer(1).getPoints().equals("AD")){
                for(Player p: match.getPlayers())
                    p.resetPoint();

                match.getPlayer(0).addGamesWon();
            }
            else if(match.getPlayer(1).getPoints().equals("AD") && !match.getPlayer(0).getPoints().equals("AD")){
                for(Player p: match.getPlayers())
                    p.resetPoint();

                match.getPlayer(1).addGamesWon();
            }
        }
        else {
            if (match.getPlayer(0).getPoints().equals("-") && !match.getPlayer(1).getPoints().equals("-")){
                for(Player p: match.getPlayers())
                    p.resetPoint();

                match.getPlayer(0).addGamesWon();
                match.setDeuce(false);
            }
            else if(match.getPlayer(1).getPoints().equals("-") && !match.getPlayer(0).getPoints().equals("-")){
                for(Player p: match.getPlayers())
                    p.resetPoint();

                match.getPlayer(1).addGamesWon();
                match.setDeuce(false);
            } else if (match.getPlayer(0).getPoints().equals("AD") && match.getPlayer(1).getPoints().equals("AD")) {
                for(Player p: match.getPlayers())
                    p.removeAdvantage();
            }
        }

    }

    /**
     * Generate random number, between 1-99
     * and add points according players probabilities to win
     */
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

    /**
     * Update graphic values: number of sets, games and points
     * for each player
     */
    private void updateTable(){

        for (int index=0; index<match.getPlayers().size(); index++){
            playerSetsLabel.get(index).setText(Integer.toString(match.getPlayer(index).getSetsWon()));
            playerGamesLabel.get(index).setText(Integer.toString(match.getPlayer(index).getGamesWon()));
            playerPointsLabel.get(index).setText(match.getPlayer(index).getPoints());
        }
    }

    /**
     * Check if some player reaches MAX_GAMES
     * and in positive case, increase the number of sets
     * to the corresponding winner
     */
    private void checkGames(){
        for(Player p: match.getPlayers()){
            if(p.getGamesWon() == MAX_GAMES){
                updateSetsTable();
                p.addSetsWon();
                for(Player pp: match.getPlayers())
                    pp.resetGamesWon();
            }
        }
    }

    /**
     * Set visible the corresponding set label and update its value
     * when a player wins a set
     */
    private void updateSetsTable(){
        int label = match.getPlayer(0).getSetsWon() + match.getPlayer(1).getSetsWon();

        for(int player=0; player<match.getPlayers().size(); player++){
            setsLabels.get(player).get(label).setVisible(true);
            setsLabels.get(player).get(label).setText(Integer.toString(match.getPlayer(player).getGamesWon()));
        }
    }

    /**
     * Checks if some player has advantage, according the number of NumSets
     * For example, NumSets = 3 so if player1 has 2 sets, he has advantage and
     * there is no sense to play the last set. Same if NumSets = 5 and
     * player1 has 3 sets or 4 sets and player2 has 1 set.
     */
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

    /**
     * Check if Timer has a thread running and,
     * in this case kill that thread and
     * program exits normally and safely
     * @param event buton event
     */
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

    /**
     * Change to match-view scene to rematch
     *
     * @param event button event
     * @throws IOException  exception handler for javafx.fxml.FXMLLoader.load()
     */
    public void rematch(ActionEvent event) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/match-view.fxml"));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.parent);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    /**
     * Change to tournament-view scene to start a new match
     *
     * @param event button event
     * @throws IOException exception handler for javafx.fxml.FXMLLoader.load()
     */
    public void backToHome(ActionEvent event) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/tournament-view.fxml"));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.parent);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

}
