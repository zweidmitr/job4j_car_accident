package ru.job4j.accident.repository.jdbc;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

@Data
public class TypeJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentType add(AccidentType type) {
        jdbc.update("insert into accident_type (name) values (?)",
                type.getName());
        return type;
    }

    public void delete(int id) {
        jdbc.update("delete from accident_type t where id = ?", id);
    }

    public Collection<AccidentType> findAll() {
        return jdbc.query("select id,name from accident_type",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    public AccidentType findById(int id) {
        return jdbc.queryForObject("select id, name from accident_type where id = ?",
                getType(), id);
    }

    private RowMapper<AccidentType> getType() {
        return (rs, row) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("name"));
            return type;
        };
    }
}
