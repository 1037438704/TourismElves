package com.tourismelves.model.net;

/**
 * post请求参数类   这里可以根据项目抽取成泛型
 */
public class ParamObject {
    String key;
    Object value;

    public ParamObject() {
    }

    public ParamObject(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}