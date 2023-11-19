package Exceptions;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException(){
        super("Банкомат не может выдать данную сумму!");
    }
}
