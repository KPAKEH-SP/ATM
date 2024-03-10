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
    public String createUser(@RequestBody RegistryForm registryForm) {
        try {
            usersStorage.newUser(registryForm);
            return "Новый пользователь " + registryForm.getLogin() + " создан";
        } catch (UserWithThisLoginAlreadyExists | UserWithThisEmailAlreadyExists e) {
            return e.getMessage();
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

    @ExceptionHandler(UserWithThisIdNotExist.class)
    public String idException(UserWithThisIdNotExist e){
        return e.getMessage();
    }
}