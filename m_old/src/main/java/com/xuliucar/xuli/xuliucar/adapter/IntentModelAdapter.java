package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.IntentModelBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/7.
 *
 */
public class IntentModelAdapter extends CommonAdapter<IntentModelBean> {

    public IntentModelAdapter(Context context, List<IntentModelBean> intentModelBeanList) {
       super(context,intentModelBeanList);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext, view, viewGroup, R.layout.intent_model_list_item, i);
        IntentModelBean bean = mDatas.get(i);
        ((TextView) holder.getView(R.id.intent_model_title)).setText(bean.getModel());
        return holder.getmConcertView();
    }
}
