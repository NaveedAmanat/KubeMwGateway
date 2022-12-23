package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwPortEmpRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MwPortEmpRel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwPortEmpRelRepository extends JpaRepository<MwPortEmpRel, Long> {

    List<MwPortEmpRel> findAllByPortSeqAndCrntRecFlg(long portSeq, boolean flag);

    MwPortEmpRel findOneByPortEmpSeqAndCrntRecFlg(long seq, boolean flag);

    MwPortEmpRel findOneByPortSeqAndCrntRecFlg(long portSeq, boolean flag);

    MwPortEmpRel findOneByEmpSeqAndCrntRecFlg(long seq, boolean flag);
}
