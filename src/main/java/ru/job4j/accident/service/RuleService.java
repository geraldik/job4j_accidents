package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll()
                .forEach(rules::add);
        return rules;
    }

    public Rule findById(int id) {
        return ruleRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}

