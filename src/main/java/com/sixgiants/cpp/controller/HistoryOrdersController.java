package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Employee;
import com.sixgiants.cpp.service.impl.HistoryOrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class HistoryOrdersController {
    @Autowired
    private HistoryOrdersServiceImpl historyOrdersServiceImpl;

    @GetMapping("/historyorders")
    @ResponseBody
    public List<HashMap> findAllEmployees() {
        return historyOrdersServiceImpl.findAllHistoryOrders();
    }

    @GetMapping("/lengthofemployee")
    @ResponseBody
    public int getLengthOfEmployee() {
        return historyOrdersServiceImpl.findAllHistoryOrders().size();
    }
}
