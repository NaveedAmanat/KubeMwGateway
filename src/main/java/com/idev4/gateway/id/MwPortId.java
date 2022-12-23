package com.idev4.gateway.id;


import java.io.Serializable;
import java.time.Instant;

/**
 * A MwPort.
 */

public class MwPortId implements Serializable {

    private static final long serialVersionUID = 1L;


    public Long portSeq;

    public Instant effStartDt;
}
