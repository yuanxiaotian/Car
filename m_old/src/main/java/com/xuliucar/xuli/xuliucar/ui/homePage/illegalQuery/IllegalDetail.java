package com.xuliucar.xuli.xuliucar.ui.homePage.illegalQuery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.IllegalDetailAdapter;
import com.xuliucar.xuli.xuliucar.bean.IllegalDetailBean;

import java.util.ArrayList;
import java.util.List;

public class IllegalDetail extends AppCompatActivity {

    private Toolbar illegalDetailToolbar;
    private ListView illegalDetailListView;
    private CheckBox illegal_select_all;
    private Button handle_illegal_layout;
    private List<IllegalDetailBean> mList;
    private IllegalDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_detail);
        initView();
    }

    private void initView() {
        illegalDetailToolbar = (Toolbar) findViewById(R.id.illegal_detail_toolbar);
        illegalDetailListView = (ListView) findViewById(R.id.illegal_detail_listView);
        illegal_select_all = (CheckBox) findViewById(R.id.illegal_select_all);
        handle_illegal_layout = (Button) findViewById(R.id.handle_illegal_layout);
        illegalDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mList = new ArrayList<>();
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        mList.add(new IllegalDetailBean("4406147904876663","佛山 2016-10-26 04：22：34","1344","200元","20元","698.60.[698.60]","698.900"));
        illegal_select_all.setChecked(false);
        mAdapter = new IllegalDetailAdapter(this, mList, new IllegalDetailAdapter.OnSelectedItemChanged() {
            @Override
            public void selectedItemChange(int count) {
                Log.i("myLog","count "+count);
            }
        });
        illegalDetailListView.setAdapter(mAdapter);

        illegal_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mAdapter.selectAll();
                }else{
                    mAdapter.disSelectAll();
                }
            }
        });
    }
}
