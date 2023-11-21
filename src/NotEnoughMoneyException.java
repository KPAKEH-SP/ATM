public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        System.out.println("Банкомат не может выдать данную сумму!");
        Display.showStartMenu();
    }
}
