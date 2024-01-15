package ru.denis.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BanknoteStorage {
    final BanknoteStorageSaver banknoteStorageSaver;

    @Autowired
    public BanknoteStorage(BanknoteStorageSaver banknoteStorageSaver) {
        this.banknoteStorageSaver = banknoteStorageSaver;
    }

    public Map<String, Integer> giveMoney(int sum) throws NotEnoughMoneyException {
        Map<String, Integer> availableBanknotes = banknoteStorageSaver.getStorage();
        Map<String, Integer> returnedBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            if (availableBanknotes.getOrDefault(String.valueOf(currentBanknote.getBanknote()), 0) > 0) {
                int banknoteCount = sum / currentBanknote.getBanknote();
                if (availableBanknotes.getOrDefault(String.valueOf(currentBanknote.getBanknote()), 0) >= banknoteCount) {
                    sum -= banknoteCount * currentBanknote.getBanknote();
                }

                if (banknoteCount > 0) {
                    returnedBanknotes.put(String.valueOf(currentBanknote.getBanknote()), banknoteCount);

                    int newAvailableBanknoteCount = availableBanknotes.get(String.valueOf(currentBanknote.getBanknote()));
                    newAvailableBanknoteCount -= banknoteCount;
                    availableBanknotes.put(String.valueOf(currentBanknote.getBanknote()), newAvailableBanknoteCount);
                }
            }
        }

        if (sum > 0) {
            throw new NotEnoughMoneyException();
        } else {
            banknoteStorageSaver.saveStorage(availableBanknotes);
            return returnedBanknotes;
        }
    }
}
