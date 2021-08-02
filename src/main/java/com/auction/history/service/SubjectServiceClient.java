package com.auction.history.service;

import com.auction.history.model.Subject;
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

    //    @PostMapping("/codes")
//    List<PurchaseDto> getSubjectsByCodes(@RequestHeader(name = "Authorization") String token, @RequestBody List<String> codes);
}
