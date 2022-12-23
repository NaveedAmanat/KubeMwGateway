package com.idev4.gateway.service.dto;

import org.springframework.http.HttpStatus;

public class Error {

    public String error;

    public HttpStatus statusCode;

    public Error(String error, HttpStatus code) {
        this.error = error;
        this.statusCode = code;
    }

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
