package com.geek.bloglib.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrTime(){
//        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        return format0.format(time);
    }

}
