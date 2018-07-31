package com.xuliucar.xuli.xuliucar.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.bean.ContractManageBean;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.widget.SearchView;
import com.xuliucar.xuli.xuliucar.adapter.ContractMAdapter;
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.newmvp.view.saleManage.detail.ContractMDetail;
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContractManageS extends BaseActivity implements SearchView.SearchViewListener,AdapterView.OnItemClickListener{
    private static final String URL ="http://www.gzxlxx.com:8866/index.php/Home/App/contract_list";
    private ContractMAdapter resultAdapter;
    private RecyclerView lvResults;
    private List<ContractManageBean.DataBean> saleMBeanList;
    private List<ContractManageBean.DataBean> resultData;
    private SearchAdapter autoCompleteAdapter;
    private List<SearchLightBean> autoCompleteData;
    private List<String> allData;
    private List<String> filterAutoCompleteData;
    private Gson mGson;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        initData();
    }

    @Override
    public void initView() {
        lvResults = findViewById(R.id.recyclerView);
        SearchView searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter,autoCompleteData);
        mGson = new Gson();
    }

    protected void initData() {
        getData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    private void getData() {
        saleMBeanList = new ArrayList<>();
        allData = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ContractManageBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.CONTRACTMANAGE).toString(),ContractManageBean.class);
                saleMBeanList.addAll(bean.getData());
                int size = bean.getData().size();
                for (int i = 0; i < size; i++) {
                    allData.add(bean.getData().get(i).getCnum()+" "+bean.getData().get(i).getClientname());
                }
                filterAutoCompleteData  = removeDuplicateWithOrder(allData);
            }
        },300);

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
            autoCompleteAdapter = new SearchAdapter(ContractManageS.this,autoCompleteData);
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
            for (int i = 0; i < saleMBeanList.size(); i++) {
                String search = saleMBeanList.get(i).getCnum()+" "+saleMBeanList.get(i).getClientname();
                if (search.contains(text.trim())) {
                    resultData.add(saleMBeanList.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new ContractMAdapter(R.layout.contract_manage_item);
        } else {
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

        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);

        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();

        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ContractManageBean.DataBean saleMBean = resultData.get(i);
        Intent intent = new Intent(view.getContext(),ContractMDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("saleMBean",saleMBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
