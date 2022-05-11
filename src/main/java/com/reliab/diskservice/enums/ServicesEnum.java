package com.reliab.diskservice.enums;

public enum ServicesEnum {
    YANDEX("yandex"),
    GOOGLE("google"),
    UNKNOWN;

    private String service;

    ServicesEnum(String service) {
        this.service = service;
    }

    ServicesEnum() {

    }

    public String getService() {
        return service;
    }
}
