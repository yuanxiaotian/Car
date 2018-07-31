package com.xuliucar.xuli.xuliucar.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.CustomPeopleSAdapter;
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.CustomPeopleBean;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.widget.SearchView;
import com.xuliucar.xuli.xuliucar.widget.tagView.FlowTagLayout;
import com.xuliucar.xuli.xuliucar.widget.tagView.OnTagSelectListener;
import com.xuliucar.xuli.xuliucar.utils.LogUtil;
import com.xuliucar.xuli.xuliucar.utils.PreferencesUtils;
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomPeopleS extends BaseActivity implements SearchView.SearchViewListener{

    private CustomPeopleSAdapter resultAdapter;
    private FlowTagLayout lvResults;
    private List<CustomPeopleBean> mCustomPeopleBeanList;
    private List<CustomPeopleBean> resultData;
    private SearchAdapter autoCompleteAdapter;
    private List<SearchLightBean> autoCompleteData;
    private List<String> allData;
    private List<String> filterAutoCompleteData;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.custom_people_search);
        initData();
    }

    @Override
    public void initView() {
        lvResults = (FlowTagLayout) findViewById(R.id.custom_people_search);
        SearchView searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter,autoCompleteData);
        lvResults.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);

    }

    protected void initData() {
        getData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    private void getData() {
        mCustomPeopleBeanList = new ArrayList<>();
        allData = new ArrayList<>();
        Intent intent = getIntent();
        mCustomPeopleBeanList = (List<CustomPeopleBean>) intent.getSerializableExtra("custompeople");
        for (int i = 0; i < mCustomPeopleBeanList.size(); i++) {
            allData.add(mCustomPeopleBeanList.get(i).getName());
        }
        filterAutoCompleteData  = removeDuplicateWithOrder(allData);

    }

    //过滤自动填充搜索内容
    @SuppressWarnings("unchecked")
    private static List<String> removeDuplicateWithOrder(List<String> list) {
        Set set = new HashSet();
        List<String> newList = new ArrayList();
        for (String element : list) {
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }


    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {

        int hintSize = 20;
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < filterAutoCompleteData.size() && count < hintSize; i++) {
                if (filterAutoCompleteData.get(i).contains(text.trim())) {
                    SpannableString s = SearchLightUtil.setKeyWordColor(filterAutoCompleteData.get(i),text);
                    autoCompleteData.add(new SearchLightBean(s));
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new SearchAdapter(CustomPeopleS.this,autoCompleteData);

        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        } else {
            resultData.clear();
            for (int i = 0; i < mCustomPeopleBeanList.size(); i++) {
                if (mCustomPeopleBeanList.get(i).getName().contains(text.trim())) {
                    resultData.add(mCustomPeopleBeanList.get(i));
                }
            }

        }
        if (resultAdapter == null) {
            resultAdapter = new CustomPeopleSAdapter(this,resultData);
        }else {
            resultAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onRefreshAutoComplete(String text) {
        getAutoCompleteData(text);
    }

    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);


            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);
            lvResults.setOnTagSelectListener(new OnTagSelectListener() {
                @Override
                public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList, int position) {
                    CustomPeopleBean bean = (CustomPeopleBean) parent.getAdapter().getItem(position);
                    LogUtil.LogPrint("选择了 "+bean.getName());
                    LogUtil.LogPrint("index是 "+bean.getIndex());
                }
            });

            resultAdapter.notifyDataSetChanged();

        for (int i = 0; i < resultData.size(); i++) {
            Boolean b = PreferencesUtils.getSharePreBoolean(getApplicationContext(),"customphone", String.valueOf(resultData.get(i).getIndex()));

            LogUtil.LogPrint("b "+b);
        }

    }
}
