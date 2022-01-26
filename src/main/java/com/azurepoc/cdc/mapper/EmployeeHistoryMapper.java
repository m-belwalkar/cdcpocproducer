package com.azurepoc.cdc.mapper;

import com.azurepoc.cdc.model.EmployeeHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeHistoryMapper implements RowMapper<EmployeeHistory> {
    @Override
    public EmployeeHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeHistory employeeHistory = new EmployeeHistory();
        employeeHistory.setId(rs.getInt("id"));
        employeeHistory.setName(rs.getString("name"));
        employeeHistory.setDesignation(rs.getString("designation"));
        employeeHistory.setDepartment(rs.getString("department"));
        employeeHistory.setUpdatedUserId(rs.getString("updatedUserId"));
        employeeHistory.setCreationDate(rs.getDate("creationDate"));
        employeeHistory.setUpdatedDate(rs.getDate("updatedDate"));
        employeeHistory.setOperation(rs.getString("operation"));
        return employeeHistory;
    }
}
