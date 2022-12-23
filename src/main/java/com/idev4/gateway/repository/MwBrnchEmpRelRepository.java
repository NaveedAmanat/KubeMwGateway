package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwBrnchEmpRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MwBrnchEmpRel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwBrnchEmpRelRepository extends JpaRepository<MwBrnchEmpRel, Long> {

    List<MwBrnchEmpRel> findAllByBrnchSeqAndCrntRecFlg(long brnchSeq, boolean flag);

    MwBrnchEmpRel findOneByBrnchEmpSeqAndCrntRecFlg(long seq, boolean flag);

    MwBrnchEmpRel findOneByBrnchSeqAndCrntRecFlgAndDelFlg(long brnchSeq, boolean flag, boolean dflg);

    MwBrnchEmpRel findOneByBrnchSeqAndCrntRecFlg(long brnchSeq, boolean flag);

    MwBrnchEmpRel findOneByEmpSeqAndCrntRecFlg(long seq, boolean flag);
}
