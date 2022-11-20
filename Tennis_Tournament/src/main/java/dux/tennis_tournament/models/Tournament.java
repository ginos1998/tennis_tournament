package dux.tennis_tournament.models;

public class Tournament {
    private String name;
    private Player winner;
    private int num_sets;

    public Tournament() {
        setName("none");
        setWinner(null);
        setNum_sets(0);
    }

    // ---------------- Getter methods ---------------- //

    public String getName() {
        return name;
    }

    public Player getWinner() {
        return winner;
    }

    public int getNum_sets() {
        return num_sets;
    }

    // ---------------- Setter methods ---------------- //

    public void setName(String name) {
        this.name = name;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setNum_sets(int num_sets) {
        this.num_sets = num_sets;
    }
}
