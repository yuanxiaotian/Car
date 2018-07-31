package com.xuliucar.xuli.xuliucar.bean;

import android.text.SpannableString;

/**
 * Created by ucarit003 on 2016/9/11.
 *
 */
public class SearchLightBean {
    private SpannableString spannableString;

    public SearchLightBean(SpannableString spannableString) {
        this.spannableString = spannableString;
    }

    public SpannableString getSpannableString() {
        return spannableString;
    }

    public void setSpannableString(SpannableString spannableString) {
        this.spannableString = spannableString;
    }
}
