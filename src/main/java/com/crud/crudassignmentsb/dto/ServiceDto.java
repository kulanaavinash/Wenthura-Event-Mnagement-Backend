package com.crud.crudassignmentsb.dto;

import com.crud.crudassignmentsb.modal.Packages;

import java.math.BigDecimal;

public class ServiceDto {

    private int serId;
    private int pacId;
    private String serName;
    private String pacName;
    private String serDesc;
    private String serStatus;
    private String serOffer;
    private String serImg;


    public int getSerId() {
        return serId;
    }

    public void setSerId(int serId) {
        this.serId = serId;
    }

    public int getPacId() {
        return pacId;
    }

    public void setPacId(int pacId) {
        this.pacId = pacId;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getPacName() {
        return pacName;
    }

    public void setPacName(String pacName) {
        this.pacName = pacName;
    }

    public String getSerDesc() {
        return serDesc;
    }

    public void setSerDesc(String serDesc) {
        this.serDesc = serDesc;
    }

    public String getSerStatus() {
        return serStatus;
    }

    public void setSerStatus(String serStatus) {
        this.serStatus = serStatus;
    }

    public String getSerOffer() {
        return serOffer;
    }

    public void setSerOffer(String serOffer) {
        this.serOffer = serOffer;
    }

    public String getSerImg() {
        return serImg;
    }

    public void setSerImg(String serImg) {
        this.serImg = serImg;
    }
}
