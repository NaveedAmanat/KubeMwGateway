package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwDvcRgstrId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwAddr.
 */
@Entity
@Table(name = "MW_DVC_RGSTR")
@IdClass(MwDvcRgstrId.class)
public class MwDvcRgstr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DVC_RGSTR_SEQ")
    private Long dvcRgstrSeq;

    @Column(name = "DVC_ADDR")
    private String dvcAddr;

    @Column(name = "ENTY_TYP_FLG")
    private Long entyTypFlg;

    @Column(name = "ENTY_TYP_SEQ")
    private Long entyTypSeq;

    @Column(name = "crtd_by")
    private String crtdBy;

    @Column(name = "crtd_dt")
    private Instant crtdDt;

    @Column(name = "last_upd_by")
    private String lastUpdBy;

    @Column(name = "last_upd_dt")
    private Instant lastUpdDt;

    @Column(name = "del_flg")
    private Boolean delFlg;

    @Id
    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "eff_end_dt")
    private Instant effEndDt;

    @Column(name = "crnt_rec_flg")
    private Boolean crntRecFlg;

    //Made by Rizwan on 3 February 2022
    @Column(name = "APK_VRSN_UPD_DT")
    private Instant apk_vrsn_upd_dt;

    @Column(name = "APP_VRSN_CD")
    private String app_vrsn_cd;
    //End

    //Made by Rizwan on 20 JULY 2022
    @Column(name = "ANDROID_API_LEVEL")
    private Integer android_api_level;
    //End

    //Made by Rizwan on 3 February 2022
    public String getApp_vrsn_cd() {
        return app_vrsn_cd;
    }

    public void setApp_vrsn_cd(String app_vrsn_cd) {
        this.app_vrsn_cd = app_vrsn_cd;
    }


    public Instant getApk_vrsn_upd_dt() {
        return apk_vrsn_upd_dt;
    }

    public void setApk_vrsn_upd_dt(Instant apk_vrsn_upd_dt) {
        this.apk_vrsn_upd_dt = apk_vrsn_upd_dt;
    }
    //End


    public Integer getAndroid_api_level() {
        return android_api_level;
    }

    public void setAndroid_api_level(Integer android_api_level) {
        this.android_api_level = android_api_level;
    }

    public Long getDvcRgstrSeq() {
        return dvcRgstrSeq;
    }

    public void setDvcRgstrSeq(Long dvcRgstrSeq) {
        this.dvcRgstrSeq = dvcRgstrSeq;
    }

    public String getDvcAddr() {
        return dvcAddr;
    }

    public void setDvcAddr(String dvcAddr) {
        this.dvcAddr = dvcAddr;
    }

    public Long getEntyTypFlg() {
        return entyTypFlg;
    }

    public void setEntyTypFlg(Long entyTypFlg) {
        this.entyTypFlg = entyTypFlg;
    }

    public Long getEntyTypSeq() {
        return entyTypSeq;
    }

    public void setEntyTypSeq(Long entyTypSeq) {
        this.entyTypSeq = entyTypSeq;
    }

    public String getCrtdBy() {
        return crtdBy;
    }

    public void setCrtdBy(String crtdBy) {
        this.crtdBy = crtdBy;
    }

    public Instant getCrtdDt() {
        return crtdDt;
    }

    public void setCrtdDt(Instant crtdDt) {
        this.crtdDt = crtdDt;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public Instant getLastUpdDt() {
        return lastUpdDt;
    }

    public void setLastUpdDt(Instant lastUpdDt) {
        this.lastUpdDt = lastUpdDt;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public Instant getEffEndDt() {
        return effEndDt;
    }

    public void setEffEndDt(Instant effEndDt) {
        this.effEndDt = effEndDt;
    }

    public Boolean getCrntRecFlg() {
        return crntRecFlg;
    }

    public void setCrntRecFlg(Boolean crntRecFlg) {
        this.crntRecFlg = crntRecFlg;
    }

    @Override
    public String toString() {
        return "MwDvcRgstr{" +
                "dvcRgstrSeq=" + dvcRgstrSeq +
                ", dvcAddr='" + dvcAddr + '\'' +
                ", entyTypFlg=" + entyTypFlg +
                ", entyTypSeq=" + entyTypSeq +
                ", crtdBy='" + crtdBy + '\'' +
                ", crtdDt=" + crtdDt +
                ", lastUpdBy='" + lastUpdBy + '\'' +
                ", lastUpdDt=" + lastUpdDt +
                ", delFlg=" + delFlg +
                ", effStartDt=" + effStartDt +
                ", effEndDt=" + effEndDt +
                ", crntRecFlg=" + crntRecFlg +
                ", apk_vrsn_upd_dt=" + apk_vrsn_upd_dt +
                ", app_vrsn_cd='" + app_vrsn_cd + '\'' +
                ", android_api_level='" + android_api_level + '\'' +
                '}';
    }
}
