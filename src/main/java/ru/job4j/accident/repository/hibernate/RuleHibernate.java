package ru.job4j.accident.repository.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class RuleHibernate implements DBStoreSession {
    private final SessionFactory sf;

    public Rule add(Rule rule) {
        return  tx(session -> {
            session.save(rule);
            return rule;
        }, sf);
    }

    public Collection<Rule> findAll() {
        return tx(session -> session.createQuery(
                        "from Rule r order by r.id")
                .getResultList(), sf);
    }

    public Rule findById(int id) {
        return tx(session -> session.get(Rule.class, id), sf);
    }

    public void delete(int id) {
        tx(session -> session.createQuery(
                        "delete from Rule r where r.id = :rId")
                .setParameter("rId", id)
                .executeUpdate(), sf);
    }

}
