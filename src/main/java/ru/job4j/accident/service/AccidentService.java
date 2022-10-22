package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public void save(Accident accident) {
        accidentJdbcTemplate.save(accident);
    }

    public List<Accident> findAll() {
        return accidentJdbcTemplate.findAll();
    }

    public Accident findById(int id) {
        return accidentJdbcTemplate
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public boolean update(Accident accident) {
        return accidentJdbcTemplate.update(accident);
    }

    public List<Rule> getRulesByIds(String[] rIds) {
        return accidentJdbcTemplate.getRulesByIds(rIds);
    }
}