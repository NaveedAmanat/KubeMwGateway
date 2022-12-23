package com.idev4.gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * A MwEmp.
 */
@Entity
@Table(name = "MW_APP_VRSN")
public class MwAppVrsn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APP_VRSN_CD")
    private Long appVrsnCd;

    public Long getAppVrsnCd() {
        return appVrsnCd;
    }

    public void setAppVrsnCd(Long appVrsnCd) {
        this.appVrsnCd = appVrsnCd;
    }

}
