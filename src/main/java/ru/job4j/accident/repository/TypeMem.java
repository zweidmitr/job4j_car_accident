package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Repository
public class TypeMem {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public TypeMem() {
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и человек"));
        types.put(3, new AccidentType(3, "Машина и самокат"));
        types.put(4, new AccidentType(4, "Машина и домашнее животное"));
    }

    public Collection<AccidentType> findAll() {
        return new ArrayList<>(types.values());
    }

    public AccidentType findById(int id) {
        return types.get(id);
    }
}