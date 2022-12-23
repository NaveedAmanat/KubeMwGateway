package com.idev4.gateway.repository;

import com.idev4.gateway.domain.MwEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MwEmp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MwEmpRepository extends JpaRepository<MwEmp, Long> {

    List<MwEmp> findAllByEmpNmContaining(String chars);

    MwEmp findOneByEmpNmContaining(String chars);

    MwEmp findOneByEmpSeq(Long chars);

    MwEmp findOneByEmpLanId(String lanId);

    //Added by Areeba
    @Query(value = " select reg.reg_seq from mw_reg reg \n" +
            "                 join hr.hris hri on hri.DETAIL_LOCATION_ID = reg.HR_LOC_CD and hri.EMPLOYEE_ID = :employeeId\n" +
            "                 where reg.CRNT_REC_FLG = 1 ", nativeQuery = true)
    Long checkForIsbKpkEmp(@Param("employeeId") String empId);
    //Ended by Areeba

    //Added by Areeba
    @Query(value = " select reg.reg_seq from mw_reg reg \n" +
            "                 join hr.hris hri on hri.DETAIL_LOCATION_ID = reg.HR_LOC_CD and hri.EMPLOYEE_ID = :employeeId\n" +
            "                 where reg.CRNT_REC_FLG = 1 ", nativeQuery = true)
    Long checkForMulEmp(@Param("employeeId") String empId);
    //Ended by Areeba
}
