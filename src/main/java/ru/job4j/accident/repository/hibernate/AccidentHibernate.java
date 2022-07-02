package ru.job4j.accident.repository.hibernate;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class AccidentHibernate implements DBStoreSession {
    private final SessionFactory sf;

    public Accident add(Accident accident) {
        return tx(session -> {
            session.save(accident);
            return accident;
        }, sf);

    }

    public Collection<Accident> findAll() {
        return tx(session -> session.createQuery(
                        "select distinct a from Accident a "
                                + "join fetch a.type type "
                                + "join fetch a.rules rules "
                                + "order by a.id", Accident.class)
                .getResultList(), sf);
    }

    public Accident findById(int id) {
        return tx(session -> session.createQuery(
                        "select distinct a from Accident a "
                                + "join fetch a.type type "
                                + "join fetch a.rules rules "
                                + "where a.id = :aId", Accident.class)
                .setParameter("aId", id)
                .getSingleResult(), sf);
    }

    public void delete(int id) {
        tx(session -> session.createQuery(
                        "delete from Accident a where a.id = :aId")
                .setParameter("aId", id)
                .executeUpdate(), sf);
    }

    public Accident update(Accident accident) {
        return tx(session -> {
            session.update(accident);
            return accident;
        }, sf);
    }
}
