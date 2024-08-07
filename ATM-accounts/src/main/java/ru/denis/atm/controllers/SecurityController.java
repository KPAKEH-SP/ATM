package ru.denis.atm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.exceptions.validation.EmailUniqueException;
import ru.denis.atm.exceptions.UserWithThisIdNotExist;
import ru.denis.atm.exceptions.validation.LoginUniqueException;
import ru.denis.atm.kafka.KafkaProducer;
import ru.denis.atm.service.UsersBalancesStorage;
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
    private final UsersBalancesStorage usersBalancesStorage;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/getUsers")
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/newUser")
    public RegistryForm createUser(@RequestBody RegistryForm registryForm) throws LoginUniqueException, EmailUniqueException {
        usersStorage.newUser(registryForm);
        Long userId = usersStorage.getUserIdByLogin(registryForm.getLogin());
        usersBalancesStorage.newUserBalance(registryForm, userId);
        kafkaProducer.sendMessage("new-user-event", registryForm.getLogin());
        return registryForm;
    }

    @PostMapping("/deleteUser")
    public DeleteForm deleteUser(@RequestBody DeleteForm deleteForm) throws UserWithThisIdNotExist {
        usersStorage.deleteUser(deleteForm);
        usersBalancesStorage.deleteUserBalance(deleteForm);
        kafkaProducer.sendMessage("del-user-event", deleteForm.getLogin());
        return deleteForm;
    }
}