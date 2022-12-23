package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwAppSbMod;
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
public interface MwAppSbModRepository extends JpaRepository<MwAppSbMod, Long> {

    @Query(value = "select * from mw_app_sb_mod sub \r\n"
            + "join mw_app_auth auth on auth.sb_mod_seq=sub.SB_MOD_SEQ and auth.del_flg=0 and auth.crnt_rec_flg=1 and auth.AFTR_CLSNG in :aftrClsng\r\n"
            + "join mw_usr_rol rol on rol.usr_rol_seq=auth.USR_ROL_SEQ and rol.del_flg=0 and rol.crnt_rec_flg=1\r\n"
            + "where rol.USR_ROL_SEQ=:roleSeq\r\n" + "and sub.crnt_rec_flg=1", nativeQuery = true)
    List<MwAppSbMod> findAllAppSbModForRole(@Param("roleSeq") Long role, @Param("aftrClsng") List<Boolean> aftrClsng);

    @Query(value = "select * from mw_app_sb_mod sub \r\n"
            + "join mw_app_auth auth on auth.sb_mod_seq=sub.SB_MOD_SEQ and auth.del_flg=0 and auth.crnt_rec_flg=1 and auth.AFTR_CLSNG in :aftrClsng\r\n"
            + "join mw_usr_rol rol on rol.usr_rol_seq=auth.USR_ROL_SEQ and rol.del_flg=0 and rol.crnt_rec_flg=1\r\n"
            + "where rol.USR_ROL_SEQ=:roleSeq and sub.MOD_SEQ = :modSeq\r\n" + "and sub.crnt_rec_flg=1", nativeQuery = true)
    List<MwAppSbMod> findAllAppSbModForModSeq(@Param("roleSeq") Long role, @Param("modSeq") Long modSeq,
                                              @Param("aftrClsng") List<Boolean> aftrClsng);

    List<MwAppSbMod> findAllByModSeqAndCrntRecFlg(Long role, boolean flg);
}
