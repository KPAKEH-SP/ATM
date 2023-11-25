public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() throws NotEnoughMoneyException {
        System.out.println("Банкомат не может выдать данную сумму!");
        Display.showStartMenu();
    }
}
