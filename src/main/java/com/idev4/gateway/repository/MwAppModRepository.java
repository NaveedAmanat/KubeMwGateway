package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAppMod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MwPort entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwAppModRepository extends JpaRepository<MwAppMod, Long> {

    @Query(value = "select distinct mod.* from mw_app_mod mod \r\n"
            + "join mw_app_sb_mod sub on sub.mod_seq=mod.mod_seq and sub.del_flg=0 and sub.crnt_rec_flg=1\r\n"
            + "join mw_app_auth auth on auth.sb_mod_seq=sub.SB_MOD_SEQ and auth.del_flg=0 and auth.crnt_rec_flg=1  and auth.AFTR_CLSNG in :aftrClsng\r\n"
            + "join mw_usr_rol rol on rol.usr_rol_seq=auth.USR_ROL_SEQ and rol.del_flg=0 and rol.crnt_rec_flg=1\r\n"
            + "where rol.USR_ROL_SEQ=:roleSeq\r\n" + "and mod.crnt_rec_flg=1\r\n" + "order by mod.sort_ordr", nativeQuery = true)
    List<MwAppMod> findAllAppModForRole(@Param("roleSeq") Long role, @Param("aftrClsng") List<Boolean> aftrClsng);

}
