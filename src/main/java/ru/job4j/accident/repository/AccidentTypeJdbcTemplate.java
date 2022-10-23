package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {

    private final JdbcTemplate jdbc;

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select * from accident_type",
                (rs, row) -> {
                    var accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }
}
