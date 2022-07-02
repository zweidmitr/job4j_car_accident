package ru.job4j.accident.service;

import lombok.Data;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.data.RuleRepository;
import ru.job4j.accident.repository.hibernate.RuleHibernate;
import ru.job4j.accident.repository.jdbc.RuleJdbcTemplate;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
@Service
@Data
public class RuleService {
    private final RuleRepository store;

    public Iterable<Rule> findAll() {
        return store.findAll();
    }

    public Optional<Rule> findById(int id) {
        return store.findById(id);
    }

    public void delete(Rule rule) {
        store.delete(rule);
    }

    public void add(Rule rule) {
        store.save(rule);
    }
}
