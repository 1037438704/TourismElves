package com.tourismelves.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 *微信支付
 */

public class WXPayBean {

    /**
     * package : Sign=WXPay
     * appid : wx127d8dd53cf7aabc
     * sign : FE12324C628CCBA2FD8E28721E087B39806ED975D3491B53CEA5467A426C374D
     * partnerid : 1501522521
     * prepayid : wx242244055827337f178102bb3397855418
     * noncestr : 2d515d4678b24a759bd6a18341ef95fa
     * timestamp : 1535121845
     */

    @SerializedName("package")
    private String packageX;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
