package ru.job4j.accident.repository.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TypeHibernate implements DBStoreSession {
    private final SessionFactory sf;

    public AccidentType save(AccidentType type) {

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

    public Optional<AccidentType> findById(int id) {
        AccidentType type;
        try{
            type = tx(session -> session.get(AccidentType.class, id), sf);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(type);
    }

    public void delete(AccidentType type) {
        tx(session -> session.createQuery(
                        "delete from AccidentType at where at.id = : atId")
                .setParameter("atId", type.getId())
                .executeUpdate(), sf);
    }
}
