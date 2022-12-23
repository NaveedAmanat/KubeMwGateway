package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwUsrRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MwPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwUsrRolRepository extends JpaRepository<MwUsrRol, Long> {

    MwUsrRol findOneByUsrRolNmAndCrntRecFlg(String role, boolean flg);

    List<MwUsrRol> findAllByCrntRecFlg(boolean flg);
}
