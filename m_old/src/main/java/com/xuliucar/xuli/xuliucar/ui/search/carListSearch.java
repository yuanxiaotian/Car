package com.xuliucar.xuli.xuliucar.ui.search;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.CarListAdapter;
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.CarsListBean;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.ui.homePage.allCar.CarDetail;
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil;
import com.xuliucar.xuli.xuliucar.widget.SearchView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class carListSearch extends BaseActivity implements AdapterView.OnItemClickListener, SearchView.SearchViewListener {

    /**
     * 搜索结果列表adapter
     */
    private CarListAdapter resultAdapter;
    /**
     * 搜索结果列表view
     */
    private RecyclerView lvResults;
    /**
     * 搜索结果的数据
     */
    private List<CarsListBean.DataBean.InfoBean> resultData;
    /**
     * 自动补全列表adapter
     */
//    private ArrayAdapter<String> autoCompleteAdapter;
    private SearchAdapter autoCompleteAdapter;
    private List<CarsListBean.DataBean.InfoBean> carListBeen;
    /**
     * 搜索过程中自动补全数据
     */
    private List<SearchLightBean> autoCompleteData;
    /**
     * 默认提示框显示项的个数
     */
    private static final int DEFAULT_HINT_SIZE = 20;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;

    private List<String> allData;
    private List<String> filterAutoCompleteData;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        carListSearch.hintSize = hintSize;
    }

    private Gson mGson;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);

        // Intent intent = getIntent();
        // String ids = intent.getStringExtra("ids");
        initData();
    }

    @Override
    public void initView() {
        lvResults = findViewById(R.id.recyclerView);
        /*
         搜索view
       */
        SearchView searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter, autoCompleteData);
        mGson = new Gson();
    }

    protected void initData() {
        //从数据库获取数据
        getCarListData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }


    private void getCarListData() {
        carListBeen = new ArrayList<>();
        allData = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CarsListBean bean = mGson.fromJson(App.mCache.getAsJSONObject(CacheName.CARLIST).toString(), CarsListBean.class);
                int size = bean.getData().getInfo().size();
                carListBeen.addAll(bean.getData().getInfo());
                for (int i = 0; i < size; i++) {
                    allData.add(bean.getData().getInfo().get(i).getPlates() + " " + bean.getData().getInfo().get(i).getOwner());
                }
                filterAutoCompleteData = removeDuplicateWithOrder(allData);
            }
        }, 300);
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

        Log.i("myLog", "输入 " + text);
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < filterAutoCompleteData.size() && count < hintSize; i++) {
                if (filterAutoCompleteData.get(i).contains(text.trim())) {
                    SpannableString s = SearchLightUtil.setKeyWordColor(filterAutoCompleteData.get(i), text);
                    autoCompleteData.add(new SearchLightBean(s));
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new SearchAdapter(carListSearch.this, autoCompleteData);
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
            for (int i = 0; i < carListBeen.size(); i++) {
                String searchRes = carListBeen.get(i).getPlates() + " " + carListBeen.get(i).getOwner();
                if (searchRes.contains(text.trim())) {
                    resultData.add(carListBeen.get(i));
                }
            }
        }
        if (resultAdapter == null) {
//            resultAdapter = new CarListAdapter(this, resultData);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CarsListBean.DataBean.InfoBean carListData = resultData.get(i);
        Intent intent = new Intent(view.getContext(), CarDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("carInfoBean", carListData);
        bundle.putString("page", "0");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onRefreshAutoComplete(String text) {
        //更新数据
        getAutoCompleteData(text);
    }

    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
//            lvResults.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }
    }
}
