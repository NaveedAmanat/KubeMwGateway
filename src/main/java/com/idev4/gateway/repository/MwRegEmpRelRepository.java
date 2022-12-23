package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwRegEmpRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MwRegEmpRel entity.
 * Added By Naveed - Date - 23-02-2022
 * For RM User Login
 */
@Repository
public interface MwRegEmpRelRepository extends JpaRepository<MwRegEmpRel, Long> {

    MwRegEmpRel findOneByEmpSeqAndCrntRecFlg(long seq, boolean flag);
}
