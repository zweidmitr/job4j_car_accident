package ru.job4j.accident.service;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.data.TypeRepository;
import ru.job4j.accident.repository.hibernate.TypeHibernate;
import ru.job4j.accident.repository.jdbc.TypeJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
@Data
public class TypeService {
    public final TypeRepository store;

    public Optional<AccidentType> findById(int id) {
        return store.findById(id);
    }

    public Iterable<AccidentType> findAll() {
        return store.findAll();
    }

    public void delete(AccidentType type) {
        store.delete(type);
    }

    public void add(AccidentType type) {
        store.save(type);
    }
}
