package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {

    @Select("insert into employee(id,order_id,status,employee_id,create_time,is_enable) values(#{id},#{orderId},#{status},#{employeeId},#{createTime},#{isEnable})")
    void saveOrder(Employee employee);

    @Select("select count(*) from employee where order_id=#{orderId}")
    int CountOrder(String orderId);

    @Select("select count(*) from employee where order_id=#{orderId} and employee_id=#{employeeId}")
    int findEmployeeByOrderIdAndEmployeeId(@Param("orderId") String orderId, @Param("employeeId") String employeeId);
}
