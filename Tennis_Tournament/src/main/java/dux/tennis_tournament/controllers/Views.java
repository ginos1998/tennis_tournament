package dux.tennis_tournament.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Views extends Controller implements Initializable{
    /**
     * No constructor needed
     */
    public Views() {}
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {}

    /**
     * Open tournament-view when user click on "Iniciar Torneo" button
     *
     * @param event button event
     * @throws IOException exception handler for javafx.fxml.FXMLLoader.load()
     */
    public void startTournament(ActionEvent event) throws IOException {
        this.parent = FXMLLoader.load(getClass().getResource("/dux/tennis_tournament/tournament-view.fxml"));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.parent);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    /**
     * Close program window and stop it
     *
     * @param event button event
     */
    @Override
    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

}
