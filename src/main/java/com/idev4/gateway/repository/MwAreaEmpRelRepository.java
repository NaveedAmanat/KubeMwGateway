package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAreaEmpRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MwBrnchEmpRel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwAreaEmpRelRepository extends JpaRepository<MwAreaEmpRel, Long> {

    MwAreaEmpRel findOneByAreaEmpSeqAndCrntRecFlg(long seq, boolean flag);

    MwAreaEmpRel findOneByAreaSeqAndCrntRecFlg(long brnchSeq, boolean flag);

    MwAreaEmpRel findOneByEmpSeqAndCrntRecFlg(long seq, boolean flag);
}
