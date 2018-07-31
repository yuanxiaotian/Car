package com.xuliucar.xuli.xuliucar.ui.search;

import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.NewCarAdapter;
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.bean.StockCarBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil;
import com.xuliucar.xuli.xuliucar.widget.SearchView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class newCarSearch extends BaseActivity implements SearchView.SearchViewListener{

    private NewCarAdapter resultAdapter;
    private ListView lvResults;
    private List<StockCarBean.DataBean.InfoBean> stockCarBean;
    private List<StockCarBean.DataBean.InfoBean> resultData;
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
        lvResults = (ListView) findViewById(R.id.recyclerView);
        SearchView searchView = (SearchView) findViewById(R.id.main_search_layout);
        stockCarBean = new ArrayList<>();
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

        allData = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                StockCarBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.NEWCAR).toString(),StockCarBean.class);
                int size = bean.getData().getInfo().size();

                for (int i = 0; i < size; i++) {
                    int length = bean.getData().getInfo().get(i).getPlates().length();
                    if(bean.getData().getInfo().get(i).getPlates().isEmpty() || (length == 2)){
                        stockCarBean.add(new StockCarBean.DataBean.InfoBean(bean.getData().getInfo().get(i).getFnum(), bean.getData().getInfo().get(i).getEnumX(),bean.getData().getInfo().get(i).getPlates()));
                        allData.add(bean.getData().getInfo().get(i).getPlates()+" "+bean.getData().getInfo().get(i).getEnumX());
                    }
                }
                filterAutoCompleteData  = removeDuplicateWithOrder(allData);
            }
        },200);
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

        Log.i("myLog","输入 "+text);
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
            autoCompleteAdapter = new SearchAdapter(newCarSearch.this,autoCompleteData);
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
            for (int i = 0; i < stockCarBean.size(); i++) {
                String search = stockCarBean.get(i).getPlates()+" "+stockCarBean.get(i).getEnumX();
                if (search.contains(text.trim())) {
                    resultData.add(stockCarBean.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new NewCarAdapter(this, resultData);
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
}
