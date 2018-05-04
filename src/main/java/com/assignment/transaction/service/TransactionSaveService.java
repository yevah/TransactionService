package com.assignment.transaction.service;

import com.assignment.statistics.service.StatisticsService;
import com.assignment.transaction.repository.BankTransaction;
import com.assignment.transaction.repository.BankTransactionRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Service saving data in  database(currently in memory database is configured) and updating in statistics data kept in memory.
 * Please note that when getting statistical data database will not be used. I use it to keep historical data.
 */
@Component
public class TransactionSaveService {

    private BankTransactionRepository bankTransactionRepository;

    private StatisticsService statisticsService;

    @Inject
    public TransactionSaveService(BankTransactionRepository bankTransactionRepository, StatisticsService statisticsService) {
        this.bankTransactionRepository = bankTransactionRepository;
        this.statisticsService = statisticsService;
    }

    public BankTransaction saveTransaction(BankTransaction bankTransaction) {
        BankTransaction savedTransaction = bankTransactionRepository.save(bankTransaction);

        statisticsService.updateRecentStatistics(savedTransaction);

        return savedTransaction;
    }

}
