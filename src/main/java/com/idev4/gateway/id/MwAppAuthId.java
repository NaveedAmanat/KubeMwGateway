package com.idev4.gateway.id;

import java.io.Serializable;
import java.time.Instant;

public class MwAppAuthId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long appAuthSeq;

    private Instant effStartDt;
}
