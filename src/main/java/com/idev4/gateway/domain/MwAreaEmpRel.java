package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwAreaEmpRelId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwBrnchEmpRel.
 */
@Entity
@Table(name = "MW_AREA_EMP_REL")
@IdClass(MwAreaEmpRelId.class)
public class MwAreaEmpRel implements Serializable {

    private static final long serialVersionUID = 9L;

    @Id
    @Column(name = "AREA_EMP_SEQ")
    private Long areaEmpSeq;

    @Column(name = "AREA_SEQ")
    private Long areaSeq;

    @Column(name = "emp_seq")
    private Long empSeq;

    @Column(name = "CVRNG_EMP_SEQ")
    private Long cvrngEmpSeq;

    @Column(name = "CVRNG_EMP_FROM_DT")
    private Instant cvrngEmpFromDt;

    @Column(name = "CVRNG_EMP_TO_DT")
    private Instant cvrngEmpToDt;

    @Column(name = "crtd_by")
    private String crtdBy;

    @Column(name = "crtd_dt")
    private Instant crtdDt;

    @Column(name = "last_upd_by")
    private String lastUpdBy;

    @Column(name = "last_upd_dt")
    private Instant lastUpdDt;

    @Id
    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "eff_end_dt")
    private Instant effEndDt;

    @Column(name = "crnt_rec_flg")
    private Boolean crntRecFlg;

    public Long getAreaEmpSeq() {
        return areaEmpSeq;
    }

    public void setAreaEmpSeq(Long areaEmpSeq) {
        this.areaEmpSeq = areaEmpSeq;
    }

    public Long getAreaSeq() {
        return areaSeq;
    }

    public void setAreaSeq(Long areaSeq) {
        this.areaSeq = areaSeq;
    }

    public Long getEmpSeq() {
        return empSeq;
    }

    public void setEmpSeq(Long empSeq) {
        this.empSeq = empSeq;
    }

    public Long getCvrngEmpSeq() {
        return cvrngEmpSeq;
    }

    public void setCvrngEmpSeq(Long cvrngEmpSeq) {
        this.cvrngEmpSeq = cvrngEmpSeq;
    }

    public Instant getCvrngEmpFromDt() {
        return cvrngEmpFromDt;
    }

    public void setCvrngEmpFromDt(Instant cvrngEmpFromDt) {
        this.cvrngEmpFromDt = cvrngEmpFromDt;
    }

    public Instant getCvrngEmpToDt() {
        return cvrngEmpToDt;
    }

    public void setCvrngEmpToDt(Instant cvrngEmpToDt) {
        this.cvrngEmpToDt = cvrngEmpToDt;
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

}
