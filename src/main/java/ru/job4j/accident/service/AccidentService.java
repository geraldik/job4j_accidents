package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentsRepository;

    public void save(Accident accident) {
        accidentsRepository.save(accident);
    }

    public List<Accident> findAll() {
        List<Accident> accidents = new ArrayList<>();
        accidentsRepository.findAll()
                .forEach(accidents::add);
        return accidents;
    }

    public Accident findById(int id) {
        return accidentsRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public void update(Accident accident) {
        accidentsRepository.save(accident);
    }
}