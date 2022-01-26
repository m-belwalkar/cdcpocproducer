package com.azurepoc.cdc.repository;

import com.azurepoc.cdc.model.EmployeeHistory;

import java.util.List;
import java.util.Map;

public interface EmployeeHistoryRepository {

    List<Map<String, Object>> findAll();

    List<EmployeeHistory> findAllLastUpdated(String t1, String t2);

    List<EmployeeHistory> findAllLastUpdatedByTime();


}
