package ru.denis.atm;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.denis.atm.config.SpringConfig;

public class ATM {
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
        Display display = context.getBean("display", Display.class);
        BanknoteStorage banknoteStorage = context.getBean("banknoteStorage", BanknoteStorage.class);

        display.showStartMenu(banknoteStorage);
    }
}
