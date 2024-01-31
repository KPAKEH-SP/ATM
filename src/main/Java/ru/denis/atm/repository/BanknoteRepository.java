package ru.denis.atm.repository;

import org.springframework.data.repository.CrudRepository;
import ru.denis.atm.Banknote;

public interface BanknoteRepository extends CrudRepository<Banknote, Integer> {
}
