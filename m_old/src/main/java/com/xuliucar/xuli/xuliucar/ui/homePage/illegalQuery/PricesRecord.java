package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.PriceRecordAdapter;
import com.xuliucar.xuli.xuliucar.bean.PriceRecordBean;

import java.util.ArrayList;
import java.util.List;

public class PricesRecord extends AppCompatActivity {
    private Toolbar illegalDetailToolbar;
    private ListView priceRecordListView;
    private LinearLayout priceRecordTipNo;
    private LinearLayout priceRecordRefreshTip;
    private List<PriceRecordBean> mList;
    private PriceRecordAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices_record);
        initView();
    }

    private void initView() {
        illegalDetailToolbar = (Toolbar) findViewById(R.id.illegal_detail_toolbar);
        priceRecordListView = (ListView) findViewById(R.id.price_record_listView);
        priceRecordTipNo = (LinearLayout) findViewById(R.id.price_record_tip_no);
        priceRecordRefreshTip = (LinearLayout) findViewById(R.id.price_record_refresh_tip);
        mList = new ArrayList<>();
        mList.add(new PriceRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new PriceRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new PriceRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new PriceRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new PriceRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mAdapter = new PriceRecordAdapter(this,mList);
        priceRecordListView.setAdapter(mAdapter);
        illegalDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
