package dux.tennis_tournament.controllers;

import dux.tennis_tournament.controllers.Controller;
import dux.tennis_tournament.controllers.TournamentController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Views extends Controller implements Initializable{

    public Views() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void startTournament(ActionEvent event) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/tournament-view.fxml"));
        //this.parent = FXMLLoader.load(getClass().getResource("tournament-view.fxml"));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.parent);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void salir(ActionEvent event){ System.exit(0);}
}
