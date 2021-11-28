package com.auction.purchase.service;

import com.auction.purchase.model.HistoryPurchase;
import com.auction.purchase.model.Subject;

import java.util.List;

public interface HistoryPurchaseService {
    void addHistoryPurchase(HistoryPurchase historyPurchase);

    List<Subject> findHistoryPurchasesByUsername(String username);

    Boolean existsByUsernameAndCode(String username, String code);
}
