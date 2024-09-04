package ru.denis.atm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denis.atm.forms.DeleteForm;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserBalanceModel;
import ru.denis.atm.repository.UserBalanceRepository;

@Service
@AllArgsConstructor
public class UsersBalancesStorage {
    private final UserBalanceRepository userBalanceRepository;

    public void newUserBalance(RegistryForm registryForm, long userId) {
        UserBalanceModel newUserBalanceModel = new UserBalanceModel();
        newUserBalanceModel.setId(userId);
        newUserBalanceModel.setLogin(registryForm.getLogin());
        newUserBalanceModel.setBalance(0L);

        userBalanceRepository.save(newUserBalanceModel);
    }

    @Transactional
    public void deleteUserBalance(DeleteForm deleteForm) {
        userBalanceRepository.deleteByLogin(deleteForm.getLogin());
    }
}
