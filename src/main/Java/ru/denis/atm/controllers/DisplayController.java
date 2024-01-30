package ru.denis.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.BanknoteObject;
import ru.denis.atm.BanknoteStorage;
import ru.denis.atm.repository.DefaultCrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DisplayController {
    private final BanknoteStorage banknoteStorage;
    @Autowired
    private DefaultCrudRepository defaultCrudRepository;

    @Autowired
    public DisplayController(BanknoteStorage banknoteStorage, DefaultCrudRepository defaultCrudRepository) {
        this.banknoteStorage = banknoteStorage;
    }

    @GetMapping("/showStorage")
    public String showStorage() {
        List<BanknoteObject> storage = new ArrayList<>();
        storage = (List<BanknoteObject>) defaultCrudRepository.findAll();
        return storage.toString();
    }

    @PatchMapping("/editStorage")
    public String updateStorage(@RequestBody Map<Integer, Integer> banknotes) {
        banknoteStorage.updateStorage(banknotes);
        return "Updated!";
    }

    @PostMapping("/getMoney")
    public String getMoney(@RequestBody Integer sum) {
        String returnedString = "";
        try {
            Map<String, Integer> issuedMoney = banknoteStorage.giveMoney(sum);
            returnedString = "Вам было выдано: " + issuedMoney;
        } catch (java.util.InputMismatchException e) {
            return "Введите сумму цифрами!";
        }

        return returnedString;
    }
}
