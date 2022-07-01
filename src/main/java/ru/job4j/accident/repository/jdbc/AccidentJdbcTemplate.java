package ru.job4j.accident.repository.jdbc;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Set;

@Data
@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public Accident add(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident (name, text,address,type_id) values (?,?,?,?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;

        }, keyHolder);
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?,?)",
                    keyHolder.getKey(), rule.getId());
        }
        return accident;
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("select a.id, a.name, a.text, a.address, a.type_id, "
                        + "t.name type_name "
                        + "from accident a "
                        + "join accident_type t on a.type_id = t.id "
                        + "where a.id = ?",
                getAccident(), id);
    }

    public Collection<Accident> findAll() {
        return jdbc.query("select a.id, a.name, a.text, a.address, a.type_id, "
                        + "t.name type_name "
                        + "from accident a "
                        + "join accident_type t on a.type_id = t.id",
                getAccident());
    }

    public void update(Accident accident) {
        jdbc.update("delete from accident_rule where accident_id = ?", accident.getId());
        jdbc.update("update accident set name = ?, text = ?, address = ?, type_id = ? "
                        + "where accident.id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accident_rule (accident_id, rule_id) values (?,?)",
                    accident.getId(), rule.getId());
        }
    }

    public void delete(int id) {
        jdbc.update("delete from accident_rule where accident_id = ?", id);
        jdbc.update("delete from accident where id = ?", id);
    }

    private RowMapper<Accident> getAccident() {
        return (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(new AccidentType(
                    rs.getInt("type_id"),
                    rs.getString("type_name")));
            accident.setRules(Set.copyOf(getRulesById(rs.getInt("id"))));
            return accident;
        };
    }

    private Collection<Rule> getRulesById(int id) {
        return jdbc.query("select r.id, r.name from accident_rule ar "
                        + "join rule r on ar.rule_id = r.id where accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }
}
