package com.auction.purchase.controller;

import com.auction.purchase.model.HistoryPurchase;
import com.auction.purchase.model.Purchase;
import com.auction.purchase.model.Subject;
import com.auction.purchase.service.HistoryPurchaseService;
import com.auction.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final HistoryPurchaseService historyPurchaseService;

    @GetMapping("/bought/{username}")
    public List<Subject> getHistoryPurchases(@PathVariable String username) {
        return historyPurchaseService.findHistoryPurchasesByUsername(username);
    }

    @PostMapping
    public ResponseEntity<?> addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.addPurchase(purchase);
    }

    @PostMapping("/buy")
    ResponseEntity<?> buySubjects(@RequestBody List<String> subjects) {
        subjects.forEach(subject -> {
            Purchase purchase = purchaseService.findPurchasesByCode(subject).stream()
                    .max(Comparator.comparing(Purchase::getPrice))
                    .get();

            HistoryPurchase historyPurchase = new HistoryPurchase(purchase.getCode(), purchase.getUsername());
            historyPurchaseService.addHistoryPurchase(historyPurchase);
            purchaseService.deletePurchases(subject);
        });
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/bidded/{username}")
    public List<Subject> getPurchases(@PathVariable String username) {
        return purchaseService.findPurchasesByUsername(username);
    }

    @GetMapping("/{username}/{code}")
    public Double getPurchase(@PathVariable String username, @PathVariable String code) {
        return purchaseService.getPriceForUserAndSubject(username, code);
    }

    @GetMapping("bought/{code}/{username}")
    public Boolean getNewPurchase(@PathVariable String username, @PathVariable String code) {
        return historyPurchaseService.existsByUsernameAndCode(username, code);
    }
}
