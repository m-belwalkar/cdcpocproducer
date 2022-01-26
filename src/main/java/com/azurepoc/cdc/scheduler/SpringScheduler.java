package com.azurepoc.cdc.scheduler;

import com.azurepoc.cdc.service.EmployeeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringScheduler {


    @Autowired
    EmployeeHistoryService employeeHistoryService ;

    //300000
   // @Scheduled(fixedDelay = 30000)
    public void ddlSchedule() {
        employeeHistoryService.pushEmployeeHistorydataByTime();
    }

}
