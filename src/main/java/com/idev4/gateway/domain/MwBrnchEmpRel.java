package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwBrnchEmpRelId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MwBrnchEmpRel.
 */
@Entity
@Table(name = "mw_brnch_emp_rel")
@IdClass(MwBrnchEmpRelId.class)
public class MwBrnchEmpRel implements Serializable {

    private static final long serialVersionUID = 8L;

    @Id
    @Column(name = "brnch_emp_seq")
    private Long brnchEmpSeq;

    @Column(name = "brnch_seq")
    private Long brnchSeq;

    @Column(name = "emp_seq")
    private Long empSeq;

    @Column(name = "covering_emp_seq")
    private Long coveringEmpSeq;

    @Column(name = "covering_emp_from_dt")
    private Instant coveringEmpFromDt;

    @Column(name = "covering_emp_to_dt")
    private Instant coveringEmpToDt;

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

    @Column(name = "DEL_FLG")
    private Boolean delFlg;

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    public Boolean getCrntRecFlg() {
        return crntRecFlg;
    }

    public void setCrntRecFlg(Boolean crntRecFlg) {
        this.crntRecFlg = crntRecFlg;
    }

    public Long getBrnchEmpSeq() {
        return brnchEmpSeq;
    }

    public void setBrnchEmpSeq(Long brnchEmpSeq) {
        this.brnchEmpSeq = brnchEmpSeq;
    }

    public MwBrnchEmpRel brnchEmpSeq(Long brnchEmpSeq) {
        this.brnchEmpSeq = brnchEmpSeq;
        return this;
    }

    public Long getBrnchSeq() {
        return brnchSeq;
    }

    public void setBrnchSeq(Long brnchSeq) {
        this.brnchSeq = brnchSeq;
    }

    public MwBrnchEmpRel brnchSeq(Long brnchSeq) {
        this.brnchSeq = brnchSeq;
        return this;
    }

    public Long getEmpSeq() {
        return empSeq;
    }

    public void setEmpSeq(Long empSeq) {
        this.empSeq = empSeq;
    }

    public MwBrnchEmpRel empSeq(Long empSeq) {
        this.empSeq = empSeq;
        return this;
    }

    public Long getCoveringEmpSeq() {
        return coveringEmpSeq;
    }

    public void setCoveringEmpSeq(Long coveringEmpSeq) {
        this.coveringEmpSeq = coveringEmpSeq;
    }

    public MwBrnchEmpRel coveringEmpSeq(Long coveringEmpSeq) {
        this.coveringEmpSeq = coveringEmpSeq;
        return this;
    }

    public Instant getCoveringEmpFromDt() {
        return coveringEmpFromDt;
    }

    public void setCoveringEmpFromDt(Instant coveringEmpFromDt) {
        this.coveringEmpFromDt = coveringEmpFromDt;
    }

    public MwBrnchEmpRel coveringEmpFromDt(Instant coveringEmpFromDt) {
        this.coveringEmpFromDt = coveringEmpFromDt;
        return this;
    }

    public Instant getCoveringEmpToDt() {
        return coveringEmpToDt;
    }

    public void setCoveringEmpToDt(Instant coveringEmpToDt) {
        this.coveringEmpToDt = coveringEmpToDt;
    }

    public MwBrnchEmpRel coveringEmpToDt(Instant coveringEmpToDt) {
        this.coveringEmpToDt = coveringEmpToDt;
        return this;
    }

    public String getCrtdBy() {
        return crtdBy;
    }

    public void setCrtdBy(String crtdBy) {
        this.crtdBy = crtdBy;
    }

    public MwBrnchEmpRel crtdBy(String crtdBy) {
        this.crtdBy = crtdBy;
        return this;
    }

    public Instant getCrtdDt() {
        return crtdDt;
    }

    public void setCrtdDt(Instant crtdDt) {
        this.crtdDt = crtdDt;
    }

    public MwBrnchEmpRel crtdDt(Instant crtdDt) {
        this.crtdDt = crtdDt;
        return this;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public MwBrnchEmpRel lastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
        return this;
    }

    public Instant getLastUpdDt() {
        return lastUpdDt;
    }

    public void setLastUpdDt(Instant lastUpdDt) {
        this.lastUpdDt = lastUpdDt;
    }

    public MwBrnchEmpRel lastUpdDt(Instant lastUpdDt) {
        this.lastUpdDt = lastUpdDt;
        return this;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public MwBrnchEmpRel effStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
        return this;
    }

    public Instant getEffEndDt() {
        return effEndDt;
    }

    public void setEffEndDt(Instant effEndDt) {
        this.effEndDt = effEndDt;
    }

    public MwBrnchEmpRel effEndDt(Instant effEndDt) {
        this.effEndDt = effEndDt;
        return this;
    }

    public Boolean isCrntRecFlg() {
        return crntRecFlg;
    }

    public MwBrnchEmpRel crntRecFlg(Boolean crntRecFlg) {
        this.crntRecFlg = crntRecFlg;
        return this;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MwBrnchEmpRel mwBrnchEmpRel = (MwBrnchEmpRel) o;
        if (mwBrnchEmpRel.getBrnchEmpSeq() == null || getBrnchEmpSeq() == null) {
            return false;
        }
        return Objects.equals(getBrnchEmpSeq(), mwBrnchEmpRel.getBrnchEmpSeq());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getBrnchEmpSeq());
    }

    @Override
    public String toString() {
        return "MwBrnchEmpRel{" + "id=" + getBrnchEmpSeq() + ", brnchEmpSeq=" + getBrnchEmpSeq() + ", brnchSeq=" + getBrnchSeq()
                + ", empSeq=" + getEmpSeq() + ", coveringEmpSeq=" + getCoveringEmpSeq() + ", coveringEmpFromDt='" + getCoveringEmpFromDt()
                + "'" + ", coveringEmpToDt='" + getCoveringEmpToDt() + "'" + ", crtdBy='" + getCrtdBy() + "'" + ", crtdDt='" + getCrtdDt()
                + "'" + ", lastUpdBy='" + getLastUpdBy() + "'" + ", lastUpdDt='" + getLastUpdDt() + "'" + ", effStartDt='" + getEffStartDt()
                + "'" + ", effEndDt='" + getEffEndDt() + "'" + ", crntRecFlg='" + isCrntRecFlg() + "'" + "}";
    }
}
