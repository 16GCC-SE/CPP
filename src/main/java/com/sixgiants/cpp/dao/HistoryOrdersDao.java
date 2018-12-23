package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.mapper.HistoryOrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryOrdersDao {
    @Autowired
    private HistoryOrdersMapper historyOrdersMapper;
    public List<Employee> findAllEmployees() {
        return historyOrdersMapper.findAllEmployees();
    }
}
