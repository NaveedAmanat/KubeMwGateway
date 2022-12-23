package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAppAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MwPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwAppAuthRepository extends JpaRepository<MwAppAuth, Long> {

    MwAppAuth findOneByUsrRolSeqAndSbModSeqAndCrntRecFlg(Long role, Long sbModSeq, boolean flg);
}
