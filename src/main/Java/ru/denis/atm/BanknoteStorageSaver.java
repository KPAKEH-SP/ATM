package ru.denis.atm;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class BanknoteStorageSaver {
    final File BANKNOTE_STORAGE_FILE = new File("src/main/resources/BanknoteStorage.bin");
    public void saveStorage(Map<Integer, Integer> savedMap) {                                                                                   // С помощью сериализации сохраняет хештаблицу с банкнотами в файл BanknoteStorage.bin
        try {
            FileOutputStream fos = new FileOutputStream(BANKNOTE_STORAGE_FILE.getPath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(savedMap);
            oos.close();
        } catch (IOException e) {
            System.out.println("Потерян файл хранилища!");
        }
    }

    public Map<Integer, Integer> getStorage() {                                                                             // С помощью сериализации загружает хештаблицу с банкнотами из файла BanknoteStorage.bin
        Map<Integer, Integer> returnedBanknotes = new HashMap<Integer, Integer>();

        try {
            FileInputStream fis = new FileInputStream(BANKNOTE_STORAGE_FILE.getPath());
            ObjectInputStream ois = new ObjectInputStream(fis);

            returnedBanknotes = (Map<Integer, Integer>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("Потерян файл хранилища!");
        } catch (ClassNotFoundException e) {
            System.out.println("Произошла внутренняя ошибка!");
        }

        return returnedBanknotes;
    }
}
