package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public int findMaxId() {
        return accidents.keySet().stream()
                .mapToInt(value -> value)
                .max()
                .orElse(1);
    }
}
