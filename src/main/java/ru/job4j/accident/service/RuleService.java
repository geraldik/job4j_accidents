package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.List;

@Service
public class RuleService {

    private final RuleMem ruleMem;

    public RuleService(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    public List<Rule> getRules() {
        return ruleMem.getRules();
    }

    public List<Rule> getByIds(List<Integer> ids) {
        return ruleMem.getByIds(ids);
    }
}
