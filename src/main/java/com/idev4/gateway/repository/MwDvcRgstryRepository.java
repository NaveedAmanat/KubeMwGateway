package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwDvcRgstr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MwAddr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwDvcRgstryRepository extends JpaRepository<MwDvcRgstr, Long> {

    MwDvcRgstr findOneByDvcRgstrSeqAndCrntRecFlg(long seq, boolean flag);

    MwDvcRgstr findOneByDvcAddrAndCrntRecFlg(String seq, boolean flag);
}
