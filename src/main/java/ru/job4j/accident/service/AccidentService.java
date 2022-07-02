package ru.job4j.accident.service;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.data.AccidentRepository;
import ru.job4j.accident.repository.hibernate.AccidentHibernate;
import ru.job4j.accident.repository.jdbc.AccidentJdbcTemplate;
import ru.job4j.accident.repository.memory.AccidentMem;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
@Data
public class AccidentService {
    private final AccidentRepository store;

    public void add(Accident accident) {
        store.save(accident);
    }

    public Optional<Accident> findById(int id) {
        return store.findById(id);
    }

    public Iterable<Accident> findAll() {
        return store.findAll();
    }

    public void update(Accident accident) {
        store.save(accident);
    }

    public void delete(Accident accident) {
        store.delete(accident);
    }


}
