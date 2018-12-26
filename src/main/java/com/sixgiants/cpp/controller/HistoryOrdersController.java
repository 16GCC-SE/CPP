package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.entity.User;
import com.sixgiants.cpp.service.impl.HistoryOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HistoryOrdersController {
    @Autowired
    private HistoryOrdersServiceImpl historyOrdersServiceImpl;

    @GetMapping("/historyorders")
    @ResponseBody
    public List<HashMap> findAllEmployees() {
        List<HashMap> historyOrders = historyOrdersServiceImpl.findAllHistoryOrders();
        List<HashMap> newHistoryOrders = new ArrayList<HashMap>();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User employeeUser = (User)authentication.getPrincipal();
        for (HashMap order : historyOrders) {
            if (employeeUser.getId().equals(order.get("employeeId"))) {
                newHistoryOrders.add(order);
            }
        }
        return newHistoryOrders;
    }

    @GetMapping("/lengthofemployee")
    @ResponseBody
    public int getLengthOfEmployee() {
        return historyOrdersServiceImpl.findAllHistoryOrders().size();
    }
}
