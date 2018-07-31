package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by skyward on 2016/3/12.
 *
 */
public class ViewPagerAdapter extends PagerAdapter {

    private final List<View> views;

    public ViewPagerAdapter(List<View> views, Context context){
        this.views =views;

    }

    //确定要删除的页面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        (container).removeView(views.get(position));
    }

    //确定要加载的页面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    //页面的个数
    @Override
    public int getCount() {
        return views.size();
    }

    //比较
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
