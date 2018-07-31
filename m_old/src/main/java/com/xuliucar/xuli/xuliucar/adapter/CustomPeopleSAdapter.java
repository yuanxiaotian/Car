package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.CustomPeopleBean;
import com.xuliucar.xuli.xuliucar.widget.tagView.OnInitSelectedPosition;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/12/8.
 *
 */

public class CustomPeopleSAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<CustomPeopleBean> mDataList;

    public CustomPeopleSAdapter(Context context,List<CustomPeopleBean> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = ViewHolder.get(mContext,convertView,parent,R.layout.tag_item,position);
        CustomPeopleBean t = mDataList.get(position);
        ((TextView)holder.getView(R.id.tv_tag)).setText(t.getName());

        return holder.getmConcertView();
    }



    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
