package com.tourismelves.model.res;

/**
 * 选择城市
 */

public class SelectCityRes {
    private String name;
    private String remark;

    public SelectCityRes(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public SelectCityRes(String name) {
        this.name = name;
    }

    public SelectCityRes() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "SelectCityRes{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                "}\n";
    }
}
