package dux.tennis_tournament.models;

import java.util.ArrayList;

/**
 *  Player class
 * @author ginos
 * @version 1.0.0
 */
public class Player {

    private boolean serveTurn;
    private boolean winner;
    private String name;
    private int probToWin;
    private int setsWon;
    private int gamesWon;
    private int points;
    private ArrayList<Integer> prevSetsWon;

    /**
     * Constructor to initialize player's attributes
     * @param name
     */
    public Player() {
        setName("none");
        setPoints(0);
        setGamesWon(0);
        setWinner(false);
        setServeTurn(false);
        setProbToWin(0);
        setSetsWon(0);
        prevSetsWon = new ArrayList<>();
    }

    // ---------------- Getter methods ---------------- //

    public boolean isServeTurn() {
        return serveTurn;
    }

    public boolean isWinner() {
        return winner;
    }

    public String getName() {
        return name;
    }

    public int getProbToWin() {
        return probToWin;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Integer> getPrevSetsWon() {
        return prevSetsWon;
    }

    // ---------------- Setter methods ---------------- //

    public void setServeTurn(boolean serveTurn) {
        this.serveTurn = serveTurn;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProbToWin(int probToWin) {
        if(probToWin >=1 && probToWin <= 100)
            this.probToWin = probToWin;
        else
            this.probToWin = 50;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPrevSetsWon(ArrayList<Integer> prevSetsWon) {
        this.prevSetsWon = prevSetsWon;
    }




}
