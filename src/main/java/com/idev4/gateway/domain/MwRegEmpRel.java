package com.idev4.gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MwRegEmpRel.
 * Added By Naveed - 23-02-2022
 * For RM User Login
 */
@Entity
@Table(name = "MW_REG_EMP_REL")
public class MwRegEmpRel implements Serializable {

    private static final long serialVersionUID = 9L;

    @Id
    @Column(name = "REG_EMP_SEQ")
    private Long regEmpSeq;

    @Column(name = "REG_SEQ")
    private Long regSeq;

    @Column(name = "emp_seq")
    private Long empSeq;

    @Column(name = "COVERING_EMP_SEQ")
    private Long coveringEmpSeq;

    @Column(name = "COVERING_EMP_FROM_DT")
    private Instant coveringEmpFromDt;

    @Column(name = "COVERING_EMP_TO_DT")
    private Instant coveringEmpToDt;

    @Column(name = "crtd_by")
    private String crtdBy;

    @Column(name = "crtd_dt")
    private Instant crtdDt;

    @Column(name = "last_upd_by")
    private String lastUpdBy;

    @Column(name = "last_upd_dt")
    private Instant lastUpdDt;

    @Column(name = "eff_start_dt")
    private Instant effStartDt;

    @Column(name = "eff_end_dt")
    private Instant effEndDt;

    @Column(name = "crnt_rec_flg")
    private Boolean crntRecFlg;

    public MwRegEmpRel() {

    }

    public MwRegEmpRel(Long regEmpSeq, Long regSeq, Long empSeq, Long coveringEmpSeq, Instant coveringEmpFromDt, Instant coveringEmpToDt, String crtdBy, Instant crtdDt, String lastUpdBy, Instant lastUpdDt, Instant effStartDt, Instant effEndDt, Boolean crntRecFlg) {
        this.regEmpSeq = regEmpSeq;
        this.regSeq = regSeq;
        this.empSeq = empSeq;
        this.coveringEmpSeq = coveringEmpSeq;
        this.coveringEmpFromDt = coveringEmpFromDt;
        this.coveringEmpToDt = coveringEmpToDt;
        this.crtdBy = crtdBy;
        this.crtdDt = crtdDt;
        this.lastUpdBy = lastUpdBy;
        this.lastUpdDt = lastUpdDt;
        this.effStartDt = effStartDt;
        this.effEndDt = effEndDt;
        this.crntRecFlg = crntRecFlg;
    }

    public Long getRegEmpSeq() {
        return regEmpSeq;
    }

    public void setRegEmpSeq(Long regEmpSeq) {
        this.regEmpSeq = regEmpSeq;
    }

    public Long getRegSeq() {
        return regSeq;
    }

    public void setRegSeq(Long regSeq) {
        this.regSeq = regSeq;
    }

    public Long getEmpSeq() {
        return empSeq;
    }

    public void setEmpSeq(Long empSeq) {
        this.empSeq = empSeq;
    }

    public Long getCoveringEmpSeq() {
        return coveringEmpSeq;
    }

    public void setCoveringEmpSeq(Long coveringEmpSeq) {
        this.coveringEmpSeq = coveringEmpSeq;
    }

    public Instant getCoveringEmpFromDt() {
        return coveringEmpFromDt;
    }

    public void setCoveringEmpFromDt(Instant coveringEmpFromDt) {
        this.coveringEmpFromDt = coveringEmpFromDt;
    }

    public Instant getCoveringEmpToDt() {
        return coveringEmpToDt;
    }

    public void setCoveringEmpToDt(Instant coveringEmpToDt) {
        this.coveringEmpToDt = coveringEmpToDt;
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
