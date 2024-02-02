package ru.denis.atm;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.denis.atm.repository.BanknoteRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BanknoteStorage {
    @Autowired
    private final BanknoteRepository banknoteRepository;

    @Transactional
    public Map<String, Integer> giveMoney(int sum) {
        Map<String, Integer> returnedBanknotes = new HashMap<>();

        for (BanknotePatterns currentBanknote : BanknotePatterns.values()) {
            String currentBanknoteToString = String.valueOf(currentBanknote.getBanknote());

            Banknote currentBanknoteObject = banknoteRepository.findById(currentBanknote.getBanknote()).get();
            int banknoteCount = currentBanknoteObject.getCount();
            if (banknoteCount > 0) {
                int issuedBanknoteCount = sum / currentBanknote.getBanknote();

                if (banknoteCount >= issuedBanknoteCount) {
                    sum -= issuedBanknoteCount * currentBanknote.getBanknote();

                }

                if (issuedBanknoteCount > 0) {
                    returnedBanknotes.put(currentBanknoteToString, issuedBanknoteCount);

                    currentBanknoteObject.setCount(banknoteCount - issuedBanknoteCount);
                    banknoteRepository.save(currentBanknoteObject);
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
            Banknote updatableBanknote = new Banknote();

            if (banknoteRepository.existsById(currentBanknote.getBanknote())) {
                updatableBanknote = banknoteRepository.findById(currentBanknote.getBanknote()).get();
                updatableBanknote.setCount(banknotes.get(currentBanknote.getBanknote()));
            } else {
                updatableBanknote.setId(currentBanknote.getBanknote());
                updatableBanknote.setCount(banknotes.get(currentBanknote.getBanknote()));
            }

            banknoteRepository.save(updatableBanknote);
        }
    }
}
