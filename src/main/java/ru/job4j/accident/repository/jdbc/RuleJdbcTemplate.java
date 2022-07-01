package ru.job4j.accident.repository.jdbc;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

@Repository
@Data
public class RuleJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Rule add(Rule rule) {
        jdbc.update("insert into rule (name) values (?)",
                rule.getName());
        return rule;
    }

    public void delete(int id) {
        jdbc.update("delete from rule where id = ?", id);
    }

    public Collection<Rule> findAll() {
        return jdbc.query("select id, name from rule",
                getRule());
    }

    public Rule findById(int id) {
        return jdbc.queryForObject("select id, name from rule where id = ?",
                getRule(), id);
    }

    private RowMapper<Rule> getRule() {
        return (rs, row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
        };
    }
}
