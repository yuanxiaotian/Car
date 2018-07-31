package com.xuliucar.xuli.xuliucar.adapter;

import android.content.Context;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuliucar.xuli.xuliucar.R;

import com.xuliucar.xuli.xuliucar.bean.PayInfoDY;

import java.util.List;

/**
 * Created by skyward on 2016/7/5.
 *
 */
public class PayInfoDetailAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final List<String> group_list;
    private final List<List<PayInfoDY>> item_list;


    public PayInfoDetailAdapter(Context context, List<String> group_list, List<PayInfoDY> item_lt, List<List<PayInfoDY>> item_list) {
        this.context = context;
        this.group_list = group_list;
       // List<PayInfoDY> item_lt1 = item_lt;
        this.item_list = item_list;

    }

    /**
     *
     * 获取组的个数
     *
     * @see android.widget.ExpandableListAdapter#getGroupCount()
     */
    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    /**
     *
     * 获取指定组中的子元素个数
     *
     * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return item_list.get(groupPosition).size();
    }

    /**
     *
     * 获取指定组中的数据
     *
     * @see android.widget.ExpandableListAdapter#getGroup(int)
     */

    @Override
    public Object getGroup(int groupPosition) {
        return group_list.get(groupPosition);
    }

    /**
     *
     * 获取指定组中的指定子元素数据。
     *
     * @see android.widget.ExpandableListAdapter#getChild(int, int)
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return item_list.get(groupPosition).get(childPosition);
    }

    /**
     *
     * 获取指定组的ID，这个组ID必须是唯一的
     *

     * @see android.widget.ExpandableListAdapter#getGroupId(int)
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     *
     * 获取指定组中的指定子元素ID
     *
     * @see android.widget.ExpandableListAdapter#getChildId(int, int)
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    /**
     *
     * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
     *
     * @see android.widget.ExpandableListAdapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }
    /**
     *
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的视图组
     * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder;
        if(convertView == null){
            final ViewGroup nullParent = null;
            convertView = LayoutInflater.from(context).inflate(R.layout.pay_info_d_item,nullParent);
            groupHolder = new GroupHolder();
            groupHolder.year = (TextView) convertView.findViewById(R.id.years);
            groupHolder.img = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder)convertView.getTag();
        }
        if (!isExpanded)
        {
            groupHolder.img.setBackgroundResource(R.drawable.group_img);
        }
        else
        {
            groupHolder.img.setBackgroundResource(R.drawable.group_open_two);
        }

        groupHolder.year.setText(group_list.get(groupPosition));
        return convertView;
    }


    /**
     *
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder;
        if (convertView == null){
            final ViewGroup nullParent = null;
            convertView = LayoutInflater.from(context).inflate(R.layout.pay_info_d_item_child, nullParent);
            itemHolder = new ItemHolder();
            itemHolder.pCharge_child  = (TextView) convertView.findViewById(R.id.pCharge_child);
            itemHolder.papd_child = (TextView) convertView.findViewById(R.id.papd_child);
            itemHolder.ct_child = (TextView) convertView.findViewById(R.id.ct_child);
            itemHolder.udp_child = (TextView) convertView.findViewById(R.id.udp_child);
            itemHolder.item_child_content = (LinearLayout) convertView.findViewById(R.id.item_child_content);
            itemHolder.mark = (TextView) convertView.findViewById(R.id.mark);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder)convertView.getTag();
        }


        PayInfoDY payInfoDY = item_list.get(groupPosition).get(childPosition);
        itemHolder.pCharge_child.setText(payInfoDY.getCharge());
        itemHolder.papd_child.setText(payInfoDY.getApd());
        itemHolder.ct_child.setText(payInfoDY.getCt());
        itemHolder.udp_child.setText(payInfoDY.getUpd());
        itemHolder.mark.setText(payInfoDY.getMark());
        String itemId = payInfoDY.getItemid();


        if(!TextUtils.isEmpty(itemId)){
            int intItemId = Integer.parseInt(itemId);
            if(intItemId == childPosition){
                itemHolder.item_child_content.setBackgroundColor(Color.parseColor("#C3DCFD"));
            }
        }else {
            itemHolder.item_child_content.setBackgroundResource(R.drawable.btn_click_selector);
        }

        return convertView;
    }

    /**
     *
     * 是否选中指定位置上的子元素。
     *
     * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupHolder
    {
        public TextView year;

        public ImageView img;

    }

    class ItemHolder
    {
        private LinearLayout item_child_content;
        private TextView pCharge_child;
        private TextView papd_child;
        private TextView ct_child;
        private TextView udp_child;
        private TextView mark;


    }
}
