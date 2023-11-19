public class ATM {
    public static void main(String[] args) {
//
//        for (BanknotePatterns currentBanknote : BanknotePatterns.values()){
//            BanknotePatterns banknotePatterns = BanknotePatterns.valueOf(currentBanknote.name());
//            System.out.println(banknotePatterns.getBanknote());
//        }

        BanknoteStorageSaver.initializeStorage();
        Display.showStartMenu();
    }
}
