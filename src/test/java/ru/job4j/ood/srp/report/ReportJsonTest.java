package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJsonTest {

    @Test
    public void whenGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report report = new ReportJson(store, parser);
        String expect = String.format("[{\"Salary\":\"%s\",\"Fired\":\"%s\",\"Hired\":\"%s\",\"Name\":\"%s\"}]",
               worker.getSalary(), parser.parse(worker.getFired()), parser.parse(worker.getHired()),
                worker.getName());
        assertThat(report.generate(em -> true)).isEqualTo(expect);
    }

}