package ru.denis.atm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denis.atm.exceptions.UserWithThisEmailAlreadyExists;
import ru.denis.atm.exceptions.UserWithThisLoginAlreadyExists;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserModel;
import ru.denis.atm.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UsersStorage {
    private final UserRepository userRepository;

    public void newUser(RegistryForm registryForm) throws UserWithThisLoginAlreadyExists, UserWithThisEmailAlreadyExists {
        UserModel newUser = new UserModel();
        newUser.setLogin(registryForm.getLogin());
        newUser.setPassword(registryForm.getPassword());
        newUser.setEmail(registryForm.getEmail());
        System.out.println(registryForm.getFullName());
        newUser.setFullName(registryForm.getFullName());

        if (userRepository.existsByLogin(registryForm.getLogin())) {
            throw new UserWithThisLoginAlreadyExists();
        } else if (userRepository.existsByEmail(registryForm.getEmail())) {
            throw new UserWithThisEmailAlreadyExists();
        } else {
            userRepository.save(newUser);
        }
    }
}
