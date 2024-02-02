package ru.denis.atm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.Banknote;
import ru.denis.atm.BanknoteStorage;
import ru.denis.atm.repository.BanknoteRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DisplayController {
    private final BanknoteStorage banknoteStorage;
    private final BanknoteRepository banknoteRepository;

    @GetMapping("/showStorage")
    public String showStorage() {
        List<Banknote> storage = banknoteRepository.findAll();
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
