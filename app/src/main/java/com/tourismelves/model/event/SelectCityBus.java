package com.tourismelves.model.event;

/**
 * 选择城市的信息
 */

public class SelectCityBus {
    private String city;

    public SelectCityBus(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
