package ru.job4j.accident.repository.memory;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class RuleMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        rules.put(1, (new Rule(1, "Статья. 1")));
        rules.put(2, (new Rule(2, "Статья. 2")));
        rules.put(3, (new Rule(3, "Статья. 3")));
        rules.put(4, (new Rule(4, "Статья. 4")));
    }

    public Collection<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    public Optional<Rule> findById(int id) {
        Rule rule;
        try {
            rule = rules.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(rule);
    }
}
