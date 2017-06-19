/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.soapboxrace.jlauncher.vo;

/**
 *
 * @author Vanquish
 */
public class ServerInfoVO {

    private String ownerList;

    private String onlineNumber;

    private String numberOfRegistered;

    private String serverName;

    private String messageSrv;

    private String country;

    private String adminList;
    
    public String getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(String ownerList) {
        this.ownerList = ownerList;
    }

    public String getOnlineNumber() {
        return onlineNumber;
    }

    public void setOnlineNumber(String onlineNumber) {
        this.onlineNumber = onlineNumber;
    }

    public String getNumberOfRegistered() {
        return numberOfRegistered;
    }

    public void setNumberOfRegistered(String numberOfRegistered) {
        this.numberOfRegistered = numberOfRegistered;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMessageSrv() {
        return messageSrv;
    }

    public void setMessageSrv(String messageSrv) {
        this.messageSrv = messageSrv;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdminList() {
        return adminList;
    }

    public void setAdminList(String adminList) {
        this.adminList = adminList;
    }

    @Override
    public String toString() {
        return "ClassPojo [ownerList = " + ownerList + ", onlineNumber = " + onlineNumber + ", numberOfRegistered = " + numberOfRegistered + ", serverName = " + serverName + ", messageSrv = " + messageSrv + ", country = " + country + ", adminList = " + adminList + "]";
    }
}
