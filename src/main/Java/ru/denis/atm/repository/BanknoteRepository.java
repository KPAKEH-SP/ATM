package ru.denis.atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.denis.atm.Banknote;

public interface BanknoteRepository extends JpaRepository<Banknote, Integer> {
}
