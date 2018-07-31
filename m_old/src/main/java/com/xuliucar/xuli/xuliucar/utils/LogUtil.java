package com.xuliucar.xuli.xuliucar.utils;

import android.util.Log;

/**
 * Created by skyward on 2016/11/29.
 * email：
 */

public class LogUtil {
    private static boolean isPrintLog = true;
    private static int LOG_MAXLENGTH = 2000;
    public static void LogPrint(String msg){
        if(isPrintLog){
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.i("myLog___" + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.i("myLog___" + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
        }

    public static void LogShitou(String type, String msg) {

        if (isPrintLog) {

            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                if (strLength > end) {
                    Log.i(type + "___" + i, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.i(type + "___" + i, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

}
