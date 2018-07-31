package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.IllegalOrderAdapter;
import com.xuliucar.xuli.xuliucar.bean.IllegalOrderBean;

import java.util.ArrayList;
import java.util.List;

public class IllegalOrder extends AppCompatActivity  {
    private Toolbar illegalDetailToolbar;
    private ListView illegalOrderListView;
    private LinearLayout illegalOrderTipNo;
    private LinearLayout illegalOrderRefreshTip;
    private List<IllegalOrderBean> mList;
    private IllegalOrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_order);
        initView();
    }

    private void initView() {
        illegalDetailToolbar = (Toolbar) findViewById(R.id.illegal_detail_toolbar);
        illegalOrderListView = (ListView) findViewById(R.id.illegal_order_listView);
        illegalOrderTipNo = (LinearLayout) findViewById(R.id.illegal_order_tip_no);
        illegalOrderRefreshTip = (LinearLayout) findViewById(R.id.illegal_order_refresh_tip);

        illegalDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mList = new ArrayList<>();
        mList.add(new IllegalOrderBean("粤A2340","成交","2","乱停"));
        mList.add(new IllegalOrderBean("粤A2340","成交","2","乱停"));
        mList.add(new IllegalOrderBean("粤A2340","成交","2","乱停"));
        mList.add(new IllegalOrderBean("粤A2340","成交","2","乱停"));
        mList.add(new IllegalOrderBean("粤A2340","成交","2","乱停"));
        mAdapter = new IllegalOrderAdapter(this,mList);
        illegalOrderListView.setAdapter(mAdapter);

    }


}
