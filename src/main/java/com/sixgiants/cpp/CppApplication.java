package com.sixgiants.cpp;

import com.sixgiants.cpp.util.MD5Util;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sixgiants.cpp.mapper")
public class CppApplication {

    public static void main(String[] args) {

        SpringApplication.run(CppApplication.class, args);
        //System.out.println(MD5Util.md5("123456"));
    }
}
