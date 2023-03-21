package ru.job4j.ood.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class SimpleGeneratorTest {

    @Test
    public void whenProduceThenEqualToExpected() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Брюс ли");
        map.put("subject","you");
        assertThat(generator.produce(template, map)).isEqualTo("I am a Брюс ли, Who are you? ");
    }

    @Test
    public void whenMapHasNoKeysThenGetException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Брюс ли");
        map.put("object","you");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateHasNoKeysThenGetException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Брюс ли");
        map.put("subject","you");
        map.put("object","he");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    public void whenTemplateIsIncorrectThenGetException() {
        String template = "I am a *{name}, Who are *{subject}? ";
        Generator generator = new SimpleGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Брюс ли");
        map.put("subject","you");
        assertThatThrownBy(() -> generator.produce(template, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

}