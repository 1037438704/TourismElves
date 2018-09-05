package com.tourismelves.model.bean;

/**
 * 购物车列表
 */

public class ShopListBean {

    /**
     * cartId : 51
     * userId : 37
     * orgId : 68
     * name : 故宫博物馆
     * image : M00/00/18/052iAltNboOAWdG2AAQAZ_aEKmg926.jpg
     * price : 10.0
     * preferential : 0.0
     */

    private int cartId;
    private int userId;
    private int orgId;
    private String name;
    private String image;
    private double price;
    private double preferential;
    private boolean isSelect=false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPreferential() {
        return preferential;
    }

    public void setPreferential(double preferential) {
        this.preferential = preferential;
    }
}
