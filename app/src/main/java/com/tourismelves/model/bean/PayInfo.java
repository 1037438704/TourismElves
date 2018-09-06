package com.tourismelves.model.bean;

/**
 * Created by fanhui on 2018/9/6.
 */

public class PayInfo {

    /**
     * type : json
     * code : 200
     * message : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018031802402589&biz_content=%7B%22body%22%3A%22%E5%85%85%E5%80%BC%E9%87%91%E5%B8%81%22%2C%22out_trade_no%22%3A%22487293097399549952%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%E9%87%91%E5%B8%81%22%2C%22timeout_express%22%3A%2260m%22%2C%22total_amount%22%3A%2230.0%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F211.157.162.2%2F%2Flyjl%2Fweb%2FalipayNotify.do&sign=iRB9Ds12hVEicWfE7peyfMB9%2BaypoWzddDZj7USb5acKIhZko6DBsrYyuY3lbXYhpFd83FxsTBEIhxt6haveOVzxb1niFC5%2BQrwtAKQWYrjik71%2BZdmWy%2FR32kaUtSlQejuxWirxR3ffei5IvVsv1HuNgu3XulpFd2o7zPKmHuGwbVNAH%2BFE%2BdV%2BYMevKZ0nVJtzt490W9OWua0nv0HVwC7zozqHnIB%2BqGwrP2VxD%2BJftk%2BjmXdSAJjI1luXLApSAqoRRFW7FGFLFn4mUh6O7GCFLKbfFdYInc%2BSb2C3xZoqp62K5v8IJ0qGzBNj1FFexoeRidouWSJefyW6WGVnYw%3D%3D&sign_type=RSA2&timestamp=2018-09-06+16%3A08%3A50&version=1.0
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
