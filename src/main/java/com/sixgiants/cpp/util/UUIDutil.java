package com.sixgiants.cpp.util;

import java.util.UUID;

/**
 * @author GMUK
 * @date 2018/11/17 0017
 */
public class UUIDutil {
    public static String getUUID(){
       return UUID.randomUUID().toString().replaceAll("-","");
    }
}
