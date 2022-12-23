package com.idev4.gateway.domain;

import com.idev4.gateway.id.MwPortEmpRelId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MwPortEmpRel.
 */
@Entity
@Table(name = "mw_port_emp_rel")
@IdClass(MwPortEmpRelId.class)
public class MwPortEmpRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "port_emp_seq")
    private Long portEmpSeq;

    @Column(name = "port_seq")
    private Long portSeq;

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

    public Long getPortEmpSeq() {
        return portEmpSeq;
    }

    public void setPortEmpSeq(Long portEmpSeq) {
        this.portEmpSeq = portEmpSeq;
    }

    public MwPortEmpRel portEmpSeq(Long portEmpSeq) {
        this.portEmpSeq = portEmpSeq;
        return this;
    }

    public Long getPortSeq() {
        return portSeq;
    }

    public void setPortSeq(Long portSeq) {
        this.portSeq = portSeq;
    }

    public MwPortEmpRel portSeq(Long portSeq) {
        this.portSeq = portSeq;
        return this;
    }

    public Long getEmpSeq() {
        return empSeq;
    }

    public void setEmpSeq(Long empSeq) {
        this.empSeq = empSeq;
    }

    public MwPortEmpRel empSeq(Long empSeq) {
        this.empSeq = empSeq;
        return this;
    }

    public Long getCoveringEmpSeq() {
        return coveringEmpSeq;
    }

    public void setCoveringEmpSeq(Long coveringEmpSeq) {
        this.coveringEmpSeq = coveringEmpSeq;
    }

    public MwPortEmpRel coveringEmpSeq(Long coveringEmpSeq) {
        this.coveringEmpSeq = coveringEmpSeq;
        return this;
    }

    public Instant getCoveringEmpFromDt() {
        return coveringEmpFromDt;
    }

    public void setCoveringEmpFromDt(Instant coveringEmpFromDt) {
        this.coveringEmpFromDt = coveringEmpFromDt;
    }

    public MwPortEmpRel coveringEmpFromDt(Instant coveringEmpFromDt) {
        this.coveringEmpFromDt = coveringEmpFromDt;
        return this;
    }

    public Instant getCoveringEmpToDt() {
        return coveringEmpToDt;
    }

    public void setCoveringEmpToDt(Instant coveringEmpToDt) {
        this.coveringEmpToDt = coveringEmpToDt;
    }

    public MwPortEmpRel coveringEmpToDt(Instant coveringEmpToDt) {
        this.coveringEmpToDt = coveringEmpToDt;
        return this;
    }

    public String getCrtdBy() {
        return crtdBy;
    }

    public void setCrtdBy(String crtdBy) {
        this.crtdBy = crtdBy;
    }

    public MwPortEmpRel crtdBy(String crtdBy) {
        this.crtdBy = crtdBy;
        return this;
    }

    public Instant getCrtdDt() {
        return crtdDt;
    }

    public void setCrtdDt(Instant crtdDt) {
        this.crtdDt = crtdDt;
    }

    public MwPortEmpRel crtdDt(Instant crtdDt) {
        this.crtdDt = crtdDt;
        return this;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public MwPortEmpRel lastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
        return this;
    }

    public Instant getLastUpdDt() {
        return lastUpdDt;
    }

    public void setLastUpdDt(Instant lastUpdDt) {
        this.lastUpdDt = lastUpdDt;
    }

    public MwPortEmpRel lastUpdDt(Instant lastUpdDt) {
        this.lastUpdDt = lastUpdDt;
        return this;
    }

    public Instant getEffStartDt() {
        return effStartDt;
    }

    public void setEffStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
    }

    public MwPortEmpRel effStartDt(Instant effStartDt) {
        this.effStartDt = effStartDt;
        return this;
    }

    public Instant getEffEndDt() {
        return effEndDt;
    }

    public void setEffEndDt(Instant effEndDt) {
        this.effEndDt = effEndDt;
    }

    public MwPortEmpRel effEndDt(Instant effEndDt) {
        this.effEndDt = effEndDt;
        return this;
    }

    public Boolean isCrntRecFlg() {
        return crntRecFlg;
    }

    public MwPortEmpRel crntRecFlg(Boolean crntRecFlg) {
        this.crntRecFlg = crntRecFlg;
        return this;
    }

    public void setCrntRecFlg(Boolean crntRecFlg) {
        this.crntRecFlg = crntRecFlg;
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
        MwPortEmpRel mwPortEmpRel = (MwPortEmpRel) o;
        if (mwPortEmpRel.getPortEmpSeq() == null || getPortEmpSeq() == null) {
            return false;
        }
        return Objects.equals(getPortEmpSeq(), mwPortEmpRel.getPortEmpSeq());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPortEmpSeq());
    }

    @Override
    public String toString() {
        return "MwPortEmpRel{" + "id=" + getPortEmpSeq() + ", portEmpSeq=" + getPortEmpSeq() + ", portSeq=" + getPortSeq() + ", empSeq="
                + getEmpSeq() + ", coveringEmpSeq=" + getCoveringEmpSeq() + ", coveringEmpFromDt='" + getCoveringEmpFromDt() + "'"
                + ", coveringEmpToDt='" + getCoveringEmpToDt() + "'" + ", crtdBy='" + getCrtdBy() + "'" + ", crtdDt='" + getCrtdDt() + "'"
                + ", lastUpdBy='" + getLastUpdBy() + "'" + ", lastUpdDt='" + getLastUpdDt() + "'" + ", effStartDt='" + getEffStartDt() + "'"
                + ", effEndDt='" + getEffEndDt() + "'" + ", crntRecFlg='" + isCrntRecFlg() + "'" + "}";
    }
}
