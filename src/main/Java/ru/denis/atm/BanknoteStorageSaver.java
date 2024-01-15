package ru.denis.atm;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class BanknoteStorageSaver {
    final File BANKNOTE_STORAGE_FILE = new File(getClass().getClassLoader().getResource("BanknoteStorage.bin").getFile());
    public void saveStorage(Map<String, Integer> savedMap) {                                                                                   // С помощью сериализации сохраняет хештаблицу с банкнотами в файл BanknoteStorage.bin
        try {
            FileOutputStream fos = new FileOutputStream(BANKNOTE_STORAGE_FILE.getPath());
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(savedMap);
            oos.close();
        } catch (IOException e) {
            System.out.println("Потерян файл хранилища!");
        }
    }

    public Map<String, Integer> getStorage() {                                                                             // С помощью сериализации загружает хештаблицу с банкнотами из файла BanknoteStorage.bin
        Map<String, Integer> returnedBanknotes = new HashMap<>();

        try {
            FileInputStream fis = new FileInputStream(BANKNOTE_STORAGE_FILE.getPath());
            ObjectInputStream ois = new ObjectInputStream(fis);

            returnedBanknotes = (Map<String, Integer>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("Потерян файл хранилища!");
        } catch (ClassNotFoundException e) {
            System.out.println("Произошла внутренняя ошибка!");
        }

        return returnedBanknotes;
    }
}
