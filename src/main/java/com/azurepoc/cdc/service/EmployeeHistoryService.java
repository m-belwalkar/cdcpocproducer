package com.azurepoc.cdc.service;

import com.azurepoc.cdc.eventhub.EventProducer;
import com.azurepoc.cdc.model.EmployeeHistory;
import com.azurepoc.cdc.repository.EmployeeHistoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class EmployeeHistoryService {

    public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeHistoryService.class);

    @Autowired
    EmployeeHistoryRepository employeeHistoryRepository;
    @Autowired
    EventProducer eventProducer;

    public List<Map<String, Object>> getEmployeeHistory() {
        return employeeHistoryRepository.findAll();
    }


    public void pushEmployeeHistorydata() {
        System.out.println("Scheduled pushing Employee History data");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObj = "";

        List<EmployeeHistory> employeeList = employeeHistoryRepository.findAllLastUpdated(getDateTime(), getDelayedDateTime());

        try {
            jsonObj = objectMapper.writeValueAsString(employeeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        eventProducer.sendMessage(jsonObj);
    }

    public void pushEmployeeHistorydataByTime() {
        System.out.println("Scheduled pushing Employee History data by time");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObj = "";

        List<EmployeeHistory> employeeList = employeeHistoryRepository.findAllLastUpdatedByTime();

        try {
            jsonObj = objectMapper.writeValueAsString(employeeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        eventProducer.sendMessage(jsonObj);
    }

    public void pushEmployeeStaticHistorydata(){
        // List<EmployeeHistory> employeeHistoryList = getLastUpdatedEmployeeHistory();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObj = "";

        List<EmployeeHistory> employeeList = new ArrayList<>();
        for(int i=0; i< 5; i++) {
            Random random = new Random(10);
            int randomIndex = random.nextInt();
            EmployeeHistory employee = new EmployeeHistory();

            employee.setDepartment("desig" + new Random().nextInt(10));
            employee.setDesignation("depart" + new Random().nextInt(10));
            employee.setUpdatedUserId("user" + new Random().nextInt(10));
            employeeList.add(employee);
        }


        try {
            //jsonObj = objectMapper.writeValueAsString(employeeHistoryList);
            jsonObj = objectMapper.writeValueAsString(employeeList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        eventProducer.sendMessage(jsonObj);
    }

    private String getDateTime() {
        String timeColonPattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        LocalDateTime colonTime = LocalDateTime.now();
        System.out.println("getDateTime()->"+colonTime.format(timeColonFormatter));
        return colonTime.format(timeColonFormatter);
    }

    private String getDelayedDateTime() {
        String timeColonPattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
        LocalDateTime colonTime = LocalDateTime.now();
        System.out.println("getDelayedDateTime() ->"+colonTime.minus(5, ChronoUnit.MINUTES).format(timeColonFormatter));
        return colonTime.minus(5, ChronoUnit.MINUTES).format(timeColonFormatter);
    }
}
