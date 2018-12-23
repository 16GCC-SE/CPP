package com.sixgiants.cpp.dao;

import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.mapper.EmployeeMapper;
import com.sixgiants.cpp.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class EmployeeDao {

    @Autowired
    private EmployeeMapper employeeMapper;

    public void saveOrder(Employee employee){
        employee.setId(UUIDutil.getUUID());
        employee.setStatus("进行中");
        employee.setCreateTime(new Date());
        employee.setEnable(false);
        employeeMapper.saveOrder(employee);
    }

    public int CountOrder(String orderId){
        return employeeMapper.CountOrder(orderId);
    }

    public int findEmployeeByOrderIdAndEmployeeId(String orderId, String employeeId) {
        return employeeMapper.findEmployeeByOrderIdAndEmployeeId(orderId, employeeId);
    }
}
