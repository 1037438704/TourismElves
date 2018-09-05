package com.tourismelves.model.res;

import android.text.TextUtils;

import com.tourismelves.utils.file.RootFile;

import java.io.Serializable;

public class ApkDownloadInfoRes implements Serializable {
    private static final long serialVersionUID = 5202315092313336951L;
    private String downKey;
    private String imageUrl;
    private String localUrl;
    private String name;
    private String targetFolder;
    private String url;
    private String content="";

    public String getDownKey() {
        return this.downKey;
    }

    public String getFileName() {
        if (TextUtils.isEmpty(this.name))
            return System.currentTimeMillis() + ".mp3";
        String str = this.name.replaceAll("\\?", "").replaceAll("'", "").replaceAll("\\\\", "").replaceAll("/", "").replaceAll("【", "").replaceAll("】", "").replaceAll(":", "").replaceAll("：", "");
        return "/" + str + ".mp3";
    }

    public String getFilePath() {
        String str = RootFile.getDownloadFiles().getPath();
        if (!(TextUtils.isEmpty(this.targetFolder)))
            str = this.targetFolder;
        return str + getFileName();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getLocalUrl() {
        return this.localUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getTargetFolder() {
        return this.targetFolder;
    }

    public String getUrl() {
        return this.url;
    }

    public void setDownKey(String paramString) {
        this.downKey = paramString;
    }

    public void setImageUrl(String paramString) {
        this.imageUrl = paramString;
    }

    public void setLocalUrl(String paramString) {
        this.localUrl = paramString;
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setTargetFolder(String paramString) {
        this.targetFolder = paramString;
    }

    public void setUrl(String paramString) {
        this.url = paramString;
    }

    public String toString() {
        return "{\"content\":\"" + this.content + "\",\"url\":\"" + this.url + "\", \"name\":\"" + this.name + "\", \"imageUrl\":\"" + this.imageUrl + "\", \"downKey\":\"" + this.downKey + "\", \"targetFolder\":\"" + this.targetFolder + "\", \"localUrl\":\"" + this.localUrl + "\"}";
    }
}
