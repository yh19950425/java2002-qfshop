package com.java.yh.domain;

// 地址
public class Address {
    // 主键
    private String id;

    // 详细地址
    private String detail;

    // 收件人
    private String name;

    // 收件人手机号
    private String phone;

    // 外键-用户
    private int uid;

    //等级
    private String level;

    // 外键-用户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public Address() { }

    public Address(String id, String detail, String name, String phone, int uid, String level) {
        this.id = id;
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.uid = uid;
        this.level = level;

    }
}
