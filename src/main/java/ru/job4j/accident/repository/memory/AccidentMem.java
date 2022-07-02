package ru.job4j.accident.repository.memory;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(2);

    private AccidentMem() {
        accidents.put(0, new Accident(
                0, "Котастрофа",
                "кот балкон прыжок мяу-мяу",
                "Питер",
                null, null));
        accidents.put(1, new Accident(
                1, "Самоскат",
                "спуск нева бултых поплыли",
                "там же",
                null, null));
    }

    public void add(Accident accident) {
        int newId = id.incrementAndGet();
        accident.setId(newId);
        accidents.putIfAbsent(newId, accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public void delete(int id) {
        accidents.remove(id);
    }
}
