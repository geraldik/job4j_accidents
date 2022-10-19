package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.List;

@Service
public class AccidentTypeService {

    private final AccidentTypeMem accidentTypeMem;

    public AccidentTypeService(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentTypeMem.findAccidentTypeById(id);
    }

    public List<AccidentType> getAccidentTypes() {
        return accidentTypeMem.getAccidentTypes();
    }
}
