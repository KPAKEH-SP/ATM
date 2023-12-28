package ru.denis.atm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.denis.atm.BanknotePatterns;
import ru.denis.atm.BanknoteStorage;
import ru.denis.atm.BanknoteStorageSaver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class DisplayController {

    private final BanknoteStorage banknoteStorage;
    private final BanknoteStorageSaver banknoteStorageSaver;

    @Autowired
    public DisplayController(BanknoteStorage banknoteStorage, BanknoteStorageSaver banknoteStorageSaver) {
        this.banknoteStorage = banknoteStorage;
        this.banknoteStorageSaver = banknoteStorageSaver;
    }

    @GetMapping("/menu")
    public String menu() {
        return "display/menu";
    }

    @GetMapping("/showStorage")
    public String showStorage(Model model) {
        List<String> banknotesCount = new ArrayList<>();

        for (BanknotePatterns banknotePattern : BanknotePatterns.values()) {
            int banknoteDenomination = banknotePattern.getBanknote();
            int banknoteCount = banknoteStorage.getStorage().get(banknoteDenomination);
            String banknoteCountString = banknoteDenomination + "-" + banknoteCount;
            banknotesCount.add(banknoteCountString);
        }

        model.addAttribute("banknotes", banknotesCount);
        return "display/showStorage";
    }

    @GetMapping("/takeCash")
    public String takeCash() {
        return "display/takeCash";
    }

    @GetMapping("/editStorage")
    public String editStorage(Model model) {
        model.addAttribute("banknotes", banknoteStorage.getStorage());
        return "display/editStorage";
    }

    @PatchMapping("/editStorage")
    public String updateStorage(@RequestBody Map<String, Integer> banknotes){
        System.out.println(banknoteStorageSaver.getStorage());
        System.out.println(banknotes);
        banknoteStorageSaver.saveStorage(banknotes);
        return null;
    }
}
