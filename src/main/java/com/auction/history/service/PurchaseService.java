package com.auction.history.service;

import com.auction.history.model.Purchase;
import com.auction.history.model.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    ResponseEntity<?> addPurchase(Purchase purchase);

    List<Subject> findPurchasesByUsername(String username);

    Double getPriceForUserAndSubject(String username, String code);

    List<Purchase> findPurchasesByCode(String code);

    void deletePurchases(String subject);
}
