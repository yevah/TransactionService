package com.assignment.statistics.controller;

import com.assignment.statistics.service.PeriodStatistics;
import com.assignment.statistics.service.StatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Controller for endpoints related to statistics data
 */
@RestController
public class StatisticsController {

    @Inject
    private StatisticsService statisticsService;

    @RequestMapping(path = "/statistics")
    public PeriodStatistics getRecentStatistics() {
        return statisticsService.getStatisticsForLastSeconds();
    }
}
