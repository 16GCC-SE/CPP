package com.sixgiants.cpp.controller;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.service.impl.OrderListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderListController {
    @Autowired
    private OrderListServiceImpl orderListServiceImpl;

    @GetMapping("/orders")
    @ResponseBody
    public List<Order> getOrders() {
        List<Order> orders = orderListServiceImpl.getOrders();
        return orders;
    }
    @GetMapping("/length")
    @ResponseBody
    public int getLengthOfUsers() {
        return orderListServiceImpl.getLengthOfOrders();
    }

    @GetMapping("/timesettlement")
    @ResponseBody
    public  List<Order> getTimeSettlement() {
        return orderListServiceImpl.getSettlementMethod("次结");
    }

    @GetMapping("/daysettlement")
    @ResponseBody
    public  List<Order> getDaySettlement() {
        return orderListServiceImpl.getSettlementMethod("日结");
    }

    @GetMapping("/monsettlement")
    @ResponseBody
    public  List<Order> getMonSettlement() {
        return orderListServiceImpl.getSettlementMethod("月结");
    }

    @GetMapping("/needmale")
    @ResponseBody
    public  List<Order> getMale() {
        return orderListServiceImpl.getSexNeed("男");
    }

    @GetMapping("/needfamale")
    @ResponseBody
    public  List<Order> getFamale() {
        return orderListServiceImpl.getSexNeed("女");
    }

    @GetMapping("/minsalary")
    @ResponseBody
    public  List<Order> getMinSalary() {
        return orderListServiceImpl.getSalarySort(0);
    }

    @GetMapping("/merchantjob")
    @ResponseBody
    public  List<Order> getMerchantJob() {
        return orderListServiceImpl.getMerchantJob();
    }

    @GetMapping("/personjob")
    @ResponseBody
    public  List<Order> getPersonJob() {
        return orderListServiceImpl.getPersonJob();
    }

    @GetMapping("/latestrelease")
    @ResponseBody
    public  List<Order> getLatestRelease() {
        return orderListServiceImpl.getReleaseSort(0);
    }

    @GetMapping("/agentclass")
    @ResponseBody
    public  List<Order> getAgentClass() {
        return orderListServiceImpl.getClassification("代课员");
    }

    @GetMapping("/agentpick")
    @ResponseBody
    public  List<Order> getAgentPick() {
        return orderListServiceImpl.getClassification("代拿员");
    }

    @GetMapping("/delivefoot")
    @ResponseBody
    public  List<Order> getDeliveFoot() {
        return orderListServiceImpl.getClassification("送餐员");
    }

    @GetMapping("/promoter")
    @ResponseBody
    public  List<Order> getPromoter() {
        return orderListServiceImpl.getClassification("宣传员");
    }

    @GetMapping("/insidesparty")
    @ResponseBody
    public  List<Order> getInsidesParty() {
        return orderListServiceImpl.getClassification("校园活动");
    }

    @GetMapping("/outsides")
    @ResponseBody
    public  List<Order> getOutsides() {
        return orderListServiceImpl.getClassification("校外");
    }

    @GetMapping("/longjob")
    @ResponseBody
    public  List<Order> getLongJob() {
        return orderListServiceImpl.getClassification("长工");
    }

    @GetMapping("/shortjob")
    @ResponseBody
    public  List<Order> getShortJob() {
        return orderListServiceImpl.getClassification("短工");
    }

    @GetMapping("/others")
    @ResponseBody
    public  List<Order> getOthers() {
        return orderListServiceImpl.getClassification("其他");
    }
}
