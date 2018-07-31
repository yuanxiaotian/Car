package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by ucarit003 on 2016/9/11.
 *
 */
public class SearchAdapter extends BaseAdapter {
    private final Context context;
    private final List<SearchLightBean> searchLightBeanList;

    public SearchAdapter(Context context, List<SearchLightBean> searchLightBeanList) {
        this.context = context;
        this.searchLightBeanList = searchLightBeanList;
    }

    @Override
    public int getCount() {
        return searchLightBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return searchLightBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context,view,viewGroup, R.layout.search_light_result,i);
        SearchLightBean searchLightBean = searchLightBeanList.get(i);
        ((TextView)holder.getView(R.id.autoComplete)).setText(searchLightBean.getSpannableString());
        return holder.getmConcertView();
    }
}
