public class ATM {
    public static void main(String[] args) throws NotEnoughMoneyException {
        BanknoteStorageSaver.initializeStorage();
        Display.showStartMenu();
    }
}
