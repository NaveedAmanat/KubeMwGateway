package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAppVrsn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MwPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwAppVrsnRepository extends JpaRepository<MwAppVrsn, Long> {
}
