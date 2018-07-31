package com.xuliucar.xuli.xuliucar.utils;

import android.annotation.SuppressLint;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by skyward on 2016/7/21.
 *
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {



    public static Date stringToDate(String dateString) {
        ParsePosition position = new ParsePosition(0);
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return simpleDateFormat.parse(dateString, position);
    }

    public static Date stringToDateTime(String dateString) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(dateString, position);
    }


}
