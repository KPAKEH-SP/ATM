package ru.denis.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.denis.atm.exceptions.UserWithThisEmailAlreadyExists;
import ru.denis.atm.exceptions.UserWithThisLoginAlreadyExists;
import ru.denis.atm.service.UsersStorage;
import ru.denis.atm.forms.DeleteForm;
import ru.denis.atm.forms.RegistryForm;
import ru.denis.atm.models.UserModel;
import ru.denis.atm.repository.UserRepository;

import java.util.List;

@RestController
public class SecurityController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersStorage usersStorage;

    @GetMapping("/getUsers")
    public String getUsers() {
        List<UserModel> users = userRepository.findAll();

        return users.toString();
    }

    @PostMapping("/newUser")
    public String createUser(@RequestBody RegistryForm registryForm) {
        try {
            usersStorage.newUser(registryForm);
            return "Новый пользователь " + registryForm.getLogin() + " создан";
        } catch (UserWithThisLoginAlreadyExists | UserWithThisEmailAlreadyExists e) {
            return e.getMessage();
        }
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody DeleteForm deleteForm) {
        if (userRepository.existsById(deleteForm.id)) {
            userRepository.deleteById(deleteForm.id);
            return "Пользователь с id " + deleteForm.id + " удалён";
        } else {
            return "Пользователя с данным id не существует";
        }
    }
}