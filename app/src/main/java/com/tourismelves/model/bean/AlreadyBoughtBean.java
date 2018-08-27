package com.tourismelves.model.bean;

import java.util.List;

/**
 * 已购
 */

public class AlreadyBoughtBean {

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
         * orderId : 611
         * userId : 37
         * orderSn : 483593898074046464
         * goodsAmount : 10.0
         * preferential : 0.0
         * orderAmount : 10.0
         * evaluationStatus : 0
         * orderStatus : 1
         * payStatus : 2
         * createTime : 2018-08-26 23:09:30
         * finnshedTime : 2018-08-26 23:09:31
         * paymentTime : 2018-08-26 23:09:31
         * lockState : 0
         * orderFrom : 2
         */

        private int orderId;
        private int userId;
        private String orderSn;
        private double goodsAmount;
        private double preferential;
        private double orderAmount;
        private int evaluationStatus;
        private int orderStatus;
        private int payStatus;
        private String createTime;
        private String finnshedTime;
        private String paymentTime;
        private int lockState;
        private String orderFrom;
        private List<OrgListBean> orgList;

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public double getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(double goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public double getPreferential() {
            return preferential;
        }

        public void setPreferential(double preferential) {
            this.preferential = preferential;
        }

        public double getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(double orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getEvaluationStatus() {
            return evaluationStatus;
        }

        public void setEvaluationStatus(int evaluationStatus) {
            this.evaluationStatus = evaluationStatus;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFinnshedTime() {
            return finnshedTime;
        }

        public void setFinnshedTime(String finnshedTime) {
            this.finnshedTime = finnshedTime;
        }

        public String getPaymentTime() {
            return paymentTime;
        }

        public void setPaymentTime(String paymentTime) {
            this.paymentTime = paymentTime;
        }

        public int getLockState() {
            return lockState;
        }

        public void setLockState(int lockState) {
            this.lockState = lockState;
        }

        public String getOrderFrom() {
            return orderFrom;
        }

        public void setOrderFrom(String orderFrom) {
            this.orderFrom = orderFrom;
        }

        public List<OrgListBean> getOrgList() {
            return orgList;
        }

        public void setOrgList(List<OrgListBean> orgList) {
            this.orgList = orgList;
        }

        public static class OrgListBean {
            /**
             * orgId : 68
             * code : BJ-GGBWY
             * name : 故宫博物馆
             * shortName : 故宫
             * foreignName :
             * address :
             * ticketDescription :
             * tourBeginTime :
             * tourEndTime :
             * areaId : 2
             * tel :
             * mail :
             * website :
             * image : M00/00/18/052iAltNboOAWdG2AAQAZ_aEKmg926.jpg
             * description : 在中国建筑史上，历代皇宫都是等级最高、规模最大、成就最卓著、世人最向往的建筑，一切其他建筑和建筑技术都无可比拟。外国的皇宫基本都是豪华奢侈的石材单体建筑，而中国的皇宫则是规模宏大的砖木结构建筑群。在我国漫长的封建社会中，历朝历代的皇宫都非常的恢宏壮观，但至今保存最完整、规模最大的古代皇宫只有北京故宫。它是我国皇宫建筑的集大成者，是古代高超建筑工艺水平的最高典范。因为它是明清两代的皇宫，所以民国后被称为故宫，正名为紫禁城。我国古代天文学家把浩瀚的北天极分为太微垣、紫微垣、天市垣三个天区，紫微垣位于三垣正中，是天帝居住之所，为紫宫。皇帝是天帝之子，人间至尊，因此他们也要模仿天帝，在自己宫殿的名字上冠以“紫”字。而“禁”字的意思是皇宫禁地，戒备森严，万民莫近。
             朱元璋灭元之后，定都南京，封四子朱棣为燕王，镇守北平。朱元璋驾崩后，朱棣发动靖难之役，夺取了侄子朱允炆的皇位。处于政治和军事上的深谋远虑，永乐元年，也就是1403年，朱棣将北平更名为北京，并于永乐四年下诏营建皇宫。
             紫禁城规模形制“悉如南京”，以周礼为设计理念，体现着封建社会森严的等级礼制和帝王至高无上的权威，以五行理论为营造方略，天人合一，乾坤和谐。紫禁城的每一座殿宇、院落都统一在整体规划中，每座建筑规制合理、建造精良，具有皇家气场，与周围其他建筑协调，自身具有和谐美，更与整个紫禁城形成和谐美，这和谐之美也是中国古建一个突出的特点，魅力无穷。
             紫禁城在设计中不仅充分依据人文理念，而且纳入了天气、地质等自然因素，为防止大雨浸泡，设计为北高南低，中轴高、两翼低，北门神武门地平标高46．05米，南门午门地平标高44．28米，落差近2米。故宫内的古代雨水沟长约15公里，其中暗沟13公里，至今仍在发挥作用，不管下多大的雨，都淹不了它。
             紫禁城在兴建过程中动用了工匠23万，民夫百万，于永乐十八年竣工。民间流传着紫禁城的设计者是蒯祥的说法，但蒯祥于洪武三十一年，也就是1398年生于江苏吴县香山，卒于成化十七年。所以说紫禁城兴建时，他只是个五六岁的孩子，不可能是设计师。不过后来在复建烧毁的大殿时他往往有神奇之举，所以有“蒯鲁班”之称。据《史记》载，永乐四年泰宁侯陈珪被任命为营建紫禁城、建都的工程总指挥，他“董治其事”，计划周密，条理分明，深得朱棣器重和信任，可惜他没有看到紫禁城启用就去世了。
             永乐十八年九月朱棣下诏正式迁都北京，次年正月初一，朱棣在奉天殿受朝贺，紫禁城正式启用。
             1644年暮春初夏，清朝统治者入主中原，成了紫禁城的新主人。自1420年紫禁城启用，到1911年清朝灭亡，491年间，共有15位明朝皇帝、10位清朝皇帝在这里生活、执政。
             紫禁城自建成之后由于各种原因的火灾，明清两朝曾多次重修或扩建，但始终保持了最初的布局。这座明清皇宫建筑恢宏、形制严谨、彩绘绚丽、园林典雅。北京城中轴线长7748米，紫禁城坐落在中轴线的中央部位，面南朝阳，背靠“镇山”即景山，其左前方为太庙，右前方为社稷坛，符合“左祖右社”的礼法，占地面积72万多平方米，共有宫殿房屋9371间，城墙高9.3米、周长3437.6米，是我国现存规模最大、保存最完整的皇家宫殿城墙。城墙四面绕以护城河，也叫筒子河，河宽52米，深4.1米，康熙十六年在河内广植莲藕，荷叶碧绿，莲花粉红，风光旖旎。
             紫禁城四隅建有角楼，每个角楼9梁18柱72条脊，造型独特优美。传说当年修建角楼时正值盛夏，众人为不知如何修建而苦闷，吃不下饭，一个老汉挑着蝈蝈担子来到工地，高声喊着“卖蝈蝈，卖蝈蝈”.工匠们又热又烦，于是拿了一块干粮递给老汉，没好气地让他离开，说道：“大爷，您上别处卖去吧，我们建不好角楼，正烦着哪。”老汉说：“别烦啊，我的蝈蝈笼子好啊，看会儿就乐了，送你们俩。”说完就走了。管事的发现这蝈蝈笼子编得太精巧了，正欲寻老汉，却已了无踪影了。工匠们觉得那老汉是鲁班爷下凡，解他们危难来了。他们按照蝈蝈笼子的结构盖起了角楼。现在东南角楼已正式开放，角楼内有角楼结构模型。虽然角楼的四面都有门，但供人出入的只有两个门，一个是正门，一个是配门，门额上绘龙的是正门，画草花的是配门。
             紫禁城四正位各有一门，南为午门，北为神武门，东为东华门，西为西华门。西华门是帝后日常出宫、回宫的门，皇帝驾崩在宫外，梓宫，也就是棺材，都由西华门进，午门只能进喜不进丧。从午门东侧马道登上城墙，在木质行道上漫步，可东望筒子河、南眺太庙、北瞰鳞次栉比的宫殿。
             紫禁城严格按照《周礼》之制建设，布局分为前朝和后廷两部分，前朝以太和殿、中和殿、保和殿为核心，是听政、举行大典的地方。后延以乾清宫、交泰殿、坤宁宫为核心，是帝后的生活区。
             1924年10月23日，冯玉样发动“北京政变”，部将鹿钟麟于11月5日逼宫，令溥仪“即日移出宮禁”。1925年10月10日举行故宫博物院成立典礼，紫禁城正式对社会开放。
             截至2017年3月30日故宮收藏的珍贵文物超过186万件，分为25大类，69小类，浩如烟海的史料典籍、传世珍品是中华民族5000年来政治、文化、经济、军事的高度凝聚。
             北京故宫和美国纽约大都会博物馆、英国伦敦大英博物馆、法国巴黎卢浮宫、俄罗斯圣彼得堡博物馆，并称为世界五大博物馆，但就藏品之多、之精，历史之久远，故宫都远胜于其他四个博物馆。
             故宫留下了无比丰富详实的历史信息，是中国传统文化一个集中的象征，是中华文明成就的标志。1987年北京故宫被列为世界文化遗产，世界文化遗产委员会对故宫的评价语为：“紫禁城是中国5个多世纪以来的最高权力中心。它以园林景观和容纳了家具及工艺品的9000多个房间的庞大建筑群，成为明清时代中国文明无价的历史见证。”它是全世界共同的文化财富！好了，现在我们就一起走进故宫，去参观一下这几百年来一直是无比神秘、无比威严的皇家禁地吧！我是精灵说景区讲解小秘书，我们一会儿见！

             * descriptionVoice :
             * remark :
             * longitude : 116.397026
             * latitude : 39.918058
             * price : 10.0
             * status : true
             * insertTime : 2018-06-26 11:02:36
             * updateTime : 2018-08-13 14:59:28
             * insertIp : 117.9.23.223
             * updateIp : 60.25.5.8
             * orders : 10000
             * creator : 31
             * editor : 17
             * isAutoplay : 1
             * summary : 故宫博物院，是一座特殊的博物馆，成立于1925年的故宫博物院，建立在明清两朝皇宫——紫禁城的基础上。
             历经五百年兴衰荣辱，帝王宫殿的大门终于向公众敞开。
             * imageLayer :
             * imageBounds :
             * imageZooms :
             * area : {"areaId":2,"parent":1,"code":"BJ","name":"北京市","postalCode":"110000","remark":"Beijing Shi","orders":1,"isDisplay":true,"pinYin":"Beijing Shi","parentArea":{"areaId":1,"parent":0,"code":"China","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true,"pinYin":"Zhong Guo"}}
             * sceneryCount : 70
             * articleList : []
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
            private double price;
            private boolean status;
            private String insertTime;
            private String updateTime;
            private String insertIp;
            private String updateIp;
            private int orders;
            private int creator;
            private int editor;
            private int isAutoplay;
            private String summary="";
            private String imageLayer;
            private String imageBounds;
            private String imageZooms;
            private AreaBean area;
            private int sceneryCount;
            private List<?> articleList;

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

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
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

            public List<?> getArticleList() {
                return articleList;
            }

            public void setArticleList(List<?> articleList) {
                this.articleList = articleList;
            }

            public static class AreaBean {
                /**
                 * areaId : 2
                 * parent : 1
                 * code : BJ
                 * name : 北京市
                 * postalCode : 110000
                 * remark : Beijing Shi
                 * orders : 1
                 * isDisplay : true
                 * pinYin : Beijing Shi
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
}
