package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.HistoryOrdersDao;
import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HistoryOrdersServiceImpl {
    @Autowired
    private HistoryOrdersDao historyOrdersDao;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private UserServiceImpl userService;
    public List<HashMap> findAllHistoryOrders() {
        String orderId;
        String employerId;
        Order order;
        User user;
        HashMap attSet;
        List historyOrders = new ArrayList<HashMap>();
        List<Employee> employees = historyOrdersDao.findAllEmployees();
        for (Employee employee : employees) {
            orderId = employee.getOrderId();
            order = orderDetailService.findOrderById(orderId);
            employerId = order.getEmployerId();
            user = userService.findUserById(employerId);
            employee.setCreateTime(java.sql.Date.valueOf(DateFormatUtil.dateFormat(employee.getCreateTime())));
            attSet= new HashMap<String, Object>();
            attSet.put("title", order.getTitle());
            attSet.put("name", user.getName());
            attSet.put("createTime", employee.getCreateTime());
            attSet.put("salary", order.getSalary());
            attSet.put("status", employee.getStatus());
            historyOrders.add(attSet);
        }
        return historyOrders;
    }
}
