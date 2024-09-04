package ru.denis.atm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.dto.User;
import ru.denis.atm.exceptions.validation.EmailUniqueException;
import ru.denis.atm.exceptions.UserWithThisIdNotExist;
import ru.denis.atm.exceptions.validation.LoginUniqueException;
import ru.denis.atm.kafka.KafkaProducer;
import ru.denis.atm.kafkaevents.EditUserEvent;
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
    public User createUser(@RequestBody RegistryForm registryForm) throws LoginUniqueException, EmailUniqueException {
        User newUser = usersStorage.newUser(registryForm);
        Long userId = newUser.getId();
        usersBalancesStorage.newUserBalance(registryForm, userId);
        EditUserEvent editUserEvent = new EditUserEvent();
        editUserEvent.setLogin(registryForm.getLogin());
        editUserEvent.setDeleted(false);
        kafkaProducer.sendEditUserEvent(newUser.getLogin(), editUserEvent);
        return newUser;
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestBody DeleteForm deleteForm) throws UserWithThisIdNotExist {
        usersStorage.deleteUser(deleteForm);
        usersBalancesStorage.deleteUserBalance(deleteForm);
        EditUserEvent editUserEvent = new EditUserEvent();
        editUserEvent.setLogin(deleteForm.getLogin());
        editUserEvent.setDeleted(true);
        kafkaProducer.sendEditUserEvent(deleteForm.getLogin(), editUserEvent);
        return ResponseEntity.noContent().build();
    }
}