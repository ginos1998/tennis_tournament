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
    private String points;
    private int pointsWon;
    private ArrayList<Integer> prevSetsWon;
    private ArrayList<String> gamePoints;

    /**
     * Constructor to initialize player's attributes
     */
    public Player() {
        prevSetsWon = new ArrayList<>();
        gamePoints = new ArrayList<>();
        gamePoints.add("00");
        gamePoints.add("15");
        gamePoints.add("30");
        gamePoints.add("40");
        gamePoints.add("AD");
        gamePoints.add("-");
        setName("none");
        setWinner(false);
        setServeTurn(false);
        setProbToWin(0);
        reset();
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

    public String getPoints() {
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
    public void resetGamesWon(){
        this.gamesWon = 0;
    }
    public void addSetsWon() {
        this.setsWon++;
    }

    public void addGamesWon() {
        this.gamesWon++;
    }

    public void addPoints() {
        this.points = gamePoints.get(pointsWon);
        pointsWon++;
        if(pointsWon == gamePoints.size())  pointsWon = 0;
    }

    public void resetPoint(){
        this.pointsWon = 0;
        this.points = gamePoints.get(pointsWon);
    }

    public void removeAdvantage(){
        pointsWon-=2;
        this.points = gamePoints.get(pointsWon);
    }

    public void reset(){
        points = gamePoints.get(0);
        serveTurn = false;
        winner = false;
        setsWon = 0;
        gamesWon = 0;
        pointsWon = 0;
    }





}
