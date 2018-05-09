package com.assignment.transaction.controller;

import com.assignment.statistics.service.StatisticsService;
import com.assignment.transaction.repository.BankTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.Instant;

/**
 *
 */
@RestController

public class TransactionSaveController {

    @Value("${statistics.periodinsec}")
    int statisticPeriodFromNow;
    @Inject
    StatisticsService statisticsService;


    @RequestMapping(path = "/transactions")
    public ResponseEntity saveBankTransaction(@RequestBody @Valid BankTransaction bankTransaction) {
        statisticsService.updateRecentStatistics(bankTransaction);
        if (Instant.now().toEpochMilli() - bankTransaction.getTimestamp() > statisticPeriodFromNow * 1000) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
