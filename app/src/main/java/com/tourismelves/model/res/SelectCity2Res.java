package com.tourismelves.model.res;

import java.util.List;

/**
 * 选择城市
 */

public class SelectCity2Res {
    private List<String> citys;
    private String remark;

    public List<String> getCitys() {
        return citys;
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SelectCity2Res{" +
                "citys=" + citys +
                ", remark='" + remark + '\'' +
                '}';
    }
}
