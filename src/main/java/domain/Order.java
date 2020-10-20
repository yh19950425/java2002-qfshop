package com.java.yh.domain;

import java.util.Date;

public class Order {
    // 主键
    private String id;
    // 外键-用户
    private int uid;
    // 地址
    private int aid;
    // 总金额
    private int money;
    // 状态
    private String status;
    // 创建时间
    private String time;


    public Order(String id, int uid, int aid, int money, String status, String time) {
        this.id = id;
        this.uid = uid;
        this.aid = aid;
        this.money = money;
        this.status = status;
        this.time = time;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", uid=" + uid +
                ", aid=" + aid +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

