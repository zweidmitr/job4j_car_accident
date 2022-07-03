package ru.job4j.accident.repository.data;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import javax.persistence.NamedEntityGraph;
import java.util.List;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Override
    @EntityGraph(value = "aRules")
    @Query("from Accident")
    List<Accident> findAll();

    @Override
    @EntityGraph(value = "aRules")
    @Query("select a from Accident a where a.id = :Id")
    Optional<Accident> findById(@Param("Id") Integer id);
}
