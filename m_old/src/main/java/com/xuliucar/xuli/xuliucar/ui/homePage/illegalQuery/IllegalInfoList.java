package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.IllegalInfoListAdapter;
import com.xuliucar.xuli.xuliucar.bean.IllegalInfoListBean;

import java.util.ArrayList;
import java.util.List;

public class IllegalInfoList extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private LinearLayout activityIllegalInfoList;
    private Toolbar illegalInfoListToobar;
    private LinearLayout illegalInfoListToSearch;
    private ListView illegalInfoListView;
    private LinearLayout illegalInfoRefreshTip;
    private IllegalInfoListAdapter mAdapter;
    private List<IllegalInfoListBean> mList;
    private TextView count_points,already_use,today_give;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_info_list);
        initView();
    }

    private void initView() {
        activityIllegalInfoList = (LinearLayout) findViewById(R.id.activity_illegal_info_list);
        illegalInfoListToobar = (Toolbar) findViewById(R.id.illegal_info_List_toobar);
        illegalInfoListToSearch = (LinearLayout) findViewById(R.id.illegal_info_List_toSearch);
        illegalInfoListView = (ListView) findViewById(R.id.illegal_info_listView);
        illegalInfoRefreshTip = (LinearLayout) findViewById(R.id.illegal_info_refresh_tip);
        count_points = (TextView) findViewById(R.id.count_points);
        already_use = (TextView) findViewById(R.id.already_use);
        today_give = (TextView) findViewById(R.id.today_give);
        illegalInfoListView.setOnItemClickListener(this);
        mList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            mList.add(new IllegalInfoListBean("粤A12"+i,""+i," "+i,"20"));
        }
        mAdapter = new IllegalInfoListAdapter(this,mList);
        illegalInfoListView.setAdapter(mAdapter);

        illegalInfoListToobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(),IllegalDetail.class);
        startActivity(intent);
    }
}
