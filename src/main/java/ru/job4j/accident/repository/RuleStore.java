package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
@Repository
public class RuleStore {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleStore() {
        rules.put(1, (new Rule(1, "Статья. 1")));
        rules.put(2, (new Rule(2, "Статья. 2")));
        rules.put(3, (new Rule(3, "Статья. 3")));
        rules.put(4, (new Rule(4, "Статья. 4")));
    }

    public Collection<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    public Rule findById(int id) {
        return rules.get(id);
    }
}
