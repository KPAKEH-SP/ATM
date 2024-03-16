package ru.denis.atm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denis.atm.exceptions.validation.EmailUniqueException;
import ru.denis.atm.exceptions.validation.LoginUniqueException;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserModel;
import ru.denis.atm.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UsersStorage {
    private final UserRepository userRepository;

    public void newUser(RegistryForm registryForm) throws LoginUniqueException, EmailUniqueException {
        UserModel newUser = new UserModel();
        newUser.setLogin(registryForm.getLogin());
        newUser.setPassword(registryForm.getPassword());
        newUser.setEmail(registryForm.getEmail());
        System.out.println(registryForm.getFullName());
        newUser.setFullName(registryForm.getFullName());
        userValidation(newUser);
    }

    private void userValidation(UserModel user) throws LoginUniqueException, EmailUniqueException {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new LoginUniqueException();
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailUniqueException();
        }

        userRepository.save(user);
    }
}
