package com.xuliucar.xuli.xuliucar.ui;

import android.content.Intent;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xuliucar.xuli.xuliucar.R;
import com.xuliucar.xuli.xuliucar.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuidePage extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private List<View> views;
    private ImageView[] dots;
    private final int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_page);
        initViews();
        initDots();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<>();
        final ViewGroup nullParent = null;
        views.add(inflater.inflate(R.layout.guide_item01, nullParent));
        views.add(inflater.inflate(R.layout.guide_item02, nullParent));
        views.add(inflater.inflate(R.layout.guide_item03, nullParent));

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(views, GuidePage.this);
        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(vpAdapter);
        vp.addOnPageChangeListener(this);

        LinearLayout toLogin = (LinearLayout) views.get(2).findViewById(R.id.toLogin);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuidePage.this, Login.class);
                startActivity(intent);
                GuidePage.this.finish();
            }
        });

    }

    //初始化ImageView控件
    private void initDots() {
        dots = new ImageView[views.size()];//dots 数组的长度是viewpager的个数
        //进行遍历，将图片的id一一相对应的赋值给dots数组里面的元素
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//进行遍历
        for (int i = 0; i < ids.length; i++) {
            //然后在位置上进行判断
            if (position == i) {
                dots[i].setImageResource(R.drawable.login_point_selected);//如果在当前位置，就设置为被选中状态
            } else {
                dots[i].setImageResource(R.drawable.login_point);//否则就设置为未被选中状态
            }

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
