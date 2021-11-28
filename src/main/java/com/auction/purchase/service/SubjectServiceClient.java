package com.auction.purchase.service;

import com.auction.purchase.model.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "SUBJECT-SERVICE")
@RequestMapping("/subjects")
public interface SubjectServiceClient {
    @PostMapping("/codes")
    List<Subject> getSubjectsByCodes(@RequestBody List<String> codes);
}
