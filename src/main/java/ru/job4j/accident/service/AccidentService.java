package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAllAccidents();
    }

    public Accident findAccidentById(int id) {
        return accidentMem.findAccidentById(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentMem.findAccidentTypeById(id);
    }

    public List<AccidentType> findAllAccidentTypes() {
        return accidentMem.findAllAccidentTypes();
    }

    public int incrementAndGet() {
        return accidentMem.incrementAndGet();
    }
}
