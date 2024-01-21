package ru.denis.atm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.denis.atm.exceptions.CouldNotBeFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BanknoteStorageDAO {

    public void saveBanknote(int id, int count) {
        Configuration cfg = new Configuration().configure();
        cfg.addAnnotatedClass(BanknoteObject.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());

        SessionFactory sessionFactory = cfg.buildSessionFactory(sBuilder.build());

        Session saveSession = sessionFactory.openSession();
        Transaction saveTransaction = saveSession.beginTransaction();

        BanknoteObject banknote = saveSession.find(BanknoteObject.class, id);

        if (banknote != null) {
            banknote.setCount(count);
        } else {
            banknote = new BanknoteObject();
            banknote.setId(id);
            banknote.setCount(count);
        }

        saveSession.merge(banknote);

        saveTransaction.commit();
        saveSession.close();
    }

    public int getBanknoteCount(int id) throws CouldNotBeFoundException {
        Configuration cfg = new Configuration().configure();
        cfg.addAnnotatedClass(BanknoteObject.class);

        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties());

        SessionFactory sessionFactory = cfg.buildSessionFactory(sBuilder.build());

        Session getSession = sessionFactory.openSession();
        Transaction getTransaction = getSession.beginTransaction();

        BanknoteObject banknoteObject = getSession.find(BanknoteObject.class, id);

        getTransaction.commit();
        getSession.close();

        if (banknoteObject != null)
            return banknoteObject.getCount();
        else
            throw new CouldNotBeFoundException();
    }

    public Map <Integer, Integer> getStorage(){
        Map<Integer, Integer> returnedMap = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()){
            try {
                returnedMap.put(currentBanknote.getBanknote(), getBanknoteCount(currentBanknote.getBanknote()));
            } catch (CouldNotBeFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        return returnedMap;
    }

    public void updateStorage(Map<Integer, Integer> updatedMap){
        for (BanknotePatterns currentBanknote : BanknotePatterns.values()){
            int count = updatedMap.get(currentBanknote.getBanknote());
            saveBanknote(currentBanknote.getBanknote(), count);
        }
    }
}
