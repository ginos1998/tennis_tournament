package dux.tennis_tournament.controllers;

import dux.tennis_tournament.models.Match;
import dux.tennis_tournament.models.Player;
import dux.tennis_tournament.models.Tournament;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {
    protected Parent parent;
    protected Scene scene;
    protected Stage stage;
    protected static Tournament tournament = new Tournament();
    protected static Match match;
    abstract void salir(ActionEvent event);
}
