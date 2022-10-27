package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AccidentTypeService {

    private final AccidentTypeRepository accidentTypeRepository;

    public List<AccidentType> getAccidentTypes() {
        List<AccidentType> types = new ArrayList<>();
        accidentTypeRepository.findAll()
                .forEach(types::add);
        return types;
    }

    public AccidentType findById(int id) {
        return accidentTypeRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
