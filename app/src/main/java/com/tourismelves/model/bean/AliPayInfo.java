package com.tourismelves.model.bean;

/**
 * Created by fanhui on 2018/8/21.
 */

public class AliPayInfo {


    /**
     * type : json
     * code : 200
     * message : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018031802402589&biz_content=%7B%22body%22%3A%22%E5%85%85%E5%80%BC%E9%87%91%E5%B8%81%22%2C%22out_trade_no%22%3A%22481430859635752960%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%E9%87%91%E5%B8%81%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%2230.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F211.157.162.2%2F%2Flyjl%2Fweb%2FalipayNotify.do&sign=B6IDzj5a2vQQmIMgeuJ%2BX5FXk1doTle8yLnD4yNsC2d1Mgt2x3JbI6Sa0QgrNjH%2FQqfCLL3j28JAAVCRRNEK3hKc2J16BbrmTvMV9%2Bl9AhorcktLVQSkY1%2FBxwbmwGc6CGiY1U587xpDI%2B8EDc2ZtlPLZLMYLC0rFTxQXb6mxYqboLIzDzNZmg7vgLR%2BnI8Q7tzFOg0KH6RinS%2Be4d8rpsJJxy%2FiXl4aWebakapa9e%2FegsL8wI0o8Bwg0kpFrL98B12A0swPG9oqj0TROxbwTMkVRasTE05aJ%2F%2B%2FeJX%2B%2BnhAmE8nXnOkCZd6PTbs%2BeQLnAU0YninBBQxbaonQVit9g%3D%3D&sign_type=RSA2&timestamp=2018-08-21+11%3A54%3A46&version=1.0
     */

    private String type;
    private int code;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
