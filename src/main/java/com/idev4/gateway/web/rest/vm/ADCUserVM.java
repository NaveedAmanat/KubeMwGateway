package com.idev4.gateway.web.rest.vm;

public class ADCUserVM {

    private String apiSecret;

    private String adcNm;

    private String adcTkn;

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getAdcNm() {
        return adcNm;
    }

    public void setAdcNm(String adcNm) {
        this.adcNm = adcNm;
    }

    public String getAdcTkn() {
        return adcTkn;
    }

    public void setAdcTkn(String adcTkn) {
        this.adcTkn = adcTkn;
    }

}
