package ru.job4j.ood.srp.report;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJson implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportJson(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JSONArray jsonArray = new JSONArray();
        for (Employee employee : store.findBy(filter)) {
            jsonArray.put(new JSONObject()
                    .put("Name", employee.getName())
                    .put("Hired", dateTimeParser.parse(employee.getHired()))
                    .put("Fired", dateTimeParser.parse(employee.getFired()))
                    .put("Salary", String.valueOf(employee.getSalary()))
            );
        }
        return jsonArray.toString();
    }
}
