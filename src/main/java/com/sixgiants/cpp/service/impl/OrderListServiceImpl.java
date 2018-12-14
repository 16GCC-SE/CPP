package com.sixgiants.cpp.service.impl;

import com.sixgiants.cpp.dao.OrderListDao;
import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderListServiceImpl {
    @Autowired
    private OrderListDao orderListDao;
    List<String> schoolList = setSchoolListData();
    List<String> personList = setPersonListData();
    List<String> merchantList = setMerchantListData();

    public List<String> setSchoolListData() {
        List<String> schoolList = new ArrayList<String>();
        schoolList.add("校园活动");
        return schoolList;
    }

    public List<String> setPersonListData() {
        List<String> personList = new ArrayList<String>();
        personList.add("代课员");
        personList.add("代拿员");
        personList.add("其它");
        return personList;
    }

    public List<String> setMerchantListData() {
        List<String> merchantList = new ArrayList<String>();
        merchantList.add("宣传员");
        merchantList.add("送餐员");
        merchantList.add("校外");
        merchantList.add("长工");
        merchantList.add("短工");
        return merchantList;

    }
    public List<Order> getOrders() {
        List<Order> orders = orderListDao.findOrders();
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setCreateTime(java.sql.Date.valueOf(DateFormatUtil.dateFormat(orders.get(i).getCreateTime())));
        }
        return orders;
    }

    public int getLengthOfOrders() {
        return orderListDao.findOrders().size();
    }

    public List<Order> getSettlementMethod(String settlement) {
        List<Order> newOrders = new ArrayList<Order>();
        List<Order> orders = getOrders();
        for (Order order : orders) {
            if (settlement.equals(order.getSettlementMethod())) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }

    public List<Order> getClassification(String classification) {
        List<Order> newOrders = new ArrayList<Order>();
        List<Order> orders = getOrders();
        for (Order order : orders) {
            if (classification.equals(order.getClassification())) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }

    public List<Order> getSexNeed(String sex) {
        List<Order> newOrders = new ArrayList<Order>();
        List<Order> orders = getOrders();
        for (Order order : orders) {
            if (sex.equals(order.getSex())) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }

    public List<Order> getSalarySort(String method) {
        List<Order> orders = getOrders();
        Collections.sort(orders,new Comparator<Order>() {

            @Override
            public int compare(Order o1, Order o2) {
                if ("max".equals(method)) { //从高到低
                    return o2.getSalary() - o1.getSalary();
                } else {
                    return o1.getSalary()-o2.getSalary();
                }
            }
        });
        return orders;
    }

    public List<Order> getJobType(String jobType) {
        List<Order> newOrders = new ArrayList<Order>();
        List<Order> orders = getOrders();
        for (Order order : orders) {
            switch (jobType) {
                case "school":
                    if (schoolList.contains(order.getClassification())) {
                        newOrders.add(order);
                    }
                    break;
                case "merchant":
                    if (merchantList.contains(order.getClassification())) {
                        newOrders.add(order);
                    }
                    break;
                case "person":
                    if (personList.contains(order.getClassification())) {
                        newOrders.add(order);
                    }
                    break;
                default:
                    break;
            }
        }
        return newOrders;
    }

    public List<Order> getReleaseSort(String method) {
        List<Order> orders = getOrders();
        Collections.sort(orders,new Comparator<Order>() {

            @Override
            public int compare(Order o1, Order o2) {
                if ("newest".equals(method)) {  //从新到晚
                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                } else {
                    return o1.getCreateTime().compareTo(o2.getCreateTime());
                }
            }
        });
        return orders;
    }
}
