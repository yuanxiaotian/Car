package com.xuliucar.xuli.xuliucar.bean;


import java.io.Serializable;

/**
 * Created by skyward on 2016/7/16.
 *
 */
public class ContactsBean implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String phone2;
    private String sortLetters;  //显示数据拼音的首字母
    private String search;
    public ContactsBean() {

    }

    public ContactsBean(String id, String name, String phone,String sortLetters) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sortLetters = sortLetters;
    }

    public ContactsBean(String id, String name, String phone,String phone2,String sortLetters) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.phone2 = phone2;
        this.sortLetters = sortLetters;
    }

    public ContactsBean(String id,String name,String phone,String search,int type){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.search = search;
    }

    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
