package ru.job4j.accident.service;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.TypeMem;

import java.util.Collection;

@ThreadSafe
@Service
@Data
public class TypeService {
    public final TypeMem store;

    public AccidentType findById(int id) {
        return store.findById(id);
    }

    public Collection<AccidentType> findAll() {
        return store.findAll();
    }
}
