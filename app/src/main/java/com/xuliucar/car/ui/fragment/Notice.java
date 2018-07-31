package com.xuliucar.car.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cangmaomao.lib.base.BaseNewFragment;
import com.cangmaomao.lib.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.NoticeAdapter;
import com.xuliucar.xuli.xuliucar.bean.NoticeBean;
import com.xuliucar.xuli.xuliucar.mvp.presenter.NoticePresenter;
import com.xuliucar.xuli.xuliucar.mvp.view.NoticeView;
import com.xuliucar.xuli.xuliucar.ui.notice.FileReadActivity;
import com.xuliucar.xuli.xuliucar.ui.notice.OpenPDF;
import com.xuliucar.xuli.xuliucar.ui.notice.OtherNotices;
import com.xuliucar.xuli.xuliucar.ui.notice.Web;
import com.xuliucar.xuli.xuliucar.ui.notice.World;
import com.xuliucar.xuli.xuliucar.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyward on 2016/6/29.
 */
public class Notice extends BaseNewFragment implements AdapterView.OnItemClickListener, NoticeView {
    private SmartRefreshLayout notice_refresh;
    private ListView notice_listView;
    private NoticeAdapter adapter;
    private List<NoticeBean.NoticeList> mNoticeLists;
    private NoticePresenter mNoticePresenter;


    @Override
    public int layViewId() {
        return R.layout.notice;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NotNull View view) {
        notice_refresh = view.findViewById(R.id.notice_refresh);
        notice_listView = view.findViewById(R.id.notice_listView);

        mNoticePresenter = new NoticePresenter(this);
        mNoticePresenter.getNotice();
        mNoticeLists = new ArrayList<>();
        adapter = new NoticeAdapter(getActivity(), mNoticeLists);
        notice_listView.setAdapter(adapter);
        notice_listView.setOnItemClickListener(this);
    }

    @Override
    public int addViewId() {
        return 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNoticePresenter.unSubcrible();
    }


    @Override
    @SuppressWarnings("all")
    public String getLoginId() {
        return (String) SPUtils.INSTANCE.create(getActivity(), "userInfo").get("loginid", "");
    }

    @Override
    @SuppressWarnings("all")
    public String getCompanyId() {
        return (String) SPUtils.INSTANCE.create(getActivity(), "userInfo").get("compid", "");
    }

    @Override
    public void getdata(List<NoticeBean.NoticeList> bean) {
        mNoticeLists.addAll(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg) {
        switch (msg) {
            case "already logout":
                ToastUtil.showShortToast(getActivity(), msg);
                mNoticePresenter.toLogin(getActivity());
                break;
            case "未登陆":
                ToastUtil.showShortToast(getActivity(), msg);
                mNoticePresenter.toLogin(getActivity());
                break;
            case "已登出,或在其它设备上登陆!":
                ToastUtil.showShortToast(getActivity(), msg);
                mNoticePresenter.alreadyLogin(getActivity());
                break;
        }

    }

    @Override
    public void showError() {
        Snackbar.make(notice_listView, getString(R.string.snack_infor), Snackbar.LENGTH_SHORT).setAction("重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoticePresenter.getNotice();
            }
        }).show();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, final View v, final int i, long l) {
        NoticeBean.NoticeList noticeBean = mNoticeLists.get(i);
        String type = noticeBean.getType();
        String title = noticeBean.getTitle();
        String scon = noticeBean.getScon();
        if (!(noticeBean.getUrl()).equals("")) {
            String url = noticeBean.getUrl();
            String isPDF = url.substring(url.length() - 3, url.length());
            if (type.equals("0")) {
                Intent intent = new Intent(getActivity(), Web.class);
                intent.putExtra("url", url);
                intent.putExtra("title", title);
                intent.putExtra("con", scon);
                startActivity(intent);
            } else if (type.equals("1") && isPDF.equals("pdf")) {
                Intent intent2 = new Intent(getActivity(), OpenPDF.class);
                intent2.putExtra("url", url);
                intent2.putExtra("title", title);
                intent2.putExtra("con", scon);
                startActivity(intent2);
            } else if (type.equals("1")) {
                String str = url.substring(url.length() - 3, url.length());
                if (str.equals("doc")) {
                    Intent intent3 = new Intent(getActivity(), World.class);
                    intent3.putExtra("url", url);
                    intent3.putExtra("title", title);
                    intent3.putExtra("con", scon);
                    startActivity(intent3);
                } else {
                    Intent intent4 = new Intent(getActivity(), FileReadActivity.class);
                    intent4.putExtra("url", url);
                    intent4.putExtra("title", title);
                    intent4.putExtra("con", scon);
                    startActivity(intent4);
                }
            }
        }
        if (type.equals("2")) {
            Intent intent5 = new Intent(getActivity(), OtherNotices.class);
            intent5.putExtra("con", noticeBean.getCon());
            startActivity(intent5);
        }
    }

}
