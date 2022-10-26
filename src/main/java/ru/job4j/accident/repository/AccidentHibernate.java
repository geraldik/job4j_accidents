package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.util.TransactionService;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements TransactionService {

    public static final String QUERY_FIND_ALL = "select distinct a from Accident a join fetch a.rules";
    public static final String QUERY_FIND_BY_ID = "select a from Accident a join fetch a.rules where a.id = :id";
    private final SessionFactory sf;

    public Accident save(Accident accident) {
        return this.tx(
                session -> {
                    session.save(accident);
                    return accident;
                }, sf
        );
    }

    public List<Accident> findAll() {
        return this.tx(session -> session
                .createQuery(QUERY_FIND_ALL, Accident.class)
                .list(), sf);
    }

    public Optional<Accident> findById(int id) {
        return this.tx(
                session -> Optional.ofNullable(session
                        .createQuery(QUERY_FIND_BY_ID, Accident.class)
                        .setParameter("id", id)
                        .getSingleResult()),
                sf
        );
    }

    public void update(Accident accident) {
        this.tx(
                session -> {
                    session.update(accident);
                    return session;
                }, sf
        );
    }
}