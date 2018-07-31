package com.xuliucar.xuli.xuliucar.utils;

import android.content.Context;
import android.widget.LinearLayout;


/**
 * Created by skyward on 2016/9/5.
 *
 */
public class Pull {


    public static void pullToRefreshDown(final Context context, final Object refreshLayout, final LinearLayout refresh_tip_no, int size){
        if(size == 0){
            ToastUtil.tips(context,refresh_tip_no);
        }
//        refreshLayout.endRefreshing();
    }


    public static void pullToRefreshUp(final Context context, final Object refreshLayout, final LinearLayout refresh_tip, int size, int starts, int ends){

        if (size > 10) {
            if (ends >= size ) {
//                refreshLayout.endLoadingMore();
//                refreshLayout.setOnLastListViewItemVisibleListener(new OnLastListViewItemVisibleListener() {
//                    @Override
//                    public void onLastItemVisible() {
//                        ToastUtil.tips(context,refresh_tip);
//                    }
//                });

            } else {
//                refreshLayout.endLoadingMore();
            }
        }else{
//            refreshLayout.endLoadingMore();
            ToastUtil.tips(context,refresh_tip);
        }
    }
}
