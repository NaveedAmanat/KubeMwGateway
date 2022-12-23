package com.idev4.gateway.web.rest;

import com.idev4.gateway.service.dto.BillInquiry;
import com.idev4.gateway.service.dto.BillPayment;
import com.idev4.gateway.web.rest.vm.ADCUsrVm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.core.HttpHeaders;

@FeignClient(name = "recoverydisbursementservice")
public interface ADCServiceClient {

    @RequestMapping(value = "/api/v1/bill-inquiry/{id}")
    ResponseEntity<BillInquiry> getBillInquiryForClient(@PathVariable(value = "id") String id,
                                                        @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @RequestMapping(value = "/api/adc/v1/bill-payment")
    ResponseEntity<BillPayment> applyPaymentForADCRDS(@RequestBody ADCUsrVm body,
                                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

}
