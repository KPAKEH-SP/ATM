package ru.denis.atm.repository;

import org.springframework.data.repository.CrudRepository;
import ru.denis.atm.BanknoteObject;

public interface DefaultCrudRepository extends CrudRepository<BanknoteObject, Integer> {
}
