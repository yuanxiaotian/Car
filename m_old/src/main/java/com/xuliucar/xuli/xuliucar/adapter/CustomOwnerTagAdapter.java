package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.PeopleBean;
import com.xuliucar.xuli.xuliucar.widget.tagView.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyward on 2016/12/8.
 * email：
 */

public class CustomOwnerTagAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<PeopleBean.DataBean.Bean> mDataList;
    public CustomOwnerTagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        PeopleBean.DataBean.Bean t = mDataList.get(position);
        textView.setText(t.getName());
//        if (t instanceof String) {
//            textView.setText((String) t);
//        }
        return view;
    }

    public void onlyAddAll(List<PeopleBean.DataBean.Bean> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<PeopleBean.DataBean.Bean> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
