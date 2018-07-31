package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.Policy;

import java.util.List;

/**
 * Created by skyward on 2016/7/21.
 *
 */
public class PolicyAdapter extends PagerAdapter {
    private final List<Policy> policyList;
    private final Context context;

    public PolicyAdapter(Context context,List<Policy> policyList) {
        this.context = context;
        this.policyList = policyList;
    }

    @Override
    public int getCount() {
        return policyList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Policy policy = policyList.get(position);
        View view = View.inflate(context, R.layout.iviewpager, null);

        TextView s_indemnity_norm = (TextView) view.findViewById(R.id.s_indemnity_norm);
        TextView s_abatement = (TextView) view.findViewById(R.id.s_abatement);
        TextView s_base = (TextView) view.findViewById(R.id.s_base);
        TextView s_coefficient = (TextView) view.findViewById(R.id.s_coefficient);
        TextView s_total = (TextView) view.findViewById(R.id.s_total);
        s_indemnity_norm.setText(policy.getIndemnity_norm());
        s_abatement.setText(policy.getAbatement());
        s_base.setText(policy.getBase());
        s_coefficient.setText(policy.getCoefficient());
        s_total.setText(policy.getTotal());
        container.addView(view);
        return view;
    }

    /**
     * 销毁page position:当前要销毁第几个page object：当前需要销毁的page
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);

    }
}
