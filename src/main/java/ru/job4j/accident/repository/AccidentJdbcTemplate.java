package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident (name, text, address, accident_type_id) values (?, ?, ?, ?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        int id = keyHolder.getKey().intValue();
        accident.setId(id);
        saveRule(accident.getRules(), id);
        return accident;
    }

    public List<Accident> findAll() {
        return jdbc.query("select * from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(getType(rs.getInt(("accident_type_id"))));
                    var id = accident.getId();
                    accident.setRules(getRules(id));
                    return accident;
                });
    }

    public Optional<Accident> findById(int id) {
        Accident result = jdbc.queryForObject("select * from accident where id = ?",
                (rs, row) -> new Accident(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("address"),
                        getType(rs.getInt("accident_type_id"))), id);
        if (result != null) {
            result.setRules(getRules(id));
        }
        return Optional.ofNullable(result);
    }

    private AccidentType getType(int id) {
        return jdbc.queryForObject("select * from accident_type where id = ?",
                (rs, row) -> new AccidentType(
                        rs.getInt("id"),
                        rs.getString("name")), id);
    }

    private List<Rule> getRules(int id) {
        return getRuleIds(id)
                .stream()
                .map(i -> jdbc.queryForObject("select * from rule where id = ?",
                        (rs, row) -> new Rule(rs.getInt("id"),
                                rs.getString("name")), i))
                .collect(Collectors.toList());
    }

    private List<Integer> getRuleIds(int id) {
        return jdbc.query("select rule_id from accident_rule where accident_id = ?",
                (rs, row) -> rs.getInt("rule_id"), id);
    }

    public boolean update(Accident accident) {
        int id = accident.getId();
        var count = jdbc.update("update accident set "
                        + "name = ?, text = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), id);
        updateRules(accident.getRules(), id);
        return count > 0;
    }

    private void updateRules(Collection<Rule> rules, int id) {
        clearAccidentRules(id);
        saveRule(rules, id);
    }

    private void saveRule(Collection<Rule> rules, int id) {
        rules.stream()
                .map(Rule::getId)
                .forEach(i -> jdbc.update(
                        "insert into accident_rule values (?, ?)",
                        id, i));
    }

    private void clearAccidentRules(int id) {
        jdbc.update("delete from accident_rule where accident_id = ?", id);
    }

    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select * from accident_type",
                (rs, row) -> new AccidentType(
                        rs.getInt("id"),
                        rs.getString("name")));
    }

    public List<Rule> getRulesByIds(String[] rIds) {
        return Stream.of(rIds)
                .mapToInt(Integer::parseInt)
                .boxed()
                .map(i -> jdbc.queryForObject("select * from rule where id = ?",
                        (rs, row) -> new Rule(rs.getInt("id"),
                                rs.getString("name")), i))
                .collect(Collectors.toList());
    }
}