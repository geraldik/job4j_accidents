package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RuleMem {

    private final HashMap<Integer, Rule> rules = new HashMap<>();

    public RuleMem() {
        initRules();
    }

    private void initRules() {
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules.values());
    }

    public List<Rule> getByIds(List<Integer> ids) {
        return ids.stream()
                .map(rules::get)
                .collect(Collectors.toList());
    }
}
