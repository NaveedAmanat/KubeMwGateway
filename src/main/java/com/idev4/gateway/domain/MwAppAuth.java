package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwAppAuthId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwAddr.
 */
@Entity
@Table(name = "MW_APP_AUTH")
@IdClass(MwAppAuthId.class)
public class MwAppAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_AUTH_SEQ")
    private Long appAuthSeq;

    @Id
    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "USR_ROL_SEQ")
    private Long usrRolSeq;

    @Column(name = "SB_MOD_SEQ")
    private Long sbModSeq;

    @Column(name = "READ_PRM_FLG")
    private Boolean readPrmFlg;

    @Column(name = "WRT_PRM_FLG")
    private Boolean wrtPrmFlg;

    @Column(name = "DEL_PRM_FLG")
    private Boolean delPrmFlg;

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

    @Column(name = "eff_end_dt")
    private Instant effEndDt;

    @Column(name = "crnt_rec_flg")
    private Boolean crntRecFlg;

    @Column(name = "AFTR_CLSNG")
    private Boolean aftrClsng;

    public Boolean getAftrClsng() {
        return aftrClsng;
    }

    public void setAftrClsng(Boolean aftrClsng) {
        this.aftrClsng = aftrClsng;
    }

    public Long getAppAuthSeq() {
        return appAuthSeq;
    }

    public void setAppAuthSeq(Long appAuthSeq) {
        this.appAuthSeq = appAuthSeq;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public Long getUsrRolSeq() {
        return usrRolSeq;
    }

    public void setUsrRolSeq(Long usrRolSeq) {
        this.usrRolSeq = usrRolSeq;
    }

    public Long getSbModSeq() {
        return sbModSeq;
    }

    public void setSbModSeq(Long sbModSeq) {
        this.sbModSeq = sbModSeq;
    }

    public Boolean getReadPrmFlg() {
        return readPrmFlg;
    }

    public void setReadPrmFlg(Boolean readPrmFlg) {
        this.readPrmFlg = readPrmFlg;
    }

    public Boolean getWrtPrmFlg() {
        return wrtPrmFlg;
    }

    public void setWrtPrmFlg(Boolean wrtPrmFlg) {
        this.wrtPrmFlg = wrtPrmFlg;
    }

    public Boolean getDelPrmFlg() {
        return delPrmFlg;
    }

    public void setDelPrmFlg(Boolean delPrmFlg) {
        this.delPrmFlg = delPrmFlg;
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

}
