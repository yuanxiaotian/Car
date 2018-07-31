package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by skyward on 2016/3/26.
 *
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected final Context mContext;
    protected final List<T> mDatas;
    protected final LayoutInflater inflater;

    public CommonAdapter(Context context, List<T> Datas) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mDatas = Datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup);
}
