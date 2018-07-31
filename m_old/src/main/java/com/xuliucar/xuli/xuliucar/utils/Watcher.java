package com.xuliucar.xuli.xuliucar.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.base.App;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**监听EditText输入状态
 * Created by skyward on 2016/6/28.
 */
public class Watcher {
    /**
     *
     * @param editText 要监听的输入框
     * @param imageView 显示清除输入内容的图标
     */
    public static void watcher(EditText editText, final ImageView imageView){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = String.valueOf(editable);
                //Log.i("myLog","length: "+str);
                if(str.length() > 0){

                    imageView.setVisibility(View.VISIBLE);
                }else{
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * 邮箱选填友好处理
     */
    public static void watcherText(EditText editText, final TextView textView){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String str = String.valueOf(editable);
                //Log.i("myLog","length: "+str);
                if(str.length() > 0){

                    textView.setVisibility(View.INVISIBLE);
                }else{
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 过滤填写特殊字符
     *
     */

    public static void IllegalCharacter(final EditText editText){
        final int mMaxLength = 200;//设置允许输入的字符长度
        editText.addTextChangedListener(new TextWatcher() {
            private int cou = 0;
            int selectionEnd = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                cou = before + count;
                String editable = editText.getText().toString();
                String str = stringFilter(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    editText.setText(str);
                }
                editText.setSelection(editText.length());
                cou = editText.length();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (cou > mMaxLength) {
                    selectionEnd = editText.getSelectionEnd();
                    s.delete(mMaxLength, selectionEnd);
                }
            }
        });
    }

    public static void IllegalCustomMsg(final EditText editText) {
        final int mMaxLength = 200;//设置允许输入的字符长度
        editText.addTextChangedListener(new TextWatcher() {
            private int cou = 0;
            int selectionEnd = 0;

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                cou = before + count;
                String editable = editText.getText().toString();
                String str = customMsg(editable); //过滤特殊字符
                if (!editable.equals(str)) {
                    editText.setText(str);
                }
                editText.setSelection(editText.length());
                cou = editText.length();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (cou > mMaxLength) {
                    selectionEnd = editText.getSelectionEnd();
                    s.delete(mMaxLength, selectionEnd);
                }
            }
        });
    }

    public static String customMsg(String str)throws PatternSyntaxException {
        String   regEx  =  "[^a-zA-Z0-9(.。！!)，,-_:：？?—\u4E00-\u9FA5]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        return   m.replaceAll("").trim();
    }

    /**
     * 只允许输入中文英文数字
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str)throws PatternSyntaxException {
        String   regEx  =  "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        return   m.replaceAll("").trim();
    }
    //表单号的输入
    public static void watcherText(EditText editText, final Context context){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String edt= editable.toString();
//                Log.i("myLog ","edt " +edt);
                App.from = edt;
            }
        });
    }

    public static void calculateCounts(EditText editText, final TextView textView){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView.setText(String.format("当前已输入：%s", String.valueOf(s.length() + "字")));
            }
        });
    }


}
