package ru.job4j.accident.repository.data;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
