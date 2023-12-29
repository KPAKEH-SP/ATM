package ru.denis.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.BanknotePatterns;
import ru.denis.atm.BanknoteStorage;
import ru.denis.atm.BanknoteStorageSaver;
import ru.denis.atm.NotEnoughMoneyException;

import java.util.Map;

@RestController
public class DisplayController {
    private final BanknoteStorageSaver banknoteStorageSaver;
    private final BanknoteStorage banknoteStorage;

    @Autowired
    public DisplayController(BanknoteStorageSaver banknoteStorageSaver, BanknoteStorage banknoteStorage) {
        this.banknoteStorageSaver = banknoteStorageSaver;
        this.banknoteStorage = banknoteStorage;
    }

    @GetMapping("/showStorage")
    public String showStorage() {
        Map<String, Integer> loadedBanknotes = banknoteStorageSaver.getStorage();
        return loadedBanknotes.toString();
    }

    @PatchMapping("/editStorage")
    public String updateStorage(@RequestBody Map<String, Integer> banknotes) {
        String updatedStorage;
        banknoteStorageSaver.saveStorage(banknotes);
        updatedStorage = banknoteStorageSaver.getStorage().toString();
        return updatedStorage;
    }

    @PostMapping("/getMoney")
    public String getMoney(@RequestBody Integer sum) {
        String returnedString = "";
        try {
            Map<String, Integer> issuedMoney = banknoteStorage.giveMoney(sum);
            returnedString = "Вам было выдано: " + issuedMoney;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Введите сумму цифрами!");
        } catch (NotEnoughMoneyException ignored) {}

        return returnedString;
    }
}
