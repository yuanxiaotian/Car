package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.SaveRecordAdapter;
import com.xuliucar.xuli.xuliucar.bean.SaveRecordBean;

import java.util.ArrayList;
import java.util.List;

public class SaveRecord extends AppCompatActivity  {
    private Toolbar saveRecordToolbar;
    private ListView saveRecordListView;
    private LinearLayout saveRecordTipNo;
    private LinearLayout saveRecordRefreshTip;
    private SaveRecordAdapter mAdapter;
    private List<SaveRecordBean> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_record);
        initView();
    }

    private void initView() {
        saveRecordToolbar = (Toolbar) findViewById(R.id.save_record_toolbar);
        saveRecordListView = (ListView) findViewById(R.id.save_record_listView);
        saveRecordTipNo = (LinearLayout) findViewById(R.id.save_record_tip_no);
        saveRecordRefreshTip = (LinearLayout) findViewById(R.id.save_record_refresh_tip);
        mList = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new SaveRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new SaveRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new SaveRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new SaveRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mList.add(new SaveRecordBean("粤A12345","16874446668741464","50元","成交","2016-11-20 09:54:08"));
        mAdapter = new SaveRecordAdapter(this,mList);
        saveRecordListView.setAdapter(mAdapter);

        saveRecordToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
