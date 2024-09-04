package ru.denis.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denis.atm.models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    void deleteByLogin(String login);
}
