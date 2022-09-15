package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "match")
public class Match {
    @XmlAttribute
    private String opponent;
    @XmlAttribute
    private int scored;
    @XmlAttribute
    private int conceded;

    public Match() {

    }

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

