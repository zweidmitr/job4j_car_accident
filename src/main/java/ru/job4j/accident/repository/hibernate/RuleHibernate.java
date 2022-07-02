package ru.job4j.accident.repository.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RuleHibernate implements DBStoreSession {
    private final SessionFactory sf;

    public Rule save(Rule rule) {
        return tx(session -> {
            session.save(rule);
            return rule;
        }, sf);
    }

    public Collection<Rule> findAll() {
        return tx(session -> session.createQuery(
                        "from Rule r order by r.id")
                .getResultList(), sf);
    }

    public Optional<Rule> findById(int id) {
        Rule rule;
        try {
            rule = tx(session -> session.get(Rule.class, id), sf);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(rule);
    }

    public void delete(Rule rule) {
        tx(session -> session.createQuery(
                        "delete from Rule r where r.id = :rId")
                .setParameter("rId", rule.getId())
                .executeUpdate(), sf);
    }

}
