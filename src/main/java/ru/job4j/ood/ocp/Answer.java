package ru.job4j.ood.ocp;

public class Answer {

    private String title = "Ответ от трактариста";
    private String code = "300";

    public String getAnswer(String ask) {
        String rsl = "Ответа нет";
        if (ask.endsWith(code)) {
            rsl = title;
        }
        return rsl;
    }
}
