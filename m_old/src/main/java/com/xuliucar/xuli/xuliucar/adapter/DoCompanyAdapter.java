package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.bean.CommonBean;
import com.xuliucar.xuli.xuliucar.bean.DoCompanyDocBean;
import com.xuliucar.xuli.xuliucar.utils.ViewHolder;

import java.util.List;

/**
 * Created by skyward on 2016/7/9.
 *
 */
public class DoCompanyAdapter extends BaseAdapter {
    private final Context context;
    private final List<DoCompanyDocBean.DataBean.InfoBean> commentBeanList;
    public DoCompanyAdapter(Context context,List<DoCompanyDocBean.DataBean.InfoBean> commentBeanList){
        this.context = context;
        this.commentBeanList = commentBeanList;
    }
    @Override
    public int getCount() {
        return commentBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(context,view,viewGroup, R.layout.do_company_doc_item,i);
        DoCompanyDocBean.DataBean.InfoBean commentBean = commentBeanList.get(i);
        ((TextView)holder.getView(R.id.doc_licname)).setText(commentBean.getLicname());
        ((TextView)holder.getView(R.id.doc_etime)).setText(commentBean.getEtime());
        return holder.getmConcertView();
    }
}
