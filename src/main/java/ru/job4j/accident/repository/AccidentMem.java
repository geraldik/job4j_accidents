package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    private final  HashMap<Integer, AccidentType> accidentTypes = new HashMap<>();

    private final AtomicInteger id = new AtomicInteger(0);

    public AccidentMem() {
        initAccidentTypes();
    }

    public void add(Accident accident) {
        accident.setId(id.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType findAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    private void initAccidentTypes() {
        accidentTypes.put(1, new AccidentType(1, "Две машины"));
        accidentTypes.put(2, new AccidentType(2, "Машина и человек"));
        accidentTypes.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public List<AccidentType> getAccidentTypes() {
        return new ArrayList<>(accidentTypes.values());
    }
}
