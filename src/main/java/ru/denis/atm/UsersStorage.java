package ru.denis.atm;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denis.atm.Exceptions.UserWithThisEmailAlreadyExists;
import ru.denis.atm.Exceptions.UserWithThisLoginAlreadyExists;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserModel;
import ru.denis.atm.repository.UserRepository;

@Component
public class UsersStorage {
    @Autowired
    private UserRepository userRepository;

    public void newUser(RegistryForm registryForm) throws UserWithThisLoginAlreadyExists, UserWithThisEmailAlreadyExists {
        UserModel newUser = new UserModel();
        newUser.setLogin(registryForm.login);
        newUser.setPassword(registryForm.password);
        newUser.setEmail(registryForm.email);
        System.out.println(registryForm.getFullName());
        newUser.setFullName(registryForm.getFullName());

        if (userRepository.existsByLogin(registryForm.login)) {
            throw new UserWithThisLoginAlreadyExists();
        } else if (userRepository.existsByEmail(registryForm.email)) {
            throw new UserWithThisEmailAlreadyExists();
        } else {
            userRepository.save(newUser);
        }
    }
}
