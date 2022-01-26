package com.azurepoc.cdc.repository;

import com.azurepoc.cdc.mapper.EmployeeHistoryMapper;
import com.azurepoc.cdc.model.EmployeeHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcEmployeeHistoryRepository implements EmployeeHistoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> findAll() {
        String query = "select top 10 * from employee_history ";
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public List<EmployeeHistory> findAllLastUpdated(String t1, String t2) {
        String query = "select  * from employee_history where updatedDate > ? and  updatedDate <= ?";
        //return jdbcTemplate.queryForList(query);
        List<EmployeeHistory> employeeHistoryList = jdbcTemplate.query(query, new PreparedStatementSetter() {

            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, t1);
                preparedStatement.setString(2, t2);
            }
        }, new EmployeeHistoryMapper());
        return employeeHistoryList;
    }

    @Override
    public List<EmployeeHistory> findAllLastUpdatedByTime() {
        String query = "select  * from employee_history where updatedDate >= dateadd(day,datediff(day,1,GETDATE()),0)";
        //return jdbcTemplate.queryForList(query);
        List<EmployeeHistory> employeeHistoryList = jdbcTemplate.query(query, new EmployeeHistoryMapper());
        return employeeHistoryList;
    }
}
