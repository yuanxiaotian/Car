package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.NoticeBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.LinkedList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by skyward on 2016/7/15.
 *
 */
public class NoticeAdapter extends CommonAdapter<NoticeBean.NoticeList>{

    public NoticeAdapter(Context context, List<NoticeBean.NoticeList> mNoticeLists) {
        super(context,mNoticeLists);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, R.layout.notice_item,i);
        NoticeBean.NoticeList noticeBean = mDatas.get(i);
        ((TextView)holder.getView(R.id.notice_title)).setText(noticeBean.getTitle());
        ((TextView)holder.getView(R.id.notice_scon)).setText(noticeBean.getScon());
        ((TextView)holder.getView(R.id.notice_time)).setText(noticeBean.getTime());
        CardView notice_item_layout = holder.getView(R.id.notice_item_layout);
//        RxView.clicks(notice_item_layout)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//
//                    }
//                });

        return holder.getmConcertView();
    }
}
