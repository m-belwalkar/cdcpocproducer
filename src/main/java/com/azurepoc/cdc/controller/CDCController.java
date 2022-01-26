package com.azurepoc.cdc.controller;

import com.azurepoc.cdc.service.EmployeeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CDCController {


    @Autowired
    EmployeeHistoryService employeeHistoryService;

    @GetMapping("/whoami")
    public String getGreeting() {
        return "CDC Producer Testing";
    }


    @GetMapping("/latestEmployeehistory")
    public void getLatestEmployeeHistory() {
        employeeHistoryService.pushEmployeeHistorydata();
    }

    @GetMapping("/latestEmployeehistoryByTime")
    public void getLatestEmployeeHistoryByTime() {
        employeeHistoryService.pushEmployeeHistorydataByTime();
    }

}
