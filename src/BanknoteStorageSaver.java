import java.io.*;
import java.util.Hashtable;

public class BanknoteStorageSaver {
    public static void SaveStorage(){                                                                                   // С помощью сериализации сохраняет хештаблицу с банкнотами в файл BanknoteStorage.bin
        try {
            FileOutputStream fos = new FileOutputStream("src/BanknoteStorage.bin");
            ObjectOutputStream oos =  new ObjectOutputStream(fos);

            oos.writeObject(BanknoteStorage.availableBanknotes);
            oos.close();
        } catch (IOException e) {
            System.out.println("Потерян файл хранилища!");
        }
    }

    public static void InitializeStorage(){                                                                             // С помощью сериализации загружает хештаблицу с банкнотами из файла BanknoteStorage.bin
        try {
            FileInputStream fis = new FileInputStream("src/BanknoteStorage.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            BanknoteStorage.availableBanknotes = (((Hashtable<Integer, Integer>) ois.readObject()));
            ois.close();
        } catch (IOException e) {
            System.out.println("Потерян файл хранилища!");
        } catch (ClassNotFoundException e) {
            System.out.println("Произошла внутренняя ошибка!");
        }
    }
}
