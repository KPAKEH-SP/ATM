import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BanknoteStorage implements Serializable {
    private static Map<Integer, Integer> availableBanknotes = new HashMap<Integer, Integer>();

    public static void setAvailableBanknotes(HashMap<Integer, Integer> value) {
        availableBanknotes = value;
    }

    public static void setAvailableBanknotes(Integer key, Integer value) {
        availableBanknotes.put(key, value);
    }

    public static Map<Integer, Integer> getAvailableBanknotes() {
        return availableBanknotes;
    }

    public static Map<Integer, Integer> giveMoney(int sum) throws NotEnoughMoneyException {                             // Возвращает Map купюр, подсчитанных из переданой суммы
        Map<Integer, Integer> returnBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            if (availableBanknotes.getOrDefault(currentBanknote.getBanknote(), 0) > 0) {                     // Проверяет закончились ли банкноты с нужным номиналом
                int banknoteCount = sum / currentBanknote.getBanknote();                                                // Высчитывает количество необходимых купюр
                if (availableBanknotes.getOrDefault(currentBanknote.getBanknote(), 0) >= banknoteCount) {
                    sum -= banknoteCount * currentBanknote.getBanknote();                                               // Вычетает из суммы ползователя сумму, которую банкомат подсчитал
                }

                if (banknoteCount > 0) {
                    returnBanknotes.put(currentBanknote.getBanknote(), banknoteCount);                                  // Записывает новое количество банкнот в хешмэп

                    int newAvailableBanknoteCount = availableBanknotes.get(currentBanknote.getBanknote());              // Достаёт из хранилища количество необходимых купюр
                    newAvailableBanknoteCount -= banknoteCount;                                                         // Вычитает необходимое количество купюр
                    availableBanknotes.put(currentBanknote.getBanknote(), newAvailableBanknoteCount);                   // Записывает в хранилище новое количество купюр
                }
            }
        }

        if (sum > 0) {                                                                                                  // Если банкомат не может выдать всю необходимую сумму, то выдаёт ошибку
            BanknoteStorageSaver.initializeStorage();
            throw new NotEnoughMoneyException();
        } else {
            BanknoteStorageSaver.saveStorage();                                                                         // Сохраняет текущее количество купюр в банкомате в хранилище
            return returnBanknotes;                                                                                     // Если банкомат может выдать всю необходимую сумму, возвращает хешмэп купюр
        }
    }
}
