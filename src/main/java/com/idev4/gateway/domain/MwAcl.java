package com.idev4.gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * A MwAcl.
 */
@Entity
@Table(name = "MW_ACL")
public class MwAcl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ACL_SEQ")
    private Long aclSeq;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PORT_SEQ")
    private Long portSeq;

    public Long getAclSeq() {
        return aclSeq;
    }

    public void setAclSeq(Long aclSeq) {
        this.aclSeq = aclSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPortSeq() {
        return portSeq;
    }

    public void setPortSeq(Long portSeq) {
        this.portSeq = portSeq;
    }

}
