package dux.tennis_tournament.controllers;

import dux.tennis_tournament.models.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MatchController extends Controller implements Initializable {
    public Label namePlayer1;
    public Label namePlayer2;
    public ImageView tennisBall1;
    public ImageView tennisBall2;
    public Label pointsPlayer1;
    public Label pointsPlayer2;
    public Label gamesPlayer1;
    public Label gamesPlayer2;
    public Label setsPlayer2;
    public Label setsPlayer1;
    public Label set1Player1;
    public Label set2Player1;
    public Label set3Player1;
    public Label set4Player1;
    public Label set5Player1;
    public Label set1Player2;
    public Label set2Player2;
    public Label set3Player2;
    public Label set4Player2;
    public Label set5Player2;
    private Match match;
    public MatchController(){
        match = new Match();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void salir(ActionEvent event){ System.exit(0);}
}
