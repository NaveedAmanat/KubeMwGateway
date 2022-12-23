package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwAppModId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwAddr.
 */
@Entity
@Table(name = "MW_APP_MOD")
@IdClass(MwAppModId.class)
public class MwAppMod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MOD_SEQ")
    private Long modSeq;

    @Id
    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "MOD_ID")
    private String modId;

    @Column(name = "MOD_NM")
    private String modNm;

    @Column(name = "MOD_COMENTS")
    private String modComents;

    @Column(name = "MOD_URL")
    private String modUrl;

    @Column(name = "SORT_ORDR")
    private Long sortOrdr;

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

    public Long getModSeq() {
        return modSeq;
    }

    public void setModSeq(Long modSeq) {
        this.modSeq = modSeq;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public String getModNm() {
        return modNm;
    }

    public void setModNm(String modNm) {
        this.modNm = modNm;
    }

    public String getModComents() {
        return modComents;
    }

    public void setModComents(String modComents) {
        this.modComents = modComents;
    }

    public String getModUrl() {
        return modUrl;
    }

    public void setModUrl(String modUrl) {
        this.modUrl = modUrl;
    }

    public Long getSortOrdr() {
        return sortOrdr;
    }

    public void setSortOrdr(Long sortOrdr) {
        this.sortOrdr = sortOrdr;
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
