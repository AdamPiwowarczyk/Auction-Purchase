package com.auction.history.service;

import com.auction.history.model.HistoryPurchase;
import com.auction.history.model.Subject;

import java.util.List;

public interface HistoryPurchaseService {
    void addHistoryPurchase(HistoryPurchase historyPurchase);

    List<Subject> findHistoryPurchasesByUsername(String username);

    Boolean existsByUsernameAndCode(String username, String code);
}
