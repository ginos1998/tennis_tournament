package dux.tennis_tournament.models;

import java.util.ArrayList;
import java.util.Random;

public class Match {
    private final int MIN_PROB = 1;
    private final int MAX_PROB = 99;
    private boolean deuce;
    private boolean tieBreak;
    private int currentSets;
    private String tournamentName;
    private ArrayList<Player> players;

    private boolean matchRunning;

    public Match(Player player_1, Player player_2) {
        players = new ArrayList<>();
        players.add(player_1);
        players.add(player_2);
        setDeuce(false);
        setCurrentSets(0);
        setTieBreak(false);
        setTournamentName("");
    }

    // ---------------- Getter methods ---------------- //

    public boolean isMatchRunning(){
        return matchRunning;
    }
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

    public Player getPlayer(int player){
        return players.get(player);
    }

    // ---------------- Setter methods ---------------- //
    public void setMatchRunning() {
        matchRunning = true;
    }

    public void disableMatchRunning(){
        matchRunning = false;
    }
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

    // ---------------- utils methods ---------------- //

    public void randomServe(){
        Random random = new Random();
        int rdm_int = random.nextInt(10);
        if(rdm_int <= 5){
            getPlayer(0).setServeTurn(true);
            getPlayer(1).setServeTurn(false);
        }
        else{
            getPlayer(0).setServeTurn(false);
            getPlayer(1).setServeTurn(true);
        }
    }

    /*
    DEJO LA IDEA...
     */
}
