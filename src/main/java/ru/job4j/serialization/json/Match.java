package ru.job4j.serialization.json;

public class Match {
    private String opponent;
    private int scored;
    private int conceded;

    public Match(String opponent, int scored, int conceded) {
        this.opponent = opponent;
        this.scored = scored;
        this.conceded = conceded;
    }

    @Override
    public String toString() {
        return "Match{"
                + "opponent='" + opponent + '\''
                + ", scored=" + scored
                + ", conceded=" + conceded
                + '}';
    }
}
