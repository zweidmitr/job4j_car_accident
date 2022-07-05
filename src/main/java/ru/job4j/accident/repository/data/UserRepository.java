package ru.job4j.accident.repository.data;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
