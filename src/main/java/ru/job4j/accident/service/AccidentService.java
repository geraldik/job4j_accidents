package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
        initAccidents();
    }

    private void initAccidents() {
        Accident accident1 = new Accident(1, "Проезд на запрещающий сигнал светофора",
                "Водитель проехал перекресток на красный цвет светофора",
                "г. Москва, ул. Арбат, д.1");
        Accident accident2 = new Accident(2, "Превышение скорости от 20 до 40 км/ч",
                "Водитель ехал со скоростью 65 км/ч на участке с разрешенной скоростью 40 км/ч",
                "г. Москва, Варшавское шоссе, д.58");
        Accident accident3 = new Accident(3, "Не соблюдение требований дорожной разметки",
                "Водитель выполнил разворот через сплошную линию",
                "г. Москва, ул.Нагорный проезд, д.13");
        this.add(accident1);
        this.add(accident2);
        this.add(accident3);
    }

    public void add(Accident accident) {
        accidentMem.add(accident);
    }

    public List<Accident> findAll() {
        return accidentMem.findAll()
                .stream()
                .toList();
    }
}
