import java.util.Scanner;

public class Display {
    public static void ShowStartMenu(){
        System.out.println("Выберите действие: \n├─1)Снятие наличных \n└─2)Пополнение хранилища");
        Scanner userInputAction = new Scanner(System.in);
        int userAction = 0;

        try {
            userAction = userInputAction.nextInt();
        }catch (java.util.InputMismatchException e) {
            System.out.println("Введите только цифру действия!");
            ShowStartMenu();
        }

        switch (userAction) {
            case 1 -> {                                                                                                 //Снятие наличных
                GetMoney();
            }

            case 2 -> {                                                                                                 //Пополнение хранилища
                RefillStorage();
            }

            default -> {
                System.out.println("Выбрано несуществующее действие!");
                ShowStartMenu();
            }
        }
    }

    private static void GetMoney(){
        System.out.println("Введите сумму для снятия:");
        Scanner userInputSum = new Scanner(System.in);
        int userSum = 0;

        try{
            userSum = userInputSum.nextInt();
        }catch (java.util.InputMismatchException e) {
            System.out.println("Введите сумму цифрами!");
            GetMoney();
        }

        int[] banknotes = BanknoteStorage.GiveMoney(userSum);
        System.out.println("Вам было выдано:");

        for (int id = 0; id < banknotes.length; id++){
            if (banknotes[id] > 0){
                System.out.println(banknotes[id] + " купюр(-ы/-а) номиналом " + BanknoteStorage.banknotePatterns[id]);
            }
        }

        ShowStartMenu();
    }

    private static void RefillStorage(){
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
        }catch (java.util.InputMismatchException e) {
            System.out.println("Введите только цифру!");
            RefillStorage();
        }

        switch (userBanknote){
            case 1->{                                                                                                   // Купюра номиналом 5000
                BanknoteStorage.availableBanknotes.put(5000, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 5000");
            }
            case 2->{                                                                                                   // Купюра номиналом 2000
                BanknoteStorage.availableBanknotes.put(2000, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 2000");
            }
            case 3->{                                                                                                   // Купюра номиналом 1000
                BanknoteStorage.availableBanknotes.put(1000, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 1000");
            }
            case 4->{                                                                                                   // Купюра номиналом 500
                BanknoteStorage.availableBanknotes.put(500, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 500");
            }
            case 5->{                                                                                                   // Купюра номиналом 200
                BanknoteStorage.availableBanknotes.put(200, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 200");
            }
            case 6->{                                                                                                   // Купюра номиналом 100
                BanknoteStorage.availableBanknotes.put(100, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 100");
            }
            case 7->{                                                                                                   // Купюра номиналом 50
                BanknoteStorage.availableBanknotes.put(50, userBanknoteCount);
                System.out.println("Сейчас в хранилище " + userBanknoteCount + " купюр(-ы/-а) номиналом 50");
            }
            case 8->{                                                                                                   // Выход в главное меню
                ShowStartMenu();
            }

            default -> {
                System.out.println("Выбрано неверное действие");
                RefillStorage();
            }
        }

        BanknoteStorageSaver.SaveStorage();
        RefillStorage();
    }
}
