package dux.tennis_tournament.models;

import java.util.ArrayList;

public class Match {

    private boolean deuce;
    private boolean tieBreak;
    private int currentSets;
    private String tournamentName;
    private ArrayList<Player> players;


    public Match() {
        players = new ArrayList<>();
        players.add(null);
        players.add(null);
        setDeuce(false);
        setCurrentSets(0);
        setTieBreak(false);
        setTournamentName("");
    }

    // ---------------- Getter methods ---------------- //

    public boolean isDeuce() {
        return deuce;
    }

    public boolean isTieBreak() {
        return tieBreak;
    }

    public int getCurrentSets() {
        return currentSets;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    // ---------------- Setter methods ---------------- //
    public void setDeuce(boolean deuce) {
        this.deuce = deuce;
    }

    public void setTieBreak(boolean tieBreak) {
        this.tieBreak = tieBreak;
    }

    public void setCurrentSets(int currentSets) {
        this.currentSets = currentSets;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
