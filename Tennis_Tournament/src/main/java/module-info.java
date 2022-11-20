module dux.tennis_tournament {
    requires javafx.controls;
    requires javafx.fxml;

    opens dux.tennis_tournament.models;
    exports dux.tennis_tournament.models;

    opens dux.tennis_tournament.controllers to javafx.fxml;
    exports dux.tennis_tournament.controllers;

    opens dux.tennis_tournament to javafx.fxml;
    exports dux.tennis_tournament;

}