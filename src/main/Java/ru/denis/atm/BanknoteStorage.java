package ru.denis.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denis.atm.exceptions.CouldNotBeFoundException;
import ru.denis.atm.exceptions.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

@Component
public class BanknoteStorage {
    final BanknoteStorageDAO banknoteStorageDAO;

    @Autowired
    public BanknoteStorage(BanknoteStorageDAO banknoteStorageSaver) {
        this.banknoteStorageDAO = banknoteStorageSaver;
    }

    public Map<String, Integer> giveMoney(int sum) throws NotEnoughMoneyException {
        Map<String, Integer> returnedBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            String currentBanknoteToString = String.valueOf(currentBanknote.getBanknote());

            try {
                if (banknoteStorageDAO.getBanknoteCount(currentBanknote.getBanknote()) > 0) {
                    int banknoteCount = sum / currentBanknote.getBanknote();

                    if (banknoteStorageDAO.getBanknoteCount(currentBanknote.getBanknote()) >= banknoteCount) {
                        sum -= banknoteCount * currentBanknote.getBanknote();
                    }

                    if (banknoteCount > 0) {
                        returnedBanknotes.put(currentBanknoteToString, banknoteCount);

                        int newBanknoteCount = banknoteStorageDAO.getBanknoteCount(currentBanknote.getBanknote()) - banknoteCount;
                        banknoteStorageDAO.saveBanknote(currentBanknote.getBanknote(), newBanknoteCount);
                    }
                }
            } catch (CouldNotBeFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        if (sum > 0) {
            throw new NotEnoughMoneyException();
        } else {
            return returnedBanknotes;
        }
    }
}
