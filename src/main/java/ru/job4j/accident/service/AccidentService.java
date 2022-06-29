package ru.job4j.accident.service;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@ThreadSafe
@Service
@Data
public class AccidentService {
    private final AccidentMem store;

    public void add(Accident accident) {
        store.add(accident);
    }

    public Accident findById(int id) {
        return store.findById(id);
    }

    public Collection<Accident> findAll() {
        return store.findAll();
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public void delete(int id) {
        store.delete(id);
    }


}
