package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/10/12.
 */
public class PhotoVideoData implements Serializable {
    private int pvid;
    private String proId;
    private String videoName;
    private String videoUrl;
    private String photoName;
    private String photoUrl;
    private String creartTime;

    public int getPvid() {
        return pvid;
    }

    public void setPvid(int pvid) {
        this.pvid = pvid;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }


    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }


    public String getCreartTime() {
        return creartTime;
    }

    public void setCreartTime(String creartTime) {
        this.creartTime = creartTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
