package com.tourismelves.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.tourismelves.model.res.ApkDownloadInfoRes;

import java.util.List;

/**
 * 景点
 */

public class AttractionsBean implements Parcelable {
    private ApkDownloadInfoRes apkDownloadInfoRes;
    private boolean isSelect=false;
    private int sceneryId;
    private int orgId;
    private String name;
    private String shortName;
    private String foreignName;
    private String photo;
    private String description;
    private String descriptionVoice;
    private double ticketPrice;
    private String ticketDescription;
    private double longitude;
    private double latitude;
    private String insertTime;
    private int hot;
    private int praise;
    private boolean status;
    private int orders;
    private int playDistance;
    private int unLocked;
    private int locked;
    private List<PhotoListBean> photoList;
    private List<AudioListBean> audioList;
    private List<DoorListBean> doorList;

    public ApkDownloadInfoRes getApkDownloadInfoRes() {
        return apkDownloadInfoRes;
    }

    public void setApkDownloadInfoRes(ApkDownloadInfoRes apkDownloadInfoRes) {
        this.apkDownloadInfoRes = apkDownloadInfoRes;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
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

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getPlayDistance() {
        return playDistance;
    }

    public void setPlayDistance(int playDistance) {
        this.playDistance = playDistance;
    }

    public int getUnLocked() {
        return unLocked;
    }

    public void setUnLocked(int unLocked) {
        this.unLocked = unLocked;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public List<PhotoListBean> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoListBean> photoList) {
        this.photoList = photoList;
    }

    public List<AudioListBean> getAudioList() {
        return audioList;
    }

    public void setAudioList(List<AudioListBean> audioList) {
        this.audioList = audioList;
    }

    public List<DoorListBean> getDoorList() {
        return doorList;
    }

    public void setDoorList(List<DoorListBean> doorList) {
        this.doorList = doorList;
    }

    public static class PhotoListBean implements Parcelable {
        /**
         * photoId : 446
         * photoTypeId : 61
         * orgId : 7
         * sceneryId : 17
         * author :
         * name : 丽正门
         * photoAlt :
         * photoPath : M00/00/15/052iAlsWQfmAB02DAAPQ50SbnCE862.jpg
         * photoUrl :
         * photoText :
         * info :
         * photoSize : 250087
         * width : 710
         * height : 380
         * suffix : jpg
         * audit : true
         * insertTime : 2018-06-05 03:55:40
         * insertIp : 117.11.214.111
         * addUserId : 17
         */



        private int photoId;
        private int photoTypeId;
        private int orgId;
        private int sceneryId;
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

        public int getSceneryId() {
            return sceneryId;
        }

        public void setSceneryId(int sceneryId) {
            this.sceneryId = sceneryId;
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
            dest.writeInt(this.sceneryId);
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
            this.sceneryId = in.readInt();
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

    public static class AudioListBean implements Parcelable {


        /**
         * audioId : 1391
         * audioTypeId : 67
         * orgId : 7
         * sceneryId : 17
         * name : 丽正门
         * image :
         * audioPath : M00/00/15/052iAlsV6juAXMDAAAf1uZmYIkY179.mp3
         * audioUrl :
         * duration : 65
         * info :
         * author :
         * audioSize : 521657
         * suffix : mp3
         * insertTime : 2018-06-04 21:41:17
         * insertIp : 117.11.214.111
         * isOriginal : true
         * audit : true
         * addUserId : 17
         */

        private int audioId;
        private int audioTypeId;
        private int orgId;
        private int sceneryId;
        private String name;
        private String image;
        private String audioPath;
        private String audioUrl;
        private int duration;
        private String info;
        private String author;
        private int audioSize;
        private String suffix;
        private String insertTime;
        private String insertIp;
        private boolean isOriginal;
        private boolean audit;
        private int addUserId;

        public int getAudioId() {
            return audioId;
        }

        public void setAudioId(int audioId) {
            this.audioId = audioId;
        }

        public int getAudioTypeId() {
            return audioTypeId;
        }

        public void setAudioTypeId(int audioTypeId) {
            this.audioTypeId = audioTypeId;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public int getSceneryId() {
            return sceneryId;
        }

        public void setSceneryId(int sceneryId) {
            this.sceneryId = sceneryId;
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

        public String getAudioPath() {
            return audioPath;
        }

        public void setAudioPath(String audioPath) {
            this.audioPath = audioPath;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getAudioSize() {
            return audioSize;
        }

        public void setAudioSize(int audioSize) {
            this.audioSize = audioSize;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
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

        public boolean isIsOriginal() {
            return isOriginal;
        }

        public void setIsOriginal(boolean isOriginal) {
            this.isOriginal = isOriginal;
        }

        public boolean isAudit() {
            return audit;
        }

        public void setAudit(boolean audit) {
            this.audit = audit;
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
            dest.writeInt(this.audioId);
            dest.writeInt(this.audioTypeId);
            dest.writeInt(this.orgId);
            dest.writeInt(this.sceneryId);
            dest.writeString(this.name);
            dest.writeString(this.image);
            dest.writeString(this.audioPath);
            dest.writeString(this.audioUrl);
            dest.writeInt(this.duration);
            dest.writeString(this.info);
            dest.writeString(this.author);
            dest.writeInt(this.audioSize);
            dest.writeString(this.suffix);
            dest.writeString(this.insertTime);
            dest.writeString(this.insertIp);
            dest.writeByte(this.isOriginal ? (byte) 1 : (byte) 0);
            dest.writeByte(this.audit ? (byte) 1 : (byte) 0);
            dest.writeInt(this.addUserId);
        }

        public AudioListBean() {
        }

        protected AudioListBean(Parcel in) {
            this.audioId = in.readInt();
            this.audioTypeId = in.readInt();
            this.orgId = in.readInt();
            this.sceneryId = in.readInt();
            this.name = in.readString();
            this.image = in.readString();
            this.audioPath = in.readString();
            this.audioUrl = in.readString();
            this.duration = in.readInt();
            this.info = in.readString();
            this.author = in.readString();
            this.audioSize = in.readInt();
            this.suffix = in.readString();
            this.insertTime = in.readString();
            this.insertIp = in.readString();
            this.isOriginal = in.readByte() != 0;
            this.audit = in.readByte() != 0;
            this.addUserId = in.readInt();
        }

        public static final Creator<AudioListBean> CREATOR = new Creator<AudioListBean>() {
            @Override
            public AudioListBean createFromParcel(Parcel source) {
                return new AudioListBean(source);
            }

            @Override
            public AudioListBean[] newArray(int size) {
                return new AudioListBean[size];
            }
        };
    }

    public static class DoorListBean implements Parcelable {


        /**
         * id : 59
         * orgId : 7
         * longitude : 117.940755
         * latitude : 40.981444
         * playDistance : 20
         * locked : 0
         */

        private int id;
        private int orgId;

        private double longitude;
        private double latitude;
        private int playDistance;
        private int locked;
        private String name;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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
            dest.writeString(this.name);
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
            this.name = in.readString();
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

    public AttractionsBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.apkDownloadInfoRes);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        dest.writeInt(this.sceneryId);
        dest.writeInt(this.orgId);
        dest.writeString(this.name);
        dest.writeString(this.shortName);
        dest.writeString(this.foreignName);
        dest.writeString(this.photo);
        dest.writeString(this.description);
        dest.writeString(this.descriptionVoice);
        dest.writeDouble(this.ticketPrice);
        dest.writeString(this.ticketDescription);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeString(this.insertTime);
        dest.writeInt(this.hot);
        dest.writeInt(this.praise);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeInt(this.orders);
        dest.writeInt(this.playDistance);
        dest.writeInt(this.unLocked);
        dest.writeInt(this.locked);
        dest.writeTypedList(this.photoList);
        dest.writeTypedList(this.audioList);
        dest.writeTypedList(this.doorList);
    }

    protected AttractionsBean(Parcel in) {
        this.apkDownloadInfoRes = (ApkDownloadInfoRes) in.readSerializable();
        this.isSelect = in.readByte() != 0;
        this.sceneryId = in.readInt();
        this.orgId = in.readInt();
        this.name = in.readString();
        this.shortName = in.readString();
        this.foreignName = in.readString();
        this.photo = in.readString();
        this.description = in.readString();
        this.descriptionVoice = in.readString();
        this.ticketPrice = in.readDouble();
        this.ticketDescription = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.insertTime = in.readString();
        this.hot = in.readInt();
        this.praise = in.readInt();
        this.status = in.readByte() != 0;
        this.orders = in.readInt();
        this.playDistance = in.readInt();
        this.unLocked = in.readInt();
        this.locked = in.readInt();
        this.photoList = in.createTypedArrayList(PhotoListBean.CREATOR);
        this.audioList = in.createTypedArrayList(AudioListBean.CREATOR);
        this.doorList = in.createTypedArrayList(DoorListBean.CREATOR);
    }

    public static final Creator<AttractionsBean> CREATOR = new Creator<AttractionsBean>() {
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
