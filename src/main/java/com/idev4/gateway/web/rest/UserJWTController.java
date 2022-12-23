package com.idev4.gateway.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.idev4.gateway.domain.*;
import com.idev4.gateway.repository.*;
import com.idev4.gateway.security.AuthoritiesConstants;
import com.idev4.gateway.security.CustomAuthenticationManager;
import com.idev4.gateway.security.jwt.JWTConfigurer;
import com.idev4.gateway.security.jwt.TokenProvider;
import com.idev4.gateway.service.dto.Module;
import com.idev4.gateway.service.dto.*;
import com.idev4.gateway.service.util.MembersUtil;
import com.idev4.gateway.service.util.SequenceFinder;
import com.idev4.gateway.web.rest.vm.ADCUserVM;
import com.idev4.gateway.web.rest.vm.ADCUsrVm;
import com.idev4.gateway.web.rest.vm.LoginVM;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final MwBrnchEmpRelRepository mwBrnchEmpRelRepository;

    private final MwDvcRgstryRepository mwDvcRgstryRepository;

    private final MwEmpRepository mwEmpRepository;

    private final MwPortEmpRelRepository mwPortEmpRelRepository;

    private final MwAdcRgstrRepository mwAdcRgstrRepository;

    private final MwBrnchClsngRepository mwBrnchClsngRepository;

    private final MwPortRepository mwPortRepository;

    private final MwAppVrsnRepository mwAppVrsnRepository;

    private final MwAppModRepository mwAppModRepository;

    private final MwAppSbModRepository mwAppSbModRepository;

    private final MwUsrRolRepository mwUsrRolRepository;

    private final MwAppAuthRepository mwAppAuthRepository;

    private final MwAclRepository mwAclRepository;

    private final MwAreaEmpRelRepository mwAreaEmpRelRepository;

    private final MwRegEmpRelRepository mwRegEmpRelRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;
    @Autowired
    private ADCServiceClient proxyADC;
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager,
                             MwBrnchEmpRelRepository mwBrnchEmpRelRepository, MwDvcRgstryRepository mwDvcRgstryRepository, MwEmpRepository mwEmpRepository,
                             MwPortEmpRelRepository mwPortEmpRelRepository, MwAdcRgstrRepository mwAdcRgstrRepository,
                             MwBrnchClsngRepository mwBrnchClsngRepository, MwPortRepository mwPortRepository, MwAppVrsnRepository mwAppVrsnRepository,
                             MwAppModRepository mwAppModRepository, MwAppSbModRepository mwAppSbModRepository, MwUsrRolRepository mwUsrRolRepository,
                             MwAppAuthRepository mwAppAuthRepository, MwAclRepository mwAclRepository, MwAreaEmpRelRepository mwAreaEmpRelRepository,
                             MwRegEmpRelRepository mwRegEmpRelRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.mwBrnchEmpRelRepository = mwBrnchEmpRelRepository;
        this.mwDvcRgstryRepository = mwDvcRgstryRepository;
        this.mwEmpRepository = mwEmpRepository;
        this.mwPortEmpRelRepository = mwPortEmpRelRepository;
        this.mwAdcRgstrRepository = mwAdcRgstrRepository;
        this.mwBrnchClsngRepository = mwBrnchClsngRepository;
        this.mwPortRepository = mwPortRepository;
        this.mwAppVrsnRepository = mwAppVrsnRepository;
        this.mwAppModRepository = mwAppModRepository;
        this.mwAppSbModRepository = mwAppSbModRepository;
        this.mwUsrRolRepository = mwUsrRolRepository;
        this.mwAppAuthRepository = mwAppAuthRepository;
        this.mwAclRepository = mwAclRepository;
        this.mwAreaEmpRelRepository = mwAreaEmpRelRepository;
        // Added By Naveed - Date - 23-02-2022
        // For RM user Login
        this.mwRegEmpRelRepository = mwRegEmpRelRepository;
        // Ended By Naveed
    }

    static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }

    //ENd

    //Added By Rizwan Mahfooz on 25 February 2022
    @PostMapping("/apkVersionUpDt/{device_no}/{apiLevel}")
    @Timed
    public ResponseEntity<MwDvcRgstr> updateDate(@PathVariable String device_no, @PathVariable Integer apiLevel) {
        MwDvcRgstr rgstr = mwDvcRgstryRepository.findOneByDvcAddrAndCrntRecFlg(device_no, true);
        if (rgstr != null) {
            rgstr.setApk_vrsn_upd_dt(Instant.now());
            rgstr.setAndroid_api_level(apiLevel);
            return new ResponseEntity<>(mwDvcRgstryRepository.save(rgstr), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        loginVM.setUsername(loginVM.getUsername().toLowerCase());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(),
                loginVM.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Authentication auth = null;
        try {
            auth = customAuthenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new JWTToken("Incorrect Credentials."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        boolean rememberMe = loginVM.isRememberMe() != null && loginVM.isRememberMe();
        LdapUser user = (LdapUser) auth.getPrincipal();

        String jwt = tokenProvider.createToken(authentication, rememberMe, user.getUsername());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);

//        List< MwAppVrsn > vrns = mwAppVrsnRepository.findAll();
        String version = "9999";
//        if ( vrns.size() > 0 ) {
//            version = "" + vrns.get( 0 ).getAppVrsnCd();
//        }
        if (loginVM.getMacAddress() == null)
            return new ResponseEntity<>(new JWTToken("MacAddress Not Found."), new HttpHeaders(), HttpStatus.BAD_REQUEST);

        if (loginVM.getMacAddress() == null)
            return new ResponseEntity<>(new JWTToken("MacAddress Not Found."), new HttpHeaders(), HttpStatus.BAD_REQUEST);

        MwDvcRgstr rgstr = mwDvcRgstryRepository.findOneByDvcAddrAndCrntRecFlg(loginVM.getMacAddress(), true);
        if (rgstr == null)
            return new ResponseEntity<>(new JWTToken("Device Not Registered."), new HttpHeaders(), HttpStatus.BAD_REQUEST);

        if (rgstr.getEntyTypFlg() == 1) {
            MwPortEmpRel rel = mwPortEmpRelRepository.findOneByPortSeqAndCrntRecFlg(rgstr.getEntyTypSeq(), true);
            if (rel == null)
                return new ResponseEntity<>(new JWTToken("Registered Porfolio Not Found."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
            MwEmp emp = mwEmpRepository.findOneByEmpSeq(rel.getEmpSeq());
            if (!emp.getEmpLanId().equals(loginVM.getUsername()))
                return new ResponseEntity<>(new JWTToken("Device is Not Registered For this User."), new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);

            List<Screen> screens = new ArrayList<Screen>();
            JWTToken tkn = new JWTToken(jwt, "bdo", screens, rgstr.getEntyTypSeq(), loginVM.getUsername(), emp.getEmpLanId(), version);

            MwPort port = mwPortRepository.findOneByPortSeqAndCrntRecFlg(rgstr.getEntyTypSeq(), true);
            if (port != null) {
                tkn.emp_branch = port.getBrnchSeq();
            }

            List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
            if (exAcls.size() > 0) {
                List<MwAcl> aclsToDel = new ArrayList<>();
                MwAcl acl = null;
                for (MwAcl acld : exAcls) {
                    if (acld.getPortSeq() == port.getPortSeq()) {
                        acl = acld;
                    } else {
                        aclsToDel.add(acld);
                    }
                }
                mwAclRepository.deleteInBatch(aclsToDel);
                if (acl == null) {
                    MwAcl nacl = new MwAcl();
                    Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                    nacl.setAclSeq(aclSeq);
                    nacl.setPortSeq(port.getPortSeq());
                    nacl.setUserId(emp.getEmpLanId());
                    mwAclRepository.save(nacl);
                }

            } else {
                MwAcl acl = new MwAcl();
                Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                acl.setAclSeq(aclSeq);
                acl.setPortSeq(port.getPortSeq());
                acl.setUserId(emp.getEmpLanId());
                mwAclRepository.save(acl);
            }

      /*      String vrsnQuery = "select APP_VRSN_CD from mw_brnch mb\r\n"
            		+ "    join mw_port mp on mp.brnch_seq=mb.brnch_seq and mp.crnt_rec_flg=1\r\n"
            		+ "    where mp.port_seq=:port_seq\r\n"
            		+ "    and mb.crnt_rec_flg=1";*/
            String vrsnQuery = "select APP_VRSN_CD from mw_dvc_rgstr dvcc\r\n"
                    + "    where dvcc.crnt_rec_flg=1 and dvcc.dvc_addr = :dvc_addr\r\n";
//			Query qr = em.createNativeQuery(vrsnQuery).setParameter("port_seq", port.getPortSeq());
            Query qr = em.createNativeQuery(vrsnQuery).setParameter("dvc_addr", rgstr.getDvcAddr());
            List<Object[]> rulResult = qr.getResultList();
            if (rulResult.size() > 0) {
                tkn.version = rulResult.get(0) == null ? "0" : new BigDecimal("" + rulResult.get(0)).toString();
            }
            return new ResponseEntity<>(tkn, httpHeaders, HttpStatus.OK);

        } else if (rgstr.getEntyTypFlg() == 2) {
            MwBrnchEmpRel rel = mwBrnchEmpRelRepository.findOneByBrnchSeqAndCrntRecFlgAndDelFlg(rgstr.getEntyTypSeq(), true, false);
            if (rel == null)
                return new ResponseEntity<>(new JWTToken("Registered Branch Not Found."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
            MwEmp emp = mwEmpRepository.findOneByEmpSeq(rel.getEmpSeq());
            if (!emp.getEmpLanId().equals(loginVM.getUsername()))
                return new ResponseEntity<>(new JWTToken("Device is Not Registered For this User."), new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);

            List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
            List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());
            List<MwPort> ports = mwPortRepository.findAllByBrnchSeqAndCrntRecFlg(rel.getBrnchSeq(), true);
            List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

            Set<Long> ad = new HashSet<Long>(prts);
            Set<Long> bd = new HashSet<Long>(exPrts);
            ad.removeAll(bd);

            List<MwAcl> acls = new ArrayList<>();
            for (Long port : ad) {
                MwAcl acl = new MwAcl();
                Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                acl.setAclSeq(aclSeq);
                acl.setPortSeq(port);
                acl.setUserId(emp.getEmpLanId());
                acls.add(acl);
            }
            List<Screen> screens = new ArrayList<Screen>();

          /*  String vrsnQuery = "select APP_VRSN_CD from mw_brnch mb\r\n"
            		+ "    where mb.brnch_seq=:brnch_seq\r\n"
            		+ "    and mb.crnt_rec_flg=1";*/
            log.info("Device Address: BM->" + rgstr.getDvcAddr());
            String vrsnQuery = "select APP_VRSN_CD from mw_dvc_rgstr dvcc\r\n"
                    + "    where dvcc.crnt_rec_flg=1 and dvcc.dvc_addr = :dvc_addr\r\n";
            log.info("APP_VRSN_CD: BM->" + rgstr.getDvcAddr());
//			Query qr = em.createNativeQuery(vrsnQuery).setParameter("brnch_seq", rgstr.getEntyTypSeq());
            Query qr = em.createNativeQuery(vrsnQuery).setParameter("dvc_addr", rgstr.getDvcAddr());
            List<Object[]> rulResult = qr.getResultList();
            if (rulResult.size() > 0) {
                version = rulResult.get(0) == null ? "0" : new BigDecimal("" + rulResult.get(0)).toString();
            }
            return new ResponseEntity<>(
                    new JWTToken(jwt, "bm", screens, 0, rgstr.getEntyTypSeq(), loginVM.getUsername(), emp.getEmpLanId(), version),
                    httpHeaders, HttpStatus.OK);

        } else if (rgstr.getEntyTypFlg() == 3) {
            MwEmp emp = mwEmpRepository.findOneByEmpSeq(rgstr.getEntyTypSeq());
            if (!emp.getEmpLanId().equals(loginVM.getUsername()))
                return new ResponseEntity<>(new JWTToken("Device is Not Registered For this User."), new HttpHeaders(),
                        HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(new JWTToken(jwt, "compliance", new ArrayList<Screen>(), 0, rgstr.getEntyTypSeq(),
                    loginVM.getUsername(), emp.getEmpLanId(), version), httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<>(new JWTToken("Incorrect Credentials."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authenticate-ad")
    @Timed
    public ResponseEntity<JWTToken> authorizeFromADForWeb(@Valid @RequestBody LoginVM loginVM) {
        loginVM.setUsername(loginVM.getUsername().toLowerCase());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(),
                loginVM.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Authentication auth = null;
        try {
            auth = customAuthenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new JWTToken("Incorrect Credentials."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        boolean rememberMe = loginVM.isRememberMe() != null && loginVM.isRememberMe();
        LdapUser user = (LdapUser) auth.getPrincipal();
        List<MwUsrRol> roles = mwUsrRolRepository.findAllByCrntRecFlg(true);
        MwUsrRol role = null;
        for (MwUsrRol r : roles) {
            if (user.role.trim().toLowerCase().contains(r.getUsrRolCmnt().trim())) {
                role = r;
            }
        }
        if (role == null)
            return new ResponseEntity<>(new JWTToken("Role not found. Contact Admin"), new HttpHeaders(), HttpStatus.BAD_REQUEST);

        String jwt = tokenProvider.createToken(authentication, rememberMe, user.getUsername());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        List<Module> modules = new ArrayList<>();
        String brnchCd = "0";
        String position_id = "0";
        MwEmp emp = mwEmpRepository.findOneByEmpLanId(loginVM.getUsername());
        if (emp == null) {
            emp = new MwEmp();
            emp.setEmpLanId(loginVM.getUsername());
            String employeeId = emp.getEmpLanId().replaceAll("\\D+", "");
            if (employeeId != null && employeeId.length() > 0) {
                /*
		 	        UPDATED BY YOUSAF (GET INFORMATION FROM HARMONY)
                */
                Query q = em.createNativeQuery(
                                " select e.employee_id, trim(e.name) first_name, null middle_name, null last_name, mp.mw6_branch_cd \n" +
                                        "      FROM hr.hris e\n" +
                                        "      join HR_MW6_BRANCH_MAPS mp on e.detail_location_id=mp.hr_branch_cd\n" +
                                        "      where e.employee_id=LPAD(:empId, 5,'0')")
                        .setParameter("empId", employeeId);
                List<Object[]> s = q.getResultList();
                brnchCd = "";
                if (s.size() > 0) {
                    if (s.get(0) != null) {
                        Object[] obj = s.get(0);
                        String firstName = (obj[1] == null) ? "" : obj[1].toString();
                        String middleName = (obj[2] == null) ? "" : obj[2].toString();
                        String lastName = (obj[3] == null) ? "" : obj[3].toString();
                        emp.setEmpNm(firstName + ((middleName == null) ? "" : (" " + middleName))
                                + ((lastName == null) ? "" : (" " + lastName)));
                        emp.setHrid((obj[0] == null) ? "" : obj[0].toString());
                        brnchCd = (obj[4] == null) ? "0" : obj[4].toString();
                    }
                }
            } else {
                emp.setEmpNm(loginVM.getUsername());
            }
            emp.setEmpLanId(loginVM.getUsername());
            Long empSeq = SequenceFinder.findNextVal("emp_seq");
            emp.setEmpSeq(empSeq);
        }

        if (emp != null) {
            String employeeId = emp.getHrid();
            if (employeeId != null && employeeId.length() > 0) {
                /*
		 	        UPDATED BY YOUSAF (GET INFORMATION FROM HARMONY)
                 */
                Query q = em.createNativeQuery(
                                "select e.employee_id, trim(e.name) first_name, null middle_name, null last_name, mp.mw6_branch_cd, e.position_id \n" +
                                        "FROM hr.hris e\n" +
                                        "      join HR_MW6_BRANCH_MAPS mp on e.detail_location_id=mp.hr_branch_cd\n" +
                                        "      where e.employee_id=LPAD(:empId, 5,'0')")
                        .setParameter("empId", employeeId);
                List<Object[]> s = q.getResultList();
                if (s.size() > 0) {
                    if (s.get(0) != null) {
                        Object[] obj = s.get(0);
                        String firstName = (obj[1] == null) ? "" : obj[1].toString();
                        String middleName = (obj[2] == null) ? "" : obj[2].toString();
                        String lastName = (obj[3] == null) ? "" : obj[3].toString();
                        emp.setEmpNm(firstName + ((middleName == null) ? "" : (" " + middleName))
                                + ((lastName == null) ? "" : (" " + lastName)));
                        brnchCd = (obj[4] == null) ? "0" : obj[4].toString();
                        position_id = (obj[5] == null) ? "0" : obj[5].toString();
                    }
                }
            }
            mwEmpRepository.save(emp);
            Long brnchSeq = Long.parseLong(brnchCd);
            JWTToken jwtToken = new JWTToken();
            jwtToken.id_token = jwt;
            jwtToken.role = role.getUsrRolPr().toLowerCase();
            jwtToken.emp_name = emp.getEmpNm();
            jwtToken.loginId = emp.getEmpLanId();
            jwtToken.brnchUpdtd = false;

            /**
             * @Added: Naveed
             * @Description: Attendance (THIS CODE IS FOR GESA ATTENDANCE CHECK FOR EACH BDO AND BM)
             * @Date: 03-11-2022
             * */
            Object msg = "";
            Query q = em.createNativeQuery(
                            "select FN_Check_Attendance_Status(:userid) from dual")
                    .setParameter("userid", SecurityContextHolder.getContext().getAuthentication().getName());

            try {
                msg = q.getSingleResult();
            } catch (Exception e) {
                msg = "Data Not Found";
            }

            if (msg != null) {
                Object gacAppUrl = em.createNativeQuery("SELECT VAL.REF_CD_VAL_DSCR\n" +
                        "  FROM MW_STP_CNFIG_VAL VAL\n" +
                        " WHERE VAL.STP_GRP_CD = '0031'").getSingleResult();
                Map<String, String> response = new HashMap<>();
                response.put("msg", msg.toString());
                response.put("url", gacAppUrl != null ? gacAppUrl.toString() : "");
                return new ResponseEntity<>(new JWTToken(response), new HttpHeaders(),
                        HttpStatus.OK);
            }
            /**
             * @Ended: Naveed
             * */

            if (position_id.equals("258") || position_id.equals("224") || position_id.equals("225")) {
                MwPortEmpRel portEmpRel = mwPortEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (portEmpRel != null) {
                    jwtToken.emp_portfolio = portEmpRel.getPortSeq();
                    MwPort port = mwPortRepository.findOneByPortSeqAndCrntRecFlg(portEmpRel.getPortSeq(), true);
                    if (port != null) {
                        if (port.getBrnchSeq().longValue() == brnchSeq.longValue()) {
                            jwtToken.emp_branch = port.getBrnchSeq();
                            List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                            if (exAcls.size() > 0) {
                                List<MwAcl> aclsToDel = new ArrayList<>();
                                MwAcl acl = null;
                                for (MwAcl acld : exAcls) {
                                    if (acld.getPortSeq() == port.getPortSeq()) {
                                        acl = acld;
                                    } else {
                                        aclsToDel.add(acld);
                                    }
                                }
                                mwAclRepository.deleteInBatch(aclsToDel);
                                if (acl == null) {
                                    MwAcl nacl = new MwAcl();
                                    Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                                    nacl.setAclSeq(aclSeq);
                                    nacl.setPortSeq(port.getPortSeq());
                                    nacl.setUserId(emp.getEmpLanId());
                                    mwAclRepository.save(nacl);
                                }

                            } else {
                                MwAcl acl = new MwAcl();
                                Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                                acl.setAclSeq(aclSeq);
                                acl.setPortSeq(port.getPortSeq());
                                acl.setUserId(emp.getEmpLanId());
                                mwAclRepository.save(acl);
                            }
                        } else {
                            jwtToken.brnchUpdtd = true;
                            portEmpRel.setLastUpdBy("HR_AUTH_UPDT");
                            portEmpRel.setLastUpdDt(Instant.now());
                            portEmpRel.setCrntRecFlg(false);
                            portEmpRel.setEffEndDt(Instant.now());
                            mwPortEmpRelRepository.delete(portEmpRel);
                            List<MwAcl> acls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                            mwAclRepository.delete(acls);
                            jwtToken.emp_branch = brnchSeq;
                        }
                    }
                } else {
                    return new ResponseEntity<>(new JWTToken("Portfolio Assigment Missing. Contact BM/Admin"), new HttpHeaders(),
                            HttpStatus.BAD_REQUEST);
                }
            } else if (position_id.equals("015") || position_id.equals("032") || position_id.equals("056")
                    || position_id.equals("063") || position_id.equals("084") || position_id.equals("064")
                    || position_id.equals("024") || position_id.equals("177") || position_id.equals("178")) {
                // Added by Zohaib Asim - Dated 16-02-2022
                // Employee cannot be assigned if his/her certification is not completed
                /*Query query = em.createNativeQuery( "SELECT CASE WHEN HH.CERTIFICATION_DATE IS NULL THEN 'F' ELSE 'T' END CAN_PROCEED, hh.CERTIFICATION_DATE\n" +
                        " FROM HR.HRIS HH JOIN MW_EMP EMP ON EMP.HRID = HH.EMPLOYEE_ID\n" +
                        "WHERE EMP.EMP_SEQ = " + emp.getEmpSeq() );
                List< Object[] > listObj = query.getResultList();
                if ( listObj.size() > 0 && listObj.get(0)[0].toString().equals("F")) {
                    return new ResponseEntity<>( new JWTToken( "Employee didn't pass HR Certificate" ), new HttpHeaders(), HttpStatus.BAD_REQUEST );
                }*/
                // End
                MwPortEmpRel portEmpRel = mwPortEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (portEmpRel != null) {
                    portEmpRel.setLastUpdBy("HR_AUTH_UPDT");
                    portEmpRel.setLastUpdDt(Instant.now());
                    portEmpRel.setCrntRecFlg(false);
                    portEmpRel.setEffEndDt(Instant.now());
                    mwPortEmpRelRepository.delete(portEmpRel);
                    List<MwAcl> exPortAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                    mwAclRepository.delete(exPortAcls);
                }

                MwBrnchEmpRel rel = mwBrnchEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (rel != null) {
                    if (rel.getBrnchSeq().longValue() == brnchSeq.longValue()) {
                        jwtToken.emp_branch = rel.getBrnchSeq();
                        List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                        List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());
                        List<MwPort> ports = mwPortRepository.findAllByBrnchSeqAndCrntRecFlg(rel.getBrnchSeq(), true);
                        List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

                        Set<Long> ad = new HashSet<Long>(prts);
                        Set<Long> bd = new HashSet<Long>(exPrts);
                        ad.removeAll(bd);

                        List<MwAcl> acls = new ArrayList<>();
                        for (Long port : ad) {
                            MwAcl acl = new MwAcl();
                            Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                            acl.setAclSeq(aclSeq);
                            acl.setPortSeq(port);
                            acl.setUserId(emp.getEmpLanId());
                            acls.add(acl);
                        }
                        mwAclRepository.save(acls);
                        jwtToken.ports = prts;

                        ad = new HashSet<Long>(prts);
                        bd = new HashSet<Long>(exPrts);
                        bd.removeAll(ad);
                        List<Long> aList = new ArrayList<Long>();
                        aList.addAll(bd);
                        List<MwAcl> exAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), aList);
                        mwAclRepository.delete(exAclsToRemove);
                    } else {
                        jwtToken.brnchUpdtd = true;
                        rel.setLastUpdBy("HR_AUTH_UPDT");
                        rel.setLastUpdDt(Instant.now());
                        rel.setCrntRecFlg(false);
                        mwBrnchEmpRelRepository.delete(rel);
                        List<MwAcl> acls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                        mwAclRepository.deleteInBatch(acls);

                        jwtToken.emp_branch = brnchSeq;

                        MwBrnchEmpRel brel = new MwBrnchEmpRel();
                        brel.setBrnchEmpSeq(rel.getBrnchEmpSeq());
                        brel.setBrnchSeq(brnchSeq);
                        brel.setCoveringEmpFromDt(rel.getCoveringEmpFromDt());
                        brel.setCoveringEmpSeq(rel.getCoveringEmpSeq());
                        brel.setCoveringEmpToDt(rel.getCoveringEmpToDt());
                        brel.setCrntRecFlg(true);
                        brel.setCrtdBy("HR_AUTH_UPDT");
                        brel.setCrtdDt(Instant.now());
                        brel.setEffStartDt(Instant.now());
                        brel.setEmpSeq(emp.getEmpSeq());
                        brel.setLastUpdBy("HR_AUTH_UPDT");
                        brel.setLastUpdDt(Instant.now());
                        brel.setDelFlg(false);
                        mwBrnchEmpRelRepository.save(brel);

                        List<MwPort> ports = mwPortRepository.findAllByBrnchSeqAndCrntRecFlg(brnchSeq, true);
                        List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());
                        acls = new ArrayList<>();
                        for (Long port : prts) {
                            MwAcl acl = new MwAcl();
                            Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                            acl.setAclSeq(aclSeq);
                            acl.setPortSeq(port);
                            acl.setUserId(emp.getEmpLanId());
                            acls.add(acl);
                        }
                        mwAclRepository.save(acls);
                        jwtToken.ports = prts;

                    }
                } else {
                    MwBrnchEmpRel exrel = mwBrnchEmpRelRepository.findOneByBrnchSeqAndCrntRecFlg(brnchSeq, true);
                    if (exrel != null) {
                        jwtToken.brnchUpdtd = true;
                        exrel.setLastUpdBy("HR_AUTH_UPDT");
                        exrel.setLastUpdDt(Instant.now());
                        exrel.setCrntRecFlg(false);
                        exrel.setEffEndDt(Instant.now());
                        mwBrnchEmpRelRepository.delete(exrel);
                        MwEmp exemp = mwEmpRepository.findOneByEmpSeq(exrel.getEmpSeq());
                        List<MwAcl> exacls = mwAclRepository.findAllByUserId(exemp.getEmpLanId());
                        mwAclRepository.deleteInBatch(exacls);
                    }
                    jwtToken.emp_branch = brnchSeq;
                    Long seq = SequenceFinder.findNextVal("brnch_emp_rel_seq");
                    MwBrnchEmpRel brel = new MwBrnchEmpRel();
                    brel.setBrnchEmpSeq(seq);
                    brel.setBrnchSeq(brnchSeq);
                    brel.setCrntRecFlg(true);
                    brel.setCrtdBy("HR_AUTH_UPDT");
                    brel.setCrtdDt(Instant.now());
                    brel.setEffStartDt(Instant.now());
                    brel.setEmpSeq(emp.getEmpSeq());
                    brel.setLastUpdBy("HR_AUTH_UPDT");
                    brel.setLastUpdDt(Instant.now());
                    brel.setDelFlg(false);
                    mwBrnchEmpRelRepository.save(brel);

                    List<MwPort> ports = mwPortRepository.findAllByBrnchSeqAndCrntRecFlg(brnchSeq, true);
                    List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());
                    List<MwAcl> acls = new ArrayList<>();
                    for (Long port : prts) {
                        MwAcl acl = new MwAcl();
                        Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                        acl.setAclSeq(aclSeq);
                        acl.setPortSeq(port);
                        acl.setUserId(emp.getEmpLanId());
                        acls.add(acl);
                    }
                    mwAclRepository.save(acls);
                    jwtToken.ports = prts;
                }
            } else if (position_id.equals("057") || position_id.equals("289") || position_id.equals("291")
                    || position_id.equals("014") || position_id.equals("132") || position_id.equals("255") || position_id.equals("072")) {
                // AREA MANAGERS

                /*
                 * Modified By Naveed - Date 10-05-2022
                 * Improvement -  AM ACL Issue */

                MwAreaEmpRel rel = mwAreaEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (rel != null) {
                    if (rel.getAreaSeq().longValue() == brnchSeq.longValue()) {
                        jwtToken.emp_area = rel.getAreaSeq();
                        jwtToken.ports = updateAcls(emp);
                    } else {
                        jwtToken.brnchUpdtd = true;
                        rel.setLastUpdBy("HR_AUTH_UPDT");
                        rel.setLastUpdDt(Instant.now());
                        rel.setCrntRecFlg(false);
                        mwAreaEmpRelRepository.delete(rel);
                        List<MwAcl> acls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                        mwAclRepository.deleteInBatch(acls);

                        jwtToken.emp_area = brnchSeq;

                        MwAreaEmpRel brel = new MwAreaEmpRel();
                        brel.setAreaEmpSeq(rel.getAreaEmpSeq());
                        brel.setAreaSeq(brnchSeq);
                        brel.setCvrngEmpFromDt(rel.getCvrngEmpFromDt());
                        brel.setCvrngEmpSeq(rel.getCvrngEmpSeq());
                        brel.setCvrngEmpToDt(rel.getCvrngEmpToDt());
                        brel.setCrntRecFlg(true);
                        brel.setCrtdBy("HR_AUTH_UPDT");
                        brel.setCrtdDt(Instant.now());
                        brel.setEffStartDt(Instant.now());
                        brel.setEmpSeq(emp.getEmpSeq());
                        brel.setLastUpdBy("HR_AUTH_UPDT");
                        brel.setLastUpdDt(Instant.now());
                        mwAreaEmpRelRepository.save(brel);

                        jwtToken.ports = updateAcls(emp);

                    }
                } else {
                    MwAreaEmpRel exrel = mwAreaEmpRelRepository.findOneByAreaEmpSeqAndCrntRecFlg(brnchSeq, true);
                    if (exrel != null) {
                        jwtToken.brnchUpdtd = true;
                        exrel.setLastUpdBy("HR_AUTH_UPDT");
                        exrel.setLastUpdDt(Instant.now());
                        exrel.setCrntRecFlg(false);
                        exrel.setEffEndDt(Instant.now());
                        mwAreaEmpRelRepository.delete(exrel);
                        MwEmp exemp = mwEmpRepository.findOneByEmpSeq(exrel.getEmpSeq());
                        List<MwAcl> exacls = mwAclRepository.findAllByUserId(exemp.getEmpLanId());
                        mwAclRepository.deleteInBatch(exacls);
                    }
                    jwtToken.emp_area = brnchSeq;
                    Long seq = SequenceFinder.findNextVal("area_emp_rel_seq");
                    MwAreaEmpRel brel = new MwAreaEmpRel();
                    brel.setAreaEmpSeq(seq);
                    brel.setAreaSeq(brnchSeq);
                    brel.setCrntRecFlg(true);
                    brel.setCrtdBy("HR_AUTH_UPDT");
                    brel.setCrtdDt(Instant.now());
                    brel.setEffStartDt(Instant.now());
                    brel.setEmpSeq(emp.getEmpSeq());
                    brel.setLastUpdBy("HR_AUTH_UPDT");
                    brel.setLastUpdDt(Instant.now());
                    mwAreaEmpRelRepository.save(brel);

                    jwtToken.ports = updateAcls(emp);
                }

                // Added By Naveed - Date - 23-02-2022
                // For RM user Login
            } else if (position_id.equals("251") || position_id.equals("335") || position_id.equals("123")
                    || position_id.equals("363") || position_id.equals("290")) {
                // Regional MANAGERS
                log.error("Regional MANAGERS_Position " + position_id);

                MwBrnchEmpRel exrel = mwBrnchEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (exrel != null) {
                    exrel.setLastUpdBy("HR_AUTH_UPDT");
                    exrel.setLastUpdDt(Instant.now());
                    exrel.setCrntRecFlg(false);
                    exrel.setEffEndDt(Instant.now());
                    mwBrnchEmpRelRepository.delete(exrel);
                }

                List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());
                List<MwPort> ports = mwPortRepository.findAllByPortsAndRoleITO(employeeId);
                List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

                Set<Long> ad = new HashSet<Long>(prts);
                Set<Long> bd = new HashSet<Long>(exPrts);
                ad.removeAll(bd);
                List<MwAcl> acls = new ArrayList<>();
                for (Long port : ad) {
                    MwAcl acl = new MwAcl();
                    Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                    acl.setAclSeq(aclSeq);
                    acl.setPortSeq(port);
                    acl.setUserId(emp.getEmpLanId());
                    acls.add(acl);
                }
                mwAclRepository.save(acls);
                jwtToken.ports = prts;

                ad = new HashSet<Long>(prts);
                bd = new HashSet<Long>(exPrts);
                bd.removeAll(ad);
                List<Long> aList = new ArrayList<Long>();
                aList.addAll(bd);
                List<MwAcl> exAclsToRemove = new ArrayList<MwAcl>();
                if (aList.size() > 999) {
                    List<List<Long>> parts = chopped(aList, 999);
                    for (List<Long> part : parts) {
                        List<MwAcl> partExAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), part);
                        exAclsToRemove.addAll(partExAclsToRemove);
                    }
                } else
                    exAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), aList);
                mwAclRepository.delete(exAclsToRemove);

                MwRegEmpRel rel = mwRegEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                log.error("Regional MANAGERS emp " + emp.getEmpSeq());
                log.error("Regional MANAGERS MwRegEmpRel " + rel.getRegSeq());
                if (rel != null)
                    jwtToken.emp_reg = rel.getRegSeq();
            } // Ended BY Naveed
            else if (position_id.equals("243") || position_id.equals("467") || position_id.equals("474")) {
                // For ITO
                // Added By Naveed 11-08-2021
                // Assign covering portfolio
                MwBrnchEmpRel exrel = mwBrnchEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (exrel != null) {
                    exrel.setLastUpdBy("HR_AUTH_UPDT");
                    exrel.setLastUpdDt(Instant.now());
                    exrel.setCrntRecFlg(false);
                    exrel.setEffEndDt(Instant.now());
                    mwBrnchEmpRelRepository.save(exrel);
                }

                List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());

                // Modified by Areeba
                // if isb/kpk assign branches of both regions
                List<MwPort> ports;
                if (mwEmpRepository.checkForIsbKpkEmp(employeeId) == 25 || mwEmpRepository.checkForIsbKpkEmp(employeeId) == 35) {
                    ports = mwPortRepository.findAllPortsForIsbKpkRegion();
                } else {
                    ports = mwPortRepository.findAllByPortsAndRoleITO(employeeId);
                }
                //Ended by Areeba

                List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

                Set<Long> ad = new HashSet<Long>(prts);
                Set<Long> bd = new HashSet<Long>(exPrts);
                ad.removeAll(bd);
                List<MwAcl> acls = new ArrayList<>();
                for (Long port : ad) {
                    MwAcl acl = new MwAcl();
                    Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                    acl.setAclSeq(aclSeq);
                    acl.setPortSeq(port);
                    acl.setUserId(emp.getEmpLanId());
                    acls.add(acl);
                }
                mwAclRepository.save(acls);
                jwtToken.ports = prts;

                ad = new HashSet<Long>(prts);
                bd = new HashSet<Long>(exPrts);
                bd.removeAll(ad);
                List<Long> aList = new ArrayList<Long>();
                aList.addAll(bd);
                List<MwAcl> exAclsToRemove = new ArrayList<MwAcl>();
                if (aList.size() > 999) {
                    List<List<Long>> parts = chopped(aList, 999);
                    for (List<Long> part : parts) {
                        List<MwAcl> partExAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), part);
                        exAclsToRemove.addAll(partExAclsToRemove);
                    }
                } else
                    exAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), aList);
                mwAclRepository.delete(exAclsToRemove);

                MwAreaEmpRel rel = mwAreaEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (rel != null)
                    jwtToken.emp_area = rel.getAreaSeq();
            }
            // Ended By Naveed 04-08-2021
            else if (position_id.equals("170") || position_id.equals("424")) {
                log.info("Regional Accountant " + employeeId + " " + emp.getEmpLanId());
                // For RA
                // Added By Naveed 10-05-2021
                // Assign covering portfolio
                MwBrnchEmpRel exrel = mwBrnchEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (exrel != null) {
                    exrel.setLastUpdBy("HR_AUTH_UPDT");
                    exrel.setLastUpdDt(Instant.now());
                    exrel.setCrntRecFlg(false);
                    exrel.setEffEndDt(Instant.now());
                    mwBrnchEmpRelRepository.save(exrel);
                }

                List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());

                // Modified by Areeba
                // if isb/kpk assign branches of both regions
                List<MwPort> ports;
                if (mwEmpRepository.checkForIsbKpkEmp(employeeId) == 25 || mwEmpRepository.checkForIsbKpkEmp(employeeId) == 35) {
                    ports = mwPortRepository.findAllPortsForIsbKpkRegion();
                }
                // if mul assign branches of mul/swl regions
                else if (mwEmpRepository.checkForMulEmp(employeeId) == 26) {
                    ports = mwPortRepository.findAllPortsForMulSwlRegion();
                } else {
                    ports = mwPortRepository.findAllByPortsAndRoleITO(employeeId);
                }
                //Ended by Areeba

                List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

                Set<Long> ad = new HashSet<Long>(prts);
                Set<Long> bd = new HashSet<Long>(exPrts);
                ad.removeAll(bd);
                List<MwAcl> acls = new ArrayList<>();
                for (Long port : ad) {
                    MwAcl acl = new MwAcl();
                    Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                    acl.setAclSeq(aclSeq);
                    acl.setPortSeq(port);
                    acl.setUserId(emp.getEmpLanId());
                    acls.add(acl);
                }
                mwAclRepository.save(acls);
                jwtToken.ports = prts;

                ad = new HashSet<Long>(prts);
                bd = new HashSet<Long>(exPrts);
                bd.removeAll(ad);
                List<Long> aList = new ArrayList<Long>();
                aList.addAll(bd);
                List<MwAcl> exAclsToRemove = new ArrayList<MwAcl>();
                if (aList.size() > 999) {
                    List<List<Long>> parts = chopped(aList, 999);
                    for (List<Long> part : parts) {
                        List<MwAcl> partExAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), part);
                        exAclsToRemove.addAll(partExAclsToRemove);
                    }
                } else
                    exAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), aList);
                mwAclRepository.delete(exAclsToRemove);

                MwAreaEmpRel rel = mwAreaEmpRelRepository.findOneByEmpSeqAndCrntRecFlg(emp.getEmpSeq(), true);
                if (rel != null)
                    jwtToken.emp_area = rel.getAreaSeq();
            }
            // Ended By Naveed 04-08-2021
            else {
                List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
                List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());
                List<MwPort> ports = mwPortRepository.findAllByCrntRecFlg(true);
                List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

                Set<Long> ad = new HashSet<Long>(prts);
                Set<Long> bd = new HashSet<Long>(exPrts);
                ad.removeAll(bd);

                List<MwAcl> acls = new ArrayList<>();
                for (Long port : ad) {
                    MwAcl acl = new MwAcl();
                    Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
                    acl.setAclSeq(aclSeq);
                    acl.setPortSeq(port);
                    acl.setUserId(emp.getEmpLanId());
                    acls.add(acl);
                }
                mwAclRepository.save(acls);
            }
            List<Boolean> aftrClsng = new ArrayList<>();
            aftrClsng.add(true);
            boolean branchOpen = false;
            if (role.getClsngEfct()) {
                MwBrnchClsng openBrnch = mwBrnchClsngRepository.findOneByBrnchSeqAndBrnchOpnFlg(jwtToken.emp_branch, true);
                MwBrnchClsng clsng = null;
                if (openBrnch == null) {
                    clsng = mwBrnchClsngRepository.findOneByBrnchSeqAndClndrDtAfter(jwtToken.emp_branch,
                            LocalDate.now(ZoneId.of("Asia/Karachi")).atStartOfDay());
                    if (clsng == null) {
                        clsng = new MwBrnchClsng();
                        long seq = SequenceFinder.findNextVal("BRNCH_CLSNG_SEQ");
                        clsng.setBrnchClsngSeq(seq);
                        clsng.setBrnchOpnBy(loginVM.getUsername());
                        clsng.setBrnchOpnDt(LocalDateTime.from(LocalDateTime.now(ZoneId.of("Asia/Karachi"))));
                        clsng.setBrnchOpnFlg(true);
                        clsng.setBrnchSeq(jwtToken.emp_branch);
                        clsng.setClndrDt(LocalDateTime.from(LocalDateTime.now(ZoneId.of("Asia/Karachi"))));
                        mwBrnchClsngRepository.save(clsng);
                        branchOpen = true;
                    } else if (clsng.getBrnchOpnFlg())
                        branchOpen = true;
                } else
                    branchOpen = true;
                if (openBrnch != null) {
                    jwtToken.brnchOpnDt = openBrnch.getBrnchOpnDt();
                } else if (clsng != null) {
                    jwtToken.brnchOpnDt = clsng.getBrnchOpnDt();
                }

            }
            if (branchOpen)
                aftrClsng.add(false);

            List<MwAppMod> mods = mwAppModRepository.findAllAppModForRole(role.getUsrRolSeq(), aftrClsng);
            for (MwAppMod mod : mods) {
                Module module = new Module();
                module.modCmnts = mod.getModComents();
                module.modNm = mod.getModNm();
                module.modSeq = mod.getModSeq();
                module.modUrl = mod.getModUrl();
                List<SubMod> subModules = new ArrayList<>();
                List<MwAppSbMod> subMods = mwAppSbModRepository.findAllAppSbModForModSeq(role.getUsrRolSeq(), mod.getModSeq(),
                        aftrClsng);
                for (MwAppSbMod subMod : subMods) {
                    SubMod sub = new SubMod();
                    sub.modSeq = subMod.getModSeq();
                    sub.sbModSeq = subMod.getSbModSeq();
                    sub.subModNm = subMod.getSbModNm();
                    sub.subModUrl = subMod.getSbModUrl();
                    MwAppAuth appAuth = mwAppAuthRepository.findOneByUsrRolSeqAndSbModSeqAndCrntRecFlg(role.getUsrRolSeq(),
                            subMod.getSbModSeq(), true);
                    if (appAuth != null) {
                        sub.delFlg = appAuth.getDelPrmFlg();
                        sub.writeFlg = appAuth.getWrtPrmFlg();
                        sub.readFlg = appAuth.getReadPrmFlg();
                    }
                    subModules.add(sub);
                }
                module.subMods = subModules;
                modules.add(module);
            }
            jwtToken.modules = modules;
            return new ResponseEntity<>(jwtToken, httpHeaders, HttpStatus.OK);

        }
        return new ResponseEntity<>(new JWTToken("Incorrect Credentials."), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private List<Long> updateAcls(MwEmp emp) {
        List<MwAcl> exAcls = mwAclRepository.findAllByUserId(emp.getEmpLanId());
        List<Long> exPrts = exAcls.stream().map(acl -> acl.getPortSeq()).collect(Collectors.toList());
        List<MwPort> ports = mwPortRepository.findAllByPortsAndRoleAreaLevel(emp.getHrid());
        List<Long> prts = ports.stream().map(port -> port.getPortSeq()).collect(Collectors.toList());

        Set<Long> ad = new HashSet<Long>(prts);
        Set<Long> bd = new HashSet<Long>(exPrts);
        ad.removeAll(bd);
        List<MwAcl> acls = new ArrayList<>();
        for (Long port : ad) {
            MwAcl acl = new MwAcl();
            Long aclSeq = SequenceFinder.findNextVal("ACL_SEQ");
            acl.setAclSeq(aclSeq);
            acl.setPortSeq(port);
            acl.setUserId(emp.getEmpLanId());
            acls.add(acl);
        }
        mwAclRepository.save(acls);

        ad = new HashSet<Long>(prts);
        bd = new HashSet<Long>(exPrts);
        bd.removeAll(ad);
        List<Long> aList = new ArrayList<Long>();
        aList.addAll(bd);
        List<MwAcl> exAclsToRemove = new ArrayList<MwAcl>();
        if (aList.size() > 999) {
            List<List<Long>> parts = chopped(aList, 999);
            for (List<Long> part : parts) {
                List<MwAcl> partExAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), part);
                exAclsToRemove.addAll(partExAclsToRemove);
            }
        } else
            exAclsToRemove = mwAclRepository.findAllByUserIdAndPortSeqIn(emp.getEmpLanId(), aList);
        mwAclRepository.delete(exAclsToRemove);

        return prts;
    }

    @PostMapping("/authenticate-adc")
    @Timed
    public ResponseEntity<String> authorizeForADC(@Valid @RequestBody ADCUserVM user) {

        MwAdcRgstr rgstr = mwAdcRgstrRepository.findOneByAdcNm(user.getAdcNm());

        if (rgstr == null)
            return new ResponseEntity<>("ADC Not Registered", new HttpHeaders(), HttpStatus.BAD_REQUEST);

        if (rgstr.getAdcTkn() != null && rgstr.getAdcTkn().length() > 0)
            return new ResponseEntity<>(rgstr.getAdcTkn(), new HttpHeaders(), HttpStatus.OK);

        Authentication authentication = null;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, AuthoritiesConstants.ADC, rgstr.getAdcNm());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        rgstr.setAdcTkn(jwt);
        mwAdcRgstrRepository.save(rgstr);
        return new ResponseEntity<>(jwt, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/bill-inquiry")
    @Timed
    public ResponseEntity<BillInquiry> getClientRecoveryForADC(@Valid @RequestBody ADCUsrVm adcUser) {
        List<Members> members = MembersUtil.getAllMembers();
        for (int i = 0; i < members.size(); i++) {
            Members mem = members.get(i);
            if (mem.getUsername().trim().equals(adcUser.getUsername()) && mem.getPassword().trim().equals(adcUser.getPassword())) {
                if (!mem.getRole().equals("adc")) {
                    BillInquiry inq = new BillInquiry();
                    inq.error = "User Not Allowed";
                    return new ResponseEntity<>(inq, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
                }

                if (adcUser.getConsumer_number() == null || adcUser.getConsumer_number().trim().length() == 0) {
                    BillInquiry inq = new BillInquiry();
                    inq.error = "User Not Allowed";
                    return new ResponseEntity<>(inq, new HttpHeaders(), HttpStatus.BAD_REQUEST);
                }
                // HttpHeaders
                Authentication authentication = null;
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.createToken(authentication, mem.getUsername());
                return this.proxyADC.getBillInquiryForClient(adcUser.getConsumer_number(), ("Bearer " + jwt));
            }
        }
        BillInquiry inq = new BillInquiry();
        inq.error = "Invalid Credentials";
        return new ResponseEntity<>(inq, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/bill-payment")
    @Timed
    public ResponseEntity<BillPayment> applyPaymentForADC(@Valid @RequestBody ADCUsrVm adcUser) {
        List<Members> members = MembersUtil.getAllMembers();
        for (int i = 0; i < members.size(); i++) {
            Members mem = members.get(i);
            if (mem.getUsername().trim().equals(adcUser.getUsername().trim())
                    && mem.getPassword().trim().equals(adcUser.getPassword().trim())) {
                if (!mem.getRole().equals("adc")) {
                    BillPayment pay = new BillPayment();
                    pay.error = "User Not Allowed";
                    return new ResponseEntity<>(pay, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
                }

                if (adcUser.getConsumer_number() == null || adcUser.getConsumer_number().trim().length() == 0) {
                    BillPayment pay = new BillPayment();
                    pay.error = "Invalid Consumer_Number";
                    return new ResponseEntity<>(pay, new HttpHeaders(), HttpStatus.BAD_REQUEST);
                }
                // HttpHeaders
                Authentication authentication = null;
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String jwt = tokenProvider.createToken(authentication, mem.getUsername());
                return this.proxyADC.applyPaymentForADCRDS(adcUser, ("Bearer " + jwt));
            }
        }
        BillPayment pay = new BillPayment();
        pay.error = "Invalid Credentials";
        return new ResponseEntity<>(pay, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Object to return as body in JWT Authentication.
     */

    @GetMapping("/mw-mods-for-user-rol/{id}/{closeFlg}")
    @Timed
    public ResponseEntity<List<Module>> getModsForUsrRol(@PathVariable Long id, @PathVariable Boolean closeFlg) {
        List<Module> modules = new ArrayList<>();
        List<Boolean> aftrClsng = new ArrayList<>();
        aftrClsng.add(true);
        List<MwAppMod> mods = mwAppModRepository.findAllAppModForRole(id, aftrClsng);
        for (MwAppMod mod : mods) {
            Module module = new Module();
            module.modCmnts = mod.getModComents();
            module.modNm = mod.getModNm();
            module.modSeq = mod.getModSeq();
            module.modUrl = mod.getModUrl();
            List<SubMod> subModules = new ArrayList<>();
            List<MwAppSbMod> subMods = mwAppSbModRepository.findAllAppSbModForModSeq(id, mod.getModSeq(), aftrClsng);
            for (MwAppSbMod subMod : subMods) {
                SubMod sub = new SubMod();
                sub.modSeq = subMod.getModSeq();
                sub.sbModSeq = subMod.getSbModSeq();
                sub.subModNm = subMod.getSbModNm();
                sub.subModUrl = subMod.getSbModUrl();
                MwAppAuth appAuth = mwAppAuthRepository.findOneByUsrRolSeqAndSbModSeqAndCrntRecFlg(id, subMod.getSbModSeq(), true);
                if (appAuth != null) {
                    sub.delFlg = appAuth.getDelPrmFlg();
                    sub.writeFlg = appAuth.getWrtPrmFlg();
                    sub.readFlg = appAuth.getReadPrmFlg();
                }
                subModules.add(sub);
            }
            module.subMods = subModules;
            modules.add(module);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(modules));
    }
}
