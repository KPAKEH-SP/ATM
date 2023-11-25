import java.util.Map;
import java.util.Scanner;

public class Display {
    public static void showStartMenu() throws NotEnoughMoneyException {
        boolean receivedUserAction = false;

        while (!receivedUserAction) {
            System.out.println("Выберите действие: \n├─1)Снятие наличных \n├─2)Пополнение хранилища \n└─3)Показать хранилище");
            Scanner userInputAction = new Scanner(System.in);
            int userAction = 0;

            try {
                userAction = userInputAction.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Введите только цифру действия!");
            }

            switch (userAction) {
                case 1 -> {                                                                                                 //Снятие наличных
                    getMoney();
                }

                case 2 -> {                                                                                                 //Пополнение хранилища
                    refillStorage();
                }

                case 3 -> {
                    showStorage();
                }

                default -> {
                    System.out.println("Выбрано несуществующее действие!");
                    continue;
                }
            }

            receivedUserAction = true;
        }
    }

    private static void getMoney() throws NotEnoughMoneyException {
        boolean receivedUserAction = false;

        while (receivedUserAction == false) {
            System.out.println("Введите сумму для снятия:");
            Scanner userInputSum = new Scanner(System.in);
            int userSum = 0;

            try {
                userSum = userInputSum.nextInt();

                Map<Integer, Integer> banknotes = BanknoteStorage.giveMoney(userSum);
                System.out.println("Вам было выдано:");

                for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
                    BanknotePatterns banknoteValue = BanknotePatterns.valueOf(currentBanknote.name());

                    if (banknotes.get(banknoteValue.getBanknote()) != null) {
                        System.out.println(banknotes.get(banknoteValue.getBanknote()) + " купюр(-ы/-а) номиналом " + banknoteValue.getBanknote());
                    }
                }

                receivedUserAction = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Введите сумму цифрами!");
                continue;
            }


        }

        showStartMenu();
    }

    private static void refillStorage() throws NotEnoughMoneyException {
        while (true) {
            System.out.println("Выбирете купюру для изменения её количества в банкомате: \n├─1)5000 \n├─2)2000 \n├─3)1000 \n├─4)500 \n├─5)200 \n├─6)100 \n├─7)50 \n└─8)Вернуться в главное меню");
            Scanner userInputBanknote = new Scanner(System.in);
            int userBanknote = 0;

            Scanner userInputBanknoteCount = new Scanner(System.in);
            int userBanknoteCount = 0;

            try {
                userBanknote = userInputBanknote.nextInt();

                if (userBanknote != 8) {
                    System.out.println("Введите новое количество купюр: ");
                    userBanknoteCount = userInputBanknoteCount.nextInt();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Введите только цифру!");
            }

            switch (userBanknote) {
                case 1 -> {                                                                                                   // Купюра номиналом 5000
                    BanknoteStorage.setAvailableBanknotes(5000, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 5000");
                }
                case 2 -> {                                                                                                   // Купюра номиналом 2000
                    BanknoteStorage.setAvailableBanknotes(2000, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 2000");
                }
                case 3 -> {                                                                                                   // Купюра номиналом 1000
                    BanknoteStorage.setAvailableBanknotes(1000, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 1000");
                }
                case 4 -> {                                                                                                   // Купюра номиналом 500
                    BanknoteStorage.setAvailableBanknotes(500, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 500");
                }
                case 5 -> {                                                                                                   // Купюра номиналом 200
                    BanknoteStorage.setAvailableBanknotes(200, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 200");
                }
                case 6 -> {                                                                                                   // Купюра номиналом 100
                    BanknoteStorage.setAvailableBanknotes(100, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 100");
                }
                case 7 -> {                                                                                                   // Купюра номиналом 50
                    BanknoteStorage.setAvailableBanknotes(50, userBanknoteCount);
                    System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 50");
                }
                case 8 -> {                                                                                                   // Выход в главное меню
                    showStartMenu();
                }

                default -> {
                    System.out.println("Выбрано неверное действие");
                }
            }

            BanknoteStorageSaver.saveStorage();
        }
    }

    private static void showStorage() throws NotEnoughMoneyException {
        System.out.println(BanknoteStorage.getAvailableBanknotes());
        showStartMenu();
    }
}
