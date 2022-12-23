package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwUsrRolId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwAddr.
 */
@Entity
@Table(name = "MW_USR_ROL")
@IdClass(MwUsrRolId.class)
public class MwUsrRol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USR_ROL_SEQ")
    private Long usrRolSeq;

    @Id
    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "USR_ROL_ID")
    private String usrRolId;

    @Column(name = "USR_ROL_NM")
    private String usrRolNm;

    @Column(name = "USR_ROL_CMNT")
    private String usrRolCmnt;

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

    @Column(name = "CLSNG_EFCT")
    private Boolean clsngEfct;

    @Column(name = "USR_ROL_PR")
    private String usrRolPr;

    public String getUsrRolPr() {
        return usrRolPr;
    }

    public void setUsrRolPr(String usrRolPr) {
        this.usrRolPr = usrRolPr;
    }

    public Boolean getClsngEfct() {
        return clsngEfct;
    }

    public void setClsngEfct(Boolean clsngEfct) {
        this.clsngEfct = clsngEfct;
    }

    public String getUsrRolNm() {
        return usrRolNm;
    }

    public void setUsrRolNm(String usrRolNm) {
        this.usrRolNm = usrRolNm;
    }

    public Long getUsrRolSeq() {
        return usrRolSeq;
    }

    public void setUsrRolSeq(Long usrRolSeq) {
        this.usrRolSeq = usrRolSeq;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public String getUsrRolId() {
        return usrRolId;
    }

    public void setUsrRolId(String usrRolId) {
        this.usrRolId = usrRolId;
    }

    public String getUsrRolCmnt() {
        return usrRolCmnt;
    }

    public void setUsrRolCmnt(String usrRolCmnt) {
        this.usrRolCmnt = usrRolCmnt;
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
