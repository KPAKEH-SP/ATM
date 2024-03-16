package ru.denis.atm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.Banknote;
import ru.denis.atm.BanknoteStorage;
import ru.denis.atm.repository.BanknoteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DisplayController {
    private final BanknoteStorage banknoteStorage;
    private final BanknoteRepository banknoteRepository;

    @GetMapping("/showStorage")
    public List<Banknote> showStorage() {
        return banknoteRepository.findAll();
    }

    @PatchMapping("/editStorage")
    public Map<Integer, Integer> updateStorage(@RequestBody Map<Integer, Integer> banknotes) {
        banknoteStorage.updateStorage(banknotes);
        return banknotes;
    }

    @PostMapping("/getMoney")
    public Map<String, Integer> getMoney(@RequestBody Integer sum) {
        Map<String, Integer> issuedMoney = new HashMap<>();
        try {
            issuedMoney = banknoteStorage.giveMoney(sum);
        } catch (java.util.InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        return issuedMoney;
    }
}
