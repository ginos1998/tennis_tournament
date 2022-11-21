package dux.tennis_tournament.controllers;

import dux.tennis_tournament.models.Match;
import dux.tennis_tournament.models.Player;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TournamentController extends Controller implements Initializable {

    @FXML
    private Label errorMessage;
    @FXML
    private TextField player1NameField;
    @FXML
    private TextField player2NameField;
    @FXML
    private TextField tournamentNameField;
    @FXML
    private Label probP1;
    @FXML
    private Label probP2;
    @FXML
    private Slider probSlider;
    @FXML
    private ChoiceBox choiceNumSets;
    private Player player_1;
    private Player player_2;
    private final int STR_MAX_LENGTH = 8;

    public TournamentController(){
        //tournament = new Tournament();
        player_1 = new Player();
        player_2 = new Player();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SliderListener();
        ChoiceBoxNumSets();
    }

    private void SliderListener(){
        updateSlider();
        probSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                updateSlider();
            }
        });
    }

    private void updateSlider(){
        player_1.setProbToWin(100 - (int) probSlider.getValue());
        player_2.setProbToWin((int) probSlider.getValue());
        probP1.setText(player_1.getProbToWin() + " %");
        probP2.setText(player_2.getProbToWin() + " %");
    }

    private void ChoiceBoxNumSets(){
        Integer[] sets = {3, 5};
        choiceNumSets.getItems().addAll(sets);
        choiceNumSets.setOnAction(this::setNumSets);
    }

    private void setNumSets(Event event) {
        tournament.setNum_sets((Integer) choiceNumSets.getValue());
    }

    public void startMatch(ActionEvent event) throws IOException {
        if(setFieldBox()){
            errorMessage.setVisible(false);
            match = new Match(player_1, player_2);
            this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/match-view.fxml"));
            this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            this.scene = new Scene(this.parent);
            this.stage.setScene(this.scene);
            this.stage.show();
        }
        else errorMessage.setVisible(true);
    }

    private boolean setFieldBox(){

        if(tournamentNameField.getText().length() >= STR_MAX_LENGTH
            && player1NameField.getText().length() >= STR_MAX_LENGTH
            && player2NameField.getText().length() >= STR_MAX_LENGTH){

            tournament.setName(tournamentNameField.getText());
            player_1.setName(player1NameField.getText());
            player_2.setName(player2NameField.getText());

            return true;
        }
        else return false;
    }
    @Override
    @FXML
    void salir(ActionEvent event){ System.exit(0);}


}
