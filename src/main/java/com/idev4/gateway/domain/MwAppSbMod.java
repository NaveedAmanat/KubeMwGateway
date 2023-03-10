package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwAppSbModId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwAddr.
 */
@Entity
@Table(name = "MW_APP_SB_MOD")
@IdClass(MwAppSbModId.class)
public class MwAppSbMod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SB_MOD_SEQ")
    private Long sbModSeq;

    @Id
    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "MOD_SEQ")
    private Long modSeq;

    @Column(name = "SB_MOD_ID")
    private String sbModId;

    @Column(name = "SB_MOD_NM")
    private String sbModNm;

    @Column(name = "SB_MOD_CMNT")
    private String sbModCmnt;

    @Column(name = "SB_MOD_URL")
    private String sbModUrl;

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

    public Long getSbModSeq() {
        return sbModSeq;
    }

    public void setSbModSeq(Long sbModSeq) {
        this.sbModSeq = sbModSeq;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public Long getModSeq() {
        return modSeq;
    }

    public void setModSeq(Long modSeq) {
        this.modSeq = modSeq;
    }

    public String getSbModId() {
        return sbModId;
    }

    public void setSbModId(String sbModId) {
        this.sbModId = sbModId;
    }

    public String getSbModNm() {
        return sbModNm;
    }

    public void setSbModNm(String sbModNm) {
        this.sbModNm = sbModNm;
    }

    public String getSbModCmnt() {
        return sbModCmnt;
    }

    public void setSbModCmnt(String sbModCmnt) {
        this.sbModCmnt = sbModCmnt;
    }

    public String getSbModUrl() {
        return sbModUrl;
    }

    public void setSbModUrl(String sbModUrl) {
        this.sbModUrl = sbModUrl;
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
