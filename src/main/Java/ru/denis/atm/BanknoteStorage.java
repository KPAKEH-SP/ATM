package ru.denis.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BanknoteStorage {
    @Autowired
    BanknoteStorageSaver banknoteStorageSaver;

    public Map<Integer, Integer> giveMoney(int sum) throws NotEnoughMoneyException {                                    // Возвращает Map купюр, подсчитанных из переданой суммы
        Map<Integer, Integer> availableBanknotes = banknoteStorageSaver.getStorage();
        Map<Integer, Integer> returnedBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            if (availableBanknotes.getOrDefault(currentBanknote.getBanknote(), 0) > 0) {                     // Проверяет закончились ли банкноты с нужным номиналом
                int banknoteCount = sum / currentBanknote.getBanknote();                                                // Высчитывает количество необходимых купюр
                if (availableBanknotes.getOrDefault(currentBanknote.getBanknote(), 0) >= banknoteCount) {
                    sum -= banknoteCount * currentBanknote.getBanknote();                                               // Вычетает из суммы ползователя сумму, которую банкомат подсчитал
                }

                if (banknoteCount > 0) {
                    returnedBanknotes.put(currentBanknote.getBanknote(), banknoteCount);                                  // Записывает новое количество банкнот в хешмэп

                    int newAvailableBanknoteCount = availableBanknotes.get(currentBanknote.getBanknote());              // Достаёт из хранилища количество необходимых купюр
                    newAvailableBanknoteCount -= banknoteCount;                                                         // Вычитает необходимое количество купюр
                    availableBanknotes.put(currentBanknote.getBanknote(), newAvailableBanknoteCount);                   // Записывает в хранилище новое количество купюр
                }
            }
        }

        if (sum > 0) {                                                                                                  // Если банкомат не может выдать всю необходимую сумму, то выдаёт ошибку
            throw new NotEnoughMoneyException();
        } else {
            banknoteStorageSaver.saveStorage(availableBanknotes);                                                       // Сохраняет текущее количество купюр в банкомате в хранилище
            return returnedBanknotes;                                                                                     // Если банкомат может выдать всю необходимую сумму, возвращает хешмэп купюр
        }
    }


    public void setAvailableBanknotes(int key, int value) {
        Map<Integer, Integer> availableBanknotes = banknoteStorageSaver.getStorage();
        availableBanknotes.put(key, value);
        banknoteStorageSaver.saveStorage(availableBanknotes);
    }

    public Map<Integer, Integer> getStorage() {
        return banknoteStorageSaver.getStorage();
    }
}
