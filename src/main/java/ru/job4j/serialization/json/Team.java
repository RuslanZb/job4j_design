package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.Arrays;

public class Team {
    private boolean qualified;
    private int point;
    private String name;
    private String[] lineUp;
    private Match match;

    public Team(boolean qualified, int point, String name, String[] lineUp,
                Match match) {
        this.qualified = qualified;
        this.point = point;
        this.name = name;
        this.lineUp = lineUp;
        this.match = match;
    }

    @Override
    public String toString() {
        return "Team{"
                + "qualified=" + qualified
                + ", point=" + point
                + ", name='" + name + '\''
                + ", lineUp=" + Arrays.toString(lineUp)
                + ", match=" + match
                + '}';
    }

    public boolean isQualified() {
        return qualified;
    }

    public int getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public String[] getLineUp() {
        return lineUp;
    }

    public Match getMatch() {
        return match;
    }

    public static void main(String[] args) {
        Team team = new Team(true, 3, "RD", new String[]{"Garry", "Ron"},
                new Match("NY", 6, 3));
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(team));
        String teamJson = "{"
                + "\"qualified\":false,"
                + "\"point\":4,"
                + "\"name\":\"LO\","
                + "\"lineUp\":[\"Vas\",\"Jay\"],"
                + "\"match\":{\"opponent\":\"MS\",\"scored\":2,\"conceded\":2}"
                + "}";
        Team teamFromJson = gson.fromJson(teamJson, Team.class);
        System.out.println(teamFromJson);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("qualified", team.isQualified());
        jsonObject.put("point", team.getPoint());
        jsonObject.put("name", team.getName());
        jsonObject.put("lineUp", team.getLineUp());
        jsonObject.put("match", new JSONObject(team.getMatch()));
        System.out.println(jsonObject);
        System.out.println(new JSONObject(team));
    }
}
