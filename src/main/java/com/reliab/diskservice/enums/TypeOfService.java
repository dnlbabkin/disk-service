package com.reliab.diskservice.enums;

public enum TypeOfService {
    YANDEX("yandex"),
    GOOGLE("google");

    private String service;

    TypeOfService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }
}
