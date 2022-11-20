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
    private int pointsWon;
    private ArrayList<Integer> prevSetsWon;
    private ArrayList<Integer> gamePoints;

    /**
     * Constructor to initialize player's attributes
     */
    public Player() {
        gamePoints = new ArrayList<>();
        gamePoints.add(15);
        gamePoints.add(30);
        gamePoints.add(40);
        gamePoints.add(50);
        setName("none");
        setWinner(false);
        setServeTurn(false);
        setProbToWin(0);
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

    public void addSetsWon() {
        this.setsWon++;
    }

    public void addGamesWon() {
        this.gamesWon++;
    }

    public void addPoints() {
        this.points += 10;
        //this.points = gamePoints.get(pointsWon);
        pointsWon++;
    }

    public void resetPoint(){
        this.pointsWon = 0;
        this.points = 0;
    }

    public void setPrevSetsWon(ArrayList<Integer> prevSetsWon) {
        this.prevSetsWon = prevSetsWon;
    }




}
