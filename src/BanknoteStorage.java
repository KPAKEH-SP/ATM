import java.io.Serializable;
import java.util.Hashtable;

public class BanknoteStorage implements Serializable {
    public static Hashtable<Integer, Integer> availableBanknotes = new Hashtable();
    public static final int[] banknotePatterns = new int[] {5000, 2000, 1000, 500, 200, 100, 50};

    public static int[] GiveMoney(int sum){                                                                             // Возвращает массив купюр, подсчитанных из переданой суммы
        int[] returnBanknotes = new int[7];

        for (int id = 0; id < banknotePatterns.length; id++){
            int currentBanknote = banknotePatterns[id];                                                                 // Номинал для проверки

            while (sum - currentBanknote > 0) {                                                                         // Вычитает текущий проверочный номинал из указаной суммы, пока может это делать
                if (availableBanknotes.get(currentBanknote) > 0) {                                                      // Проверяет закончились ли банкноты с нужным номиналом
                    returnBanknotes[id]++;                                                                              // Прибавляет в массив по 1 банкноте необходимого номинала
                    sum -= currentBanknote;

                    int newAvailableBanknoteCount = (int) availableBanknotes.get(currentBanknote);                      // Достаёт из хранилища количество необходимых купюр
                    newAvailableBanknoteCount--;                                                                        // Вычитает 1 необходимую купюру из кранилища

                    availableBanknotes.put(currentBanknote, newAvailableBanknoteCount);                                 // Записывает в хранилище новое количество купюр
                    BanknoteStorageSaver.SaveStorage();                                                                 // Сохраняет хранилище
                }

                else {                                                                                                  // В случае нехватки необходимых купюр, переходит к работае с следующими купюрами
                    break;
                }
            }
        }

        if (sum > 0){
            System.out.println("Банкомат не может выдать сумму: " + sum +". Подходящих купюр нет в наличии!");
        }

        return returnBanknotes;
    }
}
