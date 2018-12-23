package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HistoryOrdersMapper {
    @Select("select * from employee")
    List<Employee> findAllEmployees();
}
