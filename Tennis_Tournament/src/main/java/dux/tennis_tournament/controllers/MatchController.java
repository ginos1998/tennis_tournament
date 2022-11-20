package dux.tennis_tournament.controllers;

import dux.tennis_tournament.models.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    public MatchController(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        match.randomServe();
        initializeLabels();
        initializeAnimations();

    }

    private void initializeLabels(){
        tourNameLabel.setText(tournament.getName());
        namePlayer1.setText(match.getPlayer(0).getName());
        namePlayer2.setText(match.getPlayer(1).getName());
    }

    private void initializeAnimations(){
        if(match.getPlayer(0).isServeTurn())    tennisBall1.setVisible(true);
        else tennisBall2.setVisible(true);

    }
    public void salir(ActionEvent event){ System.exit(0);}
}
