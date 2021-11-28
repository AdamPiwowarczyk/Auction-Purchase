package com.auction.purchase.repository;

import com.auction.purchase.model.HistoryPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryPurchase, Long> {
    List<HistoryPurchase> findAllByUsername(String username);

    Boolean existsByUsernameAndCode(String username, String code);
}
