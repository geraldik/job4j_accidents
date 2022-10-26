package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeHibernate;

import java.util.List;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeHibernate accidentTypeHibernate;

    public List<AccidentType> getAccidentTypes() {
        return accidentTypeHibernate.getAccidentTypes();
    }
}
