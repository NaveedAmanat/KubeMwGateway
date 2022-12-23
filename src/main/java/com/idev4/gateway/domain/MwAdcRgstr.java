package com.idev4.gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "MW_ADC_RGSTR")
public class MwAdcRgstr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "adc_rgstr_seq")
    private Long adcRgstrSeq;

    @Column(name = "adc_nm")
    private String adcNm;

    @Column(name = "adc_tkn")
    private String adcTkn;

    @Column(name = "adc_actv_flg")
    private Boolean adcActvFlg;

    public Long getAdcRgstrSeq() {
        return adcRgstrSeq;
    }

    public void setAdcRgstrSeq(Long adcRgstrSeq) {
        this.adcRgstrSeq = adcRgstrSeq;
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

    public Boolean getAdcActvFlg() {
        return adcActvFlg;
    }

    public void setAdcActvFlg(Boolean adcActvFlg) {
        this.adcActvFlg = adcActvFlg;
    }

}
