package ru.denis.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.BanknoteStorage;
import ru.denis.atm.BanknoteStorageDAO;
import ru.denis.atm.exceptions.NotEnoughMoneyException;

import java.util.Map;

@RestController
public class DisplayController {
    private final BanknoteStorageDAO banknoteStorageDAO;
    private final BanknoteStorage banknoteStorage;

    @Autowired
    public DisplayController(BanknoteStorageDAO banknoteStorageSaver, BanknoteStorage banknoteStorage) {
        this.banknoteStorageDAO = banknoteStorageSaver;
        this.banknoteStorage = banknoteStorage;
    }

    @GetMapping("/showStorage")
    public String showStorage() {
        Map<Integer, Integer> loadedBanknotes = banknoteStorageDAO.getStorage();
        return loadedBanknotes.toString();
    }

    @PatchMapping("/editStorage")
    public String updateStorage(@RequestBody Map<Integer, Integer> banknotes) {
        String updatedStorage;
        banknoteStorageDAO.updateStorage(banknotes);
        updatedStorage = banknoteStorageDAO.getStorage().toString();
        return updatedStorage;
    }

    @PostMapping("/getMoney")
    public String getMoney(@RequestBody Integer sum) {
        String returnedString = "";
        try {
            Map<String, Integer> issuedMoney = banknoteStorage.giveMoney(sum);
            returnedString = "Вам было выдано: " + issuedMoney;
        } catch (java.util.InputMismatchException e) {
            return "Введите сумму цифрами!";
        } catch (NotEnoughMoneyException e) {
            return e.getMessage();
        }

        return returnedString;
    }
}
