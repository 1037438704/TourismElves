package com.tourismelves.model.res;

import java.util.List;

/**
 * 景区数据
 */

public class HomeRes {
    /**
     * orgId : 47
     * code : HB-QDL
     * name : 清东陵
     * shortName : 清东陵
     * foreignName :
     * address :
     * ticketDescription :
     * tourBeginTime :
     * tourEndTime :
     * areaId : 4
     * tel :
     * mail :
     * website :
     * image : M00/00/14/052iAlsVMUSAKc9pAAD4ci0xsGQ476.jpg
     * description : 清朝统一全国后，先后在现在的河北省遵化县和易县开辟了两座规模宏大的皇家陵园。遵化的陵园因在京师的东面，所以叫“东陵”，易县的陵园因位于京师的西面，故称为“西陵”，清朝灭亡以后，为了表明这两座陵园的年代，才在东陵。西陵之前加了“清”字。
     * 清东陵是中国现存规模最宏大、体系最完整、布局最得体的帝王陵墓建筑群。它于顺治十八年开始修建，在247年中陆续建成了217座宫殿牌楼，组成大小15座陵园，埋葬着5位皇帝、15位皇后、136位妃嫔、3位阿哥以及2位公主，共161人。
     * 其实清朝入关以前，就已经在关外营建了3座皇帝陵，即永陵、福陵和昭陵。这三座陵寝规制各异，各有千秋，直到清入关以后营建的陵寝，才开始制定规格，基本都沿袭了明朝陵墓的风格，各座陵寝的序列组织都严格地遵照“陵制与山水相称”的原则，既要“遵照典礼规制”，又要“配合山川胜势”。
     * 清王朝统治时期，清东陵分“后龙”和“前圈”两部分，陵区南北长125公里，东西宽20公里，占地面积约80平方公里。“后龙”为“风水来龙”之地，从陵后长城开始向北延至承德，西接密云，东达遵化，层峦叠翠，山秀石奇，气象万千。当时，“后龙”禁地派重兵驻守，负责安全保卫。“前圈”是陵寝分布的地方，四周群山环绕，中间野阔川平，东、西各有一泓碧水缓缓流淌，形似一只完美无缺的金色酒杯，可以说是景物天成。
     * 这里以顺治帝的孝陵为中心，东侧有康熙帝的景陵，同治帝的惠陵，西侧有乾隆帝的裕陵，咸丰帝的定陵，以及孝庄、孝惠、孝贞和孝钦四座皇后陵，还有景妃、景双妃、裕妃、定妃及惠妃五座妃嫔园寝。接下来我们就一起去参观吧！
     * 我是精灵说景区讲解小秘书，我们一会儿见。
     * descriptionVoice :
     * remark :
     * longitude : 117.665546
     * latitude : 40.189074
     * status : true
     * insertTime : 2018-06-04 06:44:42
     * insertIp : 61.181.7.220
     * orders : 23
     * creator : 17
     * imageLayer :
     * imageBounds :
     * imageZooms :
     * area : {"areaId":4,"parent":1,"code":"HB","name":"河北省","postalCode":"130000","remark":"Hebei Sheng","orders":1,"isDisplay":true,"parentArea":{"areaId":1,"parent":0,"code":"2","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true}}
     * sceneryCount : 44
     * articleList : [{"articleId":8,"title":"乾隆点穴","author":"","summary":"","titleImg":"M00/00/15/052iAlsV5veAUHK5AAD4ci0xsGQ283.jpg","publishTime":"2018-07-16 00:00:00","hot":0,"commentNum":0},{"articleId":9,"title":"慈禧陵三绝","author":"","summary":"","titleImg":"M00/00/15/052iAlsV5uaAc1lhAADuqdoCVf0383.jpg","publishTime":"2018-06-08 00:00:00","hot":0,"commentNum":0}]
     */

    private int orgId;
    private String code;
    private String name;
    private String shortName;
    private String foreignName;
    private String address;
    private String ticketDescription;
    private String tourBeginTime;
    private String tourEndTime;
    private int areaId;
    private String tel;
    private String mail;
    private String website;
    private String image;
    private String description;
    private String descriptionVoice;
    private String remark;
    private double longitude;
    private double latitude;
    private boolean status;
    private String insertTime;
    private String insertIp;
    private String summary = "";
    private int orders;
    private int creator;
    private int isAutoplay;
    private double price = 0.0;
    private String imageLayer;
    private String imageBounds;
    private String imageZooms;
    private AreaBean area;
    private int sceneryCount;
    private int distance = 0;
    private List<ArticleListBean> articleList;

    public int getIsAutoplay() {
        return isAutoplay;
    }

    public void setIsAutoplay(int isAutoplay) {
        this.isAutoplay = isAutoplay;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getTourBeginTime() {
        return tourBeginTime;
    }

    public void setTourBeginTime(String tourBeginTime) {
        this.tourBeginTime = tourBeginTime;
    }

    public String getTourEndTime() {
        return tourEndTime;
    }

    public void setTourEndTime(String tourEndTime) {
        this.tourEndTime = tourEndTime;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionVoice() {
        return descriptionVoice;
    }

    public void setDescriptionVoice(String descriptionVoice) {
        this.descriptionVoice = descriptionVoice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertIp() {
        return insertIp;
    }

    public void setInsertIp(String insertIp) {
        this.insertIp = insertIp;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getImageLayer() {
        return imageLayer;
    }

    public void setImageLayer(String imageLayer) {
        this.imageLayer = imageLayer;
    }

    public String getImageBounds() {
        return imageBounds;
    }

    public void setImageBounds(String imageBounds) {
        this.imageBounds = imageBounds;
    }

    public String getImageZooms() {
        return imageZooms;
    }

    public void setImageZooms(String imageZooms) {
        this.imageZooms = imageZooms;
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public int getSceneryCount() {
        return sceneryCount;
    }

    public void setSceneryCount(int sceneryCount) {
        this.sceneryCount = sceneryCount;
    }

    public List<ArticleListBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleListBean> articleList) {
        this.articleList = articleList;
    }

    public static class AreaBean {
        /**
         * areaId : 4
         * parent : 1
         * code : HB
         * name : 河北省
         * postalCode : 130000
         * remark : Hebei Sheng
         * orders : 1
         * isDisplay : true
         * parentArea : {"areaId":1,"parent":0,"code":"2","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true}
         */

        private int areaId;
        private int parent;
        private String code;
        private String name;
        private String postalCode;
        private String remark;
        private int orders;
        private boolean isDisplay;
        private ParentAreaBean parentArea;

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public boolean isIsDisplay() {
            return isDisplay;
        }

        public void setIsDisplay(boolean isDisplay) {
            this.isDisplay = isDisplay;
        }

        public ParentAreaBean getParentArea() {
            return parentArea;
        }

        public void setParentArea(ParentAreaBean parentArea) {
            this.parentArea = parentArea;
        }

        public static class ParentAreaBean {
            /**
             * areaId : 1
             * parent : 0
             * code : 2
             * name : 中国
             * postalCode : 中国
             * remark : Zhong Guo
             * orders : 1
             * isDisplay : true
             */

            private int areaId;
            private int parent;
            private String code;
            private String name;
            private String postalCode;
            private String remark;
            private int orders;
            private boolean isDisplay;

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public int getParent() {
                return parent;
            }

            public void setParent(int parent) {
                this.parent = parent;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getOrders() {
                return orders;
            }

            public void setOrders(int orders) {
                this.orders = orders;
            }

            public boolean isIsDisplay() {
                return isDisplay;
            }

            public void setIsDisplay(boolean isDisplay) {
                this.isDisplay = isDisplay;
            }
        }
    }

    public static class ArticleListBean {
        /**
         * articleId : 8
         * title : 乾隆点穴
         * author :
         * summary :
         * titleImg : M00/00/15/052iAlsV5veAUHK5AAD4ci0xsGQ283.jpg
         * publishTime : 2018-07-16 00:00:00
         * hot : 0
         * commentNum : 0
         */

        private int articleId;
        private String title;
        private String author;
        private String summary;
        private String titleImg;
        private String publishTime;
        private int hot;
        private int commentNum;

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitleImg() {
            return titleImg;
        }

        public void setTitleImg(String titleImg) {
            this.titleImg = titleImg;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }
    }
}
