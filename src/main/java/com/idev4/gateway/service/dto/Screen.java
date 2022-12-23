package com.idev4.gateway.service.dto;

public class Screen {

    public String name;

    public String url;

    public boolean readFlag;

    public boolean writeFlag;

    public boolean deleteFlag;

    public Screen() {
        super();
    }

    public Screen(String name, String url, boolean readFlag, boolean writeFlag, boolean deleteFlag) {
        super();
        this.name = name;
        this.url = url;
        this.readFlag = readFlag;
        this.writeFlag = writeFlag;
        this.deleteFlag = deleteFlag;
    }
}
