package com.tourismelves.model.bean;

import java.util.List;

/**
 * 景区
 */

public class ScenicBean {

    private String type;
    private int code;
    private String message;
    private int totalPage;
    private int totalCount;
    private int page;
    private int rows;
    private List<DataListBean> dataList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * orgId : 14
         * code : TJ-PS
         * name : 盘山
         * shortName : 盘山
         * foreignName :
         * address :
         * ticketDescription :
         * tourBeginTime :
         * tourEndTime :
         * hot : 1
         * areaId : 3
         * tel :
         * mail :
         * website :
         * image : M00/00/04/052iAlrQzHuAGvMgAAPH0HDdA0c334.jpg
         * description : 盘山在历史上被称为“东方五台山”，是自然山水与名胜古迹并著，佛教寺院和皇家园林并存的旅游胜地。历朝历代的多位皇帝都曾到过盘山。其中乾隆皇帝更是对盘山情有独钟，一生巡幸盘山32次，留下了1702首诗作，在第一次来到盘山时，就发出了“早知有盘山，何必下江南”的感叹。
         * 盘山属燕山山脉余脉，东西长20公里，南北宽10公里，雄伟多姿，景色幽古，久负盛名，曾入中国15大名山之列。它山势雄伟险峻，层峦秀丽清幽，遥望云海松涛，近观水石清秀奇，可以说步步有有迷人的景色，逢景便有典故传说。登临盘山，观松烟之浩渺，赏奇石之神工，听流水之激越，探文化之奥秘，一定会让您有一种超凡脱俗的满足感。
         * 盘山的景色以“三盘之胜”，“五峰八石”而称绝。三盘，即上盘松胜，盘曲翳天；中盘石胜，千奇百怪；下盘水胜，川流不息。主峰挂月峰，海拔864.4米，它前拥紫盖峰，后依自来峰，东连九华峰，西傍舞剑峰，五峰攒簇，山峦俊秀。阳春，山花烂漫，桃杏争妍；仲夏，层峦碧染，万壑堆青，那真是“缕缕浮云横脚下，山在虚无缥缈间”；深秋，层林尽染，姹紫嫣红；严冬，白雪皑皑，苍松添翠。江山多娇，当无愧色。
         * 关于盘山名字的由来，据说东汉末年，天下大乱，州牧郡守各霸一方。当时的曹操决意统一中原，成就霸业。官渡战打败了河北的袁绍，袁绍的两个儿子袁尚和袁熙兵败后投奔了北部的乌桓，成了曹操统一中原的后顾之忧。建安12年也就是公元207年，曹操亲率十万大军北伐乌桓。大军行至无终境内，正值盛夏，天降大雨，道路泥泞。乌桓军队又占领着要道，一时无法筹集十万大军的粮草。曹操正处在进退两难之时，在无终山隐居的田畴闻讯后，赶往曹操军中献计，他对曹操说：“启禀丞相，今无终山下正值水胜，水深不能载舟船，水浅不能通车马，况且乌桓又有重兵把守，无终山又是一夫当关，万夫莫开之势，您天时、地利都不占，怎能取胜？”曹操问：“依先生之意，我该如何？”田畴说：“您若要攻打乌桓，我倒有一计。您可先大造声势，现在正是盛暑，道路不通，且等到秋冬时节，再来攻打。”曹操依计而行，乌桓军队信以为真，放松了戒备。而后田畴亲自为向导，出无终山入卢龙塞就是现在的喜峰口，直捣柳城，攻其不备，结果在河北的白狼山一仗，大获全胜。得胜归来，论功行赏，田畴自然功不可没，被封为五百户侯。但田畴却坚决不肯接受，仍率宗族隐居在这无终山。后人为了纪念田畴的功绩，将这里称为田盘山，后来简称为盘山。
         * 好了，现在就让我们一起走进这座名山，去体会一下它那吸引乾隆以及历代帝王不断前来的秀美风光吧。
         * 我是精灵说景区讲解小秘书，我们一会见。
         * <p>
         * descriptionVoice :
         * remark :
         * longitude : 117.275086
         * latitude : 40.07936
         * status : true
         * insertTime : 2018-04-12 22:04:59
         * updateTime : 2018-05-13 23:52:24
         * insertIp : 117.15.193.201
         * updateIp : 49.90.56.46
         * orders : 16
         * creator : 17
         * editor : 1
         * isAutoplay : 1
         * area : {"areaId":3,"parent":1,"code":"TJ","name":"天津市","postalCode":"120000","remark":"Tianjin Shi","orders":1,"isDisplay":true,"pinYin":"Tianjin Shi","parentArea":{"areaId":1,"parent":0,"code":"China","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true,"pinYin":"Zhong Guo"}}
         * sceneryCount : 49
         * articleList : []
         * type : 200712
         * nature : 1300
         * grade : 200714
         * imageLayer : M00/00/0B/052iAlr3jXCAfp7mAAHAYm8Jwus793.jpg
         * imageBounds : 117.402359,40.045077,117.404151,40.046358
         * imageZooms : 13,20
         * price : 10.0
         * createDate : 2018-07-17 12:00:00
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
        private int hot;
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
        private int score;
        private String insertTime;
        private String updateTime;
        private String insertIp;
        private String updateIp;
        private int orders;
        private int creator;
        private int editor;
        private int isAutoplay;
        private AreaBean area;
        private int sceneryCount;
        private int type;
        private int nature;
        private int grade;
        private String imageLayer;
        private String imageBounds;
        private String imageZooms;
        private double price;
        private String createDate;
        private List<?> articleList;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
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

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getInsertIp() {
            return insertIp;
        }

        public void setInsertIp(String insertIp) {
            this.insertIp = insertIp;
        }

        public String getUpdateIp() {
            return updateIp;
        }

        public void setUpdateIp(String updateIp) {
            this.updateIp = updateIp;
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

        public int getEditor() {
            return editor;
        }

        public void setEditor(int editor) {
            this.editor = editor;
        }

        public int getIsAutoplay() {
            return isAutoplay;
        }

        public void setIsAutoplay(int isAutoplay) {
            this.isAutoplay = isAutoplay;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNature() {
            return nature;
        }

        public void setNature(int nature) {
            this.nature = nature;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<?> getArticleList() {
            return articleList;
        }

        public void setArticleList(List<?> articleList) {
            this.articleList = articleList;
        }

        public static class AreaBean {
            /**
             * areaId : 3
             * parent : 1
             * code : TJ
             * name : 天津市
             * postalCode : 120000
             * remark : Tianjin Shi
             * orders : 1
             * isDisplay : true
             * pinYin : Tianjin Shi
             * parentArea : {"areaId":1,"parent":0,"code":"China","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true,"pinYin":"Zhong Guo"}
             */

            private int areaId;
            private int parent;
            private String code;
            private String name;
            private String postalCode;
            private String remark;
            private int orders;
            private boolean isDisplay;
            private String pinYin;
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

            public String getPinYin() {
                return pinYin;
            }

            public void setPinYin(String pinYin) {
                this.pinYin = pinYin;
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
                 * code : China
                 * name : 中国
                 * postalCode : 中国
                 * remark : Zhong Guo
                 * orders : 1
                 * isDisplay : true
                 * pinYin : Zhong Guo
                 */

                private int areaId;
                private int parent;
                private String code;
                private String name;
                private String postalCode;
                private String remark;
                private int orders;
                private boolean isDisplay;
                private String pinYin;

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

                public String getPinYin() {
                    return pinYin;
                }

                public void setPinYin(String pinYin) {
                    this.pinYin = pinYin;
                }
            }
        }
    }
}
