package ru.job4j.accident.service;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleStore;

import java.util.Collection;

@ThreadSafe
@Service
@Data
public class RuleService {
    private final RuleStore store;

    public Collection<Rule> findAll() {
        return store.findAll();
    }

    public Rule findById(int id) {
        return store.findById(id);
    }
}
