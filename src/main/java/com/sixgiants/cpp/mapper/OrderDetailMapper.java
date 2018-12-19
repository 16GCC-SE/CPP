package com.sixgiants.cpp.mapper;

import com.sixgiants.cpp.entity.Order;
import com.sixgiants.cpp.entity.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface OrderDetailMapper {
    @Select("select * from orders where id=#{id}")
    Order findOrderById(String id);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(property = "user",column = "employerId",one = @One(select = "com.sixgiants.cpp.mapper.OrderDetailMapper.findOrderById"))
    })
    User findEmployerId(String id);
}
