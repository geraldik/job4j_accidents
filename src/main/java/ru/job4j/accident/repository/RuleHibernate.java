package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.util.TransactionService;

import java.util.List;

@Repository
@AllArgsConstructor
public class RuleHibernate implements TransactionService {

    private static final String QUERY_GET_ALL_RULES = "select r from Rule r";
    private static final String QUERY_FIND_BY_ID = "select r from Rule r where r.id = :id";
    private final SessionFactory sf;

    public Rule findById(int id) {
        return this.tx(
                session -> session.createQuery(QUERY_FIND_BY_ID, Rule.class)
                        .setParameter("id", id)
                        .uniqueResult(),
                sf
        );
    }

    public List<Rule> getRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(QUERY_GET_ALL_RULES, Rule.class)
                    .list();
        }
    }
}
