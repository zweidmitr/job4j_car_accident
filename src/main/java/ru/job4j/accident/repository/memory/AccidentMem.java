package ru.job4j.accident.repository.memory;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
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

    public void save(Accident accident) {
        int newId = id.incrementAndGet();
        accident.setId(newId);
        accidents.putIfAbsent(newId, accident);
    }

    public Optional<Accident> findById(int id) {
        Accident accident;
        try {
            accident = accidents.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(accident);
    }

    public Collection<Accident> findAll() {
        return accidents.values();
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public void delete(Accident accident) {
        accidents.remove(accident.getId());
    }
}
