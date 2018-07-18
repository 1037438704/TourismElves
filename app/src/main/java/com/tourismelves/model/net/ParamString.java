package com.tourismelves.model.net;

/**
 * post请求参数类   这里可以根据项目抽取成泛型
 */
public class ParamString {
    String key;
    String value;

    public ParamString() {
    }

    public ParamString(String key, String value) {
        this.key = key;
        this.value = value;
    }
}