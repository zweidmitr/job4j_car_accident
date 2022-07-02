package ru.job4j.accident.repository.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class TypeHibernate implements DBStoreSession {
    private final SessionFactory sf;

    public AccidentType add(AccidentType type) {

        return tx(session -> {
            session.save(type);
            return type;
        }, sf);
    }

    public Collection<AccidentType> findAll() {
        return tx(session -> session.createQuery(
                        "from AccidentType at order by at.id")
                .getResultList(), sf);
    }

    public AccidentType findById(int id) {
        return tx(session -> session.get(AccidentType.class, id), sf);
    }

    public void delete(int id) {
        tx(session -> session.createQuery(
                        "delete from AccidentType at where at.id = : atId")
                .setParameter("atId", id)
                .executeUpdate(), sf);
    }
}
