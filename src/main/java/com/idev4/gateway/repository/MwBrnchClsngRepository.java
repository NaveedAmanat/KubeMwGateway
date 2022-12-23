package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwBrnchClsng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Spring Data JPA repository for the MwEmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwBrnchClsngRepository extends JpaRepository<MwBrnchClsng, Long> {

    MwBrnchClsng findOneByBrnchSeqAndBrnchOpnFlg(Long seq, boolean flag);

    MwBrnchClsng findOneByBrnchSeqAndClndrDtAfter(Long seq, LocalDateTime date);

}
