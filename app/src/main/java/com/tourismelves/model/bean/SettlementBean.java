package com.tourismelves.model.bean;

import java.io.Serializable;

/**
 * 结算数据
 */

public class SettlementBean implements Serializable {
    private String name;
    private double money;
    private double originalMoney;
    private String imgSrc;
    private int sceneryCount;

    public SettlementBean(String name, double money, double originalMoney, String imgSrc, int sceneryCount) {
        this.name = name;
        this.money = money;
        this.originalMoney = originalMoney;
        this.imgSrc = imgSrc;
        this.sceneryCount = sceneryCount;
    }

    public SettlementBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getOriginalMoney() {
        return originalMoney;
    }

    public void setOriginalMoney(int originalMoney) {
        this.originalMoney = originalMoney;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getSceneryCount() {
        return sceneryCount;
    }

    public void setSceneryCount(int sceneryCount) {
        this.sceneryCount = sceneryCount;
    }
}
