package com.tourismelves.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 景点
 */

public class AttractionsBean implements Parcelable {

    private String name;
    private String description;
    private double longitude;
    private double latitude;
    private int unLocked;
    private List<PhotoListBean> photoList;
    private List<String> audioList;
    private List<DoorListBean> doorList;
    private int sceneryId;
    private int orgId;
    private String shortName;
    private String foreignName;
    private String photo;
    private String descriptionVoice;
    private String ticketDescription;
    private String insertTime;
    private boolean status;
    private int playDistance;
    private int modifyUserId;
    private String updateTime;
    private int isAutoplay;
    private int locked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getUnLocked() {
        return unLocked;
    }

    public void setUnLocked(int unLocked) {
        this.unLocked = unLocked;
    }

    public List<PhotoListBean> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoListBean> photoList) {
        this.photoList = photoList;
    }

    public List<String> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<String> audioList) {
        this.audioList = audioList;
    }

    public List<DoorListBean> getDoorList() {
        return doorList;
    }

    public void setDoorList(List<DoorListBean> doorList) {
        this.doorList = doorList;
    }

    public int getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(int sceneryId) {
        this.sceneryId = sceneryId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescriptionVoice() {
        return descriptionVoice;
    }

    public void setDescriptionVoice(String descriptionVoice) {
        this.descriptionVoice = descriptionVoice;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPlayDistance() {
        return playDistance;
    }

    public void setPlayDistance(int playDistance) {
        this.playDistance = playDistance;
    }

    public int getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(int modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsAutoplay() {
        return isAutoplay;
    }

    public void setIsAutoplay(int isAutoplay) {
        this.isAutoplay = isAutoplay;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public static class PhotoListBean implements Parcelable {
        /**
         * photoId : 601
         * photoTypeId : 66
         * orgId : 68
         * author :
         * name : 故宫博物馆
         * photoAlt :
         * photoPath : M00/00/18/052iAltNboOAWdG2AAQAZ_aEKmg926.jpg
         * photoUrl :
         * photoText :
         * info :
         * photoSize : 262247
         * width : 710
         * height : 380
         * suffix : jpg
         * audit : true
         * insertTime : 2018-07-16 12:20:33
         * insertIp : 117.9.22.165
         * addUserId : 17
         */


        private int photoId;
        private int photoTypeId;
        private int orgId;
        private String author;
        private String name;
        private String photoAlt;
        private String photoPath;
        private String photoUrl;
        private String photoText;
        private String info;
        private int photoSize;
        private int width;
        private int height;
        private String suffix;
        private boolean audit;
        private String insertTime;
        private String insertIp;
        private int addUserId;

        public int getPhotoId() {
            return photoId;
        }

        public void setPhotoId(int photoId) {
            this.photoId = photoId;
        }

        public int getPhotoTypeId() {
            return photoTypeId;
        }

        public void setPhotoTypeId(int photoTypeId) {
            this.photoTypeId = photoTypeId;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhotoAlt() {
            return photoAlt;
        }

        public void setPhotoAlt(String photoAlt) {
            this.photoAlt = photoAlt;
        }

        public String getPhotoPath() {
            return photoPath;
        }

        public void setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getPhotoText() {
            return photoText;
        }

        public void setPhotoText(String photoText) {
            this.photoText = photoText;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getPhotoSize() {
            return photoSize;
        }

        public void setPhotoSize(int photoSize) {
            this.photoSize = photoSize;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public boolean isAudit() {
            return audit;
        }

        public void setAudit(boolean audit) {
            this.audit = audit;
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

        public int getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(int addUserId) {
            this.addUserId = addUserId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.photoId);
            dest.writeInt(this.photoTypeId);
            dest.writeInt(this.orgId);
            dest.writeString(this.author);
            dest.writeString(this.name);
            dest.writeString(this.photoAlt);
            dest.writeString(this.photoPath);
            dest.writeString(this.photoUrl);
            dest.writeString(this.photoText);
            dest.writeString(this.info);
            dest.writeInt(this.photoSize);
            dest.writeInt(this.width);
            dest.writeInt(this.height);
            dest.writeString(this.suffix);
            dest.writeByte(this.audit ? (byte) 1 : (byte) 0);
            dest.writeString(this.insertTime);
            dest.writeString(this.insertIp);
            dest.writeInt(this.addUserId);
        }

        public PhotoListBean() {
        }

        protected PhotoListBean(Parcel in) {
            this.photoId = in.readInt();
            this.photoTypeId = in.readInt();
            this.orgId = in.readInt();
            this.author = in.readString();
            this.name = in.readString();
            this.photoAlt = in.readString();
            this.photoPath = in.readString();
            this.photoUrl = in.readString();
            this.photoText = in.readString();
            this.info = in.readString();
            this.photoSize = in.readInt();
            this.width = in.readInt();
            this.height = in.readInt();
            this.suffix = in.readString();
            this.audit = in.readByte() != 0;
            this.insertTime = in.readString();
            this.insertIp = in.readString();
            this.addUserId = in.readInt();
        }

        public static final Creator<PhotoListBean> CREATOR = new Creator<PhotoListBean>() {
            @Override
            public PhotoListBean createFromParcel(Parcel source) {
                return new PhotoListBean(source);
            }

            @Override
            public PhotoListBean[] newArray(int size) {
                return new PhotoListBean[size];
            }
        };
    }

    public static class DoorListBean implements Parcelable {

        /**
         * id : 91
         * orgId : 68
         * longitude : 116.39727
         * latitude : 39.912754
         * playDistance : 20
         * locked : 0
         */

        private int id;
        private int orgId;
        private double longitude;
        private double latitude;
        private int playDistance;
        private int locked;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
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

        public int getPlayDistance() {
            return playDistance;
        }

        public void setPlayDistance(int playDistance) {
            this.playDistance = playDistance;
        }

        public int getLocked() {
            return locked;
        }

        public void setLocked(int locked) {
            this.locked = locked;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.orgId);
            dest.writeDouble(this.longitude);
            dest.writeDouble(this.latitude);
            dest.writeInt(this.playDistance);
            dest.writeInt(this.locked);
        }

        public DoorListBean() {
        }

        protected DoorListBean(Parcel in) {
            this.id = in.readInt();
            this.orgId = in.readInt();
            this.longitude = in.readDouble();
            this.latitude = in.readDouble();
            this.playDistance = in.readInt();
            this.locked = in.readInt();
        }

        public static final Creator<DoorListBean> CREATOR = new Creator<DoorListBean>() {
            @Override
            public DoorListBean createFromParcel(Parcel source) {
                return new DoorListBean(source);
            }

            @Override
            public DoorListBean[] newArray(int size) {
                return new DoorListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeInt(this.unLocked);
        dest.writeList(this.photoList);
        dest.writeList(this.audioList);
        dest.writeList(this.doorList);
        dest.writeInt(this.sceneryId);
        dest.writeInt(this.orgId);
        dest.writeString(this.shortName);
        dest.writeString(this.foreignName);
        dest.writeString(this.photo);
        dest.writeString(this.descriptionVoice);
        dest.writeString(this.ticketDescription);
        dest.writeString(this.insertTime);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeInt(this.playDistance);
        dest.writeInt(this.modifyUserId);
        dest.writeString(this.updateTime);
        dest.writeInt(this.isAutoplay);
        dest.writeInt(this.locked);
    }

    public AttractionsBean() {
    }

    protected AttractionsBean(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.unLocked = in.readInt();
        this.photoList = new ArrayList<PhotoListBean>();
        in.readList(this.photoList, PhotoListBean.class.getClassLoader());
        this.audioList = new ArrayList<String>();
        in.readList(this.audioList, String.class.getClassLoader());
        this.doorList = new ArrayList<DoorListBean>();
        in.readList(this.doorList, DoorListBean.class.getClassLoader());
        this.sceneryId = in.readInt();
        this.orgId = in.readInt();
        this.shortName = in.readString();
        this.foreignName = in.readString();
        this.photo = in.readString();
        this.descriptionVoice = in.readString();
        this.ticketDescription = in.readString();
        this.insertTime = in.readString();
        this.status = in.readByte() != 0;
        this.playDistance = in.readInt();
        this.modifyUserId = in.readInt();
        this.updateTime = in.readString();
        this.isAutoplay = in.readInt();
        this.locked = in.readInt();
    }

    public static final Parcelable.Creator<AttractionsBean> CREATOR = new Parcelable.Creator<AttractionsBean>() {
        @Override
        public AttractionsBean createFromParcel(Parcel source) {
            return new AttractionsBean(source);
        }

        @Override
        public AttractionsBean[] newArray(int size) {
            return new AttractionsBean[size];
        }
    };
}
