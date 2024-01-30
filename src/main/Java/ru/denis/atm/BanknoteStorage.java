package ru.denis.atm;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denis.atm.repository.DefaultCrudRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class BanknoteStorage {
    @Autowired
    private DefaultCrudRepository defaultCrudRepository;

    @Autowired
    public BanknoteStorage(DefaultCrudRepository defaultCrudRepository) {
        this.defaultCrudRepository = defaultCrudRepository;
    }

    @Transactional
    public Map<String, Integer> giveMoney(int sum) {
        Map<String, Integer> returnedBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            String currentBanknoteToString = String.valueOf(currentBanknote.getBanknote());

            BanknoteObject currentBanknoteObject = defaultCrudRepository.findById(currentBanknote.getBanknote()).get();
            int banknoteCount = currentBanknoteObject.getCount();
            if (banknoteCount > 0) {
                int issuedBanknoteCount = sum / currentBanknote.getBanknote();

                if (banknoteCount >= issuedBanknoteCount) {
                    sum -= issuedBanknoteCount * currentBanknote.getBanknote();

                }

                if (issuedBanknoteCount > 0) {
                    returnedBanknotes.put(currentBanknoteToString, issuedBanknoteCount);

                    currentBanknoteObject.setCount(banknoteCount - issuedBanknoteCount);
                    defaultCrudRepository.save(currentBanknoteObject);
                }
            }

        }

        if (sum > 0) {
            throw new RuntimeException();
        } else {
            return returnedBanknotes;
        }
    }

    public void updateStorage(Map<Integer, Integer> banknotes) {
        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            BanknoteObject updatableBanknoteObject = new BanknoteObject();

            if (defaultCrudRepository.existsById(currentBanknote.getBanknote())) {
                updatableBanknoteObject = defaultCrudRepository.findById(currentBanknote.getBanknote()).get();
                updatableBanknoteObject.setCount(banknotes.get(currentBanknote.getBanknote()));
            } else {
                updatableBanknoteObject.setId(currentBanknote.getBanknote());
                updatableBanknoteObject.setCount(banknotes.get(currentBanknote.getBanknote()));
            }

            defaultCrudRepository.save(updatableBanknoteObject);
        }
    }
}
