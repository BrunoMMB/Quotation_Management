package com.idp6.quotation_management.model;

public class Quotation_Management_Identification {
    private String host = "192.168.0.102";
    private Integer port = 8081;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
