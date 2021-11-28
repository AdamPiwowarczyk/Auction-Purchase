package com.auction.purchase.service;

import com.auction.purchase.model.Purchase;
import com.auction.purchase.model.Subject;
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
