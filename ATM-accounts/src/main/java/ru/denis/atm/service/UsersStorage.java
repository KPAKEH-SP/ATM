package ru.denis.atm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denis.atm.exceptions.UserWithThisIdNotExist;
import ru.denis.atm.exceptions.validation.EmailUniqueException;
import ru.denis.atm.exceptions.validation.LoginUniqueException;
import ru.denis.atm.forms.DeleteForm;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserModel;
import ru.denis.atm.repository.UserRepository;

@Service
@AllArgsConstructor
public class UsersStorage {
    private final UserRepository userRepository;

    public void newUser(RegistryForm registryForm) throws LoginUniqueException, EmailUniqueException {
        UserModel newUser = new UserModel();
        newUser.setLogin(registryForm.getLogin());
        newUser.setPassword(registryForm.getPassword());
        newUser.setEmail(registryForm.getEmail());
        System.out.println(registryForm.getFullName());
        newUser.setFullName(registryForm.getFullName());
        newUserValidation(newUser);
    }

    @Transactional
    public void deleteUser(DeleteForm deleteForm) throws UserWithThisIdNotExist {
        if (userRepository.existsByLogin(deleteForm.getLogin())) {
            userRepository.deleteByLogin(deleteForm.getLogin());
        } else {
            throw new UserWithThisIdNotExist();
        }
    }

    public Long getUserIdByLogin(String login) {
        UserModel user = userRepository.getUserModelByLogin(login);
        return user.getId();
    }

    private void newUserValidation(UserModel user) throws LoginUniqueException, EmailUniqueException {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new LoginUniqueException();
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailUniqueException();
        }

        userRepository.save(user);
    }
}
