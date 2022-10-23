package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.List;

@Service
public class RuleService {

    private final RuleJdbcTemplate ruleJdbcTemplate;

    public RuleService(RuleJdbcTemplate ruleJdbcTemplate) {
        this.ruleJdbcTemplate = ruleJdbcTemplate;
    }

    public List<Rule> getRules() {
        return ruleJdbcTemplate.getRules();
    }
}
