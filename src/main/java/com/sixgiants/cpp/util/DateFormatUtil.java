package com.sixgiants.cpp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GMUK
 * @date 2018/11/17 0017
 */
public class DateFormatUtil {
    public static String dateFormat(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
