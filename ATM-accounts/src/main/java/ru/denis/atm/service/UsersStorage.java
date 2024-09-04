package ru.denis.atm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.denis.atm.dto.User;
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

    public User newUser(RegistryForm registryForm) throws LoginUniqueException, EmailUniqueException {
        UserModel newUserModel = new UserModel();
        newUserModel.setLogin(registryForm.getLogin());
        newUserModel.setPassword(registryForm.getPassword());
        newUserModel.setEmail(registryForm.getEmail());
        System.out.println(registryForm.getFullName());
        newUserModel.setFullName(registryForm.getFullName());
        newUserValidation(newUserModel);

        User newUser = new User();
        newUser.setId(newUserModel.getId());
        newUser.setLogin(newUserModel.getLogin());
        newUser.setPassword(newUserModel.getPassword());
        newUser.setEmail(newUserModel.getEmail());
        newUser.setFullName(newUserModel.getFullName());
        return newUser;
    }

    @Transactional
    public void deleteUser(DeleteForm deleteForm) throws UserWithThisIdNotExist {
        if (userRepository.existsByLogin(deleteForm.getLogin())) {
            userRepository.deleteByLogin(deleteForm.getLogin());
        } else {
            throw new UserWithThisIdNotExist();
        }
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
