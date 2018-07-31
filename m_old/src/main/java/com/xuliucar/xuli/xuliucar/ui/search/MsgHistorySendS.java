package com.xuliucar.xuli.xuliucar.ui.search;

import android.os.Handler;
import android.os.Bundle;
import android.text.SpannableString;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.HistorySendAdapter;
import com.xuliucar.xuli.xuliucar.adapter.SearchAdapter;
import com.xuliucar.xuli.xuliucar.base.App;
import com.xuliucar.xuli.xuliucar.base.BaseActivity;
import com.xuliucar.xuli.xuliucar.bean.HistorySendBean;
import com.xuliucar.xuli.xuliucar.bean.SearchLightBean;
import com.xuliucar.xuli.xuliucar.config.CacheName;
import com.xuliucar.xuli.xuliucar.widget.SearchView;
import com.xuliucar.xuli.xuliucar.utils.SearchLightUtil;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MsgHistorySendS extends BaseActivity implements SearchView.SearchViewListener,HistorySendAdapter.sendAgainCallBack {
    private HistorySendAdapter resultAdapter;
    private ListView lvResults;
    private List<HistorySendBean.DataBean> dataBeanList;
    private List<HistorySendBean.DataBean> resultData;
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

    protected void initData() {
        getData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    private void getData() {
        dataBeanList = new ArrayList<>();
        allData = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HistorySendBean bean = mGson.fromJson(App.mCache.getAsString(CacheName.HISTORYSEND).toString(),HistorySendBean.class);
                int size = bean.getData().size();
                dataBeanList.addAll(bean.getData());
                for (int i = 0; i <size; i++) {
                    allData.add(bean.getData().get(i).getBtype()+"\t"+"\t"+"\t"+bean.getData().get(i).getStatus()+"\n"+bean.getData().get(i).getPhone()+"\t"+"\t"+"\t"+bean.getData().get(i).getSendtime());
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

    @Override
    protected void initView() {
        super.initView();
        lvResults = (ListView) findViewById(R.id.recyclerView);
        SearchView searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter,autoCompleteData);
        mGson = new Gson();
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
            autoCompleteAdapter = new SearchAdapter(MsgHistorySendS.this,autoCompleteData);
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
            for (int i = 0; i < dataBeanList.size(); i++) {
                String search = dataBeanList.get(i).getBtype()+"\t"+"\t"+"\t"+dataBeanList.get(i).getStatus()+"\n"+dataBeanList.get(i).getPhone()+"\t"+"\t"+"\t"+dataBeanList.get(i).getSendtime();
                if (search.contains(text.trim())) {
                    resultData.add(dataBeanList.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new HistorySendAdapter(this, resultData,this);
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
    public void getResult(String s) {
        try {
            JSONObject object = new JSONObject(s);
            String success = object.getString("success");
            String msg = object.getString("message");
            if(success.equals("true")){
                ToastUtil.showLongToast(getApplicationContext(),msg);
            }else {
                ToastUtil.showLongToast(getApplicationContext(),msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
