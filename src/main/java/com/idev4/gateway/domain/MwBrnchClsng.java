package com.idev4.gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A MwEmp.
 */
@Entity
@Table(name = "MW_BRNCH_CLSNG")
public class MwBrnchClsng implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BRNCH_CLSNG_SEQ")
    private Long brnchClsngSeq;

    @Column(name = "CLNDR_DT")
    private LocalDateTime clndrDt;

    @Column(name = "BRNCH_OPN_DT")
    private LocalDateTime brnchOpnDt;

    @Column(name = "BRNCH_OPN_BY")
    private String brnchOpnBy;

    @Column(name = "BRNCH_CLSD_DT")
    private LocalDateTime brnchClsdDt;

    @Column(name = "BRNCH_CLSD_BY")
    private String brnchClsdBy;

    @Column(name = "BRNCH_OPN_FLG")
    private Boolean brnchOpnFlg;

    @Column(name = "BRNCH_SEQ")
    private Long brnchSeq;

    public Long getBrnchClsngSeq() {
        return brnchClsngSeq;
    }

    public void setBrnchClsngSeq(Long brnchClsngSeq) {
        this.brnchClsngSeq = brnchClsngSeq;
    }

    public LocalDateTime getClndrDt() {
        return clndrDt;
    }

    public void setClndrDt(LocalDateTime clndrDt) {
        this.clndrDt = clndrDt;
    }

    public LocalDateTime getBrnchOpnDt() {
        return brnchOpnDt;
    }

    public void setBrnchOpnDt(LocalDateTime brnchOpnDt) {
        this.brnchOpnDt = brnchOpnDt;
    }

    public String getBrnchOpnBy() {
        return brnchOpnBy;
    }

    public void setBrnchOpnBy(String brnchOpnBy) {
        this.brnchOpnBy = brnchOpnBy;
    }

    public LocalDateTime getBrnchClsdDt() {
        return brnchClsdDt;
    }

    public void setBrnchClsdDt(LocalDateTime brnchClsdDt) {
        this.brnchClsdDt = brnchClsdDt;
    }

    public String getBrnchClsdBy() {
        return brnchClsdBy;
    }

    public void setBrnchClsdBy(String brnchClsdBy) {
        this.brnchClsdBy = brnchClsdBy;
    }

    public Boolean getBrnchOpnFlg() {
        return brnchOpnFlg;
    }

    public void setBrnchOpnFlg(Boolean brnchOpnFlg) {
        this.brnchOpnFlg = brnchOpnFlg;
    }

    public Long getBrnchSeq() {
        return brnchSeq;
    }

    public void setBrnchSeq(Long brnchSeq) {
        this.brnchSeq = brnchSeq;
    }

}
