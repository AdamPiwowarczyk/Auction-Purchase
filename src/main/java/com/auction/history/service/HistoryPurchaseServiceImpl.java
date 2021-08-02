package com.auction.history.service;

import com.auction.history.model.HistoryPurchase;
import com.auction.history.model.Subject;
import com.auction.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryPurchaseServiceImpl implements HistoryPurchaseService {
    private final HistoryRepository historyRepository;
    private final SubjectServiceClient subjectServiceClient;

    public void addHistoryPurchase(HistoryPurchase historyPurchase) {
        historyRepository.save(historyPurchase);
    }

    @Override
    public List<Subject> findHistoryPurchasesByUsername(String username) {//zmienić typ zwracany na SubjectDto (subject)
        List<String> purchases = historyRepository.findAllByUsername(username).stream()
                .map(purchase -> purchase.getCode())
                .collect(Collectors.toList());
        return subjectServiceClient.getSubjectsByCodes(purchases);
    }

    @Override
    public Boolean existsByUsernameAndCode(String username, String code) {
        return historyRepository.existsByUsernameAndCode(username, code);
    }
}
