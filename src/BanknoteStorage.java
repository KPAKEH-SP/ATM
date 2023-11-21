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

    public static Map<Integer, Integer> giveMoney(int sum) {                                                            // Возвращает массив купюр, подсчитанных из переданой суммы
        Map<Integer, Integer> returnBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            BanknotePatterns banknoteValue = BanknotePatterns.valueOf(currentBanknote.name());
            while (sum - banknoteValue.getBanknote() >= 0) {                                                            // Вычитает текущий проверочный номинал из указаной суммы, пока может это делать
                if (availableBanknotes.get(banknoteValue.getBanknote()) > 0) {                                          // Проверяет закончились ли банкноты с нужным номиналом
                    if (returnBanknotes.get(banknoteValue.getBanknote()) != null) {
                        int currentCount = returnBanknotes.get(banknoteValue.getBanknote());
                        currentCount++;                                                                                 // Прибавляет 1 банкноту необходимого номинала
                        returnBanknotes.put(banknoteValue.getBanknote(), currentCount);                                 // Записывает новое количество банкнот в хешмэп
                    } else {
                        returnBanknotes.put(banknoteValue.getBanknote(), 1);
                    }

                    sum -= banknoteValue.getBanknote();

                    int newAvailableBanknoteCount = (int) availableBanknotes.get(banknoteValue.getBanknote());          // Достаёт из хранилища количество необходимых купюр
                    newAvailableBanknoteCount--;                                                                        // Вычитает 1 необходимую купюру из кранилища

                    availableBanknotes.put(banknoteValue.getBanknote(), newAvailableBanknoteCount);                                 // Записывает в хранилище новое количество купюр
                } else {                                                                                                // В случае нехватки необходимых купюр, переходит к работае с следующими купюрами
                    break;
                }
            }
        }

        if (sum > 0) {                                                                                                   // Если банкомат не может выдать всю необходимую сумму, то выдаёт ошибку
            try {
                throw new NotEnoughMoneyException();
            } catch (NotEnoughMoneyException e) {
                throw new RuntimeException(e);
            }
        } else {
            BanknoteStorageSaver.saveStorage();                                                                         // Сохраняет текущее количество купюр в банкомате в хранилище
            return returnBanknotes;                                                                                     // Если банкомат может выдать всю необходимую сумму, возвращает хешмэп купюр
        }
    }
}
