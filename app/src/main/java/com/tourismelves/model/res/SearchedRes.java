package com.tourismelves.model.res;

import java.util.List;

public class SearchedRes {

    private String name;
    private String message;
    private String type;
    private int code;
    private int totalPage;
    private int totalCount;
    private int page;
    private int rows;
    private List<DataListBean> dataList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
         * orgId : 45
         * code : 测试2
         * name : 测试2
         * shortName : 测试2
         * foreignName : 测试2
         * type : 200713
         * nature : 1301
         * grade : 200718
         * address :
         * ticketDescription :
         * tourBeginTime :
         * tourEndTime :
         * areaId : 5002
         * tel :
         * mail :
         * website :
         * image : M00/00/17/052iAlsciwuACoBEAAX79XBKT2c760.jpg
         * description : 测试2
         * descriptionVoice : M00/00/0E/052iAlr63VaAKH-MAEAyJMQxpBI023.mp3
         * remark :
         * longitude : 120.287011
         * latitude : 31.484685
         * price : 0.1
         * status : true
         * insertTime : 2018-05-23 00:13:22
         * updateTime : 2018-06-16 04:16:05
         * insertIp : 49.80.145.96
         * updateIp : 117.85.153.14
         * creator : 1
         * editor : 1
         * isAutoplay : 1
         * imageLayer :
         * imageBounds :
         * imageZooms :
         * area : {"areaId":5002,"parent":1,"code":"320200","name":"无锡","postalCode":"214000","remark":"","isDisplay":true,"pinYin":"","parentArea":{"areaId":1,"parent":0,"code":"China","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true,"pinYin":"Zhong Guo"}}
         * sceneryCount : 14
         * articleList : []
         */

        private int orgId;
        private String code;
        private String name;
        private String shortName;
        private String foreignName;
        private int type;
        private int nature;
        private int grade;
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
        private int creator;
        private int editor;
        private int isAutoplay;
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
             * areaId : 5002
             * parent : 1
             * code : 320200
             * name : 无锡
             * postalCode : 214000
             * remark :
             * isDisplay : true
             * pinYin :
             * parentArea : {"areaId":1,"parent":0,"code":"China","name":"中国","postalCode":"中国","remark":"Zhong Guo","orders":1,"isDisplay":true,"pinYin":"Zhong Guo"}
             */

            private int areaId;
            private int parent;
            private String code;
            private String name;
            private String postalCode;
            private String remark;
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
