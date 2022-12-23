package com.idev4.gateway.web.rest.vm;

import com.idev4.gateway.config.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ADCUsrVm {

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = ManagedUserVM.PASSWORD_MIN_LENGTH, max = ManagedUserVM.PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    private String consumer_number;

    private String tran_auth_id;

    private Long transaction_amount;

    private String tran_date;

    private Long agent_id;

    private String auth_token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConsumer_number() {
        return consumer_number;
    }

    public void setConsumer_number(String consumer_number) {
        this.consumer_number = consumer_number;
    }

    public String getTran_auth_id() {
        return tran_auth_id;
    }

    public void setTran_auth_id(String tran_auth_id) {
        this.tran_auth_id = tran_auth_id;
    }

    public Long getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(Long transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public Long getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(Long agent_id) {
        this.agent_id = agent_id;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

}
