package ru.denis.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denis.atm.models.UserBalanceModel;

public interface UserBalanceRepository extends JpaRepository<UserBalanceModel, Long> {
    void deleteByLogin(String login);
}
