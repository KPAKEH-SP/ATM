package ru.denis.atm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.exceptions.UserWithThisEmailAlreadyExists;
import ru.denis.atm.exceptions.UserWithThisIdNotExist;
import ru.denis.atm.exceptions.UserWithThisLoginAlreadyExists;
import ru.denis.atm.service.UsersStorage;
import ru.denis.atm.forms.DeleteForm;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserModel;
import ru.denis.atm.repository.UserRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserRepository userRepository;
    private final UsersStorage usersStorage;

    @GetMapping("/getUsers")
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/newUser")
    public RegistryForm createUser(@RequestBody RegistryForm registryForm) throws UserWithThisLoginAlreadyExists, UserWithThisEmailAlreadyExists {
        if (userRepository.existsByLogin(registryForm.getLogin())){
            throw new UserWithThisLoginAlreadyExists();
        } else if (userRepository.existsByEmail(registryForm.getEmail())) {
            throw new UserWithThisEmailAlreadyExists();
        } else {
            usersStorage.newUser(registryForm);
            return registryForm;
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody DeleteForm deleteForm) throws UserWithThisIdNotExist {
        if (userRepository.existsById(deleteForm.id)) {
            userRepository.deleteById(deleteForm.id);
            return "Пользователь с id " + deleteForm.id + " удалён";
        } else {
            throw new UserWithThisIdNotExist();
        }
    }
}