package com.idev4.gateway.service.dto;

public class AppDueDTO {

    public String loanId;

    public String installment;

    public Long totalDue;

    public Long paidAmount;

    public Long remaingDue;

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
