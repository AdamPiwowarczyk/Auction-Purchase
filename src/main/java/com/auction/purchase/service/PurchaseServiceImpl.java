package com.auction.purchase.service;

import com.auction.purchase.model.Purchase;
import com.auction.purchase.model.Subject;
import com.auction.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final SubjectServiceClient subjectServiceClient;

    @Override
    public ResponseEntity<?> addPurchase(Purchase purchase) {
        Purchase purchaseFromDb = purchaseRepository.findByUsernameAndCode(purchase.getUsername(), purchase.getCode());

        if (purchaseFromDb != null) {
            purchaseFromDb.setPrice(purchase.getPrice());
            purchaseRepository.save(purchaseFromDb);
        } else {
            purchaseRepository.save(purchase);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public List<Subject> findPurchasesByUsername(String username) {
        List<Purchase> purchases = purchaseRepository.findAllByUsername(username);
        List<String> codes = purchases.stream()
                .map(Purchase::getCode)
                .collect(Collectors.toList());

        return subjectServiceClient.getSubjectsByCodes(codes);
    }

    @Override
    public Double getPriceForUserAndSubject(String username, String code) {
        Purchase purchase = purchaseRepository.findByUsernameAndCode(username, code);
        if (purchase != null) {
            return purchase.getPrice();
        } else {
            return 0.0;
        }
    }

    @Override
    public List<Purchase> findPurchasesByCode(String code) {
        return purchaseRepository.findLastPurchaseByCode(code);
    }

    @Override
    @Transactional
    public void deletePurchases(String subject) {
        purchaseRepository.deleteByCode(subject);
    }
}
