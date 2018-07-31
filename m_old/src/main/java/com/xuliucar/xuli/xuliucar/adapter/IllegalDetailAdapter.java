package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.ConstructionWasteBean;
import com.xuliucar.xuli.xuliucar.bean.IllegalDetailBean;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skyward on 2017/1/3.
 * email：
 */

public class IllegalDetailAdapter extends BaseAdapter {
    private Context mContext;
    private List<IllegalDetailBean> mList;
    private Map<Integer,Boolean> mMap;
    private OnSelectedItemChanged listener;

    public IllegalDetailAdapter(Context context, List<IllegalDetailBean> list, OnSelectedItemChanged listener) {
        mContext = context;
        mList = list;
        this.listener = listener;
        mMap = new HashMap<>();
        for (int i = 0; i < mList.size(); i++) {
            mMap.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext,convertView,parent, R.layout.illegal_detail_item,position);
        IllegalDetailBean bean = mList.get(position);
        ((TextView)holder.getView(R.id.document_num)).setText(bean.getDocNum());
        ((TextView)holder.getView(R.id.place_of_violation)).setText(bean.getPlace());
        ((TextView)holder.getView(R.id.illegal_code)).setText(bean.getIllegalCode());
        ((TextView)holder.getView(R.id.fine)).setText(bean.getFine());
        ((TextView)holder.getView(R.id.late_fee)).setText(bean.getLateFine());
        ((TextView)holder.getView(R.id.service_charge)).setText(bean.getServicePay());
        ((TextView)holder.getView(R.id.total)).setText(bean.getTotalMoney());
        TextView service_cost =  holder.getView(R.id.service_cost);
        if(App.userp.equals("10")){
            service_cost.setVisibility(View.VISIBLE);
        }else {
            service_cost.setVisibility(View.INVISIBLE);
        }

        final CheckBox checkBox = holder.getView(R.id.illegal_checkbox);
        checkBox.setChecked(mMap.get(position));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    mMap.put(position, true);
                }else {
                    mMap.put(position,false);
                }
                listener.selectedItemChange(getSelectedCount(mMap));
            }
        });
        return holder.getmConcertView();
    }

    /**
     * 获取选择的项的数目
     */
    private int getSelectedCount(Map<Integer, Boolean> map) {
        int i = 0;
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                i++;
            }

        }
        return i;
    }

    public interface OnSelectedItemChanged {
        void selectedItemChange(int count);
    }

    public void selectAll() { // 全选

        for (int i = 0; i < mList.size(); i++) {
            //ConstructionWasteBean.DataBean.InfoBean fullLoad = eProtectBeanList.get(i);
            mMap.put(i, true);
//            SPUtils.putSharePre(context,"GroupMsg","phone"+i, fullLoad.getRphone());
//            SPUtils.putSharePre(context,"GroupMsg","id"+i,fullLoad.getSmstarid());
        }
        listener.selectedItemChange(getSelectedCount(mMap));
        notifyDataSetChanged();
    }
    public void disSelectAll() { // 全不选

            for (int i = 0; i < mList.size(); i++) {
                mMap.put(i, false);
            }
        //SPUtils.clearSharePre(context, "GroupMsg");
        listener.selectedItemChange(getSelectedCount(mMap));
        notifyDataSetChanged();
    }

//    public void switchSelect() { // 反选
//        for (int i = 0; i < eProtectBeanList.size(); i++) {
//            boolean select = map.get(i);
//            map.put(i, !select);
//        }
//        notifyDataSetChanged();
//    }
}
