package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwPort;
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
public interface MwPortRepository extends JpaRepository<MwPort, Long> {

    List<MwPort> findAllByBrnchSeqAndCrntRecFlg(long branchSeq, boolean flag);

    List<MwPort> findAllByCrntRecFlg(boolean flag);

    MwPort findTopByOrderByPortSeqDesc();

    MwPort findOneByPortSeqAndCrntRecFlg(long seq, boolean flag);

    List<MwPort> findAllByPortSeq(long seq);

    /*
		 	UPDATED BY YOUSAF (GET INFORMATION FROM HARMONY)
    */

    @Query(value = "select distinct mp.* from hr.hris dtl  \n" +
            "    join HR_MW6_BRANCH_MAPS dtlmp on dtlmp.hr_branch_cd=dtl.detail_location_id \n" +
            "    join mw_brnch mb on mb.brnch_cd=dtlmp.mw6_branch_cd and mb.crnt_rec_flg=1\n" +
            "    join mw_port mp on mp.brnch_seq=mb.brnch_seq and mp.crnt_rec_flg=1\n" +
            "    where  dtl.STATUS = 1 and dtlmp.MAP_FLG = 1\n" +
            "    and dtl.SUB_DEPARTMENT_CODE=(\n" +
            "        select SUB_DEPARTMENT_CODE from hr.hris emp \n" +
            "        where emp.employee_id=LPAD(:empId, 5,'0') and emp.STATUS = 1\n" +
            "    )", nativeQuery = true)
    List<MwPort> findAllByPortsForEmp(@Param("empId") String empId);

    // For ITO
    // Added By Naveed 11-08-2021
    /*
		 	UPDATED BY YOUSAF (GET INFORMATION FROM HARMONY)
    */
    @Query(value = "select mp.* from MW_REG reg \n" +
            "     join mw_area ma on ma.reg_seq = reg.reg_seq and ma.crnt_rec_flg=1 \n" +
            "     join mw_brnch mb on mb.area_seq = ma.area_seq and mb.crnt_rec_flg=1\n" +
            "     join mw_port mp on mp.brnch_seq=mb.brnch_seq and mp.crnt_rec_flg=1\n" +
            "     where reg.crnt_rec_flg = 1\n" +
            "     and reg.HR_LOC_CD = (    \n" +
            "        select detail_location_id from hr.hris emp \n" +
            "        where emp.employee_id=LPAD(:empId, 5,'0') and emp.STATUS = 1       \n" +
            "     )", nativeQuery = true)
    List<MwPort> findAllByPortsAndRoleITO(@Param("empId") String empId);

    //Added by Areeba
    @Query(value = " select mp.* from MW_REG reg \n" +
            "                 join mw_area ma on ma.reg_seq = reg.reg_seq and ma.crnt_rec_flg=1 \n" +
            "                 join mw_brnch mb on mb.area_seq = ma.area_seq and mb.crnt_rec_flg=1\n" +
            "                 join mw_port mp on mp.brnch_seq=mb.brnch_seq and mp.crnt_rec_flg=1\n" +
            "                 where reg.crnt_rec_flg = 1\n" +
            "                 and reg.reg_seq IN (25, 35)", nativeQuery = true)
    List<MwPort> findAllPortsForIsbKpkRegion();
    //Ended by Areeba

    //Added by Areeba
    @Query(value = " select mp.* from MW_REG reg \n" +
            "                 join mw_area ma on ma.reg_seq = reg.reg_seq and ma.crnt_rec_flg=1 \n" +
            "                 join mw_brnch mb on mb.area_seq = ma.area_seq and mb.crnt_rec_flg=1\n" +
            "                 join mw_port mp on mp.brnch_seq=mb.brnch_seq and mp.crnt_rec_flg=1\n" +
            "                 where reg.crnt_rec_flg = 1\n" +
            "                 and reg.reg_seq IN (21, 26)", nativeQuery = true)
    List<MwPort> findAllPortsForMulSwlRegion();
    //Ended by Areeba

    /*
     * Added By Naveed - Date - 06-03-2022
     * fetch portfolio of respective AM (Area level)
     * */
    @Query(value = "SELECT mp.*\n" +
            "       from  mw_area ma \n" +
            "       JOIN mw_brnch mb ON mb.area_seq = ma.area_seq AND mb.crnt_rec_flg = 1\n" +
            "       JOIN mw_port mp ON mp.brnch_seq = mb.brnch_seq AND mp.crnt_rec_flg = 1\n" +
            " WHERE     ma.crnt_rec_flg = 1\n" +
            "       AND ma.HR_LOC_CD =\n" +
            "           (SELECT detail_location_id\n" +
            "              FROM hr.hris emp\n" +
            "             WHERE     emp.employee_id = LPAD ( :empId, 5, '0')\n" +
            "                   AND emp.STATUS = 1)", nativeQuery = true)
    List<MwPort> findAllByPortsAndRoleAreaLevel(@Param("empId") String empId);
}
