package com.auction.history.repository;

import com.auction.history.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    List<Purchase> findAllByUsername(String username);

    Purchase findByUsernameAndCode(String username, String code);

    @Query("select p from Purchase p where p.code = :code order by p.price")
    List<Purchase> findLastPurchaseByCode(String code);

    void deleteByCode(String subject);
}
