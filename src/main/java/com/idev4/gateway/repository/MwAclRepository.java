package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAcl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MwAddrRel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwAclRepository extends JpaRepository<MwAcl, Long> {

    MwAcl findOneByAclSeq(long aclSeq);

    List<MwAcl> findAllByPortSeq(long portSeq);

    List<MwAcl> findAllByUserIdAndPortSeqIn(String user, List<Long> ports);

    List<MwAcl> findAllByPortSeqIn(List<Long> ports);

    MwAcl findOneByUserIdAndPortSeq(String user, Long portSeq);

    List<MwAcl> findAllByUserId(String user);

}
