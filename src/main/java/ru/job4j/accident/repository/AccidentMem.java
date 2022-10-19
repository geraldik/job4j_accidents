package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    private final AtomicInteger id = new AtomicInteger(0);

    public void add(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }
}